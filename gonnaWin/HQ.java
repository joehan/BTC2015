package gonnaWin;

import battlecode.common.RobotController;
import battlecode.common.RobotType;

public class HQ extends Entity {

	public static void run(RobotController rc) {
		while(true) {
			try {
				countUnitsAndBroadcast(rc, rc.getTeam());
				if (rc.isWeaponReady()) {
					attackSomething(rc);
				}
				
				

				if (rc.isCoreReady()){
					if (rc.getTeamOre() >= RobotType.BEAVER.oreCost
						&&  rc.readBroadcast(Status.numBeaverChannel) < 3) {
						trySpawn(Status.directions[Status.rand.nextInt(8)], RobotType.BEAVER, rc);
					}
					shareSupply(rc);
				}
			} catch (Exception e) {
				System.out.println("HQ Exception");
				e.printStackTrace();
			}
			rc.yield();
		}
	}

}
