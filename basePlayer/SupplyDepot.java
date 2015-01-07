package basePlayer;

import battlecode.common.RobotController;

public class SupplyDepot extends Entity {

	public static void run(RobotController rc) {
		while(true) {
			try {
				//TODO: SupplyDepot
			} catch (Exception e) {
				System.out.println("SupplyDepot Exception");
	            e.printStackTrace();
			}
			rc.yield();
		}
	}
	
}
