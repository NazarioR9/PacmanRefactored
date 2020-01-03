package utilities;

import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import model.Coordinate;


public class Utils {
	public static int getRandomNumberInRange(int min, int max) {

		if (min > max) {
			throw new IllegalArgumentException("max(" + max +") must be greater than min(" + min +")");
		}

		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
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
	
	public static int[][] buildGomeMap(int index) {
		int[][] map = Utils.clone2DMatrix(Constante.blocksMaps[index]);
		
		for(int i = 0; i < map.length; i++) {
			for(int j = 0; j < map[0].length; j++) {
				map[i][j] = (map[i][j] + 1) % 2;
			}
		}
		//Add specials gomes
		addSpecialGomes(index, map);
		
		return map;
	}

	public static void addSpecialGomes(int index, int[][] map) {
		for(int i = 0; i < Constante.vs[0].length; i++) {
			map[Constante.xs[index][i]][Constante.ys[index][i]] = Constante.vs[index][i];
		}
	}
	
	public static int[][] makeGomesForNewMap(int[][] mat, int[][] gom, int cnt, int index, int index1){
		int x = mat.length;
		int y = mat[0].length;
		int[][] clone = new int[x][y];
		
		for(int i = 0; i < x & cnt > 0; i++) {
			for(int j = 0; j < y & cnt > 0; j++) {
				
				if(mat[i][j] == 0) {
					clone[i][j] = 1;
					cnt--;
				}
			}
		}
		
		int[][]xs = Constante.xs, ys = Constante.ys, vs = Constante.vs;
		
		for(int i = 0; i < vs[0].length; i++) {
			clone[xs[index][i]][ys[index][i]] = gom[xs[index1][i]][ys[index1][i]];
			//Constante.addSpecialGomes(index, clone);
		}
		
		return clone;
	}
	
	public static int[][] makeGomesForNewMapV2(int[][] mat, int[][] gom, int cnt, int newIndex, int lastIndex){
		int x = mat.length;
		int y = mat[0].length;
		int[][] clone = new int[x][y];
		List<Coordinate> list = new ArrayList<>();
		
		for(int i = 0; i < x; i++) {
			for(int j = 0; j < y; j++) {
				if(mat[i][j] == 0) {
					list.add(new Coordinate(i,j));
				}
			}
		}
		
		while(cnt > 0 && list.size() > 0) {
			int pos = getRandomNumberInRange(0, list.size()-1);
			Coordinate p = list.get(pos);
			clone[p.getX()][p.getY()] = 1;
			list.remove(pos);
			cnt--;
		}
		
		int[][]xs = Constante.xs, ys = Constante.ys, vs = Constante.vs;
		
		for(int i = 0; i < vs[0].length; i++) {
			clone[xs[newIndex][i]][ys[newIndex][i]] = gom[xs[lastIndex][i]][ys[lastIndex][i]];
		}
		
		return clone;
	}
}
