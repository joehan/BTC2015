package basePlayer;

import battlecode.common.RobotController;

public class Beaver extends Entity {

	public static void run(RobotController rc) {
		while(true) {
			try {
				mine(rc);
			} catch (Exception e) {
				System.out.println("Beaver Exception");
	            e.printStackTrace();
			}
			rc.yield();
		}
	}
	
}
