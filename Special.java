import java.awt.Image;
import javax.swing.ImageIcon;

/**
 * 
 * This class represent the Special spaceship. 
 * This ship is different from the other for it plays a role as a pikachu ship. 
 *   
 * 
 * @author orlykor12
 */
public class Special extends SpaceShip {
	
	/** The image of the special spaceship*/
	public final Image SPECIAL_IMAGE = createImageIcon("pokemon.gif","");
	
	/** The image with shield of the special spaceship*/
	public static final Image SPECIAL_IMAGE_WITH_SHIELD = createImageIcon("pocadur.gif","");
	
	/** The first round the ship turn on it's shield*/
	private static final int START = 2;
	
	/** The limited rounds the ship cannot turn on its shield*/
	private static final int MIN_ROUNDS = 6;
	
	/** The initialize value for the rounds */
	private static final int INITIALIZE_ROUNDS = 0;
	
	
	/**
	 * The constructor for the class
	 */
	public Special(){
		super();
	}

	/**
	 * The pikachu ship is not stupid, when there's a ship close to it, it tries to fire at them and then 
	 * teleports away from there. 
	 * Then it gets very angry and try to attack, by getting closer to the nearest ship
	 * and try to destroy it. the pikachu ship is Indestructible, so its not afraid to turn on its shield 
	 * whenever it wants.
	 *
	 */
	public void doAction(SpaceWars game){
		super.doAction(game);
		physics.move(true, STRIGHT);		
		closeToShip(game);		
		attackShip(game);

	}
	/*
	 * when nearest ship angle is less than 0.2 radiant, the special ship will turn towards it, will attempt 
	 * to turn on its shield and will attempt to fire a shot.
	 * it will attack the closest ship to it.
	 */
	private void attackShip(SpaceWars game) {
		if(physics.angleTo(closestShipPhysics) < MIN_ANGLE ){
			physics.move(true, RIGHT);
			shieldOn();
			super.fire(game);

		}
	}

	/*
	 * when nearest ship is closer than 0.2 radiant, and the ship feels threatened, it'll try to escape and 
	 * at the same time attempt to fire a shot. 
	 */
	private void closeToShip(SpaceWars game) {
		if(physics.distanceFrom(closestShipPhysics) < MIN_DISTANCE){	
			physics.move(true, STRIGHT);
			super.teleport();
			super.fire(game);
			shield = false;
		}
	}
	
	/**
	 * Attempts to turn on the shield every once in a while.
	 * 
	 * @param game
	 * @see SpaceShip#shieldOn()
	 */
	public void shieldOn(){
		if(rounds == START){
			super.shieldOn();
		}
		rounds ++;
		if (rounds > MIN_ROUNDS){
			rounds = INITIALIZE_ROUNDS;
		}
	}
	
	/**
	 * The ship cannot die, so it will always return false.
	 * 
	 * @return false
	 * @see SpaceShip#isDead()
	 */
	public boolean isDead() {
		return false;
	}

	/**
	 * Gets the image of this ship.
	 * 
	 * @return the image of the ship.
	 * @see SpaceShip#getImage()
	 */
	public Image getImage(){
		if(shield){
			return SPECIAL_IMAGE_WITH_SHIELD;
		}
		else{
			return SPECIAL_IMAGE;
		}
	}

	/**
	 * 
	 * Get the Image Icon from the given path (relative to the source code).
	 * 
	 * @param path the relative path to the image file.
	 * @param description A description of the file.
	 * @return the icon with the image.
	 */
	private static Image createImageIcon(String path, String description) {
		java.net.URL imgURL = Special.class.getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL, description).getImage();
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}

}

