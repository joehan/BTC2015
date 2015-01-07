package robotHunting;

import battlecode.common.RobotController;

public class TankFactory extends Entity {

	public static void run(RobotController rc) {
		while(true) {
			try {
				//TODO: TankFactory
			} catch (Exception e) {
				System.out.println("TankFactory Exception");
	            e.printStackTrace();
			}
			rc.yield();
		}
	}
	
}
