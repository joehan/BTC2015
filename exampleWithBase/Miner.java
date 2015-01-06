package exampleWithBase;

import battlecode.common.RobotController;

public class Miner {

	public static void run(RobotController rc) {
		try {
			if(rc.isCoreReady()) {
				int fate = Status.rand.nextInt(2);
				if(fate == 0) {
					rc.mine();
				} else {
					Status.tryMove(Status.directions[Status.rand.nextInt(8)], rc);
				}
			}
		} catch (Exception e) {
			System.out.println("Miner Exception");
            e.printStackTrace();
		}
	}
	
}
