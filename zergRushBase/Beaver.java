package zergRushBase;

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
					int depots = rc.readBroadcast(Status.numSupplyDepotChannel);
					int barracks = rc.readBroadcast(Status.numBarracksChannel);
					if (rc.getTeamOre() >= 300 && barracks <= 3) {
						if (depots < 3) {
							tryBuild(Status.directions[Status.rand.nextInt(8)],RobotType.SUPPLYDEPOT, rc);
						} else {
							tryBuild(Status.directions[Status.rand.nextInt(8)],RobotType.BARRACKS, rc);
						}
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