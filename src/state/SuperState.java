package state;

import java.awt.Color;

import model.PacmanObservable;

public class SuperState extends PacmanState{
	
	public SuperState(PacmanObservable pac) {
		super(pac);
		color = Color.yellow.brighter();
	}

	@Override
	public Color getColor() {
		return color;
	}
	
	public State getInnerState() {
		return State.SUPER;
	}
}
