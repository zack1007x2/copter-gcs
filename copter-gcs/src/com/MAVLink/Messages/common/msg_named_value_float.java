package com.MAVLink.Messages.common;

import com.MAVLink.Messages.IMAVLinkMessage;

public class msg_named_value_float extends IMAVLinkMessage{

	public msg_named_value_float(){ messageType = MAVLINK_MSG_ID_NAMED_VALUE_FLOAT; }

	public static final int MAVLINK_MSG_ID_NAMED_VALUE_FLOAT = 252;

	private static final long serialVersionUID = MAVLINK_MSG_ID_NAMED_VALUE_FLOAT;

	public 	char[] name = new char[10];///< Name of the debug variable
	public float value; ///< Floating point value

}