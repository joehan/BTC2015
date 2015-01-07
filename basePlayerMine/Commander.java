package basePlayerMine;

import battlecode.common.RobotController;

public class Commander extends Entity {

	public static void run(RobotController rc) {
		while(true) {
			try {
				//TODO: Commander
			} catch (Exception e) {
				System.out.println("Commander Exception");
	            e.printStackTrace();
			}
			rc.yield();
		}
	}
	
}
