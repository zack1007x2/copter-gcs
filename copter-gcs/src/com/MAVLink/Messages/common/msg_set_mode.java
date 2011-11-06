package com.MAVLink.Messages.common;

import com.MAVLink.Messages.IMAVLinkMessage;

public class msg_set_mode extends IMAVLinkMessage{

	public msg_set_mode(){ messageType = MAVLINK_MSG_ID_SET_MODE; }

	public static final int MAVLINK_MSG_ID_SET_MODE = 11;

	private static final long serialVersionUID = MAVLINK_MSG_ID_SET_MODE;

	public 	int target; ///< The system setting the mode
	public 	int mode; ///< The new mode

}