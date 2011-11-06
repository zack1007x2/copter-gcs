package com.MAVLink.Messages.common;

import com.MAVLink.Messages.IMAVLinkMessage;

public class msg_command_ack extends IMAVLinkMessage{

	public msg_command_ack(){ messageType = MAVLINK_MSG_ID_COMMAND_ACK; }

	public static final int MAVLINK_MSG_ID_COMMAND_ACK = 76;

	private static final long serialVersionUID = MAVLINK_MSG_ID_COMMAND_ACK;

	public 	float command; ///< Current airspeed in m/s
	public 	float result; ///< 1: Action ACCEPTED and EXECUTED, 1: Action TEMPORARY REJECTED/DENIED, 2: Action PERMANENTLY DENIED, 3: Action UNKNOWN/UNSUPPORTED, 4: Requesting CONFIRMATION

}