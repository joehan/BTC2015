package walkingBackwards;

import battlecode.common.MapLocation;
import battlecode.common.RobotController;
import battlecode.common.RobotInfo;
import battlecode.common.RobotType;

public class Missile extends Entity {

	public static void run(RobotController rc) {
		while(true) {
			try {
				RobotInfo[] adjacentEnemies = rc.senseNearbyRobots(2, rc.getTeam().opponent());
				RobotInfo[] adjacentFriends = rc.senseNearbyRobots(2, rc.getTeam());
	            MapLocation[] towers = rc.senseEnemyTowerLocations();
	            RobotInfo[] enemies = rc.senseNearbyRobots(36, rc.getTeam().opponent());
				if (rc.isCoreReady()) {
					if (adjacentEnemies.length > 0 && adjacentFriends.length ==0) {
						for (RobotInfo enemy: adjacentEnemies){
							if (enemy.type != RobotType.MISSILE){
								rc.explode();
								break;
							}
						}
					} else if (enemies.length > 0){
						tryMove(rc.getLocation().directionTo(enemies[0].location),rc);
					} else if (towers.length >0) {
						tryMove(rc.getLocation().directionTo(towers[0]),rc);
					} else {
						tryMove(rc.getLocation().directionTo(rc.senseEnemyHQLocation()), rc);
					}
				}
			} catch (Exception e) {
				System.out.println("Missile Exception");
	            e.printStackTrace();
			}
			rc.yield();
		}
	}
	
}
