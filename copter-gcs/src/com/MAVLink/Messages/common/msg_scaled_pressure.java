package com.MAVLink.Messages.common;

import com.MAVLink.Messages.IMAVLinkMessage;

public class msg_scaled_pressure extends IMAVLinkMessage{

	public msg_scaled_pressure(){ messageType = MAVLINK_MSG_ID_SCALED_PRESSURE; }

	public static final int MAVLINK_MSG_ID_SCALED_PRESSURE = 38;

	private static final long serialVersionUID = MAVLINK_MSG_ID_SCALED_PRESSURE;

	public long usec; ///< Timestamp (microseconds since UNIX epoch or microseconds since system boot)
	public float press_abs; ///< Absolute pressure (hectopascal)
	public float press_diff; ///< Differential pressure 1 (hectopascal)
	public int temperature; ///< Temperature measurement (0.01 degrees celsius)

}