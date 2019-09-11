import java.text.DecimalFormat;
import java.text.NumberFormat;

import java.util.Arrays; 

public class KnightsTour{
	

	static int N = 5;
	static int[][] sol = new int[N][N];
	
	static boolean isSafe(int x, int y, int sol[][]) {
		if(x >= 0 && x < N && y >= 0 && y < N) {
			if(sol[x][y] == -1) {
				//System.out.printf("o index do sol[%d][%d] era -1 (debugging) %d\n", x, y, sol[x][y]);
				//System.out.println("é seguro confia");
				return true;
			}
		}
		if(x > 0 && x < N && y > 0 && y < N) {
			//System.out.printf("ixe nao coube no x %d y %d e o sol[%d][%d] é %d\n", x, y, x, y, sol[x][y]);
		}
		else {
			//System.out.printf("ixe nao coube no x %d y %d, index invalido\n", x, y);
		}
		return false;
	}
	
	
	static void initializeBoard(int sol[][]) {
		
		for(int i = 0; i < sol.length; i++) {
			for(int j = 0; j < sol[i].length; j++) {
				sol[i][j] = -1;
			}
		}
		
		/*
		for(int i = 0; i < sol.length; i++) {
			for(int j = 0; j < sol[i].length; j++) {
				gridPane.add(zero, i, j, 1, 1);
			}
		}
		
		Scene scene = new Scene(gridPane, 240, 100);
        primaryStage.setScene(scene);
        primaryStage.show();*/
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
		//System.out.println(numMove);
		if(numMove == N * N) {
			return true;
		}
		
		for(int i = 0; i < 8; i++) {
			xAtual += moveX[i];
			yAtual += moveY[i];
			//printSolution(sol);	
			if(isSafe(xAtual, yAtual, sol)) {
				//System.out.println("cheguei no if");
				sol[xAtual][yAtual] = numMove;
				System.out.print("\033[0;0H");
				printSolution(sol);
				if(andaCavalo(sol, xAtual, yAtual, numMove+1, moveX, moveY)) return true;
				else sol[xAtual][yAtual] = -1;
			}else {
				xAtual = x;
				yAtual = y;
				//sol[xAtual][yAtual] = -1;
				continue;
			}
		}
		return false;
		
		
	}
	
	
	
	public static void main(String[] args) {



		long start = System.currentTimeMillis();
		/*initializeBoard(sol);
		printSolution(sol);*/
		backtracking(sol);
		


		long end = System.currentTimeMillis();

		NumberFormat formatter = new DecimalFormat("#0.00000");
		System.out.print("Execution time is " + formatter.format((end - start) / 1000d) + " seconds");
	}
	
}
