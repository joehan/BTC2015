package zergRushBase;

import battlecode.common.RobotController;

public class Missile extends Entity {

	public static void run(RobotController rc) {
		while(true) {
			try {
				//TODO: Missile
			} catch (Exception e) {
				System.out.println("Missile Exception");
	            e.printStackTrace();
			}
			rc.yield();
		}
	}
	
}
