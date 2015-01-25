package basePlayerTest;

import battlecode.common.RobotController;

public class Basher extends Entity {

	public static void run(RobotController rc) {
		while(true) {
			try {
				soldierRush(rc);
	        } catch (Exception e) {
				System.out.println("Basher Exception");
				e.printStackTrace();
	        }
			rc.yield();
		}
	}
	
}
