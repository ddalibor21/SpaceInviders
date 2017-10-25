package sk.tuke.spaceinvaders;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class Alien extends ActorLives {
	private static final double SPEED = 5;
	private static final double MAX_STEPS = 50;
	private static final double STEP_DOWN = 10;

	private double speedX = SPEED;

	private int step;

	private final Shape shape;

	public Alien(double x, double y, double width, double height) {
		super(x, y, width, height);
		shape = new Ellipse2D.Double(0, 0, getWidth(), getHeight());
	}

	@Override
	public void update() {
		if (step > MAX_STEPS) {
			step = 0;
			speedX = -speedX;
			setPosition(getX(), getY() + STEP_DOWN);
		} else {
			setPosition(getX() + speedX, getY());
			step++;
		}
		
		if(Math.random() < 0.005) {
			getSpace().addActor(new Bullet(getX() + 20, getY() + 50, 5, 10, 10));
		}
	}

	@Override
	public void paint(Graphics2D g2) {
		g2.setColor(new Color(indicate(),255-indicate(),0));
		g2.fill(shape);
	}
}
