package basePlayerTest;

import battlecode.common.RobotController;
import battlecode.common.RobotType;

public class TankFactory extends Entity {

	public static void run(RobotController rc) {
		while(true) {
			try {
				if(rc.isCoreReady()) {
					int tanks = rc.readBroadcast(Status.numTankChannel);
					if(rc.getTeamOre() >= RobotType.TANK.oreCost) {
						trySpawn(Status.directions[Status.rand.nextInt(8)], RobotType.TANK, rc);
					}
					shareSupply(rc);
				}
			} catch (Exception e) {
				System.out.println("TankFactory Exception");
	            e.printStackTrace();
			}
			rc.yield();
		}
	}
	
}
