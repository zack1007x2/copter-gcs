package com.MAVLink.Messages.common;

import com.MAVLink.Messages.IMAVLinkMessage;

public class msg_heartbeat extends IMAVLinkMessage{

	public msg_heartbeat(){ messageType = MAVLINK_MSG_ID_HEARTBEAT; }

	public static final int MAVLINK_MSG_ID_HEARTBEAT = 0;

	private static final long serialVersionUID = MAVLINK_MSG_ID_HEARTBEAT;

	public 	int type; ///< Type of the MAV (quadrotor, helicopter, etc., up to 15 types, defined in MAV_TYPE ENUM)
	public 	int autopilot; ///< Type of the Autopilot: 0: Generic, 1: PIXHAWK, 2: SLUGS, 3: Ardupilot (up to 15 types), defined in MAV_AUTOPILOT_TYPE ENUM
	public 	int mavlink_version; ///< MAVLink version

}