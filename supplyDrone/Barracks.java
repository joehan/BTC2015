package supplyDrone;

import battlecode.common.RobotController;

public class Barracks extends Entity {

	public static void run(RobotController rc) {
		while(true) {
			try {
				//TODO: Barracks
			} catch (Exception e) {
				System.out.println("Barracks Exception");
				e.printStackTrace();
			}
			rc.yield();
		}
	}
	
}
