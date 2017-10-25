package sk.tuke.spaceinvaders;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;

public class Shield extends ActorLives {
	private final Shape shape;

	public Shield(double x, double y, double width, double height) {
		super(x, y, width, height);
		shape = new Rectangle2D.Double(0, 0, getWidth(), getHeight());
	}

	@Override
	public void update() {
	}

	@Override
	public void paint(Graphics2D g2) {
		g2.setColor(new Color(0, indicate(), 255 - indicate()));
		g2.fill(shape);
	}
}
