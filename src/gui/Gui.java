package gui;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import model.Game;

public class Gui {
	
	Game game;
	JFrame frame;
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
		component = new Component(g);
		game = g;
		
		frame.setTitle("Pacman");
		frame.setVisible(true);
		frame.setSize(component.getDimension());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(component);
		frame.addKeyListener(listener);
	}
	
	public void paint() {
		component.repaint();
	}
}
