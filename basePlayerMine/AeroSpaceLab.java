package basePlayerMine;

import battlecode.common.RobotController;

public class AeroSpaceLab extends Entity {

	public static void run(RobotController rc) {
		while(true) {
			try {
				//TODO: AeroSpaceLab
			} catch (Exception e) {
				System.out.println("AeroSpaceLab Exception");
				e.printStackTrace();
			}
			rc.yield();
		}
	}
	
}
