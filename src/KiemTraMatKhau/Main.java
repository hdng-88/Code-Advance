package KiemTraMatKhau;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Main
 */
public class Main {

    static int[] rs = { -1, 0, 0, 1 };
    static int[] cs = { 0, -1, 1, 0 };

    static int[][] phimNhap = {
            { 1, 2, 3 },
            { 4, 5, 6 },
            { 7, 8, 9 },
            { 100, 0, 100 }
    };

    static int T, N, count, numCheck, ans;
    static boolean wrongPass;
    static int[] origin, check;

    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("src\\KiemTraMatKhau\\input.txt"));

        Scanner sc = new Scanner(System.in);

        T = sc.nextInt();
        for (int test_case = 1; test_case <= T; test_case++) {

            N = sc.nextInt();
            origin = new int[N]; // dãy mật khẩu đúng
            check = new int[N]; // dãy mật khẩu nhập vào

            for (int i = 0; i < N; i++) {
                origin[i] = sc.nextInt();
            }

            for (int i = 0; i < N; i++) {
                check[i] = sc.nextInt();
            }

            count = 0; // đếm số lần nhập sai
            ans = 0;

            // duyệt cả dãy số
            for (int i = 0; i < N; i++) {
                wrongPass = true; // kiểm tra số nhập vào sai. Mặc dịnh là true

                // nếu số nhập không sai thì false
                if (check[i] == origin[i]) {
                    wrongPass = false;
                }
                // nếu số nhập sai
                else {
                    int r = 0, c = 0;

                    // xác định vị trí phím ĐÚNG
                    for (int j = 0; j < 4; j++) {
                        for (int k = 0; k < 3; k++) {
                            if (origin[i] == phimNhap[j][k]) {
                                r = j;
                                c = k;
                            }
                        }
                    }

                    // kiểm tra xem phím SAI đó có là phím liền kề với phím ĐÚNG không
                    int cr, cc;
                    // xét 4 phím kề xung quanh
                    for (int j = 0; j < 4; j++) {
                        cr = r + rs[j];
                        cc = c + cs[j];

                        if (cr >= 0 && cr < 4 && cc >= 0 && cc < 3) {
                            // nếu phím sai đó là phím kề
                            if (check[i] == phimNhap[cr][cc]) {
                                count++;            // tăng số lần nhập sai lên 1
                                ans = i + 1;        // gán ans là vị trí phím bị sai
                                wrongPass = false;  // trả về là false để COI NHƯ đã NHẬP ĐÚNG
                            }
                        }
                    }

                }

                /*
                 * sau khi duyệt xong phần tử đó nếu wrongPass là true (tức là phím đó NHẬP SAI
                 * và KHÔNG KỀ với phím đúng) HOẶC số lần nhập sai lớn hơn 1 thì không cần duyệt
                 * nữa. trả kết quả là -1 luôn. 
                 * Còn không thì ta duyệt tới phần từ tiếp theo
                 */
                if (wrongPass || count > 1) {
                    ans = -1;
                    break;
                }
            }

            System.out.println("#" + test_case + " " + ans);
        }
        sc.close();
    }
}