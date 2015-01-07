package basePlayerMine;

import battlecode.common.RobotController;

public class HandWashStation extends Entity {

	public static void run(RobotController rc) {
		while(true) {
			try {
				//TODO: HandWashStation
			} catch (Exception e) {
				System.out.println("HandWashStation Exception");
	            e.printStackTrace();
			}
			rc.yield();
		}
	}
	
}
