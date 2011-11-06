package com.MAVLink.Messages.common;

import com.MAVLink.Messages.IMAVLinkMessage;

public class msg_debug extends IMAVLinkMessage{

	public msg_debug(){ messageType = MAVLINK_MSG_ID_DEBUG; }

	public static final int MAVLINK_MSG_ID_DEBUG = 255;

	private static final long serialVersionUID = MAVLINK_MSG_ID_DEBUG;

	public 	int ind; ///< index of debug variable
	public 	float value; ///< DEBUG value

}