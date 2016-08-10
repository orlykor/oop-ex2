
/**
 * This class represent the Aggressive spaceship. 
 * this ship pursues other ships and tries to fire at them. It will always 
 * accelerate, and turn towards the nearest ship. 
 * 
 * @author orlykor12
 */
public class Aggressive extends SpaceShip {
	
	
	/**
	 * The constructor for the class
	 */
	public Aggressive(){
		super();
		
	}

	/**
	 * Does the actions of this ship for this round. 
	 * When the ships angle to the nearest ship is less than 0.2 radiant,
	 * it will try to fire and it always pursues the other ships.
	 * 
	 * @see SpaceShip#doAction(SpaceWars)
	 */
	public void doAction(SpaceWars game){
		
		super.doAction(game);
		accelerate();
		fire(game);
	}
	

	/*
	 * Makes the ship move according to the closest ship angle, and towards it.
	 *  
	 */
	private void accelerate() {
		if(physics.angleTo(closestShipPhysics) < 0){
			physics.move(true, RIGHT);
		}
		if(physics.angleTo(closestShipPhysics) > 0){
			physics.move(true, LEFT);
		}
		else{
		physics.move(true, STRIGHT);
		}
	}
	
	/**
	 * Attempts to fire a shot if the nearest ship is less than 0.2 radiant. 
	 * 
	 * @param game
	 * @see SpaceShip#fire() 
	 */
	public void fire(SpaceWars game) {
		if (physics.angleTo(closestShipPhysics) < MIN_ANGLE){
			super.fire(game);
		}	
	}
		

}
