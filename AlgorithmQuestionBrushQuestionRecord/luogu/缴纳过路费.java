//https://www.luogu.com.cn/problem/P11005

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.Arrays;

public class 缴纳过路费 {
    public static StreamTokenizer sz = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    public static int sc() throws IOException {
        sz.nextToken();
        return (int) sz.nval;
    }

    public static int[] fa,size;
    public static int cnt = 0;
    public static void init(int n){
        fa  = new int[n+1];
        size = new int[n+1];
        for(int i=1;i<=n;size[i]=1,i=-~i) fa[i] = i;
    }

    public static int find(int x){
        if(x!=fa[x]) fa[x] = find(fa[x]);
        return fa[x];
    }

    public static void union(int a,int b){
        a = find(a);b = find(b);
        if(a==b) return;
        fa[a] = b;
        size[b]+=size[a];
    }


    public static void main(String... args) throws IOException {
        StringBuilder out = new StringBuilder();
        int n = sc(),m = sc(),l = sc(),r = sc();
        init(n);
        int[][] b = new int[m][3];
        for(int i=0;i<m;i=-~i){
            b[i][0] = sc();
            b[i][1] = sc();
            b[i][2] = sc();
        }

        Arrays.sort(b,(o1, o2) -> o1[2]-o2[2]);
        long ans = 0L;
        for(int i=0;i<m&&b[i][2]<=r;i=-~i){
            int fu = find(b[i][0]),fv = find(b[i][1]);
            if(fu!=fv){
                if(b[i][2]>=l&&b[i][2]<=r) ans += (long) size[fu] *size[fv];
                union(fu,fv);
            }
        }
        System.out.println(ans);
    }
}
