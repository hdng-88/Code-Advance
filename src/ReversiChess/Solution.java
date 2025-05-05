package ReversiChess;

import java.io.FileInputStream;
import java.util.Scanner;

class Solution {
	public static final int WHITE = 0;
	public static final int BLACK = 1;
	public static final int BLANK = -1;

	// trái phải lên xuống, chéo lên trái, chéo xuống trái, chéo lên phải, chéo
	// xuống phải
	public static int[] dx = { 0, 0, -1, 1, -1, 1, -1, 1 };
	public static int[] dy = { -1, 1, 0, 0, -1, 1, 1, -1 };

	public static int[][] matrix, flag;
	public static int N, M, T, nBlack, nWhite, ans;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream(
				"src\\ReversiChess\\input.txt"));
		Scanner sc = new Scanner(System.in);

		T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			M = sc.nextInt();
			N = sc.nextInt();

			matrix = new int[M][N];
			nBlack = 0;
			nWhite = 0;

			for (int i = 0; i < M; i++) {
				for (int j = 0; j < N; j++) {
					matrix[i][j] = sc.nextInt();
					//đếm số lượng quân trắng đen ban đầu
					if (matrix[i][j] == WHITE) {
						nWhite++;
					} else if (matrix[i][j] == BLACK) {
						nBlack++;
					}
				}
			}

			//gán ans là số lượng cao hơn
			ans = nWhite > nBlack ? nWhite : nBlack;

			/*
			 * cr, cc	: các điểm di chuyển
			 * nW, nB	: số lượng quân trắng đen sau khi thay đổi mỗi trường hợp
			 * countW, countB	: số lượng quân trắng đen sẽ thay thêm bớt
			 */
			int cr, cc, nW, nB, countW, countB;
			for (int r = 0; r < M; r++) {
				for (int c = 0; c < N; c++) {
					//nếu đó là ô trống
					if (matrix[r][c] == BLANK) {
						// đặt WHITE
						//gán nW, nB bằng số lượng ban đầu
						nW = nWhite;
						nB = nBlack;

						nW++;	//đặt WHITE nên sẽ tăng lên 1

						//xét 8 hướng
						for (int i = 0; i < 8; i++) {
							cr = r + dx[i];
							cc = c + dy[i];
							countW = 0;
							countB = 0;

							//nếu còn trong biên
							while (cr >= 0 && cr < M && cc >= 0 && cc < N) {
								//nếu con tiếp tới là BLACK thì sẽ tăng số countW và countB lên 1
								if(matrix[cr][cc] == BLACK){
									countW++;
									countB++;
								}
								//nếu con gặp ô trống thì break luôn
								else if(matrix[cr][cc] == BLANK){
									// countW++;
									break;
								}
								//còn lại nếu gặp ô trắng thì cộng số nW lên countW và giảm số nB xuống countB
								else if(matrix[cr][cc] == WHITE){
									nW += countW;
									nB -= countB;
									break;
								}

								//chạy tới điểm tiếp theo
								cr = cr + dx[i];
								cc = cc + dy[i];
								
							}							
						}

						// System.out.print("WHITE:	");
						// if(nW>nB){
						// 	if(nW>ans)	ans = nW;
						// 	System.out.println("r = " + r + ", c = " + c + ", ans = " + ans);
						// 	System.out.println("	nW = " + nW + ", nB = " + nB);
						// }
						// else{
						// 	if(nB>ans)	ans = nB;
						// 	System.out.println("r = " + r + ", c = " + c + ", ans = " + ans);
						// 	System.out.println("	nW = " + nW + ", nB = " + nB);
						// }
						ans = nW > nB ? (nW > ans ? nW : ans) : (nB > ans ? nB : ans);

						// đặt BLACK
						//tương tự với khi đặt WHITE

						nW = nWhite;
						nB = nBlack;

						nB++;
						for (int i = 0; i < 8; i++) {
							cr = r + dx[i];
							cc = c + dy[i];
							countB = 0;
							countW = 0;
							while (cr >= 0 && cr < M && cc >= 0 && cc < N) {
								if(matrix[cr][cc] == WHITE){
									countB++;
									countW++;
								}
								else if(matrix[cr][cc] == BLANK){
									// countB++;
									break;
								}
								else if(matrix[cr][cc] == BLACK){
									nB += countB;
									nW -= countW;
									break;
								}

								cr = cr + dx[i];
								cc = cc + dy[i];
							}
							
						}

						// System.out.print("BLACK:	");
						// if(nW>nB){
						// 	if(nW>ans)	ans = nW;
						// 	System.out.println("r = " + r + ", c = " + c + ", ans = " + ans);
						// 	System.out.println("	nW = " + nW + ", nB = " + nB);
						// }
						// else{
						// 	if(nB>ans)	ans = nB;
						// 	System.out.println("r = " + r + ", c = " + c + ", ans = " + ans);
						// 	System.out.println("	nW = " + nW + ", nB = " + nB);
						// }
						ans = nW > nB ? (nW > ans ? nW : ans) : (nB > ans ? nB : ans);

					}
				}
			}

			//in ra kết quả 
			System.out.println("#" + tc + " " + ans);
		}
		sc.close();
	}
}