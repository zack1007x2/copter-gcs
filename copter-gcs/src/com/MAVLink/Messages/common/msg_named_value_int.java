package com.MAVLink.Messages.common;

import com.MAVLink.Messages.IMAVLinkMessage;

public class msg_named_value_int extends IMAVLinkMessage{

	public msg_named_value_int(){ messageType = MAVLINK_MSG_ID_NAMED_VALUE_INT; }

	public static final int MAVLINK_MSG_ID_NAMED_VALUE_INT = 253;

	private static final long serialVersionUID = MAVLINK_MSG_ID_NAMED_VALUE_INT;

	public 	char[] name = new char[10];///< Name of the debug variable
	public long value; ///< Signed integer value

}