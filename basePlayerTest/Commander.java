package basePlayerTest;

import battlecode.common.RobotController;
import battlecode.common.RobotType;
import battlecode.common.RobotInfo;
import battlecode.common.MapLocation;

public class Commander extends Entity {

	public static void run(RobotController rc) {
		while(true) {
			try {
				if(rc.isWeaponReady()) {
					huntAttackList(Status.order, rc);
				}
				if(rc.isCoreReady()) {
					boolean towerInRange = false;
					MapLocation loc = rc.getLocation();
					if (rc.getHealth() < 150) {
						RobotInfo[] nearbyRobots = rc.senseNearbyRobots(RobotType.TOWER.attackRadiusSquared + 10, rc.getTeam().opponent());
						for (RobotInfo bot : nearbyRobots) {
							if (bot.location.distanceSquaredTo(rc.getLocation()) < bot.type.attackRadiusSquared  + 9) {
								towerInRange=true;
								//System.out.println(rc.getFlashCooldown());
								if (rc.getFlashCooldown() == 0) {
									rc.castFlash(loc.add(loc.directionTo(rc.senseHQLocation()), 2));
								} else {
									tryMove(rc.getLocation().directionTo(rc.senseHQLocation()), rc);
								}
								break;
							}
						}
					}
					if (!towerInRange) {
						soldierRush(rc);
					}
				}
			} catch (Exception e) {
				System.out.println("Commander Exception");
	            e.printStackTrace();
			}
			rc.yield();
		}
	}
	
}
