package supplyTest;

import battlecode.common.RobotController;
import battlecode.common.RobotType;

public class Helipad extends Entity {

	public static void run(RobotController rc) {
		while(true) {
			try {
				int numDrones = rc.readBroadcast(Status.numDroneChannel);
				
				if(rc.isCoreReady()) {
					if(numDrones < 1 && rc.getTeamOre() <= RobotType.DRONE.oreCost) {
						trySpawn(Status.directions[Status.rand.nextInt(8)], RobotType.DRONE, rc);
					}
					shareSupply(rc);
				}
			} catch (Exception e) {
				System.out.println("Helipad Exception");
	            e.printStackTrace();
			}
			rc.yield();
		}
	}
	
}
