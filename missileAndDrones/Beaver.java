package missileAndDrones;

import java.util.Random;

import battlecode.common.MapLocation;
import battlecode.common.RobotController;
import battlecode.common.RobotType;

public class Beaver extends Entity {

	public static void run(RobotController rc) {
		try {
			Random rand = new Random();
			
			if (rc.isWeaponReady()) {
				attackSomething(rc);
			}
			if (rc.isCoreReady()) {
//				int fate = rand.nextInt(1000);
//				if (fate < 8 && rc.getTeamOre() >= 300) {
//					tryBuild(directions[rand.nextInt(8)],RobotType.BARRACKS);
//				} else if (fate < 600) {
//					rc.mine();
//				} else if (fate < 900) {
//					tryMove(directions[rand.nextInt(8)]);
//				} else {
//					tryMove(rc.senseHQLocation().directionTo(rc.getLocation()));
//				}
				MapLocation myLoc = rc.getLocation();
				int depots = rc.readBroadcast(Status.numSupplyDepotChannel);
				int helipads = rc.readBroadcast(Status.numHelipadChannel);
				int aerospaceLabs = rc.readBroadcast(Status.numAeroSpaceLabChannel);
				double ore = rc.getTeamOre();
				// build 5 depots, 1 helipad, 2 aerospace labs, then more aerospace labs if there is a surplus
				if (ore >= 300 && helipads < 1) {
					tryBuild(Status.directions[rand.nextInt(8)],RobotType.HELIPAD, rc);
				} else if((ore >= 500 && aerospaceLabs < 2) || (ore>=1000)) {
					tryBuild(Status.directions[rand.nextInt(8)],RobotType.AEROSPACELAB, rc);
				} else if (rc.senseOre(rc.getLocation()) > 0) {
					if (rand.nextInt(3) < 2) {
						rc.mine();
					} else {
						boolean hasMoved = false;
						for (MapLocation loc : MapLocation.getAllMapLocationsWithinRadiusSq(myLoc, 9)) {
							if (rc.senseOre(loc) > 0 && rand.nextInt(4) < 1) {
								tryMove(rc.getLocation().directionTo(loc), rc);
								hasMoved = true;
							}
						}
						if (!hasMoved) {
							tryMove(rc.getLocation().directionTo(rc.senseEnemyHQLocation()), rc);
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
							tryMove(rc.getLocation().directionTo(loc),rc);
							hasMoved = true;
						}
					}
					if (!hasMoved) {
						tryMove(rc.getLocation().directionTo(rc.senseEnemyHQLocation()), rc);
					}
				}
			}
	} catch (Exception e) {
			System.out.println("Beaver Exception");
            e.printStackTrace();
		}
	}
	
	
	
	
}
