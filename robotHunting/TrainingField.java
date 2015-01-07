package robotHunting;

import battlecode.common.RobotController;

public class TrainingField extends Entity {

	public static void run(RobotController rc) {
		while(true) {
			try {
				//TODO: TrainingField
			} catch (Exception e) {
				System.out.println("TrainingField Exception");
	            e.printStackTrace();
			}
			rc.yield();
		}
	}
	
}
