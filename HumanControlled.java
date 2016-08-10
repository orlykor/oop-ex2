import java.awt.Image;



/**
 * This class represent the Human spaceship. 
 * Controlled by the user. 
 * 
 * @author orlykor12
 *
 */
public class HumanControlled extends SpaceShip{
	
	/** The image of the human spaceship*/
	public static final Image HUMAN_SHIP = oop.ex2.GameGUI.SPACESHIP_IMAGE;
	
	/** The image of the human spaceship with shield*/
	public static final Image HUMAN_WITH_SHIELD = oop.ex2.GameGUI.SPACESHIP_IMAGE_SHIELD;
	
	/** The acceleration boolean*/
	private static final boolean IS_ACCELERATE = false;
	
	private Image humanShip;
	
	private Image humanShipWithShield;
	
	private boolean isAccelerate;
	
	/**
	 * The constructor for the class
	 */
	public HumanControlled(){
		super();
		this.humanShip = HUMAN_SHIP;
		this.humanShipWithShield = HUMAN_WITH_SHIELD;
		this.isAccelerate = IS_ACCELERATE;

	}
	/**
	 * Does the action for one round of the game.
	 * The keys are: left-arrow and right-arrow to turn, up-arrow to accelerate, 'd' to fire a shot, 's' to 
	 * turn on the shield, 'a' to teleport.
	 * 
	 */
	public void doAction(SpaceWars game){
		shield = false;
		super.doAction(game);
		
		teleport(game);
		
		accelerate(game);
		
		shieldOn(game);
		
		fire(game);
	}
	
	/*
	 * Make the ship move towards the turn it points to, considering the keys that were pressed by the human
	 * player.
	 * 
	 */
	private void accelerate(SpaceWars game){

		if(game.getGUI().isLeftPressed()){
			physics.move(isAccelerate, LEFT);
		}
		else if(game.getGUI().isRightPressed()){
			physics.move(isAccelerate, RIGHT);
		}
		else if(game.getGUI().isUpPressed()){
			isAccelerate = true;
			physics.move(isAccelerate, STRIGHT);
		}
		else{
			isAccelerate = false;
			physics.move(isAccelerate, STRIGHT);
		}
	}
	
	/**
	 * Attempts to teleport if the 'a' key was pressed.
	 * 
	 * @param game
	 * @see SpaceShip#teleport()
	 */
	public void teleport(SpaceWars game){
		if(game.getGUI().isTeleportPressed()){
			super.teleport();
		}
	}
	
	
	/**
	 * Attempts to turn on the shield if the 's' key was pressed.
	 * 
	 * @param game
	 * @see SpaceShip#shieldOn()
	 */
	public void shieldOn(SpaceWars game) {
		if(game.getGUI().isShieldsPressed()){
			shield = true;
			super.shieldOn();
		}	
	}
	
	/**
	 * Attempts to fire a shot if the 'd' key was pressed.
	 * 
	 * @param game
	 * @see SpaceShip#fire() 
	 */
	public void fire(SpaceWars game){		
		if(game.getGUI().isShotPressed()){
			super.fire(game);
		}
	}
	
	/**
	 * Gets the image of this ship.
	 * 
	 * @see SpaceShip#getImage()
	 */
	public Image getImage(){
		if(shield){
			return humanShipWithShield;
		}
		else{
			return humanShip;
		}
	}


	
}
