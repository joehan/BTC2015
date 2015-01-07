package basePlayer;

import battlecode.common.RobotController;

public class Soldier extends Entity {

	public static void run(RobotController rc) {
		while(true) {
			try {
	            //TODO: Soldier
	        } catch (Exception e) {
				System.out.println("Soldier Exception");
				e.printStackTrace();
	        }
			rc.yield();
		}
	}
	
}
