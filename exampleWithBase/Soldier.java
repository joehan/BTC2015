package exampleWithBase;

import battlecode.common.RobotController;

public class Soldier {

	public static void run(RobotController rc) {
		try {
            if (rc.isWeaponReady()) {
				Status.attackSomething(rc);
			}
			if (rc.isCoreReady()) {
				int fate = Status.rand.nextInt(1000);
				if (fate < 800) {
					Status.tryMove(Status.directions[Status.rand.nextInt(8)], rc);
				} else {
					Status.tryMove(rc.getLocation().directionTo(rc.senseEnemyHQLocation()), rc);
				}
			}
        } catch (Exception e) {
			System.out.println("Soldier Exception");
			e.printStackTrace();
        }
	}
	
}
