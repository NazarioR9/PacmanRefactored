package event;

import model.Coordinate;

public class PacmanEvent {
	public static enum ChangeType {
		ENTER, LEAVE
	}

	private Coordinate coordinate;
	private ChangeType changeType;

	public PacmanEvent(Coordinate coordinate, ChangeType changeType) {
		this.coordinate = coordinate;
		this.changeType = changeType;
	}

	public ChangeType getChangeType() {
		return changeType;
	}

	public Coordinate getCoordinate() {
		return coordinate;
	}
}
