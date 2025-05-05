package Caro;

import java.io.FileInputStream;
import java.util.Scanner;

class Solution {
	/*
	 * MẢNG CHECK HƯỚNG
	 * cái này là để check hướng sang phải và các hướng xuống
	 * mục đích để check xem có đủ 5 quân cờ liên tiếp không 
	 */
	// phải, xuống, chéo xuống phải \, chéo xuống trái /
	public static int[] dx = { 0, 1, 1, 1 };
	public static int[] dy = { 1, 0, 1, -1 };

	/*
	 * MẢNG CHECK ĐIỂM CHẶN ĐẦU
	 * cái này để check điểm chặn trái và các hướng trên
	 * check chặn 1 đầu của hướng đó khi đã check đủ 5 quân cờ liên tiếp xong
	 * ứng với vị trí của MẢNG CHECK HƯỚNG sẽ là những ĐIỂM CHECK CHẶN ĐẦU tương đương
	 */
	// trái, lên, chéo lên trái \, chéo lên phải /
	public static int[] checkX = { 0, -1, -1, -1 };
	public static int[] checkY = { -1, 0, -1, 1 };

	public static int[][] matrix;
	public static int N, M, T;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream(
				"src\\Caro\\input.txt"));
		Scanner sc = new Scanner(System.in);

		T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			M = sc.nextInt();
			N = sc.nextInt();

			matrix = new int[M][N];

			for (int i = 0; i < M; i++) {
				for (int j = 0; j < N; j++) {
					matrix[i][j] = sc.nextInt();
				}
			}

			/*
			 * count	: đếm cờ giống nhau
			 * p1 		: với trường hợp check trước thì p1 là giá trị 1 hoặc 2 có sẵn trên ô đó. Còn trường hợp điền vào ô trống thì là SỐ ĐƯỢC ĐIỀN.
			 * p2		: là số còn lại. Được tính bằng cách p2 = 3 - p1
			 * block	: nếu false là 2 đầu không bị chặn. true thì là 2 đầu bị chặn
			 * haveAns	: trả về true nếu đã có kết quả 1 được in ra. Khi là true thì sẽ nhảy sang tc tiếp theo.
			 */
			int count, cr, cc, p1, p2;
			boolean block, haveAns = false;

			// ==== PHẦN CHECK TRƯỚC KHI ĐIỀN ====
			for (int r = 0; r < M; r++) {
				for (int c = 0; c < N; c++) {
					//nếu ô đó khác 0 (ô trống)
					if (matrix[r][c] != 0) {
						p1 = matrix[r][c];	//gán p1 là giá trị ô đó
						p2 = 3 - p1;

						//xét bốn hướng của MẢNG CHECK HƯỚNG
						for (int i = 0; i < 4; i++) {
							count = 1;	//mỗi hướng sẽ xét lại giá trị count bằng 1 tức là tính con gốc
							block = false;	//mặc định ban đầu là CHƯA bị chặn

							//bắt đầu di chuyển bằng cr, cc
							cr = r + dx[i];
							cc = c + dy[i];

							//nếu ô đó còn trong biên, còn bằng giá trị p1 và chưa đủ 5 con
							while (cr >= 0 && cr < M && cc >= 0 && cc < N && matrix[cr][cc] == p1 && count != 5) {
								count++;	// tăng số lương count lên 1 rồi tiếp tục di chuyển
								//khi này điểm cr, cc sẽ ở điểm xét TIẾP THEO rồi
								cr = cr + dx[i];
								cc = cc + dy[i];

								//khi đủ 5 con liên tiếp giống nhau thì thoát ra để check biên
								if (count == 5) {
									break;
								}

							}

							/*
							 * đây là phần check điểm chặn sau nếu đã đếm đủ 5 con liên tiếp giống nhau (count = 5)
							 * vì đang dừng ở điểm tiếp theo, tức là điểm cần check chặn cuối.
							 * nên cần check luôn điểm này có trong biên không 
							 */
							if (cr >= 0 && cr < M && cc >= 0 && cc < N && count == 5) {
								//nếu điểm này là p2 thì sẽ check tiếp điểm chặn đầu
								if (matrix[cr][cc] == p2) {
									//tất nhiên vẫn phải check biên điểm chặn đầu và nếu nó bằng p2. Tức là 5 con này bị chặn 2 đầu rồi nên lúc này block = true
									if (r + checkX[i] >= 0 && r + checkX[i] < M && c + checkY[i] >= 0
											&& c + checkY[i] < N && matrix[r + checkX[i]][c + checkY[i]] == p2) {
										block = true;
									}
								}

								//sau khi kiểm tra mà block vẫn false thì ta in ra 1 luôn và cho haveAns = true
								/*
								 * LƯU Ý:
								 * lúc này nếu haveAns = true thì ta cần break hết các vòng lặp và continue tới tc tiếp theo.
								 * vì thế với mỗi cuỗi vòng lặp không phải vòng lặp biến tc thì nếu haveAns = true thì đều break hết
								 * với vòng lặp biến tc thì sẽ continue thì tới tc tiếp theo tức là tc++
								 * còn nếu không tìm được 5 con liên tiếp giống nhau hợp lệ thì ta sẽ tiếp tục tới phần ĐIỀN SỐ VÀO Ô TRỐNG
								 */
								if (block == false) {
									System.out.println("#" + tc + " 1");
									haveAns = true;
									break;
								}
							}
						}
						if (haveAns)
							break;
					}
				}
				if (haveAns)
					break;
			}

			if (haveAns) {
				continue;
			}

			/*
			 * ==== PHẦN ĐIỀN SỐ ====
			 * sau khi check hết trước mà không có trường hợp hợp lệ thì tới phần điền số sẽ gần tương tự như phần trên
			 * nhưng thay vì tìm ô khác 0 thì giờ tìm những ô bằng 0 rồi xét 2 trường hợp cho ô đó là 1 hoặc 2
			 * khi ta coi ô trống đó là 1 hoặc 2 rồi thì ta lại xét 5 ô liên tiếp giống nhau như bình thường 
			 */
			for (int r = 0; r < M; r++) {
				for (int c = 0; c < N; c++) {
					if (matrix[r][c] == 0) {
						//xét ô đó là 1 hoặc 2
						for (int point = 1; point <= 2; point++) {
							p1 = point;
							p2 = 3 - p1;
							for (int i = 0; i < 4; i++) {
								count = 1;
								block = false;
								cr = r + dx[i];
								cc = c + dy[i];

								while (cr >= 0 && cr < M && cc >= 0 && cc < N && matrix[cr][cc] == p1 && count != 5) {
									count++;
									cr = cr + dx[i];
									cc = cc + dy[i];
									if (count == 5) {
										break;
									}
								}

								if (cr >= 0 && cr < M && cc >= 0 && cc < N && count == 5) {
									if (matrix[cr][cc] == p2) {
										if (r + checkX[i] >= 0 && r + checkX[i] < M && c + checkY[i] >= 0
												&& c + checkY[i] < N && matrix[r + checkX[i]][c + checkY[i]] == p2) {
											block = true;
										}
									}
									if (block == false) {
										System.out.println("#" + tc + " 1");
										haveAns = true;
										break;
									}
								}
							}
							if (haveAns)
								break;
						}
						if (haveAns) {
							break;
						}
					}
				}
				if (haveAns)
					break;

			}

			// đến cuối cùng mà vẫn không có đáp án, tức là haveAns bằng false thì in ra 0
			if (!haveAns) {
				System.out.println("#" + tc + " 0");
			}
		}
		sc.close();
	}
}