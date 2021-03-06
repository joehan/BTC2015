package boomNBuzz;

import battlecode.common.RobotController;
import battlecode.common.RobotType;

public class Soldier extends Entity {

	public static void run(RobotController rc) {
		RobotType[] mpri = {RobotType.TOWER, RobotType.BARRACKS, RobotType.HQ, RobotType.LAUNCHER, 
							RobotType.BEAVER, RobotType.BASHER,RobotType.SOLDIER};
		
		RobotType[] attackList = {RobotType.TOWER, RobotType.BARRACKS, 
				RobotType.MINERFACTORY, RobotType.MINER, RobotType.TANKFACTORY, 
				RobotType.HQ, RobotType.LAUNCHER, RobotType.BASHER,
				RobotType.SOLDIER,  RobotType.BEAVER};
		
		while(true) {
			try {
				if(rc.isWeaponReady()) {
					huntAttackList(attackList, rc);
				}
				if(rc.isCoreReady()) {
					int numSoldiers = rc.readBroadcast(Status.numSoldierChannel);
					if (numSoldiers > 10){
						if(!huntList(mpri, rc)) {
							tryMove(Status.directions[Status.rand.nextInt(8)], rc);
						}
					} else {
						tryMove(Status.directions[Status.rand.nextInt(8)], rc);
					}
				}
			} catch (Exception e) {
				System.out.println("Drone Exception");
	            e.printStackTrace();
			}
			rc.yield();
		}
	}
	
}
