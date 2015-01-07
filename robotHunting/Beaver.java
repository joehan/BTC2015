package robotHunting;

import battlecode.common.RobotController;
import battlecode.common.RobotType;

public class Beaver extends Entity {

	public static void run(RobotController rc) {
		while(true) {
			try {
				if (rc.isWeaponReady()) {
					attackSomething(rc);
				}
				int factories = rc.readBroadcast(Status.numMinerFactoryChannel);
				if (rc.getTeamOre() >= RobotType.MINERFACTORY.oreCost && factories < 1) {
					tryBuild(Status.directions[Status.rand.nextInt(8)],RobotType.MINERFACTORY, rc);
				} else if(factories > 0 && rc.getTeamOre() >= RobotType.HELIPAD.oreCost && rc.readBroadcast(Status.numHelipadChannel) <= 3) {
					tryBuild(Status.directions[Status.rand.nextInt(8)], RobotType.HELIPAD, rc);
				} else {
					mine(rc);
				}
			} catch (Exception e) {
				System.out.println("Beaver Exception");
	            e.printStackTrace();
			}
			rc.yield();
		}
	}
}