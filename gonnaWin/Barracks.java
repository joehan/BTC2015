package gonnaWin;

import java.util.Random;

import battlecode.common.RobotController;
import battlecode.common.RobotType;

public class Barracks extends Entity {

	public static void run(RobotController rc) {
		while(true) {
			try {
				Random rand = new Random();
				if (rc.isCoreReady() && rc.getTeamOre() >= RobotType.BASHER.oreCost) {
					trySpawn(Status.directions[rand.nextInt(8)],RobotType.BASHER,rc);
				} else if (rc.isCoreReady()){
					shareSupply(rc);
				}
			} catch (Exception e) {
				System.out.println("Barracks Exception");
	            e.printStackTrace();
			}
			rc.yield();
		}
	}
	
}
