package missileAndDrones;

import java.util.Random;

import battlecode.common.MapLocation;
import battlecode.common.RobotController;

public class Drone extends Entity {

	public static void run(RobotController rc) {
		try {
			Random rand = new Random();
			if (rc.isWeaponReady()) {
				attackSomething(rc);
			}
            
			if (rc.isCoreReady()) {
				MapLocation[] towers = rc.senseEnemyTowerLocations();
				int fate = rand.nextInt(1000);
				if (fate < 200) {
					tryMove(Status.directions[rand.nextInt(8)],rc);
				} else if (towers.length < 0) {
					tryMove(rc.getLocation().directionTo(towers[0]),rc);
				} else{
					tryMove(rc.getLocation().directionTo(rc.senseEnemyHQLocation()),rc);
				}
			}
		} catch (Exception e) {
			System.out.println("Drone Exception");
            e.printStackTrace();
		}
	}
	
}
