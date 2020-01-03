package state;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import event.PacmanEvent;
import model.AbstractPacObject;
import model.Coordinate;
import model.Direction;
import model.PacmanObserver;
import utilities.Constante;

public  abstract class PacObjectState implements PacmanObserver, AbstractState{
	protected Color color;
	protected double scale = 1;

	@Override
	public Color getColor() {
		return color;
	}
	
	public List<PacmanEvent> move(AbstractPacObject pacObject) {
		List<PacmanEvent> events = new ArrayList<>();
		Coordinate point = pacObject.getPoint();
		Direction direction = pacObject.getDirection();
		
		int x = (int) (point.getX()+direction.getX()*scale);
		int y = (int) (point.getY()+direction.getY()*scale);
		Coordinate actualPoint = new Coordinate(x,y);
		
		if(!point.equals(actualPoint)) {
			events.add(new PacmanEvent(point, PacmanEvent.ChangeType.LEAVE));
			if(pacObject.inBounds(actualPoint)) {
				if(!pacObject.collision(actualPoint)) {
					events.add(new PacmanEvent(actualPoint, PacmanEvent.ChangeType.ENTER));
					pacObject.setPoint(new Coordinate(actualPoint));
				}
			}else {
				int r = pacObject.getGame().wraparound(actualPoint);
				if(r != -1) {
					point = new Coordinate(Constante.DYNAMIC_WRAPAROUND[(r+1) % 2]);
					events.add(new PacmanEvent(point, PacmanEvent.ChangeType.ENTER));
					pacObject.setPoint(point);
				}
			}
		}
		
		return events;
	}

}
