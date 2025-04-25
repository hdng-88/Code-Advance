package SlotMachine;

import java.io.FileInputStream;
import java.util.Scanner;

class Solution {
	/*
	 * mảng dx là để di chuyển lên xuống cột 1 hay 2 hàng. Trong đó:
	 * số âm là kéo lên
	 * sô dương là kéo xuống
	 */
	public static int[] dx = { -1, -2, 1, 2 };

	public static int[][] matrix, visited;
	public static int N, M, T, ans;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream(
				"src\\SlotMachine\\input.txt"));
		Scanner sc = new Scanner(System.in);

		T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			N = sc.nextInt();
			M = sc.nextInt();

			matrix = new int[N][M];
			ans = 0;	// phải khởi tạo qua mỗi tc để reset lại giá trị

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					matrix[i][j] = sc.nextInt();
				}
			}

			int sum; // tính tổng điểm để so sánh với ans tìm ra tổng max
			//chạy lần lượt các cột và di chuyển lên xuống cho từng cột
			for (int c = 0; c < M; c++) {	
				for (int i = 0; i < 4; i++) {	//i là index mảng dx được khai báo sẵn
					//sum được tính bằng tổng 3 điểm của 3 hàng, mỗi điểm hàng được tính qua hàm point
					sum = point(c, dx[i], 1) + point(c, dx[i], 2) + point(c, dx[i], 3);
					//nếu điểm được tính lớn hơn đáp án hiện tại thì gán lại biến ans
					if (sum > ans) {
						ans = sum;
					}
				}
			}

			// in ra kết quả
			System.out.println("#" + tc + " " + ans);
		}
		sc.close();
	}

	/*
	 * đây là hàm tính tổng từng hàng (cụ thể là 3 hàng được yêu cầu tính điểm)
	 * col là cột được di chuyển lên hoặc xuống
	 * index là hàng đó kéo lên hay xuống 1 hay 2 hàng
	 * pointRow là hàng được tính điểm (giá trị 1,2,3 tương ứng với 3 hàng được tính
	 * điểm)
	 */
	public static int point(int col, int index, int pointRow) {
		/*
		 * ứng với col là bao nhiêu thì ta sẽ di chuyển giá trị các hàng tương ứng của
		 * cột đó
		 * ví dụ: col = 0, index = -1, pointRow = 1 tức là cột 1 được di chuyển LÊN 1 ô
		 * và ta đang tính điểm của hàng thứ nhất
		 * khi đó: 
		 * matrix[1 + pointRow + index][0] = matrix[1][0] 
		 * matrix[1 + pointRow][1] = matrix[2][1]
		 * matrix[1 + pointRow][2] = matrix[2][2]
		 * tính điểm 3 giá trị kia ta được điểm của hàng 1 (cách tính điểm ở hàm checkPoint)
		 */
		if (col == 0) {
			return checkPoint(matrix[1 + pointRow + index][0], matrix[1 + pointRow][1], matrix[1 + pointRow][2]);
		} else if (col == 1) {
			return checkPoint(matrix[1 + pointRow][0], matrix[1 + pointRow + index][1], matrix[1 + pointRow][2]);
		} else {
			return checkPoint(matrix[1 + pointRow][0], matrix[1 + pointRow][1], matrix[1 + pointRow + index][2]);
		}
	}

	/*
	 * Hàm cách tính điểm
	 * hàm này đưa vào 3 tham số tương ứng với 3 giá trị ô cần tính
	 */
	public static int checkPoint(int n1, int n2, int n3) {
		if (n1 == n2 && n2 == n3) {
			if (n1 == 7) {			// nếu n1 = n2 = n3 = 7
				return 100;
			} else {				// nếu n1 = n2 = n3 != 7
				return 10 * n1;
			}
		} else {					// nếu 3 số đều không cùng bằng nhau
			return n1 + n2 + n3;
		}
	}
}