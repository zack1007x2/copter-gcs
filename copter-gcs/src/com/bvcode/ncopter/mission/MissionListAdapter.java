package com.bvcode.ncopter.mission;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.MAVLink.MAVLink;
import com.MAVLink.Messages.common.msg_waypoint;
import com.bvcode.ncopter.R;

public class MissionListAdapter implements ListAdapter{
	MissionListActivity parent;
	MissionListView list;
	
	public MissionListAdapter(MissionListActivity missionListActivity, MissionListView listView) {
		parent = missionListActivity;
		list = listView;
	}

	@Override
	public void registerDataSetObserver(DataSetObserver paramDataSetObserver) {
		
	}

	@Override
	public void unregisterDataSetObserver(DataSetObserver paramDataSetObserver) {
		
	}

	@Override
	public int getCount() {
		return MissionActivity.getWaypointSize();
		
	}

	@Override
	public Object getItem(int paramInt) {
		if(paramInt < MissionActivity.getWaypointSize())
			return MissionActivity.getWaypoint(paramInt);
		
		return null;
	}

	@Override
	public long getItemId(int paramInt) {
		return paramInt;
		
	}

	@Override
	public boolean hasStableIds() {
		return true;
		
	}

	@Override
	public View getView(int paramInt, View convertView, ViewGroup parent) {
		String group;
		
		Object o = getItem(paramInt);
		if( o == null){
			group = "Not Loaded Yet";
			
		}else{
			msg_waypoint msg = (msg_waypoint)o;
			group = MAVLink.getMavCmd(msg.command);
			group = group.replace("MAV_CMD_", "");
			
			if( msg.command == 0)
				group = "No Command";
			
			if( group.equals(""))
				group = "Unknown Command";
			
		}

		if (convertView == null) {
			LayoutInflater infalInflater = (LayoutInflater) this.parent.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = infalInflater.inflate( R.layout.mission_group_layout, null);
		
		}
		
		TextView tv = (TextView) convertView.findViewById(R.id.missionText);
		tv.setText(paramInt + " " +group);
		return convertView;

	}

	@Override
	public int getItemViewType(int paramInt) {
		return 0;
		
	}

	@Override
	public int getViewTypeCount() {
		return 1;
		
	}

	@Override
	public boolean isEmpty() {
		return MissionActivity.getWaypointSize() == 0;

	}

	@Override
	public boolean areAllItemsEnabled() {
		return true;
	}

	@Override
	public boolean isEnabled(int paramInt) {		
		return true;
	}
	
	public void onDrop(int from, int to) {
		MissionActivity.swap(from, to);
		
	}
}