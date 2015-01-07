package robotHunting;

import battlecode.common.RobotController;
import battlecode.common.RobotType;

public class Drone extends Entity {

	public static void run(RobotController rc) {
		RobotType[] mpri = {RobotType.TOWER, RobotType.SOLDIER, RobotType.BASHER, RobotType.BEAVER};
		while(true) {
			try {
				if(rc.isWeaponReady()) {
					huntAttackList(mpri, rc);
				}
				if(rc.isCoreReady()) {
					if(!huntList(mpri, rc)) {
						tryMove(Status.directions[Status.rand.nextInt(8)], rc);
					}
				}
			} catch (Exception e) {
				System.out.println("Drone Exception");
	            e.printStackTrace();
			}
			rc.yield();
		}
	}
	
}
