package basePlayer;

import battlecode.common.RobotController;
import battlecode.common.RobotInfo;
import battlecode.common.RobotType;
import battlecode.common.Team;

public class HQ extends Entity {

	public static void run(RobotController rc) {
		try {					
			//TODO: HQ
		} catch (Exception e) {
			System.out.println("HQ Exception");
            e.printStackTrace();
		}
	}
	
	// Counts the number of each type of unit and broadcasts it.
	public static void countUnitsAndBroadcast(RobotController rc, Team myTeam) {
		try {
			RobotInfo[] myRobots = rc.senseNearbyRobots(999999, myTeam);
			int numSoldiers = 0;
			int numBashers = 0;
			int numBeavers = 0;
			int numBarracks = 0;
			int numDepot = 0;
			int numTankFactory = 0;
			int numTanks = 0;
			int numLabs = 0;
			int numCommander = 0;
			int numComputer = 0;
			int numDrone = 0;
			int numHandwash = 0;
			int numHelipads = 0;
			int numLauncher = 0;
			int numMiner = 0;
			int numMinerFactory = 0;
			int numTechInstitute = 0;
			int numTraining = 0;
			int numTower = 0;
			for (RobotInfo r : myRobots) {
				RobotType type = r.type;
				if (type == RobotType.SOLDIER) {
					numSoldiers++;
				} else if (type == RobotType.BASHER) {
					numBashers++;
				} else if (type == RobotType.BEAVER) {
					numBeavers++;
				} else if (type == RobotType.BARRACKS) {
					numBarracks++;
				} else if (type == RobotType.SUPPLYDEPOT) {
					numDepot++;
				} else if (type == RobotType.TANKFACTORY) {
					numTankFactory++;
				} else if (type == RobotType.TANK) {
					numTanks++;
				} else if (type == RobotType.AEROSPACELAB) {
					numLabs++;
				} else if (type == RobotType.COMMANDER) {
					numCommander++;
				} else if (type == RobotType.COMPUTER) {
					numComputer++;
				} else if (type == RobotType.DRONE) {
					numDrone++;
				} else if (type == RobotType.HANDWASHSTATION) {
					numHandwash++;
				} else if (type == RobotType.HELIPAD) {
					numHelipads++;
				} else if (type == RobotType.LAUNCHER) {
					numLauncher++;
				} else if (type == RobotType.MINER) {
					numMiner++;
				} else if (type == RobotType.MINERFACTORY) {
					numMinerFactory++;
				} else if (type == RobotType.TECHNOLOGYINSTITUTE) {
					numTechInstitute++;
				} else if (type == RobotType.TRAININGFIELD) {
					numTraining++;
				} else if (type == RobotType.TOWER) {
					numTower++;
				} 
			}
			rc.broadcast(Status.numBeaverChannel, numBeavers);
			rc.broadcast(Status.numSoldierChannel, numSoldiers);
			rc.broadcast(Status.numBasherChannel, numBashers);
			rc.broadcast(Status.numTankChannel, numTanks);
			rc.broadcast(Status.numBarracksChannel, numBarracks);
			rc.broadcast(Status.numSupplyDepotChannel, numDepot);
			rc.broadcast(Status.numTankFactoryChannel, numTankFactory);
			rc.broadcast(Status.numAeroSpaceLabChannel, numLabs);
			rc.broadcast(Status.numCommanderChannel, numCommander);
			rc.broadcast(Status.numComputerChannel, numComputer);
			rc.broadcast(Status.numDroneChannel, numDrone);
			rc.broadcast(Status.numHandWashStationChannel, numHandwash);
			rc.broadcast(Status.numHelipadChannel, numHelipads);
			rc.broadcast(Status.numLauncherChannel, numLauncher);
			rc.broadcast(Status.numMinerChannel, numMiner);
			rc.broadcast(Status.numMinerFactoryChannel, numMinerFactory);
			rc.broadcast(Status.numTechnologyInstituteChannel, numTechInstitute);
			rc.broadcast(Status.numTowerChannel, numTower);
			rc.broadcast(Status.numTrainingFieldChannel, numTraining);
		} catch (Exception e) {
			System.out.println("HQ Exception");
            e.printStackTrace();
		}
	}
	
}
