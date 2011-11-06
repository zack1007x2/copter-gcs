package com.MAVLink.Messages.common;

import com.MAVLink.Messages.IMAVLinkMessage;

public class msg_param_request_read extends IMAVLinkMessage{

	public msg_param_request_read(){ messageType = MAVLINK_MSG_ID_PARAM_REQUEST_READ; }

	public static final int MAVLINK_MSG_ID_PARAM_REQUEST_READ = 20;

	private static final long serialVersionUID = MAVLINK_MSG_ID_PARAM_REQUEST_READ;

	public 	int target_system; ///< System ID
	public 	int target_component; ///< Component ID
	public 	int[] param_id = new int[15];///< Onboard parameter id
	public 	int param_index; ///< Parameter index. Send -1 to use the param ID field as identifier

}