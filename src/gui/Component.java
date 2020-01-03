package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JComponent;

import model.AbstractPacObject;
import model.Game;
import model.PacGhost;

public class Component extends JComponent{
	private Game game;
	private Dimension dimension;
	private int pixelSize;
	double scale ,add;
	Color[] gomesColors;
	
	public Component(Game g) {
		pixelSize = 30;
		scale = 0.25;
		add = 0.375;
		game = g;
		dimension = new Dimension(g.getWidth()*pixelSize, g.getHeigth()*pixelSize);
		gomesColors = new Color[]{Color.blue, Color.magenta, Color.orange, Color.green};
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		drawBlocks(game.getBlocksMap(), g);
		drawGomes(game.getGomesMap(), g);
		
		drawObject(game.getPacman(), g);
		for(PacGhost ghost: game.getPacGhosts()) {
			drawObject(ghost, g);
		}
	}
	
	public void drawObject(AbstractPacObject obj, Graphics g) {
		int width = game.getWidth(), heigth = game.getHeigth();
		
		g.setColor(obj.getState().getColor());
		g.fillOval(obj.getPoint().getX()*pixelSize, obj.getPoint().getY()*pixelSize, pixelSize, pixelSize);
		
	}
	
	public void drawBlocks(int[][] map, Graphics g) {
		for(int i = 0; i < map.length; i++) {
			for(int j = 0; j < map[i].length; j++) {
				if(map[i][j] == 1) {
					g.setColor(Color.black);
					g.fill3DRect(j*pixelSize, i*pixelSize, pixelSize, pixelSize, true);
				}else if(map[i][j] == 3) {
					g.setColor(Color.red);
					g.drawLine(j*pixelSize, i*pixelSize, (j+1)*pixelSize, i*pixelSize);
				}
			}
		}
	}
	
	public void drawGomes(int[][] map, Graphics g) {
		for(int i = 0; i < map.length; i++) {
			for(int j = 0; j < map[i].length; j++) {
				int v = map[i][j];
				if(v != 0) {
					g.setColor(gomesColors[v-1]);
					g.fillOval((int)((j+add)*pixelSize), (int)((i+add)*pixelSize), (int)(scale*pixelSize), (int)(scale*pixelSize));
				}
			}
		}
	}
	
	public Dimension getDimension() {
		return dimension;
	}
}
