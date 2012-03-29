package com.bvcode.ncopter.mission;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.MAVLink.MAVLink;
import com.MAVLink.VerifiedMAVLink;
import com.MAVLink.Messages.IMAVLinkMessage;
import com.MAVLink.Messages.common.msg_global_position_int;
import com.MAVLink.Messages.common.msg_gps_raw;
import com.MAVLink.Messages.common.msg_request_data_stream;
import com.MAVLink.Messages.common.msg_waypoint;
import com.MAVLink.Messages.common.msg_waypoint_ack;
import com.MAVLink.Messages.common.msg_waypoint_count;
import com.MAVLink.Messages.common.msg_waypoint_request;
import com.MAVLink.Messages.common.msg_waypoint_request_list;
import com.bvcode.ncopter.CommonSettings;
import com.bvcode.ncopter.R;
import com.bvcode.ncopter.AC1Data.ProtocolParser;
import com.bvcode.ncopter.comms.CommunicationClient;
import com.bvcode.ncopter.gps.GPSOverlay;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

public class MissionActivity extends MapActivity{

	private MapController mapController = null;
	boolean firstGPS = true;
		
	List<Overlay> mapOverlays = null;
	
	GPSOverlay itemizedoverlay = null;
	MyLocationOverlay phoneLocationOverlay = null;
	
	static MapView mapView = null;
	static MissionOverlay missionOverlay = null;
	
	VerifiedMAVLink verifiedMAVLink = null;

	
	static protected LinkedList<msg_waypoint > waypoints= new LinkedList<msg_waypoint >();
	
	static Comparator<msg_waypoint> comparator = new Comparator<msg_waypoint>() {
		@Override
		public int compare(msg_waypoint paramT1, msg_waypoint paramT2) {
			return	new Integer(paramT1.seq).compareTo( paramT2.seq);
			
		}
	};
	
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
		if( CommonSettings.setOrientation(this, -1))
			return;

	    setContentView(R.layout.gps_view);
	    
	    mapView = (MapView) findViewById(R.id.mapview);
	    mapView.setBuiltInZoomControls(true);
	    mapView.setSatellite(true);
	     
	    itemizedoverlay = new GPSOverlay(this.getResources().getDrawable(R.drawable.bubble), this);
	 
	    missionOverlay= new MissionOverlay(this.getResources().getDrawable(R.drawable.small_pin), this, mapView);
	        
	    phoneLocationOverlay = new MyLocationOverlay(this, mapView);
	    phoneLocationOverlay.enableCompass();
	    phoneLocationOverlay.enableMyLocation();
	    
	    // Add the overlays
	    mapOverlays = mapView.getOverlays();
	    mapOverlays.add(missionOverlay);
	    mapOverlays.add(phoneLocationOverlay);
	    mapOverlays.add(itemizedoverlay);
	    	    
	    phoneLocationOverlay.runOnFirstFix(new Runnable() {
			@Override
			public void run() {
				mapController.setCenter(phoneLocationOverlay.getMyLocation());
				firstGPS = false;
				
			}
		});

	    mapController = mapView.getController();
	    mapController.setZoom(20);

	    ba.init();
	    
	    verifiedMAVLink = new VerifiedMAVLink(ba);
	    
	}
		
	public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.mission, menu);
	    return true;
	    
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	    	case R.id.menuEnableEdit:
	    		missionOverlay.enableEdit = !missionOverlay.enableEdit;
	    		
	    		break;
	    	case R.id.menuEditMission:
	    		startActivity(new Intent(this, MissionListActivity.class));
	    		break;
	    		
	    	case R.id.menuClearMission:
	    		waypointsClear();
	    		missionOverlay.notifyDataChanged();
	    		break;
	    		
	    	case R.id.menuSaveMission:
    			if( getWaypointSize() != 0){
	    			msg_waypoint_count test = new msg_waypoint_count();
	    			test.target_system = MAVLink.CURRENT_SYSID;
					test.target_component = MAVLink.MAV_COMPONENT.MAV_COMP_ID_MISSIONPLANNER;//WAYPOINTPLANNER;
					test.count = getWaypointSize() ;
	    			ba.sendBytesToComm(  MAVLink.createMessage( test ) );

	    		}else{
	    			Toast toast=Toast.makeText(this, "No Mission to Save", 2000);
	    		    toast.setGravity(Gravity.TOP, -30, 50);
	    		    toast.show();
	    			
	    		}
	    		
	    		break;
	    		
	    	case R.id.menuLoadMission:{
	    		waypointsClear();
	    		
	    		// Request the current waypoints as well.
				msg_waypoint_request_list list = new msg_waypoint_request_list();
				list.target_component = 0;
				list.target_system = MAVLink.CURRENT_SYSID;
				ba.sendBytesToComm(MAVLink.createMessage(list));
	    		return true;
	    		
	    	}	    
	    }
	    return false;
	    
	}
	
	@Override
	public void onResume() {
		super.onResume();
		if( phoneLocationOverlay != null)
			phoneLocationOverlay.enableCompass();
		if( missionOverlay != null)
			missionOverlay.notifyDataChanged();
		if( mapView != null)
			mapView.invalidate();
        
	}

	@Override
	public void onPause() {
		super.onPause();	
		if( phoneLocationOverlay != null)
			phoneLocationOverlay.disableCompass();
		
	} 
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		
		if(phoneLocationOverlay != null){
			phoneLocationOverlay.disableMyLocation();
			phoneLocationOverlay.disableCompass();
		}
		
		if( mapOverlays != null)
			mapOverlays.clear();
		
		ba.onDestroy();
		
	}
	
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		ba.onActivityResult(requestCode, resultCode, data);
		
	}
	
	CommunicationClient ba = new CommunicationClient(this) {
		@Override
		public void notifyConnected() {

			if( CommonSettings.isProtocolAC1())
				ba.sendBytesToComm(ProtocolParser.requestGPSData());
			else if (CommonSettings.isProtocolMAVLink()){
				msg_request_data_stream req = new msg_request_data_stream();
				req.req_message_rate = 1;
				
				// position stream not working...
				req.req_stream_id = MAVLink.MAV_DATA_STREAM.MAV_DATA_STREAM_EXTENDED_STATUS;
				req.start_stop = 1;
				req.target_system = MAVLink.CURRENT_SYSID;
				req.target_component = 0;
				ba.sendBytesToComm( MAVLink.createMessage(req));
			
				
			}
			
			synchronized (waypoints) {
				if( waypoints.size()==0){
			        AlertDialog.Builder alertbox = new AlertDialog.Builder(MissionActivity.this);
			        alertbox.setMessage("Would you like to load the Mission from the UAV?");
			        alertbox.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
		
			            // do something when the button is clicked
			            public void onClick(DialogInterface arg0, int arg1) {
			            	msg_waypoint_request_list list = new msg_waypoint_request_list();
							list.target_component = 0;
							list.target_system = MAVLink.CURRENT_SYSID;
							ba.sendBytesToComm(MAVLink.createMessage(list));
			            	
			            }
			        });
			        alertbox.setNegativeButton("No", null);
			        alertbox.show();
				}
			}

		}

		@Override
		public void notifyReceivedData(int count, IMAVLinkMessage m) {

			if(CommonSettings.isProtocolMAVLink()){
		
				switch(m.messageType){
					case msg_waypoint_ack.MAVLINK_MSG_ID_WAYPOINT_ACK:{
						Toast toast = Toast.makeText(MissionActivity.this, "Received Waypoint Acknowledgement\nAll waypoints saved.", 2000);
						toast.setGravity(Gravity.TOP, -30, 50);
						toast.show();

						break;
					}
					case msg_waypoint_request.MAVLINK_MSG_ID_WAYPOINT_REQUEST:{
						msg_waypoint_request msg = (msg_waypoint_request)m;
						
						msg_waypoint waypoint = getWaypoint(msg.seq);
						waypoint.target_system = MAVLink.CURRENT_SYSID;
						waypoint.target_component = MAVLink.MAV_COMPONENT.MAV_COMP_ID_MISSIONPLANNER;//WAYPOINTPLANNER;
						ba.sendBytesToComm(  MAVLink.createMessage( waypoint ) );
								    		
						break;
					}
					case msg_waypoint.MAVLINK_MSG_ID_WAYPOINT:{
						msg_waypoint msg = (msg_waypoint)m;
						verifiedMAVLink.verifyReceipt(""+msg.seq);
						
						if( verifiedMAVLink.isDone() ){
							Toast toast=Toast.makeText(MissionActivity.this, "Loaded entire Mission", 2000);
			    		    toast.setGravity(Gravity.TOP, -30, 50);
			    		    toast.show();
							
							
			    		    // Acknowledge receipt
							msg_waypoint_ack waypoint = new msg_waypoint_ack();
							waypoint.target_system = MAVLink.CURRENT_SYSID;
							waypoint.target_component = MAVLink.MAV_COMPONENT.MAV_COMP_ID_MISSIONPLANNER;//WAYPOINTPLANNER;
							ba.sendBytesToComm(  MAVLink.createMessage( waypoint ) );
			    		    
						}
						synchronized (waypoints) {
							boolean found = false;
							for( msg_waypoint exist: waypoints){
								if( exist.seq == msg.seq){
									exist = msg;
									found = true;
									
								}
							}
							
							if( !found){
								waypoints.add(msg);
								Collections.sort(waypoints, comparator);
								missionOverlay.notifyDataChanged();
								
							}
						}
						break;	
					}
					
					case msg_waypoint_count.MAVLINK_MSG_ID_WAYPOINT_COUNT:{
						msg_waypoint_count msg = (msg_waypoint_count) m;
						waypointsClear();
						missionOverlay.notifyDataChanged();

						if( msg.count > 0){
							
							for( int i = 0; i < msg.count; i++){
								msg_waypoint_request req = new msg_waypoint_request();
								req.target_component = 0;
								req.target_system = MAVLink.CURRENT_SYSID;
								req.seq = i;
								verifiedMAVLink.put(""+i, MAVLink.createMessage(req));
								
							}
							verifiedMAVLink.start(5);
							
						}
						
						break;
					}
					
					case msg_global_position_int.MAVLINK_MSG_ID_GLOBAL_POSITION_INT:{
						msg_global_position_int msg = (msg_global_position_int) m;
					
						
						GeoPoint point = new GeoPoint((int)msg.lat,(int) msg.lon);
						itemizedoverlay.setCopterOverlay(new OverlayItem(point, "Copter", "Current Copter Location"));
					
						mapView.postInvalidate();
					
						if(firstGPS){
							mapController.setCenter(point);
							firstGPS = false;
							
						}
						
						break;
					}
					
					case msg_gps_raw.MAVLINK_MSG_ID_GPS_RAW:{
						msg_gps_raw msg = (msg_gps_raw) m;
		
						if( msg.fix_type > 1){
							GeoPoint point = new GeoPoint((int)(msg.lat*1000000), (int)(msg.lon*1000000.0));
							itemizedoverlay.setCopterOverlay(new OverlayItem(point, "Copter", "Current Copter Location"));
							
							mapView.postInvalidate();
						
							if(firstGPS){
								mapController.setCenter(point);
								firstGPS = false;
								
							}
						}
						
						break;
					}
				}		
			}
		}

		@Override
		public void notifyDisconnected() {
			
		}

		@Override
		public void notifyDeviceNotAvailable() {
			
		}
		
	};
	
	@Override
	protected boolean isRouteDisplayed() {
		return false;

	}

	public static void add(msg_waypoint msg) {
		synchronized (waypoints) {
			waypoints.add( msg);
			Collections.sort(waypoints, comparator);
			
		}
	}

	public static int getWaypointSize() {
		int res;
		synchronized (waypoints) {
			res = waypoints.size();
			
		}
		return res;
	}

	
	public static msg_waypoint getWaypoint(int i) {
		msg_waypoint res;
		synchronized (waypoints) {
			res = waypoints.get(i);
			
		}
		return res;
	}

	public static msg_waypoint getLastWaypoint() {
		msg_waypoint res = null;
		synchronized (waypoints) {
			if( waypoints.size() > 0)
				res = waypoints.getLast();
			
		}
		return res;
	}

	public static void waypointsClear() {
		synchronized (waypoints) {
			waypoints.clear();
			
		}
	}

	public static void swap(int from, int to){
		synchronized (waypoints) {
			
			msg_waypoint A = waypoints.get(from);
			
			if( from > to){
				for(int i = to; i < waypoints.size(); i++)
					waypoints.get(i).seq += 1;
					
			}else if ( from < to){
				for(int i = from; i <= to; i++)
					waypoints.get(i).seq -= 1;
					
			}
			
			A.seq = to;
			
			Collections.sort(waypoints, comparator);
		
		}		
	}
	
	public static void remove(int paramInt) {
		synchronized (waypoints) {
			waypoints.remove(paramInt);
			
			Collections.sort(waypoints, comparator);
			
			// Reorder the numbers.
			for( int i = 0; i < waypoints.size(); i++)
				waypoints.get(i).seq = i;
			
			// Update the screen
			missionOverlay.notifyDataChanged();
			mapView.invalidate();
			
		}	
	}
}
