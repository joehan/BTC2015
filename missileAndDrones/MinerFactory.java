package missileAndDrones;

import battlecode.common.RobotController;
import battlecode.common.RobotType;

public class MinerFactory extends Entity {

	public static void run(RobotController rc) {
		while(true) {
			try {
				if (rc.isCoreReady()) {
					int numFactories = rc.readBroadcast(Status.numMinerFactoryChannel);
					int numMiners = rc.readBroadcast(Status.numMinerChannel);
					if (numFactories >= 1 && rc.getTeamOre() >= RobotType.MINER.oreCost && numMiners < 10) {
						trySpawn(Status.directions[Status.rand.nextInt(8)],RobotType.MINER, rc);
					}
				}
			} catch (Exception e) {
				System.out.println("MinerFactory Exception");
	            e.printStackTrace();
			}
			rc.yield();
		}
	}
	
}
