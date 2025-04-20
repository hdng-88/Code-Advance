package Tetris;

import java.io.FileInputStream;
import java.util.Scanner;

class Solution {

	public static int[] ans;
	public static int[][] matrix, visited;
	public static int N, T;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream(
				"src\\Tetris\\input.txt"));
		Scanner sc = new Scanner(System.in);

		T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			N = sc.nextInt();
			N = sc.nextInt();

			ans = new int[N];			// để tính điểm N cột
			matrix = new int[N][N];
			visited = new int[N][N]; 	//đánh dấu điểm đã xét

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					matrix[i][j] = sc.nextInt();
				}
			}

			/*
			 * vì trò này khối dưới sẽ sẽ làm ảnh hưởng đến khối trên nên ta sẽ xét từ hàng dưới lên để
			 * xét các khối dưới trước rồi các khối trên sẽ chồng lên sau
			 */
			for (int r = N-1; r >= 0; r--) {
				for (int c = 0; c < N; c++) {
					if(matrix[r][c] == 1 && visited[r][c] == 0){
						visited[r][c] = 1;			//đánh dấu điểm đã xét để tìm khối

						//KHỐI DỌC - nếu phía trên điểm đang xét có ô 1 thì đó là khối dọc
						/*khối này chỉ năm trên 1 cột thôi nên không ảnh hưởng tới điểm 
						cột khác, vì thế khối này ở cột nào thì sẽ tự thêm 2 điểm ở cột đó */
						if (r-1 >= 0 && matrix[r-1][c] == 1) {
							visited[r-1][c] = 1;	//đánh dấu để không xét tới điểm đó nữa vì đã là 1 khối
							ans[c] += 2;			//thêm 2 điểm cho cột đó luôn
						} 

						//KHỐI NGANG - nếu bên phải điểm đang xét có ô 1 thì đó là khối ngang
						/*khối này nó ảnh hưởng tới 2 cột nên ta xét xem cột nào có điểm cao hơn 
						thì ta sẽ thêm 1 vào cột cao hơn còn cột thấp hơn sẽ bằng điểm với cột cao hơn đó*/
						else if(c+1 < N && matrix[r][c+1] == 1) {
							visited[r][c+1] = 1;	//đánh dấu để không xét tới điểm đó nữa vì đã là 1 khối
							
							if(ans[c] >= ans[c+1]){ //nếu cột trái điểm cao hơn hoặc bằng cột phải
								ans[c]++;			//tăng điểm cột trái lên 1
								ans[c+1] = ans[c];	//rồi điểm cột phải sẽ bằng cột trái
							}
							
							else{					//nếu cột phải điểm cao hơn cột trái
								ans[c+1]++;			//tăng điểm cột phải lên 1
								ans[c] = ans[c+1];	//rồi điểm cột trái sẽ bằng điểm cột phải
							}
						}
					}
				}
			}
			
			//in ra kết quả 
			System.out.print("#" + tc + " ");				
			for (int i = 0; i < N; i++) {
				System.out.print(ans[i] + " ");				
			}
			System.out.println();
		}
		sc.close();
	}
}