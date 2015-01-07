package zergRushBase;

import battlecode.common.RobotController;

public class Tank extends Entity {

	public static void run(RobotController rc) {
		while(true) {
			try {
				//TODO: Tank
			} catch (Exception e) {
				System.out.println("Tank Exception");
	            e.printStackTrace();
			}
			rc.yield();
		}
	}
	
}
