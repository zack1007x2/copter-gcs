package com.MAVLink.Messages.common;

import com.MAVLink.Messages.IMAVLinkMessage;

public class msg_ping extends IMAVLinkMessage{

	public msg_ping(){ messageType = MAVLINK_MSG_ID_PING; }

	public static final int MAVLINK_MSG_ID_PING = 3;

	private static final long serialVersionUID = MAVLINK_MSG_ID_PING;

	public long seq; ///< PING sequence
	public int target_system; ///< 0: request ping from all receiving systems, if greater than 0: message is a ping response and number is the system id of the requesting system
	public int target_component; ///< 0: request ping from all receiving components, if greater than 0: message is a ping response and number is the system id of the requesting system
	public long time; ///< Unix timestamp in microseconds

}