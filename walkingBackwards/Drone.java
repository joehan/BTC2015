package walkingBackwards;

import battlecode.common.RobotController;
import battlecode.common.RobotType;

public class Drone extends Entity {

	public static void run(RobotController rc) {
		RobotType[] mpri = {RobotType.LAUNCHER,   RobotType.BEAVER, RobotType.BASHER,RobotType.SOLDIER,};
		RobotType[] attackList = {RobotType.TOWER, RobotType.HQ, RobotType.LAUNCHER, RobotType.BASHER,RobotType.SOLDIER,  RobotType.BEAVER};
		
		while(true) {
			try {
				if(rc.isWeaponReady()) {
					huntAttackList(attackList, rc);
				}
				if(rc.isCoreReady()) {
					int numDrones = rc.readBroadcast(Status.numDroneChannel);
					if (numDrones > 10){
						if(!huntListSafe(mpri, rc)) {
							tryMove(Status.directions[Status.rand.nextInt(8)], rc);
						}
					} else {
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
