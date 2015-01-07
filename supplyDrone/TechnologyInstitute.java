package supplyDrone;

import battlecode.common.RobotController;

public class TechnologyInstitute extends Entity {

	public static void run(RobotController rc) {
		while(true) {
			try {
				//TODO: TechnologyInstitute
			} catch (Exception e) {
				System.out.println("TechnologyInstitute Exception");
	            e.printStackTrace();
			}
			rc.yield();
		}
	}
	
}
