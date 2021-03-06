package gonnaWin;

import battlecode.common.Direction;
import battlecode.common.GameActionException;
import battlecode.common.MapLocation;
import battlecode.common.RobotController;
import battlecode.common.RobotInfo;
import battlecode.common.RobotType;
import battlecode.common.Team;

public class Entity {

	/*
	 * Helper Functions
	 */

	public static void attackSomething(RobotController rc) throws GameActionException {
		RobotInfo[] enemies = rc.senseNearbyRobots(rc.getType().attackRadiusSquared, rc.getTeam().opponent());
		if (enemies.length > 0) {
			rc.attackLocation(enemies[0].location);
		}
	}

	public static void tryMove(Direction d, RobotController rc) throws GameActionException {
		int offsetIndex = 0;
		int[] offsets = {0,1,-1,2,-2};
		int dirint = directionToInt(d);
		while (offsetIndex < 5 && !rc.canMove(Status.directions[(dirint+offsets[offsetIndex]+8)%8])) {
			offsetIndex++;
		}
		if (offsetIndex < 5) {
			rc.move(Status.directions[(dirint+offsets[offsetIndex]+8)%8]);
		}
	}

	public static void trySpawn(Direction d, RobotType type, RobotController rc) throws GameActionException {
		int offsetIndex = 0;
		int[] offsets = {0,1,-1,2,-2,3,-3,4};
		int dirint = directionToInt(d);
		while (offsetIndex < 8 && !rc.canSpawn(Status.directions[(dirint+offsets[offsetIndex]+8)%8], type)) {
			offsetIndex++;
		}
		if (offsetIndex < 8) {
			rc.spawn(Status.directions[(dirint+offsets[offsetIndex]+8)%8], type);
		}
	}
	
	public static void tryBuild(Direction d, RobotType type, RobotController rc) throws GameActionException {
		int offsetIndex = 0;
		int[] offsets = {0,1,-1,2,-2,3,-3,4};
		int dirint = directionToInt(d);
		while (offsetIndex < 8 && !rc.canMove(Status.directions[(dirint+offsets[offsetIndex]+8)%8])) {
			offsetIndex++;
		}
		if (offsetIndex < 8) {
			rc.build(Status.directions[(dirint+offsets[offsetIndex]+8)%8], type);
		}
	}
	
	private static void dontMine(RobotController rc, MapLocation myLoc) throws GameActionException {
		boolean hasMoved = false;
		MapLocation[] locationsInRange = MapLocation.getAllMapLocationsWithinRadiusSq(myLoc, 6);
		double sum = 0;
		BattleMap<MapLocation, Double> locationCountingOre = new BattleMap<MapLocation, Double>();
		for (MapLocation loc : locationsInRange) {
			if (!rc.isLocationOccupied(loc)) {
				sum += rc.senseOre(loc) / (Math.sqrt(myLoc.distanceSquaredTo(loc)) + 1);
				locationCountingOre.put(loc, sum);
			}
		}
		double goTo = Status.rand.nextDouble() * sum;
		if (sum > 0) {
			for (MapLocation loc : locationCountingOre.keys()) {
				if (goTo < locationCountingOre.get(loc) && goTo >= locationCountingOre.get(loc) - rc.senseOre(loc) / Math.sqrt(myLoc.distanceSquaredTo(loc))) {
					tryMove(rc.getLocation().directionTo(loc), rc);
					hasMoved = true;
					break;
				}
			}
		}
		if (!hasMoved) {
			System.out.println("towards hq");
			tryMove(rc.getLocation().directionTo(rc.senseEnemyHQLocation()), rc);
		}
	}
	
	public static void mine(RobotController rc) throws GameActionException {
		MapLocation myLoc = rc.getLocation();
		if (rc.senseOre(rc.getLocation()) > 0) {
			//if (Status.rand.nextInt(3) < 2) {
				rc.mine();
			/*} else {
				dontMine(rc, myLoc);
			}*/
		} else {
			dontMine(rc, myLoc);
		}
	}
	
	public static void shareSupply(RobotController rc) throws GameActionException {
		RobotInfo[] friendsInRange = rc.senseNearbyRobots(15, rc.getTeam());
		for (RobotInfo friend: friendsInRange){
			if (friend.supplyLevel < friend.type.supplyUpkeep * 10){
				int supplyToGive = (int) (rc.getSupplyLevel() - (friend.type.supplyUpkeep*30));
				if(supplyToGive <= 0) {
					//rc.transferSupplies((int) (rc.getSupplyLevel()/2), friend.location);
				} else {
					rc.transferSupplies(supplyToGive, friend.location);
				}
			} else if ((friend.type == RobotType.AEROSPACELAB 
					|| friend.type == RobotType.MINERFACTORY 
					|| friend.type == RobotType.HELIPAD) && (friend.supplyLevel < 500)){
				rc.transferSupplies(500-(int)(friend.supplyLevel), friend.location);
			}
		}
		
	}
	
	public static boolean hunt(RobotType type, RobotController rc) throws GameActionException {
		RobotInfo[] enemies = rc.senseNearbyRobots(99999999, rc.getTeam().opponent());
		for(RobotInfo enemy : enemies) {
			if(enemy.type == type) {
				tryMove(rc.getLocation().directionTo(enemy.location), rc);
				return true;
			}
		}
		return false;
	}
	
	public static boolean huntList(RobotType[] types, RobotController rc) throws GameActionException {
		for(RobotType type : types) {
			if(hunt(type, rc)) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean huntAttack(RobotType type, RobotController rc) throws GameActionException {
		RobotInfo[] enemies = rc.senseNearbyRobots(rc.getType().attackRadiusSquared, rc.getTeam().opponent());
		for(RobotInfo enemy : enemies) {
			if(enemy.type == type) {
				rc.attackLocation(enemy.location);
				return true;
			}
		}
		return false;
	}
	
	public static boolean huntAttackList(RobotType[] types, RobotController rc) throws GameActionException {
		for(RobotType type : types) {
			if(huntAttack(type, rc)) {
				return true;
			}
		}
		return false;
	}

	// Counts the number of each type of unit and broadcasts it.
	public static void countUnitsAndBroadcast(RobotController rc, Team myTeam) throws GameActionException {
		RobotInfo[] myRobots = rc.senseNearbyRobots(999999, myTeam);
		int numSoldiers = 0;
		int numBashers = 0;
		int numBeavers = 0;
		int numBarracks = 0;
		int numDepot = 0;
		int numTankFactory = 0;
		int numTanks = 0;
		int numLabs = 0;
		int numCommander = 0;
		int numComputer = 0;
		int numDrone = 0;
		int numHandwash = 0;
		int numHelipads = 0;
		int numLauncher = 0;
		int numMiner = 0;
		int numMinerFactory = 0;
		int numTechInstitute = 0;
		int numTraining = 0;
		int numTower = 0;
		for (RobotInfo r : myRobots) {
			RobotType type = r.type;
			if (type == RobotType.SOLDIER) {
				numSoldiers++;
			} else if (type == RobotType.BASHER) {
				numBashers++;
			} else if (type == RobotType.BEAVER) {
				numBeavers++;
			} else if (type == RobotType.BARRACKS) {
				numBarracks++;
			} else if (type == RobotType.SUPPLYDEPOT) {
				numDepot++;
			} else if (type == RobotType.TANKFACTORY) {
				numTankFactory++;
			} else if (type == RobotType.TANK) {
				numTanks++;
			} else if (type == RobotType.AEROSPACELAB) {
				numLabs++;
			} else if (type == RobotType.COMMANDER) {
				numCommander++;
			} else if (type == RobotType.COMPUTER) {
				numComputer++;
			} else if (type == RobotType.DRONE) {
				numDrone++;
			} else if (type == RobotType.HANDWASHSTATION) {
				numHandwash++;
			} else if (type == RobotType.HELIPAD) {
				numHelipads++;
			} else if (type == RobotType.LAUNCHER) {
				numLauncher++;
			} else if (type == RobotType.MINER) {
				numMiner++;
			} else if (type == RobotType.MINERFACTORY) {
				numMinerFactory++;
			} else if (type == RobotType.TECHNOLOGYINSTITUTE) {
				numTechInstitute++;
			} else if (type == RobotType.TRAININGFIELD) {
				numTraining++;
			} else if (type == RobotType.TOWER) {
				numTower++;
			} 
		}
		rc.broadcast(Status.numBeaverChannel, numBeavers);
		rc.broadcast(Status.numSoldierChannel, numSoldiers);
		rc.broadcast(Status.numBasherChannel, numBashers);
		rc.broadcast(Status.numTankChannel, numTanks);
		rc.broadcast(Status.numBarracksChannel, numBarracks);
		rc.broadcast(Status.numSupplyDepotChannel, numDepot);
		rc.broadcast(Status.numTankFactoryChannel, numTankFactory);
		rc.broadcast(Status.numAeroSpaceLabChannel, numLabs);
		rc.broadcast(Status.numCommanderChannel, numCommander);
		rc.broadcast(Status.numComputerChannel, numComputer);
		rc.broadcast(Status.numDroneChannel, numDrone);
		rc.broadcast(Status.numHandWashStationChannel, numHandwash);
		rc.broadcast(Status.numHelipadChannel, numHelipads);
		rc.broadcast(Status.numLauncherChannel, numLauncher);
		rc.broadcast(Status.numMinerChannel, numMiner);
		rc.broadcast(Status.numMinerFactoryChannel, numMinerFactory);
		rc.broadcast(Status.numTechnologyInstituteChannel, numTechInstitute);
		rc.broadcast(Status.numTowerChannel, numTower);
		rc.broadcast(Status.numTrainingFieldChannel, numTraining);
	}

	public static int directionToInt(Direction d) {
		switch(d) {
		case NORTH:
			return 0;
		case NORTH_EAST:
			return 1;
		case EAST:
			return 2;
		case SOUTH_EAST:
			return 3;
		case SOUTH:
			return 4;
		case SOUTH_WEST:
			return 5;
		case WEST:
			return 6;
		case NORTH_WEST:
			return 7;
		default:
			return -1;
		} 
	}
}
