package com.bvcode.ncopter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.MAVLink.MAVLink;
import com.MAVLink.Messages.IMAVLinkMessage;
import com.MAVLink.Messages.common.msg_attitude;
import com.MAVLink.Messages.common.msg_gps_raw;
import com.MAVLink.Messages.common.msg_gps_status;
import com.MAVLink.Messages.common.msg_nav_controller_output;
import com.MAVLink.Messages.common.msg_request_data_stream;
import com.MAVLink.Messages.common.msg_sys_status;
import com.MAVLink.Messages.common.msg_waypoint_current;
import com.bvcode.ncopter.AC1Data.FlightData;
import com.bvcode.ncopter.AC1Data.ProtocolParser;
import com.bvcode.ncopter.comms.CommunicationClient;
import com.bvcode.ncopter.widgets.HUD;

public class HUDActivity extends Activity{

	HUD hud;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if( CommonSettings.setOrientation(this, -1))
			return;
		
		setContentView(R.layout.hud);
	
		
		hud = (HUD) findViewById(R.id.hudWidget);
		
        ba.init();
    
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
				sendBytesToComm(ProtocolParser.requestFlightData());
			else if (CommonSettings.isProtocolMAVLink()){
				
				msg_request_data_stream req = new msg_request_data_stream();
				req.req_message_rate = 20;
				req.req_stream_id = MAVLink.MAV_DATA_STREAM.MAV_DATA_STREAM_EXTRA1; // MAV_DATA_STREAM_RAW_CONTROLLER;
																					// MAV_DATA_STREAM_RAW_SENSORS;
				req.start_stop = 1;
				req.target_system = MAVLink.CURRENT_SYSID;
				req.target_component = 0;
				ba.sendBytesToComm( MAVLink.createMessage(req));
				

				req = new msg_request_data_stream();
				req.req_message_rate = 1;
				req.req_stream_id = MAVLink.MAV_DATA_STREAM.MAV_DATA_STREAM_EXTENDED_STATUS; // MAV_DATA_STREAM_RAW_CONTROLLER;
																					// MAV_DATA_STREAM_RAW_SENSORS;
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
				if( FlightData.class.isInstance(m)){
					FlightData fd = (FlightData)m;
//					double g[] = {fd.gyroX, fd.gyroY, fd.gyroYaw};
					double a[] = {fd.accelX, fd.accelY, fd.accelZ};
//					double mag[] = {fd.magX, fd.magY, fd.magZ};

					float roll = (float)-Math.atan2(a[0], a[2]), 
						 pitch = (float)-Math.atan2(a[1], a[2]), 
						 yaw=0;
					
					hud.newFlightData(roll, pitch, yaw);
					hud.setBatteryRemaining(0);
					hud.setbatteryMVolt(0);
					hud.setNavMode("");
					
				}
				
			}else if (CommonSettings.isProtocolMAVLink()){
				switch(m.messageType){
					case msg_attitude.MAVLINK_MSG_ID_ATTITUDE:{
						msg_attitude msg = (msg_attitude) m;
						hud.newFlightData(msg.roll, msg.pitch, msg.yaw);
						break;
					}
					case msg_sys_status.MAVLINK_MSG_ID_SYS_STATUS:{
						msg_sys_status msg = (msg_sys_status) m;
						hud.setBatteryRemaining(msg.battery_remaining/10.0);
						hud.setbatteryMVolt(msg.voltage_battery);
		//TODO				hud.setNavMode(MAVLink.getNav(msg.nav_mode));
//						updateStatusLine(0, "NAV Mode: " + MAVLink.getNav(msg.nav_mode));
//						updateStatusLine(1, "Status: " + MAVLink.getState(msg.status));
//						updateStatusLine(2, "Mode: " + MAVLink.getMode(msg.mode));
						break;
					}	
					case msg_waypoint_current.MAVLINK_MSG_ID_WAYPOINT_CURRENT:{
						//msg_waypoint_current msg = (msg_waypoint_current) m;
						
						break;
					}
					case msg_gps_raw.MAVLINK_MSG_ID_GPS_RAW:{
						msg_gps_raw msg = (msg_gps_raw) m;
						hud.setAltitude(msg.alt);

						String s;
						
						switch(msg.fix_type){
							case 0:
							case 1:
	                            s="No Fix";
	                            break;
	                            
	                        case 2:
	                        	s="2D Fix";
	                            break;

	                        case 3:
	                        	s="3D Fix";
	                            break;
	                        
	                        default:
	                            s="No GPS";
	                            
						}

						hud.setGPSFix(s);
						
						break;
					}
					case msg_gps_status.MAVLINK_MSG_ID_GPS_STATUS:{
						break;
					}
					case msg_nav_controller_output.MAVLINK_MSG_ID_NAV_CONTROLLER_OUTPUT:{
						
						break;
					}					
				}				
			}
		}
	};
}
