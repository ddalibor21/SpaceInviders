package sk.tuke.spaceinvaders;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class Bullet extends Actor {
	private double speedY;

	private final Shape shape;

	public Bullet(double x, double y, double width, double height, double speedY) {
		super(x, y, width, height);
		shape = new Rectangle2D.Double(0, 0, getWidth(), getHeight());
		this.speedY = speedY;
	}

	@Override
	public void update() {
		setPosition(getX(), getY() + speedY);
		
		if(getY() < 20 || getY() > 550) {
			getSpace().getActors().remove(this);
		}
		
		for (int index = 0; index < getSpace().getActors().size(); index++) {
			Actor actor = getSpace().getActors().get(index);
			if(actor != this && intersects(actor)) {
				if(actor instanceof ActorLives) {
					ActorLives ac = (ActorLives) actor;
					ac.decLives();
					
					if(ac.isDead())
						getSpace().getActors().remove(actor);
						
				} else
					getSpace().getActors().remove(actor);
				
				getSpace().getActors().remove(this);
				return;
			}
		}
	}

	@Override
	public void paint(Graphics2D g2) {
		g2.setColor(Color.YELLOW);
		g2.fill(shape);
	}
}
