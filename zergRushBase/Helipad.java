package zergRushBase;

import battlecode.common.RobotController;

public class Helipad extends Entity {

	public static void run(RobotController rc) {
		while(true) {
			try {
				//TODO: Helipad
			} catch (Exception e) {
				System.out.println("Helipad Exception");
	            e.printStackTrace();
			}
			rc.yield();
		}
	}
	
}
