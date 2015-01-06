package resources;

import java.util.Random;

import battlecode.common.Direction;
import battlecode.common.GameActionException;
import battlecode.common.MapLocation;
import battlecode.common.RobotController;
import battlecode.common.RobotInfo;
import battlecode.common.RobotType;
import battlecode.common.Team;

public class RobotPlayer {
	static RobotController rc;
	static Team myTeam;
	static Team enemyTeam;
	static int myRange;
	static Random rand;
	static Direction[] directions = {Direction.NORTH, Direction.NORTH_EAST, Direction.EAST, Direction.SOUTH_EAST, Direction.SOUTH, Direction.SOUTH_WEST, Direction.WEST, Direction.NORTH_WEST};
	
	public static void run(RobotController tomatojuice) {
		rc = tomatojuice;
        rand = new Random(rc.getID());

		myRange = rc.getType().attackRadiusSquared;
		MapLocation enemyLoc = rc.senseEnemyHQLocation();
        Direction lastDirection = null;
		myTeam = rc.getTeam();
		enemyTeam = myTeam.opponent();
		RobotInfo[] myRobots;

		while(true) {
            try {
                rc.setIndicatorString(0, "This is an indicator string.");
                rc.setIndicatorString(1, "I am a " + rc.getType());
            } catch (Exception e) {
                System.out.println("Unexpected exception");
                e.printStackTrace();
            }

			if (rc.getType() == RobotType.HQ) {
				try {					
					int fate = rand.nextInt(10000);
					myRobots = rc.senseNearbyRobots(999999, myTeam);
					int numSoldiers = 0;
					int numBashers = 0;
					int numBeavers = 0;
					int numBarracks = 0;
					int numDepot = 0;
					int numFactory = 0;
					int numTanks = 0;
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
							numFactory++;
						} else if (type == RobotType.TANK) {
							numTanks++;
						}
					}
					rc.broadcast(0, numBeavers);
					rc.broadcast(1, numSoldiers);
					rc.broadcast(2, numBashers);
					rc.broadcast(3, numTanks);
					rc.broadcast(100, numBarracks);
					rc.broadcast(101, numDepot);
					rc.broadcast(102, numFactory);
					
					if (rc.isWeaponReady()) {
						attackSomething();
					}

					if (rc.isCoreReady() && rc.getTeamOre() >= 100 && fate < Math.pow(1.2,12-numBeavers)*10000) {
						trySpawn(directions[rand.nextInt(8)], RobotType.BEAVER);
					}
				} catch (Exception e) {
					System.out.println("HQ Exception");
                    e.printStackTrace();
				}
			}
			
            if (rc.getType() == RobotType.TOWER) {
                try {					
					if (rc.isWeaponReady()) {
						attackSomething();
					}
				} catch (Exception e) {
					System.out.println("Tower Exception");
                    e.printStackTrace();
				}
            }
			
			
			if (rc.getType() == RobotType.BASHER) {
                try {
                    RobotInfo[] adjacentEnemies = rc.senseNearbyRobots(2, enemyTeam);

                    // BASHERs attack automatically, so let's just move around mostly randomly
					if (rc.isCoreReady()) {
						int fate = rand.nextInt(1000);
						if (fate < 800) {
							tryMove(directions[rand.nextInt(8)]);
						} else {
							tryMove(rc.getLocation().directionTo(rc.senseEnemyHQLocation()));
						}
					}
                } catch (Exception e) {
					System.out.println("Basher Exception");
					e.printStackTrace();
                }
            }
			
            if (rc.getType() == RobotType.SOLDIER) {
                try {
                    if (rc.isWeaponReady()) {
						attackSomething();
					}
					if (rc.isCoreReady()) {
						int fate = rand.nextInt(1000);
						int numSoldiers = rc.readBroadcast(1);
						if (numSoldiers < 70) {
							tryMove(directions[rand.nextInt(8)]);
						} else {
							tryMove(rc.getLocation().directionTo(rc.senseEnemyHQLocation()));
						}
					}
                } catch (Exception e) {
					System.out.println("Soldier Exception");
					e.printStackTrace();
                }
            }
			
			if (rc.getType() == RobotType.BEAVER) {
				try {
					if (rc.isWeaponReady()) {
						attackSomething();
					}
					if (rc.isCoreReady()) {
//						int fate = rand.nextInt(1000);
//						if (fate < 8 && rc.getTeamOre() >= 300) {
//							tryBuild(directions[rand.nextInt(8)],RobotType.BARRACKS);
//						} else if (fate < 600) {
//							rc.mine();
//						} else if (fate < 900) {
//							tryMove(directions[rand.nextInt(8)]);
//						} else {
//							tryMove(rc.senseHQLocation().directionTo(rc.getLocation()));
//						}
						MapLocation myLoc = rc.getLocation();
						int depots = rc.readBroadcast(101);
						int barracks = rc.readBroadcast(100);
						int factories = rc.readBroadcast(102);
						double ore = rc.getTeamOre();
						// build 4 depots, 1 barracks, 3 tanks factories
						if (ore >= 100 && depots < 4) {
							tryBuild(directions[rand.nextInt(8)],RobotType.SUPPLYDEPOT);
						} else if (ore >= 300 && barracks < 1) {
							tryBuild(directions[rand.nextInt(8)],RobotType.BARRACKS);
						} else if(ore >= 500 && factories < 3) {
							tryBuild(directions[rand.nextInt(8)],RobotType.TANKFACTORY);
						} else if (rc.senseOre(rc.getLocation()) > 0) {
							if (rand.nextInt(3) < 2) {
								rc.mine();
							} else {
								boolean hasMoved = false;
								for (MapLocation loc : MapLocation.getAllMapLocationsWithinRadiusSq(myLoc, 9)) {
									if (rc.senseOre(loc) > 0 && rand.nextInt(4) < 1) {
										tryMove(rc.getLocation().directionTo(loc));
										hasMoved = true;
									}
								}
								if (!hasMoved) {
									tryMove(rc.getLocation().directionTo(rc.senseEnemyHQLocation()));
								}
							}
						} else {
							//if (rand.nextInt(2) < 1) {
								//tryMove(rc.getLocation().directionTo(rc.senseEnemyHQLocation()));
							/*} else {
								tryMove(directions[6]);
							}*/
							boolean hasMoved = false;
							for (MapLocation loc : MapLocation.getAllMapLocationsWithinRadiusSq(myLoc, 9)) {
								if (rc.senseOre(loc) > 0 && rand.nextInt(4) < 1) {
									tryMove(rc.getLocation().directionTo(loc));
									hasMoved = true;
								}
							}
							if (!hasMoved) {
								tryMove(rc.getLocation().directionTo(rc.senseEnemyHQLocation()));
							}
						}
					}
			} catch (Exception e) {
					System.out.println("Beaver Exception");
                    e.printStackTrace();
				}
			}
			
			if (rc.getType() == RobotType.TANKFACTORY) {
				try {
					if (rc.isCoreReady()) {
						int numFactory = rc.readBroadcast(102);
						if (numFactory >= 3 && rc.getTeamOre() >= 250) {
							trySpawn(directions[rand.nextInt(8)],RobotType.TANK);
						}
					}
				} catch (Exception e) {
					System.out.println("Tank Factory Exception");
                    e.printStackTrace();
				}
			}
			
			if (rc.getType() == RobotType.TANK) {
				try {
					if (rc.isCoreReady()) {
						int numTanks = rc.readBroadcast(3);
						if (numTanks >= 10) {
							tryMove(rc.getLocation().directionTo(rc.senseEnemyHQLocation()));
						} else {
							tryMove(directions[rand.nextInt(8)]);
						}
					}
				} catch (Exception e) {
					System.out.println("Barracks Exception");
                    e.printStackTrace();
				}
			}

            if (rc.getType() == RobotType.BARRACKS) {
				try {
					int fate = rand.nextInt(10000);
					
                    // get information broadcasted by the HQ
					int numBeavers = rc.readBroadcast(0);
					int numSoldiers = rc.readBroadcast(1);
					int numBashers = rc.readBroadcast(2);
					int numBarracks = rc.readBroadcast(100);
					
					if (numBarracks > 3) {
						if (rc.isCoreReady() && rc.getTeamOre() >= 100) {
//							if (rc.getTeamOre() > 80 && fate % 2 == 0) {
//								trySpawn(directions[rand.nextInt(8)],RobotType.BASHER);
//							} else {
								trySpawn(directions[rand.nextInt(8)],RobotType.SOLDIER);
							//}
						}
					}
				} catch (Exception e) {
					System.out.println("Barracks Exception");
                    e.printStackTrace();
				}
			}
			
			rc.yield();
		}
	}
	
    // This method will attack an enemy in sight, if there is one
	static void attackSomething() throws GameActionException {
		RobotInfo[] enemies = rc.senseNearbyRobots(myRange, enemyTeam);
		if (enemies.length > 0) {
			rc.attackLocation(enemies[0].location);
		}
	}
	
    // This method will attempt to move in Direction d (or as close to it as possible)
	static void tryMove(Direction d) throws GameActionException {
		int offsetIndex = 0;
		int[] offsets = {0,1,-1,2,-2};
		int dirint = directionToInt(d);
		boolean blocked = false;
		while (offsetIndex < 5 && !rc.canMove(directions[(dirint+offsets[offsetIndex]+8)%8])) {
			offsetIndex++;
		}
		if (offsetIndex < 5) {
			rc.move(directions[(dirint+offsets[offsetIndex]+8)%8]);
		}
	}
	
    // This method will attempt to spawn in the given direction (or as close to it as possible)
	static void trySpawn(Direction d, RobotType type) throws GameActionException {
		int offsetIndex = 0;
		int[] offsets = {0,1,-1,2,-2,3,-3,4};
		int dirint = directionToInt(d);
		boolean blocked = false;
		while (offsetIndex < 8 && !rc.canSpawn(directions[(dirint+offsets[offsetIndex]+8)%8], type)) {
			offsetIndex++;
		}
		if (offsetIndex < 8) {
			rc.spawn(directions[(dirint+offsets[offsetIndex]+8)%8], type);
		}
	}
	
    // This method will attempt to build in the given direction (or as close to it as possible)
	static void tryBuild(Direction d, RobotType type) throws GameActionException {
		int offsetIndex = 0;
		int[] offsets = {0,1,-1,2,-2,3,-3,4};
		int dirint = directionToInt(d);
		boolean blocked = false;
		while (offsetIndex < 8 && !rc.canMove(directions[(dirint+offsets[offsetIndex]+8)%8])) {
			offsetIndex++;
		}
		if (offsetIndex < 8) {
			rc.build(directions[(dirint+offsets[offsetIndex]+8)%8], type);
		}
	}
	
	static int directionToInt(Direction d) {
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