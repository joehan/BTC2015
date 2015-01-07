package supplyDrone;

import battlecode.common.RobotController;
import battlecode.common.RobotInfo;
import battlecode.common.RobotType;

public class Drone extends Entity {

	public static void run(RobotController rc) {
		RobotType[] attackList = {RobotType.LAUNCHER, RobotType.BASHER,RobotType.SOLDIER,  RobotType.BEAVER};
		boolean enoughSupply = false;
		while(true) {
			try {
				if(rc.isWeaponReady()) {
					huntAttackList(attackList, rc);
				}
				if(rc.isCoreReady()) {
					if(enoughSupply) {
						RobotInfo[] give = rc.senseNearbyRobots(15, rc.getTeam());
						if(give.length > 0) {
							boolean good = true;
							for(RobotInfo bot : give) {
								if(bot.supplyLevel < bot.type.supplyUpkeep*30) {
									int trans = (int) (bot.type.supplyUpkeep*60 - bot.supplyLevel);
									if(rc.getSupplyLevel() >= trans) {
										rc.transferSupplies(trans, bot.location);
										good = false;
									} else {
										enoughSupply = false;
									}
								}
							}
							if(good) {
								RobotInfo[] mine = rc.senseNearbyRobots(Integer.MAX_VALUE, rc.getTeam());
								RobotInfo min = null;
								for(RobotInfo bot : mine) {
									if(bot.supplyLevel < bot.type.supplyUpkeep*20) {
										if(min == null || bot.supplyLevel < min.supplyLevel) {
											min = bot;
										}
									}
								}
								tryMove(rc.getLocation().directionTo(min.location), rc);
							}
						} else {
							RobotInfo[] mine = rc.senseNearbyRobots(Integer.MAX_VALUE, rc.getTeam());
							RobotInfo min = null;
							for(RobotInfo bot : mine) {
								if(bot.supplyLevel < bot.type.supplyUpkeep*30) {
									if(min == null || bot.supplyLevel < min.supplyLevel) {
										min = bot;
									}
								}
							}
							tryMove(rc.getLocation().directionTo(min.location), rc);
						}
					} else {
						tryMove(rc.getLocation().directionTo(rc.senseHQLocation()), rc);
						if(rc.getLocation().distanceSquaredTo(rc.senseHQLocation()) < 15) {
							enoughSupply = true;
						}
					}
				}
			} catch (Exception e) {
				System.out.println("Drone Exception");
	            e.printStackTrace();
			}
			rc.yield();
		}
	}
	
}
