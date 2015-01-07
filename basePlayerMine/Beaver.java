package basePlayerMine;

import battlecode.common.RobotController;
import battlecode.common.RobotType;

public class Beaver extends Entity {

	public static void run(RobotController rc) {
		while(true) {
			try {
				if (rc.isWeaponReady()) {
					attackSomething(rc);
				}
				if (rc.isCoreReady()) {
					int factories = rc.readBroadcast(Status.numMinerFactoryChannel);
					if (rc.getTeamOre() >= RobotType.MINERFACTORY.oreCost && factories < 1) {
						tryBuild(Status.directions[Status.rand.nextInt(8)],RobotType.MINERFACTORY, rc);
					} else {
						mine(rc);
					}
				}
			} catch (Exception e) {
				System.out.println("Beaver Exception");
	            e.printStackTrace();
			}
			rc.yield();
		}
	}
}