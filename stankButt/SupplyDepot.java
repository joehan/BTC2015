package stankButt;

import battlecode.common.RobotController;

public class SupplyDepot extends Entity {

	public static void run(RobotController rc) {
		while(true) {
			try {
				shareSupply(rc);
			} catch (Exception e) {
				System.out.println("SupplyDepot Exception");
	            e.printStackTrace();
			}
			rc.yield();
		}
	}
	
}
