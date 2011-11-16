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
		public final static int MAV_DATA_STREAM_ENUM_END=13; //, *  | *
	}

	public class MAV_ROI{
		public final static int MAV_ROI_NONE=0; //, * No region of interest. | *
		public final static int MAV_ROI_WPNEXT=1; //, * Point toward next waypoint. | *
		public final static int MAV_ROI_WPINDEX=2; //, * Point toward given waypoint. | *
		public final static int MAV_ROI_LOCATION=3; //, * Point toward fixed location. | *
		public final static int MAV_ROI_TARGET=4; //, * Point toward of given id. | *
		public final static int MAV_ROI_ENUM_END=5; //, *  | *
	}

	public class MAV_CLASS{
		public final static int    MAV_CLASS_GENERIC = 0; //,        
		public final static int    MAV_CLASS_PIXHAWK = 1; //,        
		public final static int    MAV_CLASS_SLUGS = 2; //,          
		public final static int    MAV_CLASS_ARDUPILOTMEGA = 3; //,  
		public final static int    MAV_CLASS_OPENPILOT = 4; //,      
		public final static int    MAV_CLASS_GENERIC_MISSION_WAYPOINTS_ONLY = 5; //,  
		public final static int    MAV_CLASS_GENERIC_MISSION_NAVIGATION_ONLY = 6; //, 
		public final static int    MAV_CLASS_GENERIC_MISSION_FULL = 7; //,            
		public final static int    MAV_CLASS_NONE = 8; //,           
		public final static int     MAV_CLASS_NB                   = 9; //
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
		public final static int    MAV_ACTION_START_HILSIM = 41; //,
		public final static int    MAV_ACTION_STOP_HILSIM = 42; //,    
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
		public final static int     MAV_NAV_FREE_DRIFT = 9; //
	}

	public class MAV_TYPE{
		public final static int    MAV_GENERIC = 0; //,
		public final static int    MAV_FIXED_WING = 1; //,
		public final static int    MAV_QUADROTOR = 2; //,
		public final static int    MAV_COAXIAL = 3; //,
		public final static int    MAV_HELICOPTER = 4; //,
		public final static int    MAV_GROUND = 5; //,
		public final static int    OCU = 6; //,
		public final static int    MAV_AIRSHIP = 7; //,
		public final static int    MAV_FREE_BALLOON = 8; //,
		public final static int    MAV_ROCKET = 9; //,
		public final static int    UGV_GROUND_ROVER = 10; //,
		public final static int     UGV_SURFACE_SHIP = 11; //
	}

	public class MAV_AUTOPILOT_TYPE{
		public final static int    MAV_AUTOPILOT_GENERIC = 0; //,
		public final static int    MAV_AUTOPILOT_PIXHAWK = 1; //,
		public final static int    MAV_AUTOPILOT_SLUGS = 2; //,
		public final static int    MAV_AUTOPILOT_ARDUPILOTMEGA = 3; //,
		public final static int     MAV_AUTOPILOT_NONE = 4; //
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
		public final static int    MAV_COMP_ID_IMU_2 = 201; //,
		public final static int    MAV_COMP_ID_IMU_3 = 202; //,
		public final static int    MAV_COMP_ID_UDP_BRIDGE = 240; //,
		public final static int    MAV_COMP_ID_UART_BRIDGE = 241; //,
		public final static int     MAV_COMP_ID_SYSTEM_CONTROL = 250; //
	}

	public class MAV_FRAME{
		public final static int    MAV_FRAME_GLOBAL = 0; //,
		public final static int    MAV_FRAME_LOCAL = 1; //,
		public final static int    MAV_FRAME_MISSION = 2; //,
		public final static int    MAV_FRAME_GLOBAL_RELATIVE_ALT = 3; //,
		public final static int     MAV_FRAME_LOCAL_ENU = 4; //
	}

	public class MAVLINK_DATA_STREAM_TYPE{
		public final static int    MAVLINK_DATA_STREAM_IMG_JPEG = 0; //,
		public final static int    MAVLINK_DATA_STREAM_IMG_BMP = 1; //,
		public final static int    MAVLINK_DATA_STREAM_IMG_RAW8U = 2; //,
		public final static int    MAVLINK_DATA_STREAM_IMG_RAW32U = 3; //,
		public final static int    MAVLINK_DATA_STREAM_IMG_PGM = 4; //,
		public final static int     MAVLINK_DATA_STREAM_IMG_PNG = 5; //
	}

	public static String getMavCmd(int a ){
		return "Needs Fixing!"; 		//return getMAVfield(MAV_CMD.class, a);
	
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
