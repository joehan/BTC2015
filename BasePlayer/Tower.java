package BasePlayer;

import battlecode.common.RobotController;

public class Tower {

	public static void run(RobotController rc) {
		try {					
			if (rc.isWeaponReady()) {
				Status.attackSomething(rc);
			}
		} catch (Exception e) {
			System.out.println("Tower Exception");
            e.printStackTrace();
		}
	}
	
}
