package state;

import java.awt.Color;

public interface AbstractState {
	public enum State{NORMAL, INVISIBLE, SUPER, VULNERABLE};
	public State getInnerState();
	public Color getColor();
}
