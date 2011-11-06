package com.MAVLink;

import java.lang.reflect.Field;

import com.MAVLink.Messages.IMAVLinkMessage;

public class MAVLink {

        public static int CURRENT_SYSID = 0;
        public static int ARDUCOPTER_COMPONENT_ID = 0;

        public static int MAVLINK_ARDU_MEGA_SYSID = 1;
        public static int MAVLINK_ARDU_COPTER_MEGA_SYSID = 7;

        public MAVLink(){}


        public static int[] StringNameToInt(String valueName){
                int[] name = new int[15];
                for( int i = 0; i < valueName.length(); i++)
                        name[i] = valueName.charAt(i);

                return name;

        }

     public static String convertIntNameToString(int[] param_id) {
            char valueName[] = new char[ param_id.length]; 
             
             // Convert the int array to a char[] and then a string later on
             int i = 0;
             for(; i < param_id.length; i++){
                     valueName[i] = (char)param_id[i];
                    if( valueName[i] == 0)
                            break;

            }
            return new String(valueName, 0, i);

     }
      /**
       * Take the next byte from the input stream (from the drone)
       * If the byte finishes the packet, then return it
       * 
       * @param b Next byte in the stream
       * @return NULL if not a valid packet, a message object otherwise.
        */
      public static native IMAVLinkMessage receivedByte(byte b);

     /**
      * Packs up a message and checksums it to be sent out to the drone
      * @param msg The message to be sent
      * @return the byte stream to be sent, or null if the message was not recognized.
      */
     public static native byte[] createMessage(IMAVLinkMessage msg);

    /**
     * Must be called to initialize some variables in the JNI library
     */
       private static native void init();

    static {
            System.loadLibrary("jmavlink");
            init();

    }
	public class MAV_CMD{
		public final static int MAV_CMD_NAV_WAYPOINT=16; //, * Navigate to waypoint. | Hold time in decimal seconds. (ignored by fixed wing, time to stay at waypoint for rotary wing) | Acceptance radius in meters (if the sphere with this radius is hit, the waypoint counts as reached) | 0 to pass through the WP, if > 0 radius in meters to pass by WP. Positive value for clockwise orbit, negative value for counter-clockwise orbit. Allows trajectory control. | Desired yaw angle at waypoint (rotary wing) | Latitude | Longitude | Altitude | *
		public final static int MAV_CMD_NAV_LOITER_UNLIM=17; //, * Loiter around this waypoint an unlimited amount of time | Empty | Empty | Radius around waypoint, in meters. If positive loiter clockwise, else counter-clockwise | Desired yaw angle. | Latitude | Longitude | Altitude | *
		public final static int MAV_CMD_NAV_LOITER_TURNS=18; //, * Loiter around this waypoint for X turns | Turns | Empty | Radius around waypoint, in meters. If positive loiter clockwise, else counter-clockwise | Desired yaw angle. | Latitude | Longitude | Altitude | *
		public final static int MAV_CMD_NAV_LOITER_TIME=19; //, * Loiter around this waypoint for X seconds | Seconds (decimal) | Empty | Radius around waypoint, in meters. If positive loiter clockwise, else counter-clockwise | Desired yaw angle. | Latitude | Longitude | Altitude | *
		public final static int MAV_CMD_NAV_RETURN_TO_LAUNCH=20; //, * Return to launch location | Empty | Empty | Empty | Empty | Empty | Empty | Empty | *
		public final static int MAV_CMD_NAV_LAND=21; //, * Land at location | Empty | Empty | Empty | Desired yaw angle. | Latitude | Longitude | Altitude | *
		public final static int MAV_CMD_NAV_TAKEOFF=22; //, * Takeoff from ground 
		public final static int MAV_CMD_NAV_ROI=80; //, * Sets the region of interest (ROI) for a sensor set or the vehicle itself. This can then be used by the vehicles control system to control the vehicle attitude and the attitude of various sensors such as cameras. | Region of intereset mode. (see MAV_ROI enum) | Waypoint index
		public final static int MAV_CMD_NAV_PATHPLANNING=81; //, * Control autonomous path planning on the MAV. | 0: Disable local obstacle avoidance 
		public final static int MAV_CMD_NAV_LAST=95; //, * NOP - This command is only used to mark the upper limit of the NAV
		public final static int MAV_CMD_CONDITION_DELAY=112; //, * Delay mission state machine. | Delay in seconds (decimal) | Empty | Empty | Empty | Empty | Empty | Empty | *
		public final static int MAV_CMD_CONDITION_CHANGE_ALT=113; //, * Ascend
		public final static int MAV_CMD_CONDITION_DISTANCE=114; //, * Delay mission state machine until within desired distance of next NAV point. | Distance (meters) | Empty | Empty | Empty | Empty | Empty | Empty | *
		public final static int MAV_CMD_CONDITION_YAW=115; //, * Reach a certain target angle. | target angle: [0-360], 0 is north | speed during yaw change:[deg per second] | direction: negative: counter clockwise, positive: clockwise [-1,1] | relative offset or absolute angle: [ 1,0] | Empty | Empty | Empty | *
		public final static int MAV_CMD_CONDITION_LAST=159; //, * NOP - This command is only used to mark the upper limit of the CONDITION commands in the enumeration | Empty | Empty | Empty | Empty | Empty | Empty | Empty | *
		public final static int MAV_CMD_DO_SET_MODE=176; //, * Set system mode. | Mode, as defined by ENUM MAV_MODE | Empty | Empty | Empty | Empty | Empty | Empty | *
		public final static int MAV_CMD_DO_JUMP=177; //, * Jump to the desired command in the mission list. Repeat this action only the specified number of times | Sequence number | Repeat count | Empty | Empty | Empty | Empty | Empty | *
		public final static int MAV_CMD_DO_CHANGE_SPEED=178; //, * Change speed and
		public final static int MAV_CMD_DO_SET_HOME=179; //, * Changes the home location either to the current location or a specified location. | Use current (1=use current location, 0=use specified location) | Empty | Empty | Empty | Latitude | Longitude | Altitude | *
		public final static int MAV_CMD_DO_SET_PARAMETER=180; //, * Set a system parameter. Caution! Use of this command requires knowledge of the numeric enumeration value of the parameter. | Parameter number | Parameter value | Empty | Empty | Empty | Empty | Empty | *
		public final static int MAV_CMD_DO_SET_RELAY=181; //, * Set a relay to a condition. | Relay number | Setting (1=on, 0=off, others possible depending on system hardware) | Empty | Empty | Empty | Empty | Empty | *
		public final static int MAV_CMD_DO_REPEAT_RELAY=182; //, * Cycle a relay on and off for a desired number of cyles with a desired period. | Relay number | Cycle count | Cycle time (seconds, decimal) | Empty | Empty | Empty | Empty | *
		public final static int MAV_CMD_DO_SET_SERVO=183; //, * Set a servo to a desired PWM value. | Servo number | PWM (microseconds, 1000 to 2000 typical) | Empty | Empty | Empty | Empty | Empty | *
		public final static int MAV_CMD_DO_REPEAT_SERVO=184; //, * Cycle a between its nominal setting and a desired PWM for a desired number of cycles with a desired period. | Servo number | PWM (microseconds, 1000 to 2000 typical) | Cycle count | Cycle time (seconds) | Empty | Empty | Empty | *
		public final static int MAV_CMD_DO_CONTROL_VIDEO=200; //, * Control onboard camera capturing. | Camera ID (-1 for all) | Transmission: 0: disabled, 1: enabled compressed, 2: enabled raw | Transmission mode: 0: video stream, >0: single images every n seconds (decimal) | Recording: 0: disabled, 1: enabled compressed, 2: enabled raw | Empty | Empty | Empty | *
		public final static int MAV_CMD_DO_SET_ROI=201; //, * Sets the region of interest (ROI) for a sensor set or the vehicle itself. This can then be used by the vehicles control system to control the vehicle attitude and the attitude of various devices such as cameras. | Region of interest mode. (see MAV_ROI enum) | Waypoint index
		public final static int MAV_CMD_DO_LAST=240; //, * NOP - This command is only used to mark the upper limit of the DO commands in the enumeration | Empty | Empty | Empty | Empty | Empty | Empty | Empty | *
		public final static int MAV_CMD_PREFLIGHT_CALIBRATION=241; //, * Trigger calibration. This command will be only accepted if in pre-flight mode. | Gyro calibration: 0: no, 1: yes | Magnetometer calibration: 0: no, 1: yes | Ground pressure: 0: no, 1: yes | Radio calibration: 0: no, 1: yes | Empty | Empty | Empty | *
		public final static int MAV_CMD_PREFLIGHT_STORAGE=245; //, * Request storage of different parameter values and logs. This command will be only accepted if in pre-flight mode. | Parameter storage: 0: READ FROM FLASH
		public final static int 	MAV_CMD_ENUM_END = 246; //
	}

	public class MAV_DATA_STREAM{
		public final static int MAV_DATA_STREAM_ALL=0; //, * Enable all data streams | *
		public final static int MAV_DATA_STREAM_RAW_SENSORS=1; //, * Enable IMU_RAW, GPS_RAW, GPS_STATUS packets. | *
		public final static int MAV_DATA_STREAM_EXTENDED_STATUS=2; //, * Enable GPS_STATUS, CONTROL_STATUS, AUX_STATUS | *
		public final static int MAV_DATA_STREAM_RC_CHANNELS=3; //, * Enable RC_CHANNELS_SCALED, RC_CHANNELS_RAW, SERVO_OUTPUT_RAW | *
		public final static int MAV_DATA_STREAM_RAW_CONTROLLER=4; //, * Enable ATTITUDE_CONTROLLER_OUTPUT, POSITION_CONTROLLER_OUTPUT, NAV_CONTROLLER_OUTPUT. | *
		public final static int MAV_DATA_STREAM_POSITION=6; //, * Enable LOCAL_POSITION, GLOBAL_POSITION
		public final static int MAV_DATA_STREAM_EXTRA1=10; //, * Dependent on the autopilot | *
		public final static int MAV_DATA_STREAM_EXTRA2=11; //, * Dependent on the autopilot | *
		public final static int MAV_DATA_STREAM_EXTRA3=12; //, * Dependent on the autopilot | *
		public final static int 	MAV_DATA_STREAM_ENUM_END = 13; //
	}

	public class MAV_ROI{
		public final static int MAV_ROI_NONE=0; //, * No region of interest. | *
		public final static int MAV_ROI_WPNEXT=1; //, * Point toward next waypoint. | *
		public final static int MAV_ROI_WPINDEX=2; //, * Point toward given waypoint. | *
		public final static int MAV_ROI_LOCATION=3; //, * Point toward fixed location. | *
		public final static int MAV_ROI_TARGET=4; //, * Point toward of given id. | *
		public final static int 	MAV_ROI_ENUM_END = 5; //
	}

	public class MAV_CLASS{
		public final static int    MAV_CLASS_GENERIC = 0; //,        
		public final static int    MAV_CLASS_PIXHAWK = 1; //,        
		public final static int    MAV_CLASS_SLUGS = 2; //,          
		public final static int    MAV_CLASS_ARDUPILOTMEGA = 3; //,  
		public final static int MAV_CLASS_OPENPILOT = 4; //,      
		public final static int MAV_CLASS_GENERIC_MISSION_WAYPOINTS_ONLY = 5; //,  
		public final static int MAV_CLASS_GENERIC_MISSION_NAVIGATION_ONLY = 6; //, 
		public final static int MAV_CLASS_GENERIC_MISSION_FULL = 7; //,            
		public final static int MAV_CLASS_NONE = 8; //,           
		public final static int 	MAV_CLASS_NB                   = 9; //
	}

	public class MAV_ACTION{
		public final static int    MAV_ACTION_HOLD = 0; //,
		public final static int    MAV_ACTION_MOTORS_START = 1; //,
		public final static int    MAV_ACTION_LAUNCH = 2; //,
		public final static int    MAV_ACTION_RETURN = 3; //,
		public final static int    MAV_ACTION_EMCY_LAND = 4; //,
		public final static int    MAV_ACTION_EMCY_KILL = 5; //,
		public final static int    MAV_ACTION_CONFIRM_KILL = 6; //,
		public final static int    MAV_ACTION_CONTINUE = 7; //,
		public final static int    MAV_ACTION_MOTORS_STOP = 8; //,
		public final static int    MAV_ACTION_HALT = 9; //,
		public final static int    MAV_ACTION_SHUTDOWN = 10; //,
		public final static int    MAV_ACTION_REBOOT = 11; //,
		public final static int    MAV_ACTION_SET_MANUAL = 12; //,
		public final static int    MAV_ACTION_SET_AUTO = 13; //,
		public final static int    MAV_ACTION_STORAGE_READ = 14; //,
		public final static int    MAV_ACTION_STORAGE_WRITE = 15; //,
		public final static int    MAV_ACTION_CALIBRATE_RC = 16; //,
		public final static int    MAV_ACTION_CALIBRATE_GYRO = 17; //,
		public final static int    MAV_ACTION_CALIBRATE_MAG = 18; //,
		public final static int    MAV_ACTION_CALIBRATE_ACC = 19; //,
		public final static int    MAV_ACTION_CALIBRATE_PRESSURE = 20; //,
		public final static int    MAV_ACTION_REC_START = 21; //,
		public final static int    MAV_ACTION_REC_PAUSE = 22; //,
		public final static int    MAV_ACTION_REC_STOP = 23; //,
		public final static int    MAV_ACTION_TAKEOFF = 24; //,
		public final static int    MAV_ACTION_NAVIGATE = 25; //,
		public final static int    MAV_ACTION_LAND = 26; //,
		public final static int    MAV_ACTION_LOITER = 27; //,
		public final static int    MAV_ACTION_SET_ORIGIN = 28; //,
		public final static int    MAV_ACTION_RELAY_ON = 29; //,
		public final static int    MAV_ACTION_RELAY_OFF = 30; //,
		public final static int    MAV_ACTION_GET_IMAGE = 31; //,
		public final static int    MAV_ACTION_VIDEO_START = 32; //,
		public final static int    MAV_ACTION_VIDEO_STOP = 33; //,
		public final static int    MAV_ACTION_RESET_MAP = 34; //,
		public final static int    MAV_ACTION_RESET_PLAN = 35; //,
		public final static int    MAV_ACTION_DELAY_BEFORE_COMMAND = 36; //,
		public final static int    MAV_ACTION_ASCEND_AT_RATE = 37; //,
		public final static int    MAV_ACTION_CHANGE_MODE = 38; //,
		public final static int    MAV_ACTION_LOITER_MAX_TURNS = 39; //,
		public final static int    MAV_ACTION_LOITER_MAX_TIME = 40; //,
		public final static int MAV_ACTION_START_HILSIM = 41; //,
		public final static int MAV_ACTION_STOP_HILSIM = 42; //,    
		public final static int     MAV_ACTION_NB         = 43; //
	}

	public class MAV_MODE{
		public final static int    MAV_MODE_UNINIT = 0; //,     
		public final static int    MAV_MODE_LOCKED = 1; //,     
		public final static int    MAV_MODE_MANUAL = 2; //,     
		public final static int    MAV_MODE_GUIDED = 3; //,     
		public final static int    MAV_MODE_AUTO =   4; //,     
		public final static int    MAV_MODE_TEST1 =  5; //,     
		public final static int    MAV_MODE_TEST2 =  6; //,     
		public final static int    MAV_MODE_TEST3 =  7; //,     
		public final static int    MAV_MODE_READY =  8; //,     
		public final static int     MAV_MODE_RC_TRAINING = 9 ; //
	}

	public class MAV_STATE{
		public final static int    MAV_STATE_UNINIT = 0; //,
		public final static int    MAV_STATE_BOOT = 1; //,
		public final static int    MAV_STATE_CALIBRATING = 2; //,
		public final static int    MAV_STATE_STANDBY = 3; //,
		public final static int    MAV_STATE_ACTIVE = 4; //,
		public final static int    MAV_STATE_CRITICAL = 5; //,
		public final static int    MAV_STATE_EMERGENCY = 6; //,
		public final static int    MAV_STATE_HILSIM = 7; //,
		public final static int     MAV_STATE_POWEROFF = 8; //
	}

	public class MAV_NAV{
		public final static int    MAV_NAV_GROUNDED = 0; //,
		public final static int    MAV_NAV_LIFTOFF = 1; //,
		public final static int    MAV_NAV_HOLD = 2; //,
		public final static int    MAV_NAV_WAYPOINT = 3; //,
		public final static int    MAV_NAV_VECTOR = 4; //,
		public final static int    MAV_NAV_RETURNING = 5; //,
		public final static int    MAV_NAV_LANDING = 6; //,
		public final static int    MAV_NAV_LOST = 7; //,
		public final static int    MAV_NAV_LOITER = 8; //,
		public final static int 	MAV_NAV_FREE_DRIFT = 9; //
	}

	public class MAV_TYPE{
		public final static int    MAV_GENERIC = 0; //,
		public final static int    MAV_FIXED_WING = 1; //,
		public final static int    MAV_QUADROTOR = 2; //,
		public final static int    MAV_COAXIAL = 3; //,
		public final static int    MAV_HELICOPTER = 4; //,
		public final static int    MAV_GROUND = 5; //,
		public final static int    OCU = 6; //,
		public final static int MAV_AIRSHIP = 7; //,
		public final static int MAV_FREE_BALLOON = 8; //,
		public final static int MAV_ROCKET = 9; //,
		public final static int UGV_GROUND_ROVER = 10; //,
		public final static int UGV_SURFACE_SHIP = 11; //
	}

	public class MAV_AUTOPILOT_TYPE{
		public final static int    MAV_AUTOPILOT_GENERIC = 0; //,
		public final static int    MAV_AUTOPILOT_PIXHAWK = 1; //,
		public final static int    MAV_AUTOPILOT_SLUGS = 2; //,
		public final static int    MAV_AUTOPILOT_ARDUPILOTMEGA = 3; //,
		public final static int MAV_AUTOPILOT_NONE = 4; //
	}

	public class MAV_COMPONENT{
		public final static int    MAV_COMP_ID_GPS = 0; //,
		public final static int    MAV_COMP_ID_WAYPOINTPLANNER = 1; //,
		public final static int    MAV_COMP_ID_BLOBTRACKER = 2; //,
		public final static int    MAV_COMP_ID_PATHPLANNER = 3; //,
		public final static int    MAV_COMP_ID_AIRSLAM = 4; //,
		public final static int    MAV_COMP_ID_MAPPER = 5; //,
		public final static int    MAV_COMP_ID_CAMERA = 6; //,
		public final static int    MAV_COMP_ID_IMU = 200; //,
		public final static int MAV_COMP_ID_IMU_2 = 201; //,
		public final static int MAV_COMP_ID_IMU_3 = 202; //,
		public final static int    MAV_COMP_ID_UDP_BRIDGE = 240; //,
		public final static int    MAV_COMP_ID_UART_BRIDGE = 241; //,
		public final static int     MAV_COMP_ID_SYSTEM_CONTROL = 250; //
	}

	public class MAV_FRAME{
		public final static int    MAV_FRAME_GLOBAL = 0; //,
		public final static int    MAV_FRAME_LOCAL = 1; //,
		public final static int    MAV_FRAME_MISSION = 2; //,
		public final static int MAV_FRAME_GLOBAL_RELATIVE_ALT = 3; //,
		public final static int         MAV_FRAME_LOCAL_ENU = 4; //
	}

	public class MAVLINK_DATA_STREAM_TYPE{
		public final static int    MAVLINK_DATA_STREAM_IMG_JPEG = 0; //,
		public final static int MAVLINK_DATA_STREAM_IMG_BMP = 1; //,
		public final static int MAVLINK_DATA_STREAM_IMG_RAW8U = 2; //,
		public final static int MAVLINK_DATA_STREAM_IMG_RAW32U = 3; //,
		public final static int MAVLINK_DATA_STREAM_IMG_PGM = 4; //,
		public final static int 	MAVLINK_DATA_STREAM_IMG_PNG = 5; //
	}

	public static String getMavCmd(int a ){
		return getMAVfield(MAV_CMD.class, a);
	
	}

	public static String getMode(int i){
		return getMAVfield(MAV_MODE.class, i);

	}

	public static String getState(int i){
		return getMAVfield(MAV_STATE.class, i);

	}

	public static String getMavFrame(int a ){
		return getMAVfield(MAV_FRAME.class, a);

	}

	public static String getNav(int i){

		String s = getMAVfield(MAV_NAV.class, i);
		String ret = "";

		if( !s.equals("")){
			s.replace("MAV_NAV_", "");
			ret = "MAV " + s.charAt(0);

			s = s.substring(1);
			ret += s.toLowerCase();

		}

		return ret;

	}
	public static String getMAVfield(Class<?> cls, int a ){
		Field[] field = cls.getFields();

		try {
			for (Field f : field) {
				if (f.getInt(null) == a)
					return f.getName();

			}
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "" + a;
	}
}
