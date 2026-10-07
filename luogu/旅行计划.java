//https://www.luogu.com.cn/problem/P1137

import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class 旅行计划 {
    public static StreamTokenizer sz = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    public static int sc()throws IOException{
        sz.nextToken();
        return (int)sz.nval;
    }

    public static void main(String... args) throws IOException {
        StringBuilder out = new StringBuilder();

        int n = sc(),m = sc();
        List<Integer>[] list = new List[n+1];
        int[] dp =new int[n+1],rd =new int[n+1];

        for(int i=1;i<=n;++i){
            list[i] = new ArrayList<>();
            dp[i] = 1;
        }

        while(m-->0){
            int u = sc(),v = sc();
            list[u].add(v);
            rd[v]++;
        }

        Queue<Integer> qu = new ArrayDeque<>();
        for(int i=1;i<=n;++i) if(rd[i]==0) qu.add(i);

        while(!qu.isEmpty()){
            int u = qu.poll();

            for(int v:list[u]){
                rd[v]--;
                dp[v] = Math.max(dp[u]+1,dp[v]);
                if(rd[v]==0) qu.add(v);
            }
        }

        for(int i=1;i<=n;++i) out.append(dp[i]).append("\n");
        System.out.print(out);
    }
}