package supplyTest;

import battlecode.common.RobotController;
import battlecode.common.RobotInfo;
import battlecode.common.RobotType;

public class Drone extends Entity {

	public static void run(RobotController rc) {
		RobotType[] attackList = {RobotType.LAUNCHER, RobotType.BASHER,RobotType.SOLDIER,  RobotType.BEAVER};
		boolean enoughSupply = false;
		while(true) {
			try {
				if(rc.isWeaponReady()) {
					huntAttackList(attackList, rc);
				}
				if(rc.isCoreReady()) {
					RobotInfo[] mine = rc.senseNearbyRobots(Integer.MAX_VALUE, rc.getTeam());
					RobotInfo min = null;
					for(RobotInfo bot : mine) {
						if(bot.supplyLevel < bot.type.supplyUpkeep*20) {
							if(min == null || bot.supplyLevel < min.supplyLevel) {
								min = bot;
							}
						}
					}
					if(min != null) {
						tryMove(rc.getLocation().directionTo(min.location), rc);
					} else {
						tryMove(rc.getLocation().directionTo(rc.senseHQLocation()), rc);
					}
					shareSupply(rc);
				}
			} catch (Exception e) {
				System.out.println("Drone Exception");
	            e.printStackTrace();
			}
			rc.yield();
		}
	}
	
}
