package basePlayerTest;

import battlecode.common.RobotController;

public class Drone extends Entity {

	public static void run(RobotController rc) {
		while(true) {
			try {
				//TODO: Drone
			} catch (Exception e) {
				System.out.println("Drone Exception");
	            e.printStackTrace();
			}
			rc.yield();
		}
	}
	
}
