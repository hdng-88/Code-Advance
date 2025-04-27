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
        System.setIn(new FileInputStream("Array\\src\\KiemTraMatKhau\\input.txt"));

        Scanner sc = new Scanner(System.in);

        T = sc.nextInt();
        for (int test_case = 1; test_case <= T; test_case++) {

            N = sc.nextInt();
            origin = new int[N];
            check = new int[N];

            for (int i = 0; i < N; i++) {
                origin[i] = sc.nextInt();
            }

            for (int i = 0; i < N; i++) {
                check[i] = sc.nextInt();
            }

            count = 0;
            ans = 0;
            for (int i = 0; i < N; i++) {
                wrongPass = true;

                if (check[i] == origin[i]) {
                    wrongPass = false;
                } else {
                    int r = 0, c = 0;
                    for (int j = 0; j < 4; j++) {
                        for (int k = 0; k < 3; k++) {
                            if (origin[i] == phimNhap[j][k]) {
                                r = j;
                                c = k;
                            }
                        }
                    }

                    int cr, cc;
                    for (int j = 0; j < 4; j++) {
                        cr = r + rs[j];
                        cc = c + cs[j];

                        if (cr >= 0 && cr < 4 && cc >= 0 && cc < 3) {
                            if(check[i] == phimNhap[cr][cc]){
                                count++;
                                ans = i+1;
                                wrongPass = false;
                            }
                        }
                    }

                }

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