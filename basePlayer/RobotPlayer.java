package basePlayer;

import battlecode.common.RobotController;
import battlecode.common.RobotType;

public class RobotPlayer {
	
	public static void run(RobotController rc) {
		
		Status.init(rc);
		RobotType type = rc.getType();
		
		while(true) {
			
			if(type == RobotType.HQ) {
				HQ.run(rc);
			} else if(type == RobotType.TOWER) {
				Tower.run(rc);
			} else if(type == RobotType.BASHER) {
				Basher.run(rc);
			} else if(type == RobotType.SOLDIER) {
				Soldier.run(rc);
			} else if(type == RobotType.BEAVER) {
				Beaver.run(rc);
			} else if(type == RobotType.BARRACKS) {
				Barracks.run(rc);
			}
			
			rc.yield();
		}
	}
	
}
