package BallRun;

import java.io.FileInputStream;
import java.util.Scanner;

class Solution {
	public static final int PAWN = 0;
	public static final int ROCK = 1;
	public static final int CASTLE = 2;

	public static int[] dx = { -1, 0, 0, 1 };
	public static int[] dy = { 0, -1, 1, 0 };

	public static int[][] matrix;
	public static int N, T, count, castleX, castleY;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream(
				"src\\BallRun\\input.txt"));
		Scanner sc = new Scanner(System.in);

		T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			N = sc.nextInt();

			matrix = new int[N][N];
			count = 0; //số con tốt

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					matrix[i][j] = sc.nextInt();
					//nếu ví trí đó là castle thì lưu vị trí 
					if (matrix[i][j] == CASTLE) {
						castleX = i;
						castleY = j;
					}

					//đếm số con tốt
					if (matrix[i][j] == PAWN) {
						count++;
					}
				}
			}

			//duyệt 4 hướng
			for (int i = 0; i < 4; i++) {
				int cx = castleX + dx[i];
				int cy = castleY + dy[i];

				//nếu trong biên và không phải đá
				while (cx >= 0 && cx < N && cy >= 0 && cy < N && matrix[cx][cy] != ROCK) {
					count--; // trừ số con tốt
					cx = cx + dx[i];
					cy = cy + dy[i];
				}

			}
			System.out.println("#" + tc + " " + count);
		}
		sc.close();
	}
}