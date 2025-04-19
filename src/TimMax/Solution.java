package TimMax;

import java.io.FileInputStream;
import java.util.Scanner;

class Solution {
	public static final int PAWN = 0;
	public static final int ROCK = 1;
	public static final int CASTLE = 2;

	public static int[] dx = { -1, 0, 0, 1 };
	public static int[] dy = { 0, -1, 1, 0 };

	public static int[][] matrix, flag;
	public static int N, M, T, count, castleX, castleY;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream(
				"src\\TimMax\\input.txt"));
		Scanner sc = new Scanner(System.in);

		T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			M = sc.nextInt();
			N = sc.nextInt();

			matrix = new int[M][N];
			flag = new int[M][N]; //ma trận đánh dấu vị trí max của các HÀNG
			count = 0;

			for (int i = 0; i < M; i++) {
				for (int j = 0; j < N; j++) {
					matrix[i][j] = sc.nextInt();
				}
			}

			//duyệt hàng
			for (int row = 0; row < M; row++) {
				int maxRow = matrix[row][0];
				//Tìm max hàng
				for (int col = 1; col < N; col++) {
					if(matrix[row][col] > maxRow){
						maxRow = matrix[row][col];
					}
				}
				//Đánh dấu những vị trí max hàng
				for (int col = 0; col < N; col++) {
					if(matrix[row][col] == maxRow){
						flag[row][col] = 1;
					}
				}
			}

			//duyệt cột
			for (int col = 0; col < N; col++) {
				int maxCol = matrix[0][col];
				//Tìm max cột
				for (int row = 0; row < M; row++) {
					if(matrix[row][col] > maxCol){
						maxCol = matrix[row][col];
					}
				}
				//nếu max cột trùng với max hàng thì count++
				for (int row = 0; row < M; row++) {
					if(matrix[row][col] == maxCol && flag[row][col] == 1){
						count++;
					}
				}
			}
			System.out.println("#" + tc + " " + count);
		}
	}
}