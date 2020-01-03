package state;

import java.awt.Color;
import java.util.List;

import event.PacmanEvent;
import model.PacGhost;

public class GhostState extends PacObjectState{
	private PacGhost ghost;
	
	public GhostState(PacGhost gh) {
		color = Color.red;
		ghost = gh;
		gh.getGame().getPacman().register(this);
	}


	@Override
	public State getInnerState() {
		if(color == Color.blue) {
			return State.VULNERABLE;
		}
		return State.NORMAL;
	}
	
	
	@Override
	public void notify(List<PacmanEvent> events) {
		for(PacmanEvent e: events) {
			if(e.getChangeType() == PacmanEvent.ChangeType.LEAVE) {
				if(ghost.getGame().getPacman().getState().getInnerState() == State.NORMAL) {
					color = Color.red;
					scale = 1; //full speed
					if(e.getCoordinate().equals(ghost.getPoint())) {
						ghost.getGame().getPacman().die();
					}
				}else if(ghost.getGame().getPacman().getState().getInnerState() == State.SUPER) {
					color = Color.blue;
					scale = 0.5; //half speed
					if(e.getCoordinate().equals(ghost.getPoint())) {
						ghost.die();
					}
				}
			}
		}
	}
	

}
