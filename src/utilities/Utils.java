package utilities;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import model.Coordinate;


public class Utils {
	private static double THRESHOLD = 0.97;
	
	public static void sleep(int ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static int getRandomIntInRange(int min, int max) {

		if (min > max) {
			throw new IllegalArgumentException("max(" + max +") must be greater than min(" + min +")");
		}

		Random r = new Random();
		
		return r.nextInt((max - min) + 1) + min;

	}
	
	public static double getRandomDoubleInRange() {
		Random r = new Random();
		return r.nextDouble();
	}
	
	public static int[][] clone2DMatrix(int[][] mat){
		int x = mat.length;
		int y = mat[0].length;
		int[][] clone = new int[x][y];
		for(int i = 0; i < x; i++) {
			for(int j = 0; j < y; j++) {
				clone[i][j] = mat[i][j];
			}
		}
		return clone;
	}
	
	public static int[][] makeGomesForNewMapV2(int[][] mat, int cnt){
		int x = mat.length;
		int y = mat[0].length;
		int[][] clone = new int[x][y];
		List<Coordinate> list = freeBlockMapToList(mat);
		
		if(cnt == -1) cnt = list.size();
		
		while(cnt > 0 && list.size() > 0) {
			int pos = getRandomIntInRange(0, list.size()-1);
			double probability = getRandomDoubleInRange();
			Coordinate p = list.get(pos);
			
			if(probability > THRESHOLD) {
				specialGomes(clone, p);
			}else {
				clone[p.getX()][p.getY()] = 1;
			}
			
			list.remove(pos);
			cnt--;
		}
		
		return clone;
	}
	
	public static void specialGomes(int[][] gom, Coordinate p) {
		int[] values = {2, 3, 4};
		int pos = getRandomIntInRange(0, values.length-1);
		
		gom[p.getX()][p.getY()] = values[pos];
	}
	
	
	public static List<Coordinate> freeBlockMapToList(int[][] blocksMap){
		List<Coordinate> list = new ArrayList<>();
		
		for(int i = 0; i < blocksMap.length; i++) {
			for(int j = 0; j < blocksMap[0].length; j++) {
				if(blocksMap[i][j] == 0) {
					list.add(new Coordinate(i,j));
				}
			}
		}
		return list;
	}
	
}
