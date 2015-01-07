package robotHunting;

import battlecode.common.RobotController;
import battlecode.common.RobotType;

public class Basher extends Entity {

	public static void run(RobotController rc) {
		while(true) {
			try {
	            if(rc.isCoreReady()) {
	            	hunt(RobotType.BEAVER, rc);
	            }
	        } catch (Exception e) {
				System.out.println("Basher Exception");
				e.printStackTrace();
	        }
			rc.yield();
		}
	}
	
}
