package com.MAVLink.Messages.common;

import com.MAVLink.Messages.IMAVLinkMessage;

public class msg_gps_raw_int extends IMAVLinkMessage{

	public msg_gps_raw_int(){ messageType = MAVLINK_MSG_ID_GPS_RAW_INT; }

	public static final int MAVLINK_MSG_ID_GPS_RAW_INT = 25;

	private static final long serialVersionUID = MAVLINK_MSG_ID_GPS_RAW_INT;

	public 	long usec; ///< Timestamp (microseconds since UNIX epoch or microseconds since system boot)
	public 	int fix_type; ///< 0-1: no fix, 2: 2D fix, 3: 3D fix. Some applications will not use the value of this field unless it is at least two, so always correctly fill in the fix.
	public 	long lat; ///< Latitude in 1E7 degrees
	public 	long lon; ///< Longitude in 1E7 degrees
	public 	long alt; ///< Altitude in 1E3 meters (millimeters)
	public 	float eph; ///< GPS HDOP
	public 	float epv; ///< GPS VDOP
	public 	float v; ///< GPS ground speed (m/s)
	public 	float hdg; ///< Compass heading in degrees, 0..360 degrees

}