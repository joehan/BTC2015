package tankRush;

import battlecode.common.RobotController;

public class Soldier extends Entity {

	public static void run(RobotController rc) {
		while(true) {
			try {
				if(rc.isWeaponReady()) {
					huntAttackList(Status.order, rc);
				}
				if(rc.isCoreReady()) {
	            	int tanks = rc.readBroadcast(Status.numTankChannel);
	            	if(tanks > 4 || rc.readBroadcast(Status.rushChannel) == 1) {
	            		if(!huntList(Status.order, rc)) {
	            			tryMove(rc.getLocation().directionTo(rc.senseEnemyHQLocation()), rc);
	            		}
	            		rc.broadcast(Status.rushChannel, 1);
	            	} else {
	            		tryMove(rc.getLocation().directionTo(rc.senseHQLocation()), rc);
	            	}
	            	shareSupply(rc);
	            }
	        } catch (Exception e) {
				System.out.println("Soldier Exception");
				e.printStackTrace();
	        }
			rc.yield();
		}
	}
	
}
