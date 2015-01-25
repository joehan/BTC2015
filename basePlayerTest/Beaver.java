package basePlayerTest;

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
					int institutes = rc.readBroadcast(Status.numTechnologyInstituteChannel);
					int fields = rc.readBroadcast(Status.numTrainingFieldChannel);
					int barracks = rc.readBroadcast(Status.numBarracksChannel);
					int supplyDepots = rc.readBroadcast(Status.numSupplyDepotChannel);
					double ore = rc.getTeamOre();
					// 
					if (ore >= RobotType.MINERFACTORY.oreCost && mineFactories == 0){ 
						tryBuild(Status.directions[rand.nextInt(8)],RobotType.MINERFACTORY, rc);
					} else if (mineFactories != 0) {
						if(supplyDepots < 1 && rc.getTeamOre() >= RobotType.SUPPLYDEPOT.oreCost){
							tryBuild(Status.directions[rand.nextInt(8)],RobotType.SUPPLYDEPOT, rc);
						} else if(barracks < 3 && ore >= RobotType.BARRACKS.oreCost) {
							tryBuild(Status.directions[rand.nextInt(8)], RobotType.BARRACKS, rc);
						} else if(institutes < 1 && ore >= RobotType.TECHNOLOGYINSTITUTE.oreCost) {
							tryBuild(Status.directions[rand.nextInt(8)], RobotType.TECHNOLOGYINSTITUTE, rc);
						} else if(fields < 1 && ore >= RobotType.TRAININGFIELD.oreCost) {
							tryBuild(Status.directions[rand.nextInt(8)], RobotType.TRAININGFIELD, rc);
						} else {
							mine(rc);
						}
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
