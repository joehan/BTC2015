package missileAndDrones;

import java.util.Random;

import battlecode.common.RobotController;
import battlecode.common.RobotType;

public class Helipad extends Entity {

	public static void run(RobotController rc) {
		try {
			Random rand = new Random();
			if (rc.isCoreReady() && rc.getTeamOre() >= 800) {
				trySpawn(Status.directions[rand.nextInt(8)],RobotType.DRONE,rc);
			}
		} catch (Exception e) {
			System.out.println("Helipad Exception");
            e.printStackTrace();
		} 
	}
	
}
