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
				int mineFactories = rc.readBroadcast(Status.numMinerFactoryChannel);
				int helipads = rc.readBroadcast(Status.numHelipadChannel);
				int aerospaceLabs = rc.readBroadcast(Status.numAeroSpaceLabChannel);
				double ore = rc.getTeamOre();
				// build 5 depots, 1 helipad, 2 aerospace labs, then more aerospace labs if there is a surplus
				if (ore >= RobotType.MINERFACTORY.oreCost && mineFactories==0){ 
					tryBuild(Status.directions[rand.nextInt(8)],RobotType.MINERFACTORY, rc);
				} else if (ore >= 300 && helipads < 1 && mineFactories>0) {
					tryBuild(Status.directions[rand.nextInt(8)],RobotType.HELIPAD, rc);
				} else if((ore >= 500 && aerospaceLabs < 2 && helipads>0) || (ore>=1000 && helipads>0)) {
					tryBuild(Status.directions[rand.nextInt(8)],RobotType.AEROSPACELAB, rc);
				} else {
					mine(rc);
				}
			}
	} catch (Exception e) {
			System.out.println("Beaver Exception");
            e.printStackTrace();
		}
	}
	
	
	
	
}
