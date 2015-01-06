package exampleWithBase;

import battlecode.common.RobotController;
import battlecode.common.RobotInfo;

public class Basher {

	public static void run(RobotController rc) {
		try {
            RobotInfo[] adjacentEnemies = rc.senseNearbyRobots(2, Status.enemyTeam);

            // BASHERs attack automatically, so let's just move around mostly randomly
			if (rc.isCoreReady()) {
				int fate = Status.rand.nextInt(1000);
				if (fate < 800) {
					Status.tryMove(Status.directions[Status.rand.nextInt(8)], rc);
				} else {
					Status.tryMove(rc.getLocation().directionTo(rc.senseEnemyHQLocation()), rc);
				}
			}
        } catch (Exception e) {
			System.out.println("Basher Exception");
			e.printStackTrace();
        }
	}
	
}
