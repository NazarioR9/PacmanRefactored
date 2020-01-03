package gui;

import model.Game;

public class App {
	
	public static void sleep(int ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Game game = new Game();
		Gui g = new Gui(game);
		
		while(!game.noMoreGome() && !game.noMoreLife()) {
			game.play();
			g.paint();
			sleep(150);
		}
	}
}
