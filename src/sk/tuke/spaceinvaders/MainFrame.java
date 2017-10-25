package sk.tuke.spaceinvaders;

import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class MainFrame extends JFrame {
	public MainFrame() {
		super("Space Invaders");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//setUndecorated(true);
		setSize(Toolkit.getDefaultToolkit().getScreenSize());
		getContentPane().add(new SpacePanel());
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new MainFrame().setVisible(true);
			}
		});
	}
}
