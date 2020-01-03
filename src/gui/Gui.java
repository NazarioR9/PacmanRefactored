package gui;

import java.awt.BorderLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import event.PacmanEvent;
import model.Game;
import model.PacmanObserver;
import utilities.Utils;

public class Gui implements PacmanObserver{
	
	Game game;
	JFrame frame;
	private JLabel label;
	Component component;
	public KeyListener listener = new KeyAdapter() {
		@Override
		public void keyPressed(KeyEvent e) {
			super.keyPressed(e);
			int key = e.getKeyCode();
			game.getPacman().getKey(key);
		}
	};
	
	public Gui(Game g) {
		frame = new JFrame();
		JPanel container = new JPanel();
		label = new JLabel();
		component = new Component(g);
		game = g;
		
		game.getPacman().register(this);
		
		label.setVisible(true);
		actualiseLabel();
		
		container.setLayout(new BorderLayout(0,2));
		container.add(label, BorderLayout.NORTH);
		container.add(component, BorderLayout.CENTER);
		
		frame.setTitle("Pacman");
		frame.setVisible(true);
		frame.setSize(component.getDimension());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(container);
		frame.addKeyListener(listener);
	}
	
	public void paint() {
		actualiseLabel();
		component.repaint();
		Utils.sleep(150);
	}
	
	public void actualiseLabel() {
		label.setText("Score : " + game.getScore() + "  Life: " + game.getPacman().getLife());
	}
	
	public void quit() {
		frame.dispose();
	}

	@Override
	public void notify(List<PacmanEvent> events) {
		paint();
	}
}
