package basePlayerTest;

import battlecode.common.RobotController;
import battlecode.common.RobotType;

public class TrainingField extends Entity {

	public static void run(RobotController rc) {
		while(true) {
			try {
				double ore = rc.getTeamOre();
				if (ore >= RobotType.COMMANDER.oreCost && rc.readBroadcast(Status.numCommanderChannel) < 1) {
					rc.broadcast(Status.commanderTrainingChannel, 1);
					trySpawn(Status.directions[Status.rand.nextInt(8)], RobotType.COMMANDER, rc);
				}
			} catch (Exception e) {
				System.out.println("TrainingField Exception");
	            e.printStackTrace();
			}
			rc.yield();
		}
	}
	
}
