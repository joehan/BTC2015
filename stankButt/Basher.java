package stankButt;

import battlecode.common.RobotController;

public class Basher extends Entity {

	public static void run(RobotController rc) {
		while(true) {
			try {
				if(rc.isCoreReady()) {
	            	int tanks = rc.readBroadcast(Status.numTankChannel);
	            	int bashers = rc.readBroadcast(Status.numBasherChannel);
	            	if(bashers > 30 || rc.readBroadcast(Status.rushChannel) == 1) {
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
				System.out.println("Basher Exception");
				e.printStackTrace();
	        }
			rc.yield();
		}
	}
	
}
