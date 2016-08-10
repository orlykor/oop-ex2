

/**
 * 
 * It is used by the supplied driver to create all the spaceship objects
 * according to the command line arguments.
 * 
 * @author orlykor12
 *
 */
public class SpaceShipFactory {
	
	/** The argument for the human ship*/
	public static final String HUMAN = "h";
	
	/** The argument for the runner ship*/
	public static final String RUNNER = "r";
	
	/** The argument for the basher ship*/
	public static final String BASHER = "b";
	
	/** The argument for the aggressive ship*/
	public static final String Aggressive = "a";
	
	/** The argument for the drunkard ship*/
	public static final String DRUNKARD = "d";
	
	/** The argument for the special ship*/
	public static final String SPECIAL = "s";

	

	/**
	 * creates all the spaceship objects according to the command line arguments.
	 */
	public static SpaceShip[] createSpaceShips(String[] args) {
		SpaceShip[] spaceShips = new SpaceShip[args.length];
		
		for(int i=0; i< args.length; i++){
			switch(args[i]){
				case(HUMAN):
					spaceShips[i] = new HumanControlled();
					break;
					
				case(RUNNER):
					spaceShips[i] = new Runner();
					break;
					
				case(BASHER):
					spaceShips[i] = new Basher();
					break;
					
				case(Aggressive):
					spaceShips[i] = new Aggressive();
					break;
					
				case(DRUNKARD):
					spaceShips[i] = new Drunkard();
					break;
					
				case(SPECIAL):
					spaceShips[i] = new Special();
					break;
					
				default:
					return null;					
			}
		}
		return spaceShips;
	}
}
