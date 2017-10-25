package sk.tuke.spaceinvaders;

public abstract class ActorLives extends Actor {

	private int lives;
	private int init_lives;

	public ActorLives(double x, double y, double width, double height) {
		super(x, y, width, height);
		setLives(10);
	}

	public int getLives() {
		return lives;
	}

	public void setLives(int lives) {
		this.lives = lives;
		this.init_lives = lives;
	}

	public boolean isLive() {
		return lives > 0;
	}

	public boolean isDead() {
		return !isLive();
	}
	
	public void decLives() {
		lives--;
	}
	
	public int indicate() {
		return 255 - ((255 * lives) / init_lives);  
	}

}
