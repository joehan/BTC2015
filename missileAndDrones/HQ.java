package missileAndDrones;

import battlecode.common.RobotController;
import battlecode.common.RobotType;

public class HQ extends Entity {

	public static void run(RobotController rc) {
		while(true) {
			try {
				if (rc.isCoreReady()) {
					int fate = Status.rand.nextInt(10000);
					countUnitsAndBroadcast(rc, rc.getTeam());
					if (rc.isWeaponReady()) {
						attackSomething(rc);
					}
	
					if (rc.isCoreReady() 
							&& rc.getTeamOre() >= 100 
							&& fate < Math.pow(1.2,12-rc.readBroadcast(Status.numBeaverChannel))*10000 
							&& (rc.readBroadcast(Status.numMinerFactoryChannel) < 1 
									|| rc.readBroadcast(Status.numBeaverChannel) < 5)) {
						trySpawn(Status.directions[Status.rand.nextInt(8)], RobotType.BEAVER, rc);
					}
				}
			} catch (Exception e) {
				System.out.println("HQ Exception");
				e.printStackTrace();
			}
			rc.yield();
		}
	}

}
