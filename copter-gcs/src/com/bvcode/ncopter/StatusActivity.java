package com.bvcode.ncopter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.TextView;

import com.MAVLink.MAVLink;
import com.MAVLink.Messages.IMAVLinkMessage;
import com.MAVLink.Messages.common.msg_gps_raw;
import com.MAVLink.Messages.common.msg_gps_status;
import com.MAVLink.Messages.common.msg_nav_controller_output;
import com.MAVLink.Messages.common.msg_request_data_stream;
import com.MAVLink.Messages.common.msg_sys_status;
import com.MAVLink.Messages.common.msg_waypoint_current;
import com.bvcode.ncopter.AC1Data.ProtocolParser;
import com.bvcode.ncopter.AC1Data.StringMessage;
import com.bvcode.ncopter.comms.CommunicationClient;

public class StatusActivity extends Activity implements OnLongClickListener{

	TextView[] lines;
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.status_main);
		if( CommonSettings.setOrientation(this, -1))
			return;
		
		lines = new TextView[10];
		lines[0] = (TextView) findViewById(R.id.line1);
		lines[1] = (TextView) findViewById(R.id.line2);
		lines[2] = (TextView) findViewById(R.id.line3);
		lines[3] = (TextView) findViewById(R.id.line4);
		lines[4] = (TextView) findViewById(R.id.line5);
		lines[5] = (TextView) findViewById(R.id.line6);
		lines[6] = (TextView) findViewById(R.id.line7);
		lines[7] = (TextView) findViewById(R.id.line8);
		lines[8] = (TextView) findViewById(R.id.line9);
		lines[9] = (TextView) findViewById(R.id.line10);
		
		for(int i = 0; i < 10; i++)
			lines[i].setOnLongClickListener(this);
		
		ba.init();
	}
			
	
	private void updateStatusLine(int i, String s){
		//Show the GPS info.
		if( s.startsWith("gps:") == true && i+6 < lines.length){
			String arr2[] = s.split(" ");
			if(arr2.length == 9){
				lines[i+1].setText(arr2[1]); //long
				lines[i+2].setText(arr2[2]); //lat
				lines[i+3].setText(arr2[3]); //alt
				lines[i+4].setText("Ground Speed: " + arr2[4]); //Ground Speed
				lines[i+5].setText(arr2[6] + " " + arr2[7]);
				lines[i+6].setText(arr2[8]);
			}
			
		}else{
			if( i < lines.length){
				lines[i].setText(s.trim());
			}			
		}
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		ba.onDestroy();
		
	}
	
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		ba.onActivityResult(requestCode, resultCode, data);
		
	}


	CommunicationClient ba = new CommunicationClient(this) {
		@Override
		public void notifyConnected() {
			if( CommonSettings.isProtocolAC1())
				sendBytesToComm(ProtocolParser.requestStatus());
			
			else if(CommonSettings.isProtocolMAVLink()){
				msg_request_data_stream req = new msg_request_data_stream();
				req.req_message_rate = 1;
				req.req_stream_id = MAVLink.MAV_DATA_STREAM.MAV_DATA_STREAM_EXTENDED_STATUS;
				req.start_stop = 1;
				req.target_system = MAVLink.CURRENT_SYSID;
				req.target_component = 0;
				ba.sendBytesToComm( MAVLink.createMessage(req));
				
			}
		}

		@Override
		public void notifyDisconnected() {
			
		}

		@Override
		public void notifyDeviceNotAvailable() {
			
		}

		@Override
		public void notifyReceivedData(int count, IMAVLinkMessage m) {
			
			if( CommonSettings.isProtocolAC1()){
				if( StringMessage.class.isInstance(m)){
					StringMessage sm = (StringMessage)m;
					updateStatusLine(count, sm.message);
				
				}
				
			}else if(CommonSettings.isProtocolMAVLink()){
				Log.v("Sd", m.getClass().getName());
				
				switch(m.messageType){
				// Have lines 0 - 9
					case msg_sys_status.MAVLINK_MSG_ID_SYS_STATUS:{
						msg_sys_status msg = (msg_sys_status) m;
						
						updateStatusLine(0, "NAV Mode: " + MAVLink.getNav(msg.nav_mode));
						updateStatusLine(1, "Status: " + MAVLink.getState(msg.status));
						if( msg.mode < 100)
							updateStatusLine(2, "Mode: " + MAVLink.getMode(msg.mode));
						else
							updateStatusLine(2, "Mode: " + getAC2Mode(msg.mode));

						updateStatusLine(9, "Packet Drops: " + (float)(msg.packet_drop)/1000.0);
						break;
						
					}	
					case msg_gps_status.MAVLINK_MSG_ID_GPS_STATUS:{
						//msg_gps_status msg = (msg_gps_status) m;
						
						break;
					}
					case msg_waypoint_current.MAVLINK_MSG_ID_WAYPOINT_CURRENT:{
						//msg_waypoint_current msg = (msg_waypoint_current) m;
						
						break;
					}
					case msg_gps_raw.MAVLINK_MSG_ID_GPS_RAW:{
						msg_gps_raw msg = (msg_gps_raw) m;

						updateStatusLine(3, "");
						switch(msg.fix_type){
							case 0:
							case 1:
								updateStatusLine(4, "Fix Type: no fix");					
								break;
							case 2:
								updateStatusLine(4, "Fix Type: 2D fix");					
								break;
							case 3:
								updateStatusLine(4, "Fix Type: 3D fix");					
								break;
						}

						updateStatusLine(5, "Latitude: " + msg.lat);
						updateStatusLine(6, "Longitude: " + msg.lon);
						updateStatusLine(7, "Altitude: " + msg.alt);
						updateStatusLine(8, "Heading: " + msg.hdg);
						
						break;
					}
					case msg_nav_controller_output.MAVLINK_MSG_ID_NAV_CONTROLLER_OUTPUT:{
						//msg_nav_controller_output msg = (msg_nav_controller_output) m;
						
						break;
					}
					
				}				
			}			
		}

		
		
		private String getAC2Mode(int mode) {
			String modes[] = new String[]{"STABILIZE", "ACRO", "SIMPLE", "ALT_HOLD", "AUTO", "GUIDED", "LOITER", "RTL", "CIRCLE"};
			if( mode-100 < modes.length)
				return modes[mode-100];
	
			return "";

		}
	};

	@Override
	public boolean onLongClick(View v) {
		if(CommonSettings.isProtocolAC1())	
			ba.sendBytesToComm(ProtocolParser.requestStatus());
		
		else if(CommonSettings.isProtocolMAVLink()){
			
			msg_request_data_stream req = new msg_request_data_stream();
			req.req_message_rate = 1;
			req.req_stream_id = MAVLink.MAV_DATA_STREAM.MAV_DATA_STREAM_EXTENDED_STATUS;
			req.start_stop = 1;
			req.target_system = MAVLink.CURRENT_SYSID;
			req.target_component = 0;
			ba.sendBytesToComm( MAVLink.createMessage(req));
			
		}
		return true;
	}
}
