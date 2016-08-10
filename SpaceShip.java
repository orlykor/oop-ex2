import java.awt.Image;
import oop.ex2.*;

/**
 * The API spaceships need to implement for the SpaceWars game. 
 * It is your decision whether SpaceShip.java will be an interface, an abstract class,
 *  a base class for the other spaceships or any other option you will choose.
 *  
 * @author oop
 */

public class SpaceShip{

	/** The health of the spaceship*/
	protected static final int HEALTH = 20; 

	/** The current energy of the spaceship*/
	protected static final int CURR_ENERGY = 200;

	/** The max energy of the spaceship*/
	protected static final int MAX_ENERGY = 200;

	/** The amount of rounds the spaceship does*/
	protected static final int ROUNDS = 0;

	/** The shield of the spaceship*/
	protected static final boolean SHIELD = false;

	/** The image of the enemy spaceship*/
	public static final Image ENEMY_IMAGE = oop.ex2.GameGUI.ENEMY_SPACESHIP_IMAGE;

	/** The image with shield of the enemy spaceship*/
	public static final Image ENEMY_IMAGE_WITH_SHIELD = oop.ex2.GameGUI.ENEMY_SPACESHIP_IMAGE_SHIELD;

	/** The min energy needed to teleport*/
	private static final int ENERGY_TO_TELEPORT = 150;

	/** The min energy needed to turn on shield*/
	private static final int ENERGY_TO_SHIELD = 3;

	/** The min energy needed to fire a shot*/
	private static final int ENERGY_TO_FIRE = 20;

	/** The first round the ship fired a shot*/
	protected static final int START_ROUND_SHOT = 0;

	/** The limited rounds the ship cannot fire a shot*/
	private static final int MAX_ROUNDS_SHOT = 8;

	/** The energy that the ship gets if there was a collision with a shield*/
	private static final int COLLIDE_SHIELD_ENERGY = 20;

	/** The health is reduced by 1*/
	private static final int REDUCE_LIFE = 1;

	/** The amount of energy that the ship loses when its without its shield*/
	private static final int NO_SHIELD_ENERGY = 10;

	/** The ships has no life left*/
	private static final int NO_LIFE = 0;
	
	/** The ship takes no turns*/
	protected static final int STRIGHT = 0;
	
	/** The ship turn left*/
	protected static final int LEFT = 1;
	
	/** The ship turn right*/
	protected static final int RIGHT = 1;
	
	/** The minimal distance between two ships*/
	protected static final double MIN_DISTANCE = 0.2;
	
	/** The minimal angle between two ships*/
	protected static final double MIN_ANGLE = 0.2;

	public Image enemyImage;

	public Image enemyImageWithShield;

	protected int currEnergy;

	private int health;

	private int maxEnergy;

	protected int rounds;

	protected boolean shield;

	protected SpaceShipPhysics physics;

	protected SpaceShipPhysics closestShipPhysics;



	/**
	 * the constructor of the class
	 */
	public SpaceShip(){

		this.currEnergy = CURR_ENERGY;
		this.maxEnergy = MAX_ENERGY;
		this.rounds = ROUNDS;
		this.health = HEALTH;
		this.shield = SHIELD;
		this.enemyImage = ENEMY_IMAGE;
		this.enemyImageWithShield = ENEMY_IMAGE_WITH_SHIELD;
		this.physics = new SpaceShipPhysics();
		this.closestShipPhysics = new SpaceShipPhysics();


	}	



	/**
	 * Does the actions of this ship for this round. 
	 * This is called once per round by the SpaceWars game driver.
	 * 
	 * @param game the game object to which this ship belongs.
	 */
	public void doAction(SpaceWars game) {
		SpaceShip closestShip = game.getClosestShipTo(this);
		closestShipPhysics = closestShip.getPhysics();
		if (currEnergy < maxEnergy){
			currEnergy ++;
		}
	}


	/**
	 * Attempts to teleport.
	 */
	public void teleport() {    
		if(currEnergy >= ENERGY_TO_TELEPORT){
			physics = new SpaceShipPhysics();
			currEnergy -= ENERGY_TO_TELEPORT;			
		}	
	}

	/**
	 * Attempts to turn on the shield.
	 */
	public void shieldOn() {
		if(currEnergy >= ENERGY_TO_SHIELD){
			shield = true;
			currEnergy -= ENERGY_TO_SHIELD;
		}
		else{
			shield = false;
		}
	}


	/**
	 * Attempts to fire a shot.
	 * 
	 * @param game the game object.
	 */
	//TODO magic num!!
	public void fire(SpaceWars game) {
		if(currEnergy >= ENERGY_TO_FIRE && this.rounds == START_ROUND_SHOT){
			game.addShot(physics);
			currEnergy -= ENERGY_TO_FIRE;
			this.rounds ++;

		}else{
			this.rounds ++;
			if(this.rounds == MAX_ROUNDS_SHOT){
				this.rounds = START_ROUND_SHOT;
			}
		}
	}


	/**
	 * This method is called every time a collision with this ship occurs
	 *  checks whether it has a collision with the ship's shields on or not.
	 *  considered that it changes the ships health and energy.
	 */
	public void collidedWithAnotherShip(){
		if(shield){
			currEnergy += COLLIDE_SHIELD_ENERGY;
			maxEnergy += COLLIDE_SHIELD_ENERGY;
		}
		else{
			reduceEnergey();   		
		}    		
	}

	/*
	 * when a collide was made without the shields then we need to reduce the
	 * energy and change it to the max energy. 
	 */
	private void reduceEnergey(){
		health -= REDUCE_LIFE;
		maxEnergy -= NO_SHIELD_ENERGY;
		if (currEnergy > maxEnergy){
			currEnergy = maxEnergy;

		}	

	}

	/** 
	 * This method is called whenever a ship has died. It resets the ship's 
	 * attributes, and starts it at a new random position.
	 */
	public void reset(){
		health = HEALTH;
		currEnergy = CURR_ENERGY;
		maxEnergy = MAX_ENERGY;


		physics = new SpaceShipPhysics();
	}


	/**
	 * Checks if this ship is dead.
	 * 
	 * @return true if the ship is dead. false otherwise.
	 */
	public boolean isDead() {
		if(health == NO_LIFE){
			return true;
		}
		return false;

	}

	/**
	 * Gets the physics object that controls this ship.
	 * 
	 * @return the physics object that controls the ship.
	 */
	public SpaceShipPhysics getPhysics() {
		return physics;
	}

	/**
	 * This method is called by the SpaceWars game object when ever this ship
	 * gets hit by a shot.
	 */
	public void gotHit() {
		if(!shield){
			reduceEnergey();
		}    		
	}

	/**
	 * Gets the image of this ship. This method should return the image of the
	 * ship with or without the shield. This will be displayed on the GUI at
	 * the end of the round.
	 * 
	 * @return the image of this ship.
	 */
	public Image getImage(){
		if(shield){
			return enemyImageWithShield;
		}
		else{
			return enemyImage;
		}
	}



}
