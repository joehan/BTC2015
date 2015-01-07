package basePlayer;

import battlecode.common.RobotController;

public class Beaver extends Entity {

	public static void run(RobotController rc) {
		while(true) {
			try {
				if (rc.isCoreReady()) {
					mine(rc);
				}
			} catch (Exception e) {
				System.out.println("Beaver Exception");
	            e.printStackTrace();
			}
			rc.yield();
		}
	}
	
}
