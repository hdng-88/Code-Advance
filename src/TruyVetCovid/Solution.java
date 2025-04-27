package TruyVetCovid;

import java.io.FileInputStream;
import java.util.Scanner;

class Solution {
	public static final int F0 = 1;
	public static final int F1 = 2;
	public static final int F2 = 3;
	public static int[] state, f0, f1;
	public static int[][] matrix;
	public static int N, M, T, nF1, nF2;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream(
				"src\\TruyVetCovid\\input.txt"));
		Scanner sc = new Scanner(System.in);

		T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			N = sc.nextInt();
			M = sc.nextInt();

			state = new int[N];			// để thể hiện trạng thái người nhiễm
			matrix = new int[N][N];

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					matrix[i][j] = sc.nextInt();
				}
			}

			f0 = new int[M];	//lưu người bị f0
			for (int i = 0; i < M; i++) {
				f0[i] = sc.nextInt()-1;
				state[f0[i]] = F0;
			}

			nF1 = 0;	//đếm số người f1
			// f1 = new int[N-M];
			// int iF1 = 0;

			//duyệt hàng có người f0
			for (int i = 0; i < M; i++) {	
				for (int j = 0; j < N; j++) {	
					//nếu có người tiếp xúc với f0 mà chưa được xác định trạng thái
					if (matrix[f0[i]][j] == 1 && state[j] == 0) {
						state[j] = F1;	//đánh dấu là F1
						nF1++;			//tăng số lượng người f1 lên 1
						// f1[iF1++] = j;
					}
				}
			}
			
			// nF2 = 0;
			// for (int i = 0; i < iF1; i++) {
			// 	for (int j = 0; j < N; j++) {
			// 		if (matrix[f1[i]][j] == 1 && state[j] == 0) {
			// 			state[j] = F2;
			// 			nF2++;
			// 		}
			// 	}
			// }

			nF2 = N-M-nF1;	//số người f2 = tổng số người - số người F0 - số người F1

			//in ra kết quả 
			System.out.println("#" + tc + " " + nF1 + " " + nF2);
		}
		sc.close();
	}
}