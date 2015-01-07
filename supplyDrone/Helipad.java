package supplyDrone;

import battlecode.common.RobotController;
import battlecode.common.RobotType;

public class Helipad extends Entity {

	public static void run(RobotController rc) {
		while(true) {
			try {
				int numDrones = rc.readBroadcast(Status.numDroneChannel);
				if(rc.isCoreReady() && rc.getTeamOre() >= RobotType.DRONE.oreCost && numDrones < 1) {
					trySpawn(Status.directions[Status.rand.nextInt(8)], RobotType.DRONE, rc);
				}
			} catch (Exception e) {
				System.out.println("Helipad Exception");
	            e.printStackTrace();
			}
			rc.yield();
		}
	}
	
}
