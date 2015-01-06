package BasePlayer;

import battlecode.common.RobotController;
import battlecode.common.RobotType;

public class Barracks {

	public static void run(RobotController rc) {
		try {
			int fate = Status.rand.nextInt(10000);
			
            // get information broadcasted by the HQ
			int numBeavers = rc.readBroadcast(0);
			int numSoldiers = rc.readBroadcast(1);
			int numBashers = rc.readBroadcast(2);
			
			if (rc.isCoreReady() && rc.getTeamOre() >= 60 && fate < Math.pow(1.2,15-numSoldiers-numBashers+numBeavers)*10000) {
				if (rc.getTeamOre() > 80 && fate % 2 == 0) {
					Status.trySpawn(Status.directions[Status.rand.nextInt(8)],RobotType.BASHER, rc);
				} else {
					Status.trySpawn(Status.directions[Status.rand.nextInt(8)],RobotType.SOLDIER, rc);
				}
			}
		} catch (Exception e) {
			System.out.println("Barracks Exception");
            e.printStackTrace();
		}
	}
	
}
