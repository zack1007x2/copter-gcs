package com.MAVLink.Messages.common;

import com.MAVLink.Messages.IMAVLinkMessage;

public class msg_request_data_stream extends IMAVLinkMessage{

	public msg_request_data_stream(){ messageType = MAVLINK_MSG_ID_REQUEST_DATA_STREAM; }

	public static final int MAVLINK_MSG_ID_REQUEST_DATA_STREAM = 66;

	private static final long serialVersionUID = MAVLINK_MSG_ID_REQUEST_DATA_STREAM;

	public int target_system; ///< The target requested to send the message stream.
	public int target_component; ///< The target requested to send the message stream.
	public int req_stream_id; ///< The ID of the requested message type
	public int req_message_rate; ///< Update rate in Hertz
	public int start_stop; ///< 1 to start sending, 0 to stop sending.

}