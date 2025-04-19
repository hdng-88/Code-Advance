package RollingBall;

import java.io.FileInputStream;
import java.util.Scanner;

class Solution {
	public static int[] dx = {-1, 0, 0, 1};
	public static int[] dy = {0, -1, 1, 0};

	public static int[][] matrix, visited;
	public static int N, T, ans, count;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src\\RollingBall\\input.txt"));	
		Scanner sc = new Scanner(System.in);

		T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			N = sc.nextInt();

			matrix = new int[N][N];
			visited = new int[N][N]; //đánh dấu điểm XÉT đã duyệt

			//kết quả
			ans = 1;

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					matrix[i][j] = sc.nextInt();
				}
			}

			int x, y, cx, cy, nx, ny, lowest;
			boolean haveNextStep;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(visited[i][j] == 1){
						continue; // nếu đã duyệt rồi thì bỏ qua
					}
					count = 1;
					// x y là điểm XÉT, i j là điểm GỐC
					x = i;
					y = j;
					// nx ny là điểm di chuyển tới tiếp theo
					nx = x;
					ny = y;
					
					while (true) {
						//đánh dấu điểm đã XÉT
						visited[x][y] = 1;
						haveNextStep = false; 

						lowest = matrix[x][y]; // để tìm điểm thấp hơn và thấp nhất xung quanh
						//duyệt 4 hướng
						for (int k = 0; k < 4; k++) {
							cx = x + dx[k];
							cy = y + dy[k];
							
							//nếu trong biên, điểm đó mà nhỏ hơn điểm XÉT và nhỏ hơn điểm lowest
							if(cx < N && cy < N && cx >= 0 && cy >= 0 && matrix[cx][cy] < matrix[x][y] && matrix[cx][cy] < lowest){
								//gán lại điểm di chuyển tới tiếp theo
								nx = cx;
								ny = cy;
								//gán lại giá trị lowest
								lowest = matrix[cx][cy];
								haveNextStep = true; //đánh dấu có diểm tới tiếp theo
							}
						}
						
						//nếu có bước tiếp theo
						if(haveNextStep){
							count++;	//tăng số bước lên 1
							//gán điểm xét là điểm tới tiếp theo
							x = nx;	
							y = ny;
						}
						//nếu không có bước tiếp theo
						else{
							//nếu bước đi dài hơn thì gán lại vào ans
							if(ans < count){
								ans = count; 
							}
							break;
						}
					}
				}
			}

			System.out.println("#" + tc + " " + ans);
		}
	}
}