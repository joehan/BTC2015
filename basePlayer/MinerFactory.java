package basePlayer;

import battlecode.common.RobotController;

public class MinerFactory extends Entity {

	public static void run(RobotController rc) {
		while(true) {
			try {
				//TODO: MinerFactory
			} catch (Exception e) {
				System.out.println("MinerFactory Exception");
	            e.printStackTrace();
			}
			rc.yield();
		}
	}
	
}
