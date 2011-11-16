package com.MAVLink.Messages.common;

import com.MAVLink.Messages.IMAVLinkMessage;

public class msg_sys_status extends IMAVLinkMessage{

	public msg_sys_status(){ messageType = MAVLINK_MSG_ID_SYS_STATUS; }

	public static final int MAVLINK_MSG_ID_SYS_STATUS = 34;

	private static final long serialVersionUID = MAVLINK_MSG_ID_SYS_STATUS;

	public int mode; ///< System mode, see MAV_MODE ENUM in mavlink/include/mavlink_types.h
	public int nav_mode; ///< Navigation mode, see MAV_NAV_MODE ENUM
	public int status; ///< System status flag, see MAV_STATUS ENUM
	public int load; ///< Maximum usage in percent of the mainloop time, (0%: 0, 100%: 1000) should be always below 1000
	public int vbat; ///< Battery voltage, in millivolts (1 = 1 millivolt)
	public int battery_remaining; ///< Remaining battery energy: (0%: 0, 100%: 1000)
	public int packet_drop; ///< Dropped packets (packets that were corrupted on reception on the MAV)

}