package robotHunting;

import battlecode.common.RobotController;
import battlecode.common.RobotType;

public class Helipad extends Entity {

	public static void run(RobotController rc) {
		while(true) {
			try {
				if(rc.isCoreReady()) {
					if(rc.getTeamOre() >= RobotType.DRONE.oreCost && rc.readBroadcast(Status.numHelipadChannel) >= 3) {
						trySpawn(Status.directions[Status.rand.nextInt(8)], RobotType.DRONE, rc);
					}
				}
			} catch (Exception e) {
				System.out.println("Helipad Exception");
	            e.printStackTrace();
			}
			rc.yield();
		}
	}
	
}
