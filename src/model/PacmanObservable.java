package model;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;

import event.PacmanEvent;
import state.NormalState;
import state.PacObjectState;
import state.PacmanState;

public class PacmanObservable extends AbstractPacObject{
	private List<PacmanObserver> observers;
	private int life;
	
	public PacmanObservable(Game g) {
		super(g);
		start = new Coordinate(16,15);
		observers = new ArrayList<PacmanObserver>();
		objectState = new NormalState(this);
		direction = Direction.None;
		life = 3;
				
		back2Start(); //set 'point' to 'start'
	}
	
	public void register(PacmanObserver o) {
		observers.add(o);
	}
	
	public void unregister(PacmanObserver o) {
		observers.remove(o);
	}
	
	private void notifyObserver(List<PacmanEvent> events) throws ConcurrentModificationException{
		for (PacmanObserver pacmanObserver : observers) {
			pacmanObserver.notify(events);
		}
	}

	@Override
	public void move() {
		List<PacmanEvent> events = objectState.move(this);
		try {
			notifyObserver(events);
		}catch(ConcurrentModificationException cme) {
			
		}
	}
	
	@Override
	public void back2Start() {
		super.back2Start();
		direction = Direction.None;
	}
	
	public void getKey(int key) {
		if(key == KeyEvent.VK_UP) {direction = Direction.Up;}
		else if(key == KeyEvent.VK_DOWN) {direction = Direction.Down;}
		else if(key == KeyEvent.VK_LEFT) {direction = Direction.Left;}
		else if(key == KeyEvent.VK_RIGHT) {direction = Direction.Right;}
	}

	public PacObjectState getState() {
		return objectState;
	}

	public void setState(PacmanState pacmanState) {
		unregister(objectState);
		this.objectState = pacmanState;
	}

	public void die() {
		super.die();
		life--;
		direction = Direction.None;
	}

	public boolean alive() {
		return life > 0;
	}

	public void resetLife() {
		life = 3;
	}

	public int getLife() {
		return life;
	}
	
	public void addLife() {
		life++;
	}
	
}
