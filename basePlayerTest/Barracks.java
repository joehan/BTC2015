package basePlayerTest;

import battlecode.common.RobotController;
import battlecode.common.RobotType;

public class Barracks extends Entity {

	public static void run(RobotController rc) {
		while(true) {
			try {
				if(rc.isCoreReady()) {
					int fate = Status.rand.nextInt(5);
					double ore = rc.getTeamOre();
					if (rc.readBroadcast(Status.commanderTrainingChannel) == 1) {
						if(fate < 0 && ore >= RobotType.SOLDIER.oreCost) {
							trySpawn(Status.directions[Status.rand.nextInt(8)], RobotType.SOLDIER, rc);
						} else if(ore >= RobotType.BASHER.oreCost) {
							trySpawn(Status.directions[Status.rand.nextInt(8)], RobotType.BASHER, rc);
						}
					}
				}
			} catch (Exception e) {
				System.out.println("Barracks Exception");
				e.printStackTrace();
			}
			rc.yield();
		}
	}
	
}
