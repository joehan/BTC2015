package missileAndDrones;

import java.util.Random;

import battlecode.common.Direction;
import battlecode.common.MapLocation;
import battlecode.common.RobotController;
import battlecode.common.RobotInfo;

public class Launcher extends Entity {

	public static void run(RobotController rc) {
		try {
			Random rand = new Random();
			if (rc.getMissileCount()>3){
        		MapLocation[] hisTowers = rc.senseEnemyTowerLocations();
        		MapLocation[] myTowers = rc.senseTowerLocations();
        		Direction toHisTower = rc.getLocation().directionTo(hisTowers[0]);
        		RobotInfo[] enemies = rc.senseNearbyRobots(36, rc.getTeam().opponent());
        		boolean shoot = true;
        		boolean inRange = false;
        		boolean enemiesNear = false;
        		for (int tow = 0; tow <myTowers.length; tow++){
        			if (rc.getLocation().directionTo(myTowers[tow]) == toHisTower){
        				shoot = false;
        			}
        			if (rc.getLocation().distanceSquaredTo(hisTowers[0]) < 100){
        				inRange = true;
        			}
        			enemiesNear = (enemies.length > 0);
        		}
        		
        		if (rc.canLaunch(toHisTower) && shoot){
        			if (inRange || enemiesNear)
        			rc.launchMissile(toHisTower);
        		}
        	}
           
			if (rc.isCoreReady()) {
				MapLocation[] towers = rc.senseEnemyTowerLocations();
				int fate = rand.nextInt(1000);
				if (fate < 200) {
					tryMove(Status.directions[rand.nextInt(8)],rc);
				} else if (towers.length < 0){
					tryMove(rc.getLocation().directionTo(towers[0]),rc);
				} else{
					tryMove(rc.getLocation().directionTo(rc.senseEnemyHQLocation()),rc);
				}
			}
		} catch (Exception e) {
			System.out.println("Launcher Exception");
            e.printStackTrace();
		}
	}
	
}
