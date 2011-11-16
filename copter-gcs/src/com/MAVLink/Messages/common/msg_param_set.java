package com.MAVLink.Messages.common;

import com.MAVLink.Messages.IMAVLinkMessage;

public class msg_param_set extends IMAVLinkMessage{

	public msg_param_set(){ messageType = MAVLINK_MSG_ID_PARAM_SET; }

	public static final int MAVLINK_MSG_ID_PARAM_SET = 23;

	private static final long serialVersionUID = MAVLINK_MSG_ID_PARAM_SET;

	public int target_system; ///< System ID
	public int target_component; ///< Component ID
	public 	int[] param_id = new int[15];///< Onboard parameter id
	public float param_value; ///< Onboard parameter value

}