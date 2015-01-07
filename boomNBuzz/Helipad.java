package boomNBuzz;

import battlecode.common.RobotController;
import battlecode.common.RobotType;

public class Helipad extends Entity {

	public static void run(RobotController rc) {
		while(true) {
			try {
				int numDrones = rc.readBroadcast(Status.numDroneChannel);
				int numLaunchers = rc.readBroadcast(Status.numLauncherChannel);
				shareSupply(rc);
//				if(rc.isCoreReady() && (numDrones-numLaunchers) < 10) {
//					if(rc.getTeamOre() >= RobotType.DRONE.oreCost) {
//						trySpawn(Status.directions[Status.rand.nextInt(8)], RobotType.DRONE, rc);
//					}
//				}
			} catch (Exception e) {
				System.out.println("Helipad Exception");
	            e.printStackTrace();
			}
			rc.yield();
		}
	}
	
}
