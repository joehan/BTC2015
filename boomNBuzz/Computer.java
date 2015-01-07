package boomNBuzz;

import battlecode.common.RobotController;

public class Computer extends Entity {

	public static void run(RobotController rc) {
		while(true) {
			try {
				//TODO: Computer
			} catch (Exception e) {
				System.out.println("Computer Exception");
	            e.printStackTrace();
			}
			rc.yield();
		}
	}
	
}
