import java.util.Random;


/**
 * 
 * This class represent the Drunkard spaceship. 
 *  Its pilot got onboard a little tipsy, so he doens't control his ship so well. 
 * @author orlykor12
 *
 */
public class Drunkard extends SpaceShip {
	
	/** The  limited rounds the ship can not teleport*/
	private static final int MAX_ROUNDS_TO_TELEPORT = 4;
	
	
	/**
	 * The constructor for the class
	 */
	public Drunkard(){
		super();
	}

	/**
	 * This ship will do randomly moves.
	 * Sometimes it will turn right left or none.
	 * Moreover, the pilot drunk so much, he doesn't notice when he pushes the teleport button.
	 * But when the pilot see a ship near him, he gets sober and try to shot at her direction and turn on 
	 * his shield.
	 * 
	 * @see SpaceShip#doAction()
	 */
	public void doAction(SpaceWars game){
		super.doAction(game);
		teleport();
		accelerate();
		drunkMoves(game);
		super.teleport();
	}

	/*
	 * the drunk moves the ship does
	 */
	private void drunkMoves(SpaceWars game) {
		if(physics.distanceFrom(closestShipPhysics) <= MIN_DISTANCE && 
				physics.angleTo(closestShipPhysics) < MIN_ANGLE){ 
			super.shieldOn();
			super.fire(game);			
		}
	}

	
	/*
	 * 
	 * make the ship move towards the turn it points to.
	 */
	private void accelerate(){
		int turn = randomTurn();
		physics.move(true,turn);
	}

	/*
	 * makes random turn for the ship.
	 * 
	 * @return turn the ship should turn. 1, -1 or 0.
	 */
	private int randomTurn() {
		Random myRandom = new Random();
		int turn = myRandom.nextInt(1) -1; //generate random turn for the ship
		return turn;
	}

	/**
	 * The ship tries to teleport once in 4 rounds
	 * 
	 * @see SpaceShip#teleport()
	 */
	public void teleport(){
		if(rounds == START_ROUND_SHOT){
			super.teleport();
		}
		rounds ++;
		if (rounds > MAX_ROUNDS_TO_TELEPORT){
			rounds = START_ROUND_SHOT;
		}
	}



}
