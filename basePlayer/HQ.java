package basePlayer;

import battlecode.common.RobotController;
import battlecode.common.RobotInfo;
import battlecode.common.RobotType;

public class HQ {

	public static void run(RobotController rc) {
		RobotInfo[] myRobots;
		try {					
			int fate = Status.rand.nextInt(10000);
			myRobots = rc.senseNearbyRobots(999999, Status.myTeam);
			int numSoldiers = 0;
			int numBashers = 0;
			int numBeavers = 0;
			int numBarracks = 0;
			for (RobotInfo r : myRobots) {
				RobotType type = r.type;
				if (type == RobotType.SOLDIER) {
					numSoldiers++;
				} else if (type == RobotType.BASHER) {
					numBashers++;
				} else if (type == RobotType.BEAVER) {
					numBeavers++;
				} else if (type == RobotType.BARRACKS) {
					numBarracks++;
				}
			}
			rc.broadcast(0, numBeavers);
			rc.broadcast(1, numSoldiers);
			rc.broadcast(2, numBashers);
			rc.broadcast(100, numBarracks);
			
			if (rc.isWeaponReady()) {
				Status.attackSomething(rc);
			}

			if (rc.isCoreReady() && rc.getTeamOre() >= 100 && fate < Math.pow(1.2,12-numBeavers)*10000) {
				Status.trySpawn(Status.directions[Status.rand.nextInt(8)], RobotType.BEAVER, rc);
			}
		} catch (Exception e) {
			System.out.println("HQ Exception");
            e.printStackTrace();
		}
	}
	
}
