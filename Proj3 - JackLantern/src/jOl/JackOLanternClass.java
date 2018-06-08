package jOl;

import java.util.LinkedList;
import java.util.Queue;

public class JackOLanternClass implements JackOLantern {

	private int[][] lanternMatrix;
	private String[][] map;
	private int X, Y, result;
	private Queue<int[]> queue;

	public JackOLanternClass(String[][] matrix) {
		this.map = matrix;
		this.X = matrix.length;
		this.Y = matrix[0].length;
		lanternMatrix = new int[matrix.length][matrix[0].length];
		queue = new LinkedList<int[]>();
		initLanternMatrix();
		routeHome();
	}
	
	public int getResult() {
		return result;
	}

	private void initLanternMatrix() {
		for (int i = 0; i < X ; i++) {
			for (int j = 0; j < Y ; j++) {
				lanternMatrix[i][j]=-1;
			}
		}
	}

	private void routeHome() {
		int initial = 0;
		if(!map[0][0].equals("*")) {
			initial = Integer.parseInt(map[0][0]);
		}
		queue.add(new int[]{0,0,initial,0});
		lanternMatrix[0][0] = initial;

		do {

			int[] node = queue.poll(); 
			int x = node[0];
			int y = node[1];
			int lanterns = node[2];
			int depth = node[3];
			if(x == X-1 && y == Y-1) {
				//System.out.println(depth);
				result = depth;
				break;
			}
			//down
			if(canJackMove(x, y, x, y+1, lanterns)) {
				makeAMove(x, y, x, y+1, depth);
			}
			//right
			if(canJackMove(x, y, x+1, y, lanterns)){
				makeAMove(x, y, x+1, y, depth);
			}
			//left
			if(canJackMove(x, y, x-1, y, lanterns)) {
				makeAMove(x, y, x-1, y, depth);
			}
			//up
			if(canJackMove(x, y, x, y-1, lanterns)) {
				makeAMove(x, y, x, y-1, depth);
			}
		}while(!queue.isEmpty());
	}


	private boolean canJackMove(int initialX, int initialY, int nextX, int nextY, int lanterns) {
		if(initialX >= 0 && initialX < X && initialY < Y && initialY >= 0 &&
				nextX >= 0 && nextX < X && nextY >= 0 && nextY < Y) {

			if(map[initialX][initialY].equals("*") || map[nextX][nextY].equals("*")) {
				return true;
			}
			else if(lanterns > 0) {
				return true;
			}
		}
		return false;
	}
		
		
	private void makeAMove(int x, int y, int newx, int newy, int depth) {
		if(map[newx][newy].equals("*")) {
			if(lanternMatrix[newx][newy] < lanternMatrix[x][y]) {
				queue.add(new int[]{newx, newy, lanternMatrix[x][y], depth+1});
				lanternMatrix[newx][newy] = lanternMatrix[x][y];
			}
		}
		else {
			if(map[x][y].equals("*")) {
				int res = lanternMatrix[x][y];
				int value = Integer.parseInt(map[newx][newy]);
				if(value > 0) {
					if(lanternMatrix[newx][newy] < value) {
						queue.add(new int[]{newx, newy, value, depth+1});
						lanternMatrix[newx][newy] = value;
					}
				}
				else {
					if(lanternMatrix[newx][newy] < res) {
						queue.add(new int[]{newx, newy, res, depth+1});	
						lanternMatrix[newx][newy] = res;
					}
				}
			}
			else {
				if(lanternMatrix[x][y] > 0) { 
					int maxLanterns = lanternMatrix[x][y];
					int gastaAtual = 1;
					int nextlanterns = Integer.parseInt(map[newx][newy]);
					if(maxLanterns-gastaAtual < nextlanterns) {
						maxLanterns = nextlanterns;
						gastaAtual = 0;
					}
					if(maxLanterns > lanternMatrix[newx][newy]) {
						queue.add(new int[]{newx, newy, maxLanterns - gastaAtual, depth+1});
						lanternMatrix[newx][newy] = maxLanterns - gastaAtual;						
					}
				}
			}
		}
	}
}