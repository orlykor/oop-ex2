

/**
 * This class represent the Runner spaceship. 
 * This spaceship attempts to run away from the fight. It will always accelerate, and
 * will constantly turn away from the closest ship. 
 * @author orlykor12
 *
 */
public class Runner extends SpaceShip{

	/**
	 * The constructor for the class
	 */
	public Runner(){
		super();
	}

	/**
	 * If the nearest ship is closer than 0.2 units, and if its angle to the Runner is less than 0.2 
	 * radiant, the Runner feels threatened and will attempt to teleport.
	 * 
	 */
	public void doAction(SpaceWars game){

		super.doAction(game);
		teleport();		
		accelerate();					
	}


	/*
	 * Makes the ship move the opposite way from the closest ship.
	 *  
	 */
	private void accelerate() {
		if(physics.angleTo(closestShipPhysics) < 0){
			physics.move(true, LEFT);
		}
		else if(physics.angleTo(closestShipPhysics) > 0){
			physics.move(true, RIGHT);
		}
		else{
			physics.move(true, STRIGHT);
		}
	}


	/**
	 * The ship tries to teleport when the nearest ship is closer than 0.2 units and its angle to the Runner  
	 * is less than 0.2 radiant.
	 * 
	 * @see SpaceShip#teleport()
	 */
	public void teleport(){
		if(physics.distanceFrom(closestShipPhysics) <= MIN_DISTANCE && 
				physics.angleTo(closestShipPhysics) < MIN_ANGLE){
			super.teleport();
		}
	}

}
