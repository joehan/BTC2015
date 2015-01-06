package basePlayer;

import battlecode.common.RobotController;

public class HQ extends Entity {

	public static void run(RobotController rc) {
		try {					
			countUnitsAndBroadcast(rc, rc.getTeam());
		} catch (Exception e) {
			System.out.println("HQ Exception");
			e.printStackTrace();
		}
	}

}
