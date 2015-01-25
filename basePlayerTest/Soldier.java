package basePlayerTest;

import battlecode.common.RobotController;

public class Soldier extends Entity {

	public static void run(RobotController rc) {
		while(true) {
			try {
				if(rc.isWeaponReady()) {
					huntAttackList(Status.order, rc);
				}
				soldierRush(rc);
	        } catch (Exception e) {
				System.out.println("Soldier Exception");
				e.printStackTrace();
	        }
			rc.yield();
		}
	}
	
}
