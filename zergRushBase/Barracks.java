package zergRushBase;

import battlecode.common.RobotController;
import battlecode.common.RobotType;

public class Barracks extends Entity {

	public static void run(RobotController rc) {
		while(true) {
			try {
				int numBarracks = rc.readBroadcast(Status.numBarracksChannel);
				
				if (numBarracks > 3) {
					if (rc.isCoreReady() && rc.getTeamOre() >= 100) {
						trySpawn(Status.directions[Status.rand.nextInt(8)],RobotType.SOLDIER, rc);
					}
				}
			} catch (Exception e) {
				System.out.println("Barracks Exception");
				e.printStackTrace();
			}
			rc.yield();
		}
	}
	
}
