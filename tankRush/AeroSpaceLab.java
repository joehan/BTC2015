package tankRush;

import java.util.Random;


import battlecode.common.RobotController;
import battlecode.common.RobotType;

public class AeroSpaceLab extends Entity {

	public static void run(RobotController rc) {
		while(true) {
			try {
				
				Random rand = new Random();
				if (rc.isCoreReady() && rc.getTeamOre() >= 500) {
					trySpawn(Status.directions[rand.nextInt(8)],RobotType.LAUNCHER,rc);
				} else if (rc.isCoreReady()){
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
