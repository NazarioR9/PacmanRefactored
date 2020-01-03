

import javax.swing.JOptionPane;

import gui.Gui;
import model.Game;

public class App {
	Game game;
	Gui gui;
	
	public App() {
		game = new Game();
		gui = new Gui(game);
	}
	
	public void run() {
		game.play();
		
		String msg;
		if(game.noMoreLife()) msg = "Game Over";
		else msg = "You've won";
		
		ask2Play(msg);
	}
	
	public void ask2Play(String msg) {
		String response = JOptionPane.showInputDialog(null, msg+"\nPlay again ?\n y: yes  n: no");
		System.out.println("Response : " + response);
		
		if(response.equals("y")) {
			reset();
			run();
		}else if(response.equals("n")) {
			exit();
		}else {
			ask2Play("Didn't get ypur response.");
		}
	}
	
	public void reset() {
		game.totalReset();
	}
	
	public void exit() {
		gui.quit();
	}
	
	
	public static void main(String[] args) {
		App app = new App();
		app.run();
	}
}
