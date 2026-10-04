//https://www.luogu.com.cn/problem/P17563


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Program_of_base_x_2029 {
    public static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    public static long Lsc(String s){
        return Long.parseLong(s);
    }

    public static int Isc(String s){
        return Integer.parseInt(s);
    }

    public static void main(String... args) throws IOException {
        StringBuilder out = new StringBuilder();

        int T = Isc(bf.readLine());
        while(T-->0){
            int l = 10,r = Integer.MAX_VALUE;
            long n = Lsc(bf.readLine()),ans = -1;
            while(l<=r){
                int k = (l+r)>>1;
                long t = ((long) (Math.pow(k, 3)) <<1)+(k<<1)+9;
                if(t==n){
                    ans = k;
                    break;
                }

                if(t<n) l = k+1;
                else r = k-1;
            }

            if(ans!=-1) out.append(ans).append("\n");
            else out.append("035966_L3").append("\n");
        }

        System.out.print(out);
    }
}
