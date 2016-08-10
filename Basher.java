
/**
 * This class represent the Basher spaceship. 
 * This ship attempts to collide with other ships. 
 * 
 * @author orly
 *
 */
public class Basher extends SpaceShip{

	/**
	 * The constructor for the class
	 */
	public Basher(){
		super();
	}
	
	/**
	 * This ship will always accelerate, and will constantly turn towards the closest ship. 
	 * 
	 * @see SpaceShip#doAction(SpaceWars)
	 */
	public void doAction(SpaceWars game) {
		
		super.doAction(game);
		accelerate();	
		shieldOn();		
	}
	
	/*
	 * Makes the ship move right the way to the closest ship.
	 *  
	 */
	private void accelerate(){
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
	 * If it gets within a distance of 0.2 units from another ship, it will 
	 * attempt to turn on its shields.
	 * 
	 *@see SpaceShip#shieldOn()
	 * 
	 */
	public void shieldOn(){
		
		if(physics.distanceFrom(closestShipPhysics) <= MIN_DISTANCE){
			super.shieldOn();
		}
	}
}
