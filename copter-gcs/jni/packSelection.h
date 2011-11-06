	case MAVLINK_MSG_ID_HEARTBEAT:
		pack_msg_heartbeat(env, heartbeat_class, obj, msg); 

		parsed=true;
		break;
	case MAVLINK_MSG_ID_BOOT:
		pack_msg_boot(env, boot_class, obj, msg); 

		parsed=true;
		break;
	case MAVLINK_MSG_ID_SYSTEM_TIME:
		pack_msg_system_time(env, system_time_class, obj, msg); 

		parsed=true;
		break;
	case MAVLINK_MSG_ID_PING:
		pack_msg_ping(env, ping_class, obj, msg); 

		parsed=true;
		break;
	case MAVLINK_MSG_ID_SYSTEM_TIME_UTC:
		pack_msg_system_time_utc(env, system_time_utc_class, obj, msg); 

		parsed=true;
		break;
	case MAVLINK_MSG_ID_CHANGE_OPERATOR_CONTROL:
		pack_msg_change_operator_control(env, change_operator_control_class, obj, msg); 

		parsed=true;
		break;
	case MAVLINK_MSG_ID_CHANGE_OPERATOR_CONTROL_ACK:
		pack_msg_change_operator_control_ack(env, change_operator_control_ack_class, obj, msg); 

		parsed=true;
		break;
	case MAVLINK_MSG_ID_AUTH_KEY:
		pack_msg_auth_key(env, auth_key_class, obj, msg); 

		parsed=true;
		break;
	case MAVLINK_MSG_ID_ACTION_ACK:
		pack_msg_action_ack(env, action_ack_class, obj, msg); 

		parsed=true;
		break;
	case MAVLINK_MSG_ID_ACTION:
		pack_msg_action(env, action_class, obj, msg); 

		parsed=true;
		break;
	case MAVLINK_MSG_ID_SET_MODE:
		pack_msg_set_mode(env, set_mode_class, obj, msg); 

		parsed=true;
		break;
	case MAVLINK_MSG_ID_SET_NAV_MODE:
		pack_msg_set_nav_mode(env, set_nav_mode_class, obj, msg); 

		parsed=true;
		break;
	case MAVLINK_MSG_ID_PARAM_REQUEST_READ:
		pack_msg_param_request_read(env, param_request_read_class, obj, msg); 

		parsed=true;
		break;
	case MAVLINK_MSG_ID_PARAM_REQUEST_LIST:
		pack_msg_param_request_list(env, param_request_list_class, obj, msg); 

		parsed=true;
		break;
	case MAVLINK_MSG_ID_PARAM_VALUE:
		pack_msg_param_value(env, param_value_class, obj, msg); 

		parsed=true;
		break;
	case MAVLINK_MSG_ID_PARAM_SET:
		pack_msg_param_set(env, param_set_class, obj, msg); 

		parsed=true;
		break;
	case MAVLINK_MSG_ID_GPS_RAW_INT:
		pack_msg_gps_raw_int(env, gps_raw_int_class, obj, msg); 

		parsed=true;
		break;
	case MAVLINK_MSG_ID_SCALED_IMU:
		pack_msg_scaled_imu(env, scaled_imu_class, obj, msg); 

		parsed=true;
		break;
	case MAVLINK_MSG_ID_GPS_STATUS:
		pack_msg_gps_status(env, gps_status_class, obj, msg); 

		parsed=true;
		break;
	case MAVLINK_MSG_ID_RAW_IMU:
		pack_msg_raw_imu(env, raw_imu_class, obj, msg); 

		parsed=true;
		break;
	case MAVLINK_MSG_ID_RAW_PRESSURE:
		pack_msg_raw_pressure(env, raw_pressure_class, obj, msg); 

		parsed=true;
		break;
	case MAVLINK_MSG_ID_SCALED_PRESSURE:
		pack_msg_scaled_pressure(env, scaled_pressure_class, obj, msg); 

		parsed=true;
		break;
	case MAVLINK_MSG_ID_ATTITUDE:
		pack_msg_attitude(env, attitude_class, obj, msg); 

		parsed=true;
		break;
	case MAVLINK_MSG_ID_LOCAL_POSITION:
		pack_msg_local_position(env, local_position_class, obj, msg); 

		parsed=true;
		break;
	case MAVLINK_MSG_ID_GLOBAL_POSITION:
		pack_msg_global_position(env, global_position_class, obj, msg); 

		parsed=true;
		break;
	case MAVLINK_MSG_ID_GPS_RAW:
		pack_msg_gps_raw(env, gps_raw_class, obj, msg); 

		parsed=true;
		break;
	case MAVLINK_MSG_ID_SYS_STATUS:
		pack_msg_sys_status(env, sys_status_class, obj, msg); 

		parsed=true;
		break;
	case MAVLINK_MSG_ID_RC_CHANNELS_RAW:
		pack_msg_rc_channels_raw(env, rc_channels_raw_class, obj, msg); 

		parsed=true;
		break;
	case MAVLINK_MSG_ID_RC_CHANNELS_SCALED:
		pack_msg_rc_channels_scaled(env, rc_channels_scaled_class, obj, msg); 

		parsed=true;
		break;
	case MAVLINK_MSG_ID_SERVO_OUTPUT_RAW:
		pack_msg_servo_output_raw(env, servo_output_raw_class, obj, msg); 

		parsed=true;
		break;
	case MAVLINK_MSG_ID_WAYPOINT:
		pack_msg_waypoint(env, waypoint_class, obj, msg); 

		parsed=true;
		break;
	case MAVLINK_MSG_ID_WAYPOINT_REQUEST:
		pack_msg_waypoint_request(env, waypoint_request_class, obj, msg); 

		parsed=true;
		break;
	case MAVLINK_MSG_ID_WAYPOINT_SET_CURRENT:
		pack_msg_waypoint_set_current(env, waypoint_set_current_class, obj, msg); 

		parsed=true;
		break;
	case MAVLINK_MSG_ID_WAYPOINT_CURRENT:
		pack_msg_waypoint_current(env, waypoint_current_class, obj, msg); 

		parsed=true;
		break;
	case MAVLINK_MSG_ID_WAYPOINT_REQUEST_LIST:
		pack_msg_waypoint_request_list(env, waypoint_request_list_class, obj, msg); 

		parsed=true;
		break;
	case MAVLINK_MSG_ID_WAYPOINT_COUNT:
		pack_msg_waypoint_count(env, waypoint_count_class, obj, msg); 

		parsed=true;
		break;
	case MAVLINK_MSG_ID_WAYPOINT_CLEAR_ALL:
		pack_msg_waypoint_clear_all(env, waypoint_clear_all_class, obj, msg); 

		parsed=true;
		break;
	case MAVLINK_MSG_ID_WAYPOINT_REACHED:
		pack_msg_waypoint_reached(env, waypoint_reached_class, obj, msg); 

		parsed=true;
		break;
	case MAVLINK_MSG_ID_WAYPOINT_ACK:
		pack_msg_waypoint_ack(env, waypoint_ack_class, obj, msg); 

		parsed=true;
		break;
	case MAVLINK_MSG_ID_GPS_SET_GLOBAL_ORIGIN:
		pack_msg_gps_set_global_origin(env, gps_set_global_origin_class, obj, msg); 

		parsed=true;
		break;
	case MAVLINK_MSG_ID_GPS_LOCAL_ORIGIN_SET:
		pack_msg_gps_local_origin_set(env, gps_local_origin_set_class, obj, msg); 

		parsed=true;
		break;
	case MAVLINK_MSG_ID_LOCAL_POSITION_SETPOINT_SET:
		pack_msg_local_position_setpoint_set(env, local_position_setpoint_set_class, obj, msg); 

		parsed=true;
		break;
	case MAVLINK_MSG_ID_LOCAL_POSITION_SETPOINT:
		pack_msg_local_position_setpoint(env, local_position_setpoint_class, obj, msg); 

		parsed=true;
		break;
	case MAVLINK_MSG_ID_CONTROL_STATUS:
		pack_msg_control_status(env, control_status_class, obj, msg); 

		parsed=true;
		break;
	case MAVLINK_MSG_ID_SAFETY_SET_ALLOWED_AREA:
		pack_msg_safety_set_allowed_area(env, safety_set_allowed_area_class, obj, msg); 

		parsed=true;
		break;
	case MAVLINK_MSG_ID_SAFETY_ALLOWED_AREA:
		pack_msg_safety_allowed_area(env, safety_allowed_area_class, obj, msg); 

		parsed=true;
		break;
	case MAVLINK_MSG_ID_SET_ROLL_PITCH_YAW_THRUST:
		pack_msg_set_roll_pitch_yaw_thrust(env, set_roll_pitch_yaw_thrust_class, obj, msg); 

		parsed=true;
		break;
	case MAVLINK_MSG_ID_SET_ROLL_PITCH_YAW_SPEED_THRUST:
		pack_msg_set_roll_pitch_yaw_speed_thrust(env, set_roll_pitch_yaw_speed_thrust_class, obj, msg); 

		parsed=true;
		break;
	case MAVLINK_MSG_ID_ROLL_PITCH_YAW_THRUST_SETPOINT:
		pack_msg_roll_pitch_yaw_thrust_setpoint(env, roll_pitch_yaw_thrust_setpoint_class, obj, msg); 

		parsed=true;
		break;
	case MAVLINK_MSG_ID_ROLL_PITCH_YAW_SPEED_THRUST_SETPOINT:
		pack_msg_roll_pitch_yaw_speed_thrust_setpoint(env, roll_pitch_yaw_speed_thrust_setpoint_class, obj, msg); 

		parsed=true;
		break;
	case MAVLINK_MSG_ID_NAV_CONTROLLER_OUTPUT:
		pack_msg_nav_controller_output(env, nav_controller_output_class, obj, msg); 

		parsed=true;
		break;
	case MAVLINK_MSG_ID_POSITION_TARGET:
		pack_msg_position_target(env, position_target_class, obj, msg); 

		parsed=true;
		break;
	case MAVLINK_MSG_ID_STATE_CORRECTION:
		pack_msg_state_correction(env, state_correction_class, obj, msg); 

		parsed=true;
		break;
	case MAVLINK_MSG_ID_SET_ALTITUDE:
		pack_msg_set_altitude(env, set_altitude_class, obj, msg); 

		parsed=true;
		break;
	case MAVLINK_MSG_ID_REQUEST_DATA_STREAM:
		pack_msg_request_data_stream(env, request_data_stream_class, obj, msg); 

		parsed=true;
		break;
	case MAVLINK_MSG_ID_HIL_STATE:
		pack_msg_hil_state(env, hil_state_class, obj, msg); 

		parsed=true;
		break;
	case MAVLINK_MSG_ID_HIL_CONTROLS:
		pack_msg_hil_controls(env, hil_controls_class, obj, msg); 

		parsed=true;
		break;
	case MAVLINK_MSG_ID_MANUAL_CONTROL:
		pack_msg_manual_control(env, manual_control_class, obj, msg); 

		parsed=true;
		break;
	case MAVLINK_MSG_ID_RC_CHANNELS_OVERRIDE:
		pack_msg_rc_channels_override(env, rc_channels_override_class, obj, msg); 

		parsed=true;
		break;
	case MAVLINK_MSG_ID_GLOBAL_POSITION_INT:
		pack_msg_global_position_int(env, global_position_int_class, obj, msg); 

		parsed=true;
		break;
	case MAVLINK_MSG_ID_VFR_HUD:
		pack_msg_vfr_hud(env, vfr_hud_class, obj, msg); 

		parsed=true;
		break;
	case MAVLINK_MSG_ID_COMMAND:
		pack_msg_command(env, command_class, obj, msg); 

		parsed=true;
		break;
	case MAVLINK_MSG_ID_COMMAND_ACK:
		pack_msg_command_ack(env, command_ack_class, obj, msg); 

		parsed=true;
		break;
	case MAVLINK_MSG_ID_DEBUG_VECT:
		pack_msg_debug_vect(env, debug_vect_class, obj, msg); 

		parsed=true;
		break;
	case MAVLINK_MSG_ID_NAMED_VALUE_FLOAT:
		pack_msg_named_value_float(env, named_value_float_class, obj, msg); 

		parsed=true;
		break;
	case MAVLINK_MSG_ID_NAMED_VALUE_INT:
		pack_msg_named_value_int(env, named_value_int_class, obj, msg); 

		parsed=true;
		break;
	case MAVLINK_MSG_ID_STATUSTEXT:
		pack_msg_statustext(env, statustext_class, obj, msg); 

		parsed=true;
		break;
	case MAVLINK_MSG_ID_DEBUG:
		pack_msg_debug(env, debug_class, obj, msg); 

		parsed=true;
		break;
