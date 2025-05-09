package ReflectingMirrors;

import java.io.FileInputStream;
import java.util.Scanner;

class Solution {
	// Hướng đi của tia laser ứng với vị trí ma trận hướng đi dx, dy
	public static final int UP = 0;
	public static final int DOWN = 1;
	public static final int LEFT = 2;
	public static final int RIGHT = 3;

	// trên, dưới, trái, phải
	public static int[] dx = { -1, 1, 0, 0 };
	public static int[] dy = { 0, 0, -1, 1 };

	public static int[][] matrix;

	public static int count, T, n;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream(
				"src\\ReflectingMirrors\\input.txt"));
		Scanner sc = new Scanner(System.in);

		T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			n = sc.nextInt();

			matrix = new int[n][n];

			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					matrix[i][j] = sc.nextInt();
				}
			}

			/*
			 * count : Số lần chạm gương
			 * r, c : Toạ độ điểm đang xét. Khởi tại ban đầu là vị trí (0,0)
			 * dir : Hướng đi hiện tại của laser. Khởi tạo ban đầu là RIGHT
			 */
			count = 0;
			int r = 0, c = 0;
			int dir = RIGHT;

			/*
			 * theo như đề bài là cho chạy tới khi gặp tường là kết thúc. Thì ta chỉ xét khi 
			 * nó trong biên, nếu ngoài biên thì cũng đồng nghĩa với việc đã gặp tường và
			 * trò chơi kết thúc
			 */
			
			while (r >= 0 && r < n && c >= 0 && c < n) {
				/*
				 * ta xét 4 trường hợp của hướng đi. ở hướng nào thì ta sẽ chạy tới điểm TIẾP THEO của hướng đó
				 * sau đó nếu điểm TIẾP THEO đó còn trong biên thì ta sẽ xem nó có phải gương không
				 * nếu là gương thì xem là gương 1 hay 2 thì đếm count lên 1 và đồng thời thay đổi HƯỚNG ĐI TIẾP THEO 
				 * của laser. Ta cứ lặp lại quá trình đó cho tới khi điểm ra ngoài biên là sẽ bị thoát khỏi vòng while.
				 * Khi đó, count chính là số lần gặp gương của đề bài
				 */
				if (dir == UP) {
					r += dx[UP];
					c += dy[UP];
					if (r >= 0 && r < n && c >= 0 && c < n) {
						if (matrix[r][c] == 1) {
							dir = RIGHT;
							count++;
						} else if (matrix[r][c] == 2) {
							dir = LEFT;
							count++;
						}
					}
				} else if (dir == DOWN) {
					r += dx[DOWN];
					c += dy[DOWN];
					if (r >= 0 && r < n && c >= 0 && c < n) {
						if (matrix[r][c] == 1) {
							dir = LEFT;
							count++;
						} else if (matrix[r][c] == 2) {
							dir = RIGHT;
							count++;
						}
					}

				} else if (dir == LEFT) {
					r += dx[LEFT];
					c += dy[LEFT];
					if (r >= 0 && r < n && c >= 0 && c < n) {
						if (matrix[r][c] == 1) {
							dir = DOWN;
							count++;
						} else if (matrix[r][c] == 2) {
							dir = UP;
							count++;
						}
					}

				} else if (dir == RIGHT) {
					r += dx[RIGHT];
					c += dy[RIGHT];
					if (r >= 0 && r < n && c >= 0 && c < n) {
						if (matrix[r][c] == 1) {
							dir = UP;
							count++;
						} else if (matrix[r][c] == 2) {
							dir = DOWN;
							count++;
						}
					}

				}
			}

			System.out.println("#" + tc + " " + count);
		}
		sc.close();
	}
}