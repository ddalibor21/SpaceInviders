package sk.tuke.spaceinvaders;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.swing.JPanel;
import javax.swing.Timer;

public class SpacePanel extends JPanel {
	private static final int TICK = 50;

	private List<Actor> actors = new ArrayList<>();

	private Set<Integer> pressedKeys = new HashSet<>();
	private int moveStars;

	public SpacePanel() {

		setFocusable(true);
		requestFocus();
		Timer timer = new Timer(TICK, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				repaint();
			}
		});

		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				pressedKeys.add(e.getKeyCode());
			}

			@Override
			public void keyReleased(KeyEvent e) {
				pressedKeys.remove(e.getKeyCode());
			}
		});

		timer.start();

		initActors();
	}

	private void initActors() {
		addActor(new Ship(300, 500, 100, 20));
		for (int i = 0; i < 6; i++) {
			Alien a = new Alien(i * 200, 30, 50, 50);
			a.setLives(5);
			addActor(a);

			Shield s = new Shield(i * 250, 400, 150, 20);
			s.setLives(5);
			addActor(s);
		}
	}

	public List<Actor> getActors() {
		return actors;
	}

	public void addActor(Actor actor) {
		actors.add(actor);
		actor.setSpace(this);
	}

	public void removeActor(Actor actor) {
		actors.remove(actor);
		actor.setSpace(null);
	}

	public boolean isKeyPressed(int keyCode) {
		return pressedKeys.contains(keyCode);
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;

		paintBackground(g2);

		for (int index = 0; index < actors.size(); index++) {
			Actor actor = actors.get(index);
			actor.update();

			AffineTransform at = g2.getTransform();
			g2.translate(actor.getX(), actor.getY());
			actor.paint(g2);
			g2.setTransform(at);
		}

	}

	private void paintBackground(Graphics2D g2) {
		g2.setColor(Color.BLACK);
		g2.fillRect(0, 0, getWidth(), getHeight());
		g2.setColor(Color.GRAY);

		Random rnd = new Random(0);
		for (int i = 0; i < 500; i++) {
			int x = rnd.nextInt(getWidth());
			int y = (rnd.nextInt(getHeight())+moveStars) % getHeight();
			g2.fillOval(x, y, 2, 2);
		}
		moveStars++;
	}

}
