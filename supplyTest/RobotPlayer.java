package supplyTest;

import battlecode.common.RobotController;
import battlecode.common.RobotType;

public class RobotPlayer {
	
	public static void run(RobotController rc) {
		
		Status.init(rc);
		RobotType type = rc.getType();
		
		if(type == RobotType.HQ) {
			HQ.run(rc);
		} else if(type == RobotType.AEROSPACELAB) {
			AeroSpaceLab.run(rc);
		} else if(type == RobotType.BARRACKS) {
			Barracks.run(rc);
		} else if(type == RobotType.BASHER) {
			Basher.run(rc);
		} else if(type == RobotType.BEAVER) {
			Beaver.run(rc);
		} else if(type == RobotType.COMMANDER) {
			Commander.run(rc);
		} else if(type == RobotType.COMPUTER) {
			Computer.run(rc);
		} else if(type == RobotType.DRONE) {
			Drone.run(rc);
		} else if(type == RobotType.HANDWASHSTATION) {
			HandWashStation.run(rc);
		} else if(type == RobotType.HELIPAD) {
			Helipad.run(rc);
		} else if(type == RobotType.LAUNCHER) {
			Launcher.run(rc);
		} else if(type == RobotType.MINER) {
			Miner.run(rc);
		} else if(type == RobotType.MINERFACTORY) {
			MinerFactory.run(rc);
		} else if(type == RobotType.MISSILE) {
			Missile.run(rc);
		} else if(type == RobotType.SOLDIER) {
			Soldier.run(rc);
		} else if(type == RobotType.SUPPLYDEPOT) {
			SupplyDepot.run(rc);
		} else if(type == RobotType.TANK) {
			Tank.run(rc);
		} else if(type == RobotType.TANKFACTORY) {
			TankFactory.run(rc);
		} else if(type == RobotType.TECHNOLOGYINSTITUTE) {
			TechnologyInstitute.run(rc);
		} else if(type == RobotType.TOWER) {
			Tower.run(rc);
		} else if(type == RobotType.TRAININGFIELD) {
			TrainingField.run(rc);
		}
	}
	
}
