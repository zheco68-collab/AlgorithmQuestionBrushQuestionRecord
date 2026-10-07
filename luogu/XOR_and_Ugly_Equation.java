
//https://www.luogu.com.cn/problem/P17569

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class XOR_and_Ugly_Equation {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer st;

    public static String next() throws IOException {
        while (st == null || !st.hasMoreTokens()) {
            String line = br.readLine();
            if (line == null) return null;
            st = new StringTokenizer(line);
        }
        return st.nextToken();
    }

    public static int sc() throws IOException {
        return Integer.parseInt(next());
    }

    public static long scLong() throws IOException {
        return Long.parseLong(next());
    }

    public static long ans;
    public static boolean found;

    public static void dfs(int depth, long cur, int K, long targetA){
        long mask = (depth == 62) ? -1L : ((1L << depth) - 1);
        long c = cur & mask;
        long val = ((c * c) ^ c) & mask;
        if(val!=(targetA & mask)) return;

        if(depth==K){
            if(!found||cur<ans){
                ans = cur;
                found = true;
            }
            return;
        }
        dfs(depth + 1, cur, K, targetA);
        dfs(depth + 1, cur | (1L << depth), K, targetA);
    }

    public static void main(String... args) throws IOException {
        StringBuilder out = new StringBuilder();
        String token = next();
        if (token == null) return;
        int T = Integer.parseInt(token);

        while(T-- > 0){
            int k = sc();
            long a = scLong();
            if((a & 1)!=0){
                out.append("NO\n");
                continue;
            }

            found = false;
            ans = Long.MAX_VALUE;

            dfs(1, 0L, k, a);
            dfs(1, 1L, k, a);

            if(found) out.append("YES\n").append(ans).append("\n");
            else out.append("NO\n");
        }

        System.out.print(out);
    }
}
