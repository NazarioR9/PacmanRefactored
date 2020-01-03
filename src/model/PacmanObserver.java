package model;

import java.util.List;

import event.PacmanEvent;

public interface PacmanObserver {
	public void notify(List<PacmanEvent> events);
}
