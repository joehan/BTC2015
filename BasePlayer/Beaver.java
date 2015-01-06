package BasePlayer;

import battlecode.common.RobotController;
import battlecode.common.RobotType;

public class Beaver {

	public static void run(RobotController rc) {
		try {
			if (rc.isWeaponReady()) {
				Status.attackSomething(rc);
			}
			if (rc.isCoreReady()) {
				int fate = Status.rand.nextInt(1000);
				if (fate < 8 && rc.getTeamOre() >= 300) {
					Status.tryBuild(Status.directions[Status.rand.nextInt(8)],RobotType.BARRACKS, rc);
				} else if (fate < 600) {
					rc.mine();
				} else if (fate < 900) {
					Status.tryMove(Status.directions[Status.rand.nextInt(8)], rc);
				} else {
					Status.tryMove(rc.senseHQLocation().directionTo(rc.getLocation()), rc);
				}
			}
		} catch (Exception e) {
			System.out.println("Beaver Exception");
            e.printStackTrace();
		}
	}
	
}
