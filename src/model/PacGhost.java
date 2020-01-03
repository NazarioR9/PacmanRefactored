package model;

import java.util.List;

import event.PacmanEvent;
import state.AbstractState;
import state.GhostState;
import state.PacmanState;
import utilities.Constante;
import utilities.Utils;

public class PacGhost extends AbstractPacObject{
	private Direction[] choices;
	private int myIndex;
	public PacGhost(Game g, int i) {
		super(g);
		myIndex = i;
		start = new Coordinate(Constante.GHOSTS_START[i%4]);
		direction = Direction.Up;
		objectState = new GhostState(this); 
		choices = new Direction[] {
			Direction.Up, Direction.Down, Direction.Left, Direction.Right
		};
		
		back2Start();
	}

	@Override
	public void move() {
		List<PacmanEvent> events = objectState.move(this);
		if(events.size() < 2) {
			int rand = Utils.getRandomNumberInRange(0, choices.length-1);
			direction = choices[rand];
		}
	}
	
	@Override
	public void back2Start() {
		super.back2Start();
		direction = Direction.Up;
	}

	@Override
	public void manage() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public AbstractState getState() {
		return objectState;
	}
	
	public void setState(PacmanState s) {
		objectState = s;
	}

}
