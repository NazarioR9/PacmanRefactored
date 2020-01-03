package model;

import state.AbstractState;
import state.PacObjectState;
import utilities.Constante;

public abstract class AbstractPacObject {
	protected Coordinate point; //position on the map
	protected Coordinate start;
	protected Direction direction;
	protected PacObjectState objectState;
	private Game game;

	
	protected AbstractPacObject(Game g) {
		game = g;
	}
	
	public abstract void move();
	public abstract void manage();
	public abstract AbstractState getState();
	
	public Coordinate getPoint() {
		return new Coordinate(point);
	}
	
	public Game getGame() {
		return game;
	}
	
	public void die() {
		back2Start();
	}
	
	public void back2Start() {
		point = new Coordinate(start);
	}

	public Direction getDirection() {
		return direction;
	}
	
	public boolean inBounds(Coordinate p) {
		 int x = p.getX();
		 int y = p.getY();
		return (x >= 0) && (y >= 0) && (x < game.getWidth()) && (y < game.getHeight());
	}
	
	public boolean collision(Coordinate p) {		
		int i = p.getX();
		int j = p.getY();
		int[][] mat = game.getBlocksMap();
		
		return (mat[j][i] == 1) || (direction != Direction.Up && mat[j][i] == 3);
	}
	
	public boolean wraparound(Coordinate p) {
		for(int i = 0; i < Constante.STATIC_WRAPAROUND.length; i++) {
			if(p.equals(Constante.STATIC_WRAPAROUND[i])) {
				point = new Coordinate(Constante.DYNAMIC_WRAPAROUND[(i+1) % 2]);
				return true;
			}
		}
		return false;
	}

	public void setPoint(Coordinate coordinate) {
		point = coordinate;
	}

	
}
