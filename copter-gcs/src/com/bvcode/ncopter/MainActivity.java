package com.bvcode.ncopter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.MAVLink.MAVLink;
import com.MAVLink.Messages.IMAVLinkMessage;
import com.MAVLink.Messages.common.msg_heartbeat;
import com.bvcode.ncopter.comms.BluetoothModule;
import com.bvcode.ncopter.comms.CommunicationClient;
import com.bvcode.ncopter.gps.GPSActivity;
import com.bvcode.ncopter.mission.MissionActivity;
import com.bvcode.ncopter.setup.SetupActivity;


public class MainActivity extends Activity implements OnClickListener {
	
	private Button setupButton, GPSButton, readouts, status, params, hud, mission, mode;

	private TextView connectLabel;
	public MainActivity(){
		
	}
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	
		// This is the only activity that needs to manualy grab the value.
		SharedPreferences settings = getSharedPreferences(CommunicationClient.PREFS_NAME, 0);
		if(settings.contains(CommunicationClient.DEFAULT_ORIENTATION)){
			String address = settings.getString(CommunicationClient.DEFAULT_ORIENTATION, "");
			
			int orient = CommonSettings.ORIENTATION_DEFAULT;
			
			if(	address.equals(CommonSettings.LANDSCAPE))
				orient = CommonSettings.ORIENTATION_LANDSCAPE;
			else if(	address.equals(CommonSettings.PORTRAIT))
				orient = CommonSettings.ORIENTATION_PORTRAIT;

			if( orient != CommonSettings.ORIENTATION_DEFAULT){
				if( CommonSettings.setOrientation(this, orient))
					return;
			}
				
		}

		setContentView(R.layout.main_activity); 
    
		setupButton = (Button) findViewById(R.id.buttonSetup);
		setupButton.setOnClickListener(this);

		GPSButton = (Button) findViewById(R.id.gpsButton);
		GPSButton.setOnClickListener(this);

		readouts = (Button) findViewById(R.id.readoutsButton);
		readouts.setOnClickListener(this);

		status = (Button) findViewById(R.id.statusButton);
		status.setOnClickListener(this);

		params = (Button) findViewById(R.id.paramView);
		params.setOnClickListener(this);

		hud = (Button) findViewById(R.id.hudButton);
		hud.setOnClickListener(this);

		mission = (Button) findViewById(R.id.missionButton);
		mission.setOnClickListener(this);

		mode = (Button) findViewById(R.id.modeButton);
		mode.setOnClickListener(this);
		
		connectLabel = (TextView) findViewById(R.id.connectLabel);
		
		ba.init();

	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	    	case R.id.selectLink:
	    		selectLink();
	    		return true;
	    		
	    	/*case R.id.menuRC:
	    		startActivity(new Intent(this, RemoteControl.class));
				return true;*/
	    		
	    	case R.id.lockOrientation:
	    		selectOrientation();
	    		return true;
	    		
		    case R.id.menuConnect:
		        ba.module.setFirstTimeEnableDevice(true);
		        ba.module.setFirstTimeListDevices(true);
		        
		        connectLabel.setTextColor(0xFFFF0000);
				connectLabel.setText("Trying to Connect");
				
		        ba.sendMessage(BluetoothModule.MSG_GET_STATUS);
				
		    	return true;
		    	
		    case R.id.setProtocol:
		    	selectProtocol();
		    	return true;
		    	
		    case R.id.clearDefaults:

		    	// Remove the default modem selection.
		    	SharedPreferences settings = getSharedPreferences(CommunicationClient.PREFS_NAME, 0);
		        SharedPreferences.Editor editor = settings.edit();
		        editor.remove(CommunicationClient.DEFAULT_MODEM);
		        editor.remove(CommunicationClient.LINK_TYPE);
		        editor.remove(CommunicationClient.ACTIVE_PROTOCOL);
		        editor.remove(CommunicationClient.DEFAULT_ORIENTATION);
		        editor.commit();

		    	return true;
		    	
		    default:
		        return super.onOptionsItemSelected(item);
	    }
	}

	private void selectLink() {
		final CharSequence[] items = {"Bluetooth", "USB (Android 3.0+)"};

		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Pick a Link type");
		builder.setItems(items, new DialogInterface.OnClickListener() {
		    public void onClick(DialogInterface dialog, int item) {
		    	
				// Save the modem for next time
				SharedPreferences settings = getSharedPreferences(CommunicationClient.PREFS_NAME, 0);
				SharedPreferences.Editor editor = settings.edit();
				
				if(item == 1 /*&& IProxy.isUSBSupported()*/){
					//editor.putString(CommunicationClient.LINK_TYPE, ProtocolParser.LINK_USB_);
					//ProtocolParser.currentLink = ProtocolParser.LINK_USB;
				    connectLabel.setText("USB Not Supported");
				}else{
					editor.putString(CommunicationClient.LINK_TYPE, CommonSettings.LINK_BLUE);
					CommonSettings.currentLink = CommonSettings.LINK_BLUETOOTH;
				    connectLabel.setText("Bluetooth Selected");
				    
				}
				
			    editor.commit();

			    
			}
		});
		
		AlertDialog alert = builder.create();
		
		alert.show();
	}
	
	private void selectOrientation() {
		final CharSequence[] items = {"Portrait", "Landscape", "App Recommended"};

		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Default Screen Orientation");
		
		builder.setItems(items, new DialogInterface.OnClickListener() {
		    public void onClick(DialogInterface dialog, int item) {
		    
				SharedPreferences settings = getSharedPreferences(CommunicationClient.PREFS_NAME, 0);
				SharedPreferences.Editor editor = settings.edit();
				
				switch( item){
					case 0:
						editor.putString(CommunicationClient.DEFAULT_ORIENTATION, CommonSettings.PORTRAIT);
						CommonSettings.desiredOrientation = CommonSettings.ORIENTATION_PORTRAIT;
						break;
					case 1:
						editor.putString(CommunicationClient.DEFAULT_ORIENTATION, CommonSettings.LANDSCAPE);
						CommonSettings.desiredOrientation = CommonSettings.ORIENTATION_LANDSCAPE;
						break;
					case 2:
						editor.putString(CommunicationClient.DEFAULT_ORIENTATION, CommonSettings.DEFAULT);
						CommonSettings.desiredOrientation = CommonSettings.ORIENTATION_DEFAULT;
						break;
				
				}
				
			    editor.commit();

			}
		});
		
		AlertDialog alert = builder.create();
		
		alert.show();
	}
	
	private void selectProtocol() {
		final CharSequence[] items = {"ArduCopter1", "MAVLink"};

		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Pick a Protocol");
		builder.setItems(items, new DialogInterface.OnClickListener() {
		    public void onClick(DialogInterface dialog, int item) {
		    	
				// Save the modem for next time
				SharedPreferences settings = getSharedPreferences(CommunicationClient.PREFS_NAME, 0);
				SharedPreferences.Editor editor = settings.edit();
				if(item == 0){
					editor.putString(CommunicationClient.ACTIVE_PROTOCOL, CommonSettings.AC1);
					CommonSettings.currentProtocol = CommonSettings.AC1_PROTOCOL;
					
				}else{
					editor.putString(CommunicationClient.ACTIVE_PROTOCOL, CommonSettings.MAVLink);
					CommonSettings.currentProtocol = CommonSettings.MAVLINK_PROTOCOL;
					
				}
				
			    editor.commit();
			    connectLabel.setText("Protocol Selected");
			}
		});
		
		AlertDialog alert = builder.create();
		
		alert.show();
	}
	
	public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.main_window, menu);
	    return true;
	    
	}

	@Override
	public void onClick(View v) {
		if (v == setupButton) {
			startActivity(new Intent(this, SetupActivity.class));
					
		} else if (v == GPSButton) {
			startActivity(new Intent(this, GPSActivity.class));
			
		} else if (v == readouts) {
			startActivity(new Intent(this, ReadOutsActivity.class));

		} else if (v == status) {
			startActivity(new Intent(this, StatusActivity.class));
			
		} else if(v == params){
			startActivity(new Intent(this, ParameterViewActivity.class));
						
		} else if(v == hud){
			startActivity(new Intent(this, HUDActivity.class));
			
		} else if(v == mission){
			startActivity(new Intent(this, MissionActivity.class));
			
		} else if(v == mode){
			startActivity(new Intent(this, ModeSelectionActivity.class));
			
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
			
			if( CommonSettings.isNone())
				selectProtocol();
			
			if( CommonSettings.isProtocolAC1()){
				connectLabel.setText("Connected");
				params.setEnabled(false);
				
			}
			else if( CommonSettings.isProtocolMAVLink()){
				connectLabel.setText("Link Activated");
			
				msg_heartbeat msg = new msg_heartbeat();
				msg.type = MAVLink.MAV_TYPE.MAV_TYPE_GCS;
				msg.autopilot = MAVLink.MAV_AUTOPILOT.MAV_AUTOPILOT_GENERIC;
				sendBytesToComm(MAVLink.createMessage(msg));
			
			}else{
				connectLabel.setText("Protocol Selection");
				
			}
			connectLabel.setTextColor(0xFF00FF00);

		}

		@Override
		public void notifyDeviceNotAvailable() {
			connectLabel.setText("Device not available");
			connectLabel.setTextColor(0xFFFF0000);
			
		}

		@Override
		public void notifyDisconnected() {
			if( CommonSettings.isNone())
				connectLabel.setText("!!No Protocol!!");
			
			if( CommonSettings.isProtocolAC1())
				connectLabel.setText("!!AC1 Disconnected!!");

			else if( CommonSettings.isProtocolMAVLink())
				connectLabel.setText("MAVLink Disconnect");
			
			connectLabel.setTextColor(0xFFFF0000);
			
		}

		@Override
		public void notifyReceivedData(int count,  IMAVLinkMessage m) {
	
			if( m.messageType == msg_heartbeat.MAVLINK_MSG_ID_HEARTBEAT){
				MAVLink.CURRENT_SYSID = m.sysID;
				MAVLink.ARDUCOPTER_COMPONENT_ID = m.componentID;
				
				connectLabel.setTextColor(0xFF00FF00);
				connectLabel.setText("Heartbeat");
				
			}
		}
	};
}
