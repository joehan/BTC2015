package basePlayer;

import battlecode.common.RobotController;

public class Basher extends Entity {

	public static void run(RobotController rc) {
		while(true) {
			try {
	            //TODO: Basher
	        } catch (Exception e) {
				System.out.println("Basher Exception");
				e.printStackTrace();
	        }
			rc.yield();
		}
	}
	
}
