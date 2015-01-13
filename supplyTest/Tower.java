package supplyTest;

import battlecode.common.RobotController;

public class Tower extends Entity {

	public static void run(RobotController rc) {
		while(true) {
			try {					
				if(rc.isWeaponReady()) {
					attackSomething(rc);
				}
				if(rc.isCoreReady()) {
					shareSupply(rc);
				}
			} catch (Exception e) {
				System.out.println("Tower Exception");
	            e.printStackTrace();
			}
			rc.yield();
		}
	}
	
}
