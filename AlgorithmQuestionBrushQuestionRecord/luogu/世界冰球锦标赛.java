//https://www.luogu.com.cn/problem/P4799


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 世界冰球锦标赛 {
    public static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    public static int Isc(String val){
        return Integer.parseInt(val);
    }

    public static long Lsc(String val){
        return Long.parseLong(val);
    }

    public static long[] arr,ls,rs;
    public static int n;
    public static long m;

    public static void main(String... args)throws IOException{
        StringTokenizer sz = new StringTokenizer(bf.readLine());
        n = Isc(sz.nextToken());
        m = Lsc(sz.nextToken());

        arr = new long[n];
        ls = new long[1 <<(n>>1)];
        rs = new long[1 <<(n-(n>>1))];

        sz = new StringTokenizer(bf.readLine());
        for(int i=0;i<n;i=-~i) arr[i] = Lsc(sz.nextToken());
        System.out.println(compute());
    }

    public static long compute(){
        int l = f(0,n>>1,0,m,0L,ls);
        int r = f(n>>1,n,0,m,0L,rs);

        Arrays.sort(ls,0,l);
        Arrays.sort(rs,0,r);
        long ans = 0L;

        for(int i=l-1,j=0;i>=0;i--){
            while(j<r&&ls[i]+rs[j]<=m) j=-~j;
            ans+=j;
        }
        return ans;
    }

    public static int f(int strat,int end,int j,long w,long sum,long[] cur){
        if(sum>w)return j;

        if(strat==end) cur[j++] = sum;
        else {
            j = f(strat+1,end,j,w,sum,cur);
            j = f(strat+1,end,j,w,sum+arr[strat],cur);
        }
        return j;
    }
}
