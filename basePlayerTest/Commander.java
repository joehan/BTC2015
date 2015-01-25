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
				RobotInfo[] nearbyRobots = rc.senseNearbyRobots(RobotType.TOWER.attackRadiusSquared + 9, rc.getTeam().opponent());
				for (RobotInfo bot : nearbyRobots) {
					if (bot.type.equals(RobotType.TOWER)) {
						tryMove(bot.location.directionTo(rc.getLocation()), rc);
						towerInRange = true;
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
