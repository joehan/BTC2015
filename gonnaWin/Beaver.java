package gonnaWin;

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
					int barracks = rc.readBroadcast(Status.numBarracksChannel);
					int tankFactories = rc.readBroadcast(Status.numTankFactoryChannel);
					int supplyDepots = rc.readBroadcast(Status.numSupplyDepotChannel);
					double ore = rc.getTeamOre();
					// 
					if (ore >= RobotType.MINERFACTORY.oreCost && mineFactories==0){ 
						tryBuild(Status.directions[rand.nextInt(8)],RobotType.MINERFACTORY, rc);
					} else if (ore >= 300 && barracks < 3 && mineFactories>0) {
						tryBuild(Status.directions[rand.nextInt(8)],RobotType.BARRACKS, rc);
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
