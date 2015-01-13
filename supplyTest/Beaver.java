package supplyTest;

import java.util.Random;

import battlecode.common.MapLocation;
import battlecode.common.RobotController;
import battlecode.common.RobotType;

public class Beaver extends Entity {

	public static void run(RobotController rc) {
		while(true){
			try {
				Random rand = new Random();
				
				if (rc.isWeaponReady()) {
					attackSomething(rc);
				}
				if (rc.isCoreReady()) {
					
					MapLocation myLoc = rc.getLocation();
					int mineFactories = rc.readBroadcast(Status.numMinerFactoryChannel);
					int helipads = rc.readBroadcast(Status.numHelipadChannel);
					int aerospaceLabs = rc.readBroadcast(Status.numAeroSpaceLabChannel);
					int supplyDepots = rc.readBroadcast(Status.numSupplyDepotChannel);
					double ore = rc.getTeamOre();
					// 
					if (ore >= RobotType.MINERFACTORY.oreCost && mineFactories==0){ 
						tryBuild(Status.directions[rand.nextInt(8)],RobotType.MINERFACTORY, rc);
					} else if (supplyDepots < 1 && rc.getTeamOre() > 100){
						tryBuild(Status.directions[rand.nextInt(8)],RobotType.SUPPLYDEPOT, rc);
					} else if (ore >= 300 && helipads < 1 && mineFactories>0) {
						tryBuild(Status.directions[rand.nextInt(8)],RobotType.HELIPAD, rc);
					} else if((ore >= 500 && aerospaceLabs < 2 && helipads>0) || (ore>=1000 && helipads>0)) {
						tryBuild(Status.directions[rand.nextInt(8)],RobotType.AEROSPACELAB, rc);
					} else {
						mine(rc);
					}
					shareSupply(rc);
				}
		} catch (Exception e) {
				System.out.println("Beaver Exception");
	            e.printStackTrace();
		}
			
		rc.yield();
		
		}
	}
	
	
	
	
}
