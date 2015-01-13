package supplyTest;

import java.util.Random;


import battlecode.common.RobotController;
import battlecode.common.RobotType;

public class AeroSpaceLab extends Entity {

	public static void run(RobotController rc) {
		while(true) {
			try {

				Random rand = new Random();
				if (rc.isCoreReady()) {
					if(rc.getTeamOre() >= 500) {
						trySpawn(Status.directions[rand.nextInt(8)],RobotType.LAUNCHER,rc);
					}
					shareSupply(rc);
				}
			} catch (Exception e) {
				System.out.println("AerospaceLab Exception");
	            e.printStackTrace();
			}
			rc.yield();
		}
	}
	
}
