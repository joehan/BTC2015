package stankButt;

import java.util.Random;

import battlecode.common.Direction;
import battlecode.common.MapLocation;
import battlecode.common.RobotController;
import battlecode.common.RobotInfo;
import battlecode.common.RobotType;

public class Launcher extends Entity {

	public static void run(RobotController rc) {
		while (true) {
			try {
				Random rand = new Random();
				if (rc.getMissileCount()>3){
	        		MapLocation[] hisTowers = rc.senseEnemyTowerLocations();
	        		MapLocation[] myTowers = rc.senseTowerLocations();
	        		Direction toHisTower;
	        		if (hisTowers.length < 0)
	        			toHisTower = rc.getLocation().directionTo(hisTowers[0]);
	        		else
	        			toHisTower = rc.getLocation().directionTo(rc.senseEnemyHQLocation());
	        		RobotInfo[] enemies = rc.senseNearbyRobots(36, rc.getTeam().opponent());
	        		boolean shoot = true;
	        		boolean inRange = false;
	        		boolean enemiesNear = false;
	        		for (int tow = 0; tow <myTowers.length; tow++){
	        			if (rc.getLocation().directionTo(myTowers[tow]) == toHisTower){
	        				shoot = false;
	        			}
	        			if (hisTowers.length>0){
		        			if (rc.getLocation().distanceSquaredTo(hisTowers[0]) < 100){
		        				inRange = true;
		        			}
	        			} else {
	        				if (rc.getLocation().distanceSquaredTo(rc.senseEnemyHQLocation()) < 100){
		        				inRange = true;
		        			}
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
					RobotInfo[] enemiesInRange = rc.senseNearbyRobots(36, rc.getTeam().opponent());
					RobotInfo[] enemiesInClose = rc.senseNearbyRobots(16, rc.getTeam().opponent());
					boolean frightened = false;
					Direction fleeDirection = Status.directions[0];
					for (RobotInfo enemy: enemiesInClose){
						if (enemy.type == RobotType.MISSILE){
							frightened = true;
							fleeDirection = rc.getLocation().directionTo(enemy.location).opposite();
							break;
						}
					}
					if (frightened){
						tryMove(fleeDirection, rc);
					} else if (fate < 200) {
						tryMove(Status.directions[rand.nextInt(8)],rc);
					} else if (towers.length > 0){
						if (enemiesInRange.length==0){
							tryMove(rc.getLocation().directionTo(towers[0]),rc);
						}
					} else{
						if (enemiesInRange.length==0){
							tryMove(rc.getLocation().directionTo(rc.senseEnemyHQLocation()),rc);
						}
					}
					shareSupply(rc);
				}
			} catch (Exception e) {
				System.out.println("Launcher Exception");
	            e.printStackTrace();
			}
			rc.yield();
		}
	}
	
}
