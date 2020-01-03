package state;

import java.awt.Color;

import model.PacmanObservable;

public class NormalState extends PacmanState{
	
	public NormalState(PacmanObservable pac) {
		super(pac);
		color = Color.yellow.darker();
	}

	@Override
	public Color getColor() {
		return color;
	}
	
	public State getInnerState() {
		return State.NORMAL;
	}
}
