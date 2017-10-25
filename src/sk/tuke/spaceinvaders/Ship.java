package sk.tuke.spaceinvaders;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;

public class Ship extends ActorLives {
	private static final double SPEED = 10;
	private static final double FIRE_SPEED = 10;

	private double speedX = SPEED;

	private int step;

	private final Shape shape;

	public Ship(double x, double y, double width, double height) {
		super(x, y, width, height);
		shape = new Rectangle2D.Double(0, 0, getWidth(), getHeight());
	}

	@Override
	public void update() {
		if (getSpace().isKeyPressed(KeyEvent.VK_LEFT)) {
			setPosition(getX() - speedX, getY());
		} else if (getSpace().isKeyPressed(KeyEvent.VK_RIGHT)) {
			setPosition(getX() + speedX, getY());
		}
		if (getSpace().isKeyPressed(KeyEvent.VK_SPACE) && step > FIRE_SPEED) {
			getSpace().addActor(new Bullet(getX() + 50, 490, 5, 10, -20));
			step = 0;
		}
		step++;
	}

	@Override
	public void paint(Graphics2D g2) {
		g2.setColor(new Color(255 - indicate(), 0, indicate()));
		g2.fill(shape);
	}
}
