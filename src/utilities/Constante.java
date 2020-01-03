package utilities;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;

import model.Coordinate;

public class Constante {
	public static final int BLOCK_SIZE = 30;
	public static final Dimension DIMENSION = new Dimension(BLOCK_SIZE*20,BLOCK_SIZE*17);
	public static final int UNIT = 30; //duration of a special state
	public static final int JAIL_TIME_UNIT = UNIT/3;
	public static final int STD_VELOCITY = BLOCK_SIZE;
	public static final int SLOW_VELOCITY = STD_VELOCITY; // have to divide by 2
	public static final Color[] GHOSTSCOLORS = {Color.cyan, Color.darkGray, Color.pink, Color.red};
	public static final Color[] GOMESCOLORS = {Color.blue, Color.magenta, Color.orange, Color.green};
	public static final double[] SCALES = {0.3, 0.5, 0.5, 0.5};
	public static Coordinate[] STATIC_WRAPAROUND = {
			new Coordinate(-1,8),
			new Coordinate(20, 8)};
	public static Coordinate[] DYNAMIC_WRAPAROUND = {
			new Coordinate(0, 8),
			new Coordinate(19, 8)};
	public static Point PAC_START = new Point(16*BLOCK_SIZE, 15*BLOCK_SIZE);
	public static Coordinate[] GHOSTS_START = {
			new Coordinate(9, 7),
			new Coordinate(10, 7),
			new Coordinate(9, 8),
			new Coordinate(10, 8)};

	public static int[][] xs = {
			{16, 16, 8, 0, 0, 2, 4, 10},
			{15, 15, 8, 1, 1, 5, 6, 9}
	};
	public static int[][] ys = {
			{5, 7, 4, 1, 19, 6, 17, 18},
			{5, 7, 2, 1, 18, 9, 14, 18}
	};
	public static int[][] vs = {
			{3, 4,  3,  3, 2,  3, 2, 3},
			{3, 4,  3,  3, 2,  3, 2, 3}
	};
	public static int[][] blocksMap =  {
			{0,0,1,0,0,0,0,0,1,1,0,1,0,0,0,0,1,0,0,0},
			{0,1,0,0,1,1,1,0,0,0,0,0,0,0,1,0,0,0,0,0},
			{0,0,0,0,1,0,0,0,1,1,0,0,1,0,0,1,0,1,0,0},
			{1,1,0,0,1,0,1,0,1,0,0,0,0,0,0,0,0,0,0,1},
			{1,0,0,0,0,0,0,0,0,0,1,0,0,1,0,0,1,0,0,0},
			{0,0,1,0,1,0,0,0,0,0,0,0,0,0,0,1,1,1,0,0},
			{1,0,0,0,0,0,0,1,1,3,3,1,1,0,0,0,1,0,0,1},
			{1,1,0,0,0,0,0,1,-1,-1,-1,-1,1,0,0,0,0,0,1,1},
			{0,0,0,1,0,1,0,1,-1,-1,-1,-1,1,0,1,1,1,0,0,0},
			{1,1,0,0,0,0,0,1,1,1,1,1,1,0,0,1,0,0,1,1},
			{1,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			{0,0,1,0,0,0,1,0,0,1,1,0,0,1,0,0,1,1,0,0},
			{0,0,0,0,1,0,0,0,1,0,0,1,0,0,0,0,0,0,0,0},
			{0,1,0,0,0,0,0,1,0,0,0,0,1,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,0},
			{1,0,1,0,1,1,0,1,1,0,0,0,0,0,0,1,-1,0,1,0},
			{1,0,0,0,1,0,0,0,1,0,0,1,0,0,0,0,0,0,0,0}
		};
	public static int[][] blocksMap0 =  {
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
			{1,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,1},
			{1,0,1,0,0,0,1,0,0,0,0,0,0,0,0,0,1,0,0,1},
			{1,0,0,0,1,0,0,0,0,0,1,0,1,0,0,0,0,0,0,1},
			{1,0,0,0,0,0,0,0,1,0,0,0,0,0,1,0,0,0,0,1},
			{1,1,0,0,1,0,0,0,0,0,0,0,0,0,0,0,1,0,0,1},
			{1,0,0,0,1,0,0,1,1,3,3,1,1,0,0,0,1,0,0,1},
			{1,0,1,0,0,0,0,1,-1,-1,-1,-1,1,0,1,0,0,0,0,1},
			{0,0,0,0,1,1,0,1,-1,-1,-1,-1,1,0,1,1,0,0,0,0},
			{1,0,1,0,0,0,0,1,1,1,1,1,1,0,0,0,0,0,0,1},
			{1,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1},
			{1,0,1,0,0,0,0,0,1,0,1,0,1,0,0,0,0,0,0,1},
			{1,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,1},
			{1,1,0,0,1,0,0,0,0,1,0,1,0,1,0,0,0,0,0,1},
			{1,0,0,0,0,0,1,0,0,0,0,0,0,0,0,1,1,1,0,1},
		    {1,0,0,0,0,0,0,0,0,0,0,0,1,0,0,1,-1,0,0,1},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
		};
	public static int[][][] blocksMaps =  {
			blocksMap,
			blocksMap0
		};

}
