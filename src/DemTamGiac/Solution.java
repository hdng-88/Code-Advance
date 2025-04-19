package DemTamGiac;

import java.io.FileInputStream;
import java.util.Scanner;

class Solution {
	public static final int UP = 0;
	public static final int DOWN = 1;
	public static final int LEFT = 2;
	public static final int RIGHT = 3;

	public static int[] dx = { -1, 1, 0, 0 };
	public static int[] dy = { 0, 0, -1, 1 };

	public static int[][] matrix, flag;
	public static int N, T, count;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream(
				"src\\DemTamGiac\\input.txt"));
		Scanner sc = new Scanner(System.in);

		T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			N = sc.nextInt();
			N = sc.nextInt();

			matrix = new int[N][N];
			count = 0;

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					matrix[i][j] = sc.nextInt();
				}
			}

			int xUP, yUP, xDOWN, yDOWN, xLEFT, yLEFT, xRIGHT, yRIGHT;
			for (int x = 0; x < N; x++) {
				for (int y = 0; y < N; y++) {
					if (matrix[x][y] == 1) {
						xUP = x + dx[UP];
						yUP = y + dy[UP];
						if (xUP >= 0 && matrix[xUP][yUP] == 1) {
							xLEFT = x + dx[LEFT];
							yLEFT = y + dy[LEFT];
							if (yLEFT >= 0 && matrix[xLEFT][yLEFT] == 1) {
								count++;
							}
							xRIGHT = x + dx[RIGHT];
							yRIGHT = y + dy[RIGHT];
							if (yRIGHT < N && matrix[xRIGHT][yRIGHT] == 1) {
								count++;
							}
						}
						xDOWN = x + dx[DOWN];
						yDOWN = y + dy[DOWN];
						if (xDOWN < N && matrix[xDOWN][yDOWN] == 1) {
							xLEFT = x + dx[LEFT];
							yLEFT = y + dy[LEFT];
							if (yLEFT >= 0 && matrix[xLEFT][yLEFT] == 1) {
								count++;
							}
							xRIGHT = x + dx[RIGHT];
							yRIGHT = y + dy[RIGHT];
							if (yRIGHT < N && matrix[xRIGHT][yRIGHT] == 1) {
								count++;
							}
						}
					}
				}
			}

			System.out.println("#" + tc + " " + count);
		}
	}
}