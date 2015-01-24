package gonnaWin;

import battlecode.common.Direction;
import battlecode.common.MapLocation;
import battlecode.common.RobotController;
import battlecode.common.RobotType;

public class Basher extends Entity {

	public static void run(RobotController rc) {
		RobotType[] mpri = {RobotType.TOWER, RobotType.BARRACKS, RobotType.HQ, RobotType.LAUNCHER, 
							RobotType.BEAVER, RobotType.BASHER,RobotType.SOLDIER};
		
		RobotType[] attackList = {RobotType.TOWER, RobotType.BARRACKS, 
				RobotType.MINERFACTORY, RobotType.MINER, RobotType.TANKFACTORY, 
				RobotType.HQ, RobotType.LAUNCHER, RobotType.BASHER,
				RobotType.SOLDIER,  RobotType.BEAVER};
		
		while(true) {
			try {
				if(rc.isCoreReady()) {
					int numBashers = rc.readBroadcast(Status.numBasherChannel);
					if (numBashers > 45){
						if(!huntList(mpri, rc)) {
							tryMove(rc.getLocation().directionTo(rc.senseEnemyHQLocation()), rc);
						}
					} else {
						MapLocation enemyHQ = rc.senseEnemyHQLocation();
						MapLocation ourHQ = rc.senseHQLocation();
						MapLocation middle = new MapLocation((ourHQ.x+enemyHQ.x)/2, (ourHQ.y+enemyHQ.y)/2);
						Direction dirMiddle = rc.getLocation().directionTo(middle);
						tryMove(dirMiddle, rc);
					}
				}
			} catch (Exception e) {
				System.out.println("Basher Exception");
	            e.printStackTrace();
			}
			rc.yield();
		}
	}
	
}
