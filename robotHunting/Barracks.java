package robotHunting;

import battlecode.common.RobotController;
import battlecode.common.RobotType;

public class Barracks extends Entity {

	public static void run(RobotController rc) {
		while(true) {
			try {
				if(rc.isCoreReady()) {
					if(rc.getTeamOre() >= RobotType.BASHER.oreCost) {
						trySpawn(Status.directions[Status.rand.nextInt(8)], RobotType.BASHER, rc);
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
