package model;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import event.PacmanEvent;
import state.AbstractState.State;
import state.InvisibleState;
import state.PacmanState;
import state.SuperState;
import utilities.Constante;
import utilities.Utils;



public class Game implements PacmanObserver{
	private int score;
	private int mapIndex;
	private int nbGhosts;
	private int width, heigth;
	private int PAC_START_LIFE;
	private boolean over5000;
	private PacmanObservable pacman;
	private List<PacGhost> ghosts;
	
	private int[][] blocksMap;
	private int[][] gomesMap;
	
	
	public Game() {
		score = 0;
		mapIndex = 0;
		nbGhosts = 4;
		PAC_START_LIFE = 3;
		over5000 = false;
		pacman = new PacmanObservable(this);
		ghosts = new ArrayList<PacGhost>();
		for(int i = 0; i < nbGhosts; i++) {
			ghosts.add(new PacGhost(this, i));
		}
		
		blocksMap = Constante.blocksMaps[mapIndex];
		gomesMap = Utils.buildGomeMap(mapIndex);
		
		width = blocksMap[0].length;
		heigth = blocksMap.length;
		
		pacman.register(this);
	}
	
	public void play() {
		pacman.move();
		for(PacGhost ghost: ghosts) {
			ghost.move();
		}
	}
	
	
	
	@Override
	public void notify(List<PacmanEvent> events) {
		for(PacmanEvent e: events) {
			if(e.getChangeType() == PacmanEvent.ChangeType.LEAVE) {
				Coordinate c = e.getCoordinate();
				int value = gomesMap[c.getY()][c.getX()];
				if(value != 0) {
					gomesMap[c.getY()][c.getX()] = 0;
					switch(value){
						case 1:
							score += 100;
							break;
						case 2:
							score += 300;
							pacman.setState(new InvisibleState(pacman));
							break;
						case 3:
							score += 500;
							pacman.setState(new SuperState(pacman));
							break;
						case 4:
							score += 500;
							int index = mapIndex;
							mapIndex++;
							mapIndex %= Constante.blocksMaps.length;
							partialReset();
							int count = howMuchBaseGomes();
							resetMaps(count, index);
							break;
					}
				}
				for(PacGhost ghost: ghosts) {
					if(ghost.getPoint().equals(e.getCoordinate()) && 
							pacman.getState().getInnerState() == State.NORMAL) {
						partialReset();
						return;
					}
				}
				if((score >= 5000) && !over5000) {
					over5000 = true;
					pacman.addLife();
				}
			}
		}
	}
	
	public boolean noMoreGome() {
		for(int i = 0; i < gomesMap.length; i++) {
			for(int j = 0; j < gomesMap[i].length; j++) {
				if(gomesMap[i][j] != 0) return false;
			}
		}
		return true;
	}
	
	public boolean noMoreLife() {
		return !pacman.alive();
	}
	
	public int wraparound(Coordinate p) {
		for(int i = 0; i < Constante.STATIC_WRAPAROUND.length; i++) {
			if(p.equals(Constante.STATIC_WRAPAROUND[i])) {
				return i;
			}
		}
		return -1;
	}
	
	
	public void totalReset() {
		mapIndex = 0;
		score = 0;
		over5000 = false;
		pacman.resetLife();
		partialReset();
		resetMaps();
	}
	
	public void partialReset() {
		pacman.back2Start();
		for(PacGhost ghost: ghosts) {
			ghost.back2Start();
		}
		Utils.sleep(200);
	}

	private void resetMaps() {
		blocksMap = Utils.clone2DMatrix(Constante.blocksMaps[mapIndex]);
		gomesMap = Utils.buildGomeMap(mapIndex);
	}
	
	private void resetMaps(int cnt, int index) {
		blocksMap = Utils.clone2DMatrix(Constante.blocksMaps[mapIndex]);
		gomesMap = Utils.makeGomesForNewMapV2(blocksMap, gomesMap, cnt, mapIndex, index);
	}

	private int howMuchBaseGomes() {
		int x = gomesMap.length;
		int y = gomesMap[0].length;
		int cnt = 0;
		for(int i = 0; i < x; i++) {
			for(int j = 0; j < y; j++) {
				if(gomesMap[i][j] == 1) cnt++;
			}
		}
		
		return cnt;
	}
	
	public PacmanObservable getPacman() {
		return pacman;
	}
	
	public List<PacGhost> getPacGhosts(){
		return new ArrayList<PacGhost>(ghosts);
	}

	public int getWidth() {
		return width;
	}

	public int getHeigth() {
		return heigth;
	}

	public int getScore() {
		return score;
	}
	
	public int[][] getBlocksMap() {
		return Utils.clone2DMatrix(blocksMap);
	}
	
	public int[][] getGomesMap() {
		return Utils.clone2DMatrix(gomesMap);
	}
	
}
