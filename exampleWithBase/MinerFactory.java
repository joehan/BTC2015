package exampleWithBase;

import battlecode.common.RobotController;
import battlecode.common.RobotType;

public class MinerFactory {

	public static void run(RobotController rc) {
		try {
			if(rc.isCoreReady() && rc.getTeamOre() >= 50 && rc.readBroadcast(3) <= 10) {
				Status.trySpawn(Status.directions[Status.rand.nextInt(8)], RobotType.MINER, rc);
			}
		} catch (Exception e) {
			System.out.println("MinerFactory Exception");
            e.printStackTrace();
		}
	}
	
}
