import java.text.DecimalFormat;
import java.text.NumberFormat;

import java.util.Arrays; 

public class KnightsTour{
	

	static int N = 8;
	static int[][] sol = new int[N][N];
	
	static boolean isSafe(int x, int y, int sol[][]) {
		if(x >= 0 && x < N && y >= 0 && y < N) {
			if(sol[x][y] == -1) {
				return true;
			}
		}
		return false;
	}
	
	
	static void initializeBoard(int sol[][]) {
		
		for(int i = 0; i < sol.length; i++) {
			for(int j = 0; j < sol[i].length; j++) {
				sol[i][j] = -1;
			}
		}
		
	}
	
	
	static void printSolution(int sol[][]) {
		System.out.print("\n\n\n");
		for(int i = 0; i < sol.length; i++) {
			for(int j = 0; j < sol[i].length; j++) {
				System.out.format("%4d",sol[i][j]);
			}
			System.out.print("\n");
		}
		
	}
	
	static void backtracking(int sol[][]) {
		initializeBoard(sol);
		sol[0][0] = 0;
		int[] moveX = {2, 2, 1, 1,-1,-1,-2,-2};
		int[] moveY = {1,-1, 2,-2, 2,-2,-1, 1};
		if(!andaCavalo(sol, 0, 0, 1,moveX, moveY)){
			System.out.println("Sem soluções");
		}else {
			printSolution(sol);
		}
	}
	
	
	static boolean andaCavalo(int sol[][], int x, int y, int numMove, int[] moveX, int[] moveY) {
		
		
		int xAtual = x;
		int yAtual = y;
		if(numMove == N * N) {
			return true;
		}
		
		for(int i = 0; i < 8; i++) {
			xAtual += moveX[i];
			yAtual += moveY[i];
			if(isSafe(xAtual, yAtual, sol)) {
				sol[xAtual][yAtual] = numMove;
				System.out.print("\033[0;0H");
				printSolution(sol);
				if(andaCavalo(sol, xAtual, yAtual, numMove+1, moveX, moveY)) return true;
				else sol[xAtual][yAtual] = -1;
			}else {
				xAtual = x;
				yAtual = y;
				continue;
			}
		}
		return false;
		
		
	}
	
	
	
	public static void main(String[] args) {

		long start = System.currentTimeMillis();

		backtracking(sol);
		
		long end = System.currentTimeMillis();

		NumberFormat formatter = new DecimalFormat("#0.00000");
		
		System.out.print("Execution time is " + formatter.format((end - start) / 1000d) + " seconds");
	}
	
}
