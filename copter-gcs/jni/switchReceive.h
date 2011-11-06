	case MAVLINK_MSG_ID_HEARTBEAT:
		result = unpack_msg_heartbeat(env, message);	
		break;

	case MAVLINK_MSG_ID_BOOT:
		result = unpack_msg_boot(env, message);	
		break;

	case MAVLINK_MSG_ID_SYSTEM_TIME:
		result = unpack_msg_system_time(env, message);	
		break;

	case MAVLINK_MSG_ID_PING:
		result = unpack_msg_ping(env, message);	
		break;

	case MAVLINK_MSG_ID_SYSTEM_TIME_UTC:
		result = unpack_msg_system_time_utc(env, message);	
		break;

	case MAVLINK_MSG_ID_CHANGE_OPERATOR_CONTROL:
		result = unpack_msg_change_operator_control(env, message);	
		break;

	case MAVLINK_MSG_ID_CHANGE_OPERATOR_CONTROL_ACK:
		result = unpack_msg_change_operator_control_ack(env, message);	
		break;

	case MAVLINK_MSG_ID_AUTH_KEY:
		result = unpack_msg_auth_key(env, message);	
		break;

	case MAVLINK_MSG_ID_ACTION_ACK:
		result = unpack_msg_action_ack(env, message);	
		break;

	case MAVLINK_MSG_ID_ACTION:
		result = unpack_msg_action(env, message);	
		break;

	case MAVLINK_MSG_ID_SET_MODE:
		result = unpack_msg_set_mode(env, message);	
		break;

	case MAVLINK_MSG_ID_SET_NAV_MODE:
		result = unpack_msg_set_nav_mode(env, message);	
		break;

	case MAVLINK_MSG_ID_PARAM_REQUEST_READ:
		result = unpack_msg_param_request_read(env, message);	
		break;

	case MAVLINK_MSG_ID_PARAM_REQUEST_LIST:
		result = unpack_msg_param_request_list(env, message);	
		break;

	case MAVLINK_MSG_ID_PARAM_VALUE:
		result = unpack_msg_param_value(env, message);	
		break;

	case MAVLINK_MSG_ID_PARAM_SET:
		result = unpack_msg_param_set(env, message);	
		break;

	case MAVLINK_MSG_ID_GPS_RAW_INT:
		result = unpack_msg_gps_raw_int(env, message);	
		break;

	case MAVLINK_MSG_ID_SCALED_IMU:
		result = unpack_msg_scaled_imu(env, message);	
		break;

	case MAVLINK_MSG_ID_GPS_STATUS:
		result = unpack_msg_gps_status(env, message);	
		break;

	case MAVLINK_MSG_ID_RAW_IMU:
		result = unpack_msg_raw_imu(env, message);	
		break;

	case MAVLINK_MSG_ID_RAW_PRESSURE:
		result = unpack_msg_raw_pressure(env, message);	
		break;

	case MAVLINK_MSG_ID_SCALED_PRESSURE:
		result = unpack_msg_scaled_pressure(env, message);	
		break;

	case MAVLINK_MSG_ID_ATTITUDE:
		result = unpack_msg_attitude(env, message);	
		break;

	case MAVLINK_MSG_ID_LOCAL_POSITION:
		result = unpack_msg_local_position(env, message);	
		break;

	case MAVLINK_MSG_ID_GLOBAL_POSITION:
		result = unpack_msg_global_position(env, message);	
		break;

	case MAVLINK_MSG_ID_GPS_RAW:
		result = unpack_msg_gps_raw(env, message);	
		break;

	case MAVLINK_MSG_ID_SYS_STATUS:
		result = unpack_msg_sys_status(env, message);	
		break;

	case MAVLINK_MSG_ID_RC_CHANNELS_RAW:
		result = unpack_msg_rc_channels_raw(env, message);	
		break;

	case MAVLINK_MSG_ID_RC_CHANNELS_SCALED:
		result = unpack_msg_rc_channels_scaled(env, message);	
		break;

	case MAVLINK_MSG_ID_SERVO_OUTPUT_RAW:
		result = unpack_msg_servo_output_raw(env, message);	
		break;

	case MAVLINK_MSG_ID_WAYPOINT:
		result = unpack_msg_waypoint(env, message);	
		break;

	case MAVLINK_MSG_ID_WAYPOINT_REQUEST:
		result = unpack_msg_waypoint_request(env, message);	
		break;

	case MAVLINK_MSG_ID_WAYPOINT_SET_CURRENT:
		result = unpack_msg_waypoint_set_current(env, message);	
		break;

	case MAVLINK_MSG_ID_WAYPOINT_CURRENT:
		result = unpack_msg_waypoint_current(env, message);	
		break;

	case MAVLINK_MSG_ID_WAYPOINT_REQUEST_LIST:
		result = unpack_msg_waypoint_request_list(env, message);	
		break;

	case MAVLINK_MSG_ID_WAYPOINT_COUNT:
		result = unpack_msg_waypoint_count(env, message);	
		break;

	case MAVLINK_MSG_ID_WAYPOINT_CLEAR_ALL:
		result = unpack_msg_waypoint_clear_all(env, message);	
		break;

	case MAVLINK_MSG_ID_WAYPOINT_REACHED:
		result = unpack_msg_waypoint_reached(env, message);	
		break;

	case MAVLINK_MSG_ID_WAYPOINT_ACK:
		result = unpack_msg_waypoint_ack(env, message);	
		break;

	case MAVLINK_MSG_ID_GPS_SET_GLOBAL_ORIGIN:
		result = unpack_msg_gps_set_global_origin(env, message);	
		break;

	case MAVLINK_MSG_ID_GPS_LOCAL_ORIGIN_SET:
		result = unpack_msg_gps_local_origin_set(env, message);	
		break;

	case MAVLINK_MSG_ID_LOCAL_POSITION_SETPOINT_SET:
		result = unpack_msg_local_position_setpoint_set(env, message);	
		break;

	case MAVLINK_MSG_ID_LOCAL_POSITION_SETPOINT:
		result = unpack_msg_local_position_setpoint(env, message);	
		break;

	case MAVLINK_MSG_ID_CONTROL_STATUS:
		result = unpack_msg_control_status(env, message);	
		break;

	case MAVLINK_MSG_ID_SAFETY_SET_ALLOWED_AREA:
		result = unpack_msg_safety_set_allowed_area(env, message);	
		break;

	case MAVLINK_MSG_ID_SAFETY_ALLOWED_AREA:
		result = unpack_msg_safety_allowed_area(env, message);	
		break;

	case MAVLINK_MSG_ID_SET_ROLL_PITCH_YAW_THRUST:
		result = unpack_msg_set_roll_pitch_yaw_thrust(env, message);	
		break;

	case MAVLINK_MSG_ID_SET_ROLL_PITCH_YAW_SPEED_THRUST:
		result = unpack_msg_set_roll_pitch_yaw_speed_thrust(env, message);	
		break;

	case MAVLINK_MSG_ID_ROLL_PITCH_YAW_THRUST_SETPOINT:
		result = unpack_msg_roll_pitch_yaw_thrust_setpoint(env, message);	
		break;

	case MAVLINK_MSG_ID_ROLL_PITCH_YAW_SPEED_THRUST_SETPOINT:
		result = unpack_msg_roll_pitch_yaw_speed_thrust_setpoint(env, message);	
		break;

	case MAVLINK_MSG_ID_NAV_CONTROLLER_OUTPUT:
		result = unpack_msg_nav_controller_output(env, message);	
		break;

	case MAVLINK_MSG_ID_POSITION_TARGET:
		result = unpack_msg_position_target(env, message);	
		break;

	case MAVLINK_MSG_ID_STATE_CORRECTION:
		result = unpack_msg_state_correction(env, message);	
		break;

	case MAVLINK_MSG_ID_SET_ALTITUDE:
		result = unpack_msg_set_altitude(env, message);	
		break;

	case MAVLINK_MSG_ID_REQUEST_DATA_STREAM:
		result = unpack_msg_request_data_stream(env, message);	
		break;

	case MAVLINK_MSG_ID_HIL_STATE:
		result = unpack_msg_hil_state(env, message);	
		break;

	case MAVLINK_MSG_ID_HIL_CONTROLS:
		result = unpack_msg_hil_controls(env, message);	
		break;

	case MAVLINK_MSG_ID_MANUAL_CONTROL:
		result = unpack_msg_manual_control(env, message);	
		break;

	case MAVLINK_MSG_ID_RC_CHANNELS_OVERRIDE:
		result = unpack_msg_rc_channels_override(env, message);	
		break;

	case MAVLINK_MSG_ID_GLOBAL_POSITION_INT:
		result = unpack_msg_global_position_int(env, message);	
		break;

	case MAVLINK_MSG_ID_VFR_HUD:
		result = unpack_msg_vfr_hud(env, message);	
		break;

	case MAVLINK_MSG_ID_COMMAND:
		result = unpack_msg_command(env, message);	
		break;

	case MAVLINK_MSG_ID_COMMAND_ACK:
		result = unpack_msg_command_ack(env, message);	
		break;

	case MAVLINK_MSG_ID_DEBUG_VECT:
		result = unpack_msg_debug_vect(env, message);	
		break;

	case MAVLINK_MSG_ID_NAMED_VALUE_FLOAT:
		result = unpack_msg_named_value_float(env, message);	
		break;

	case MAVLINK_MSG_ID_NAMED_VALUE_INT:
		result = unpack_msg_named_value_int(env, message);	
		break;

	case MAVLINK_MSG_ID_STATUSTEXT:
		result = unpack_msg_statustext(env, message);	
		break;

	case MAVLINK_MSG_ID_DEBUG:
		result = unpack_msg_debug(env, message);	
		break;

