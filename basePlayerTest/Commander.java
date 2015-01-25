package basePlayerTest;

import battlecode.common.RobotController;
import battlecode.common.RobotType;
import battlecode.common.RobotInfo;

public class Commander extends Entity {

	public static void run(RobotController rc) {
		while(true) {
			try {
				if(rc.isWeaponReady()) {
					huntAttackList(Status.order, rc);
				}
				boolean towerInRange = false;
				RobotInfo[] nearbyRobots = rc.senseNearbyRobots(RobotType.TOWER.attackRadiusSquared + 6, rc.getTeam().opponent());
				for (RobotInfo bot : nearbyRobots) {
					if (bot.location.distanceSquaredTo(rc.getLocation()) < bot.type.attackRadiusSquared  + 9|| bot.type.equals(RobotType.TOWER) || bot.type.equals(RobotType.HQ)) {
						towerInRange=true;
						tryMove(rc.getLocation().directionTo(rc.senseHQLocation()), rc);
					}
				}
				if (!towerInRange) {
					soldierRush(rc);
				}
			} catch (Exception e) {
				System.out.println("Commander Exception");
	            e.printStackTrace();
			}
			rc.yield();
		}
	}
	
}
