package zergRushBase;

import battlecode.common.RobotController;

public class Soldier extends Entity {

	public static void run(RobotController rc) {
		while(true) {
			try {
				if (rc.isWeaponReady()) {
					attackSomething(rc);
				}
				if (rc.isCoreReady()) {
					int numSoldiers = rc.readBroadcast(Status.numSoldierChannel);
					if (numSoldiers < 70) {
						tryMove(Status.directions[Status.rand.nextInt(8)], rc);
					} else {
						tryMove(rc.getLocation().directionTo(rc.senseEnemyHQLocation()), rc);
					}
				}
	        } catch (Exception e) {
				System.out.println("Soldier Exception");
				e.printStackTrace();
	        }
			rc.yield();
		}
	}
	
}
