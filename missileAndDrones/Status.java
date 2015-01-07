package missileAndDrones;

import java.util.Random;

import battlecode.common.Direction;
import battlecode.common.RobotController;

public class Status {
	
	/*
	 * Broadcast Channels
	 */
	
	// Number of Units
	static final int numAeroSpaceLabChannel = 0;
	static final int numBarracksChannel = 1;
	static final int numBasherChannel = 2;
	static final int numBeaverChannel = 3;
	static final int numCommanderChannel = 4;
	static final int numComputerChannel = 5;
	static final int numDroneChannel = 6;
	static final int numHandWashStationChannel = 7;
	static final int numHelipadChannel = 8;
	static final int numLauncherChannel = 9;
	static final int numMinerChannel = 10;
	static final int numMinerFactoryChannel = 11;
	static final int numMissileChannel = 12;
	static final int numSoldierChannel = 13;
	static final int numSupplyDepotChannel = 14;
	static final int numTankChannel = 15;
	static final int numTankFactoryChannel = 16;
	static final int numTowerChannel = 17;
	static final int numTechnologyInstituteChannel = 18;
	static final int numTrainingFieldChannel = 19;
	
	// Game Variables
	static Direction[] directions = {Direction.NORTH, Direction.NORTH_EAST, Direction.EAST, Direction.SOUTH_EAST, Direction.SOUTH, Direction.SOUTH_WEST, Direction.WEST, Direction.NORTH_WEST};
	static Random rand = new Random();
	
	// Initialize Variables above
	public static void init(RobotController rc) {
		
	}

	/*
	Helper Functions
	*/
	// Shuffle Array
	  static void shuffleArray(Object[] ar)
	  {
	    for (int i = ar.length - 1; i > 0; i--)
	    {
	      int index = rand.nextInt(i + 1);
	      // Simple swap
	      Object a = ar[index];
	      ar[index] = ar[i];
	      ar[i] = a;
	    }
	  }
}
