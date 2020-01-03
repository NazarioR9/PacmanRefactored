package state;

import java.awt.Color;
import java.util.List;

import event.PacmanEvent;
import model.PacmanObservable;

public class InvisibleState extends PacmanState{
	
	public InvisibleState(PacmanObservable pac) {
		super(pac);
		color = Color.yellow.brighter();
	}

	@Override
	public Color getColor() {
		return color;
	}
	
	public State getInnerState() {
		return State.INVISIBLE;
	}
}
