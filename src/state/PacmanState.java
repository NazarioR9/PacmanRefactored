package state;

import java.awt.Color;
import java.util.List;

import event.PacmanEvent;
import model.PacmanObservable;
import model.PacmanObserver;

public abstract class PacmanState extends PacObjectState{
	protected static final int TIMEOUT = 50;
	protected int timer;
	protected PacmanObservable pacman;

	protected PacmanState(PacmanObservable pac) {
		pacman = pac;
		timer = TIMEOUT;
		pac.register(this);
	}
	
	@Override
	public void notify(List<PacmanEvent> events) {
		if(getInnerState() != State.NORMAL) {
			timer--;
			if(timer == 0) {
				pacman.unregister(this);
				pacman.setState(new NormalState(pacman));
			}
		}
	}
	
	public abstract Color getColor();
	public abstract State getInnerState();

}

