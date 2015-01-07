package zergRushBase;

import battlecode.common.RobotController;

public class Launcher extends Entity {

	public static void run(RobotController rc) {
		while(true) {
			try {
				//TODO: Launcher
			} catch (Exception e) {
				System.out.println("Launcher Exception");
	            e.printStackTrace();
			}
			rc.yield();
		}
	}
	
}
