package zergRushBase;

import battlecode.common.RobotController;

public class Miner extends Entity {

	public static void run(RobotController rc) {
		while(true) {
			try {
				if (rc.isCoreReady()) {
					mine(rc);
				}
			} catch (Exception e) {
				System.out.println("Miner Exception");
	            e.printStackTrace();
			}
			rc.yield();
		}
	}
	
}
