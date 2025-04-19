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
			visited = new int[N][N];

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
						continue;
					}
					count = 1;
					x = i;
					y = j;
					nx = x;
					ny = y;
					
					while (true) {
						visited[x][y] = 1;
						haveNextStep = false;

						lowest = matrix[x][y];
						for (int k = 0; k < 4; k++) {
							cx = x + dx[k];
							cy = y + dy[k];
	
							if(cx < N && cy < N && cx >= 0 && cy >= 0 && matrix[cx][cy] < matrix[x][y] && matrix[cx][cy] < lowest){
								nx = cx;
								ny = cy;
								lowest = matrix[cx][cy];
								haveNextStep = true;
							}
						}
	
						if(haveNextStep){
							count++;
							x = nx;
							y = ny;
						}
						else{
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