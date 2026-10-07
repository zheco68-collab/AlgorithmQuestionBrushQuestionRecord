//https://www.luogu.com.cn/problem/P2700


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.Arrays;

public class 逐个击破 {
    public static StreamTokenizer sz = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    public static int sc() throws IOException {
        sz.nextToken();
        return (int) sz.nval;
    }

    public static int[] fa;
    public static boolean[] vis;
    public static void init(int n){
        fa = new int[-~n];
        vis = new boolean[-~n];
        for(int i=0;i<=n;i=-~i) fa[i] = i;
    }

    public static int find(int x){
        if(x!=fa[x]) fa[x] = find(fa[x]);
        return fa[x];
    }

    public static boolean union(int a,int b){
        a = find(a);b = find(b);
        if(a==b||(vis[a]&&vis[b])) return false;
        fa[a] = b;
        vis[b] = vis[a]!=vis[b];
        return true;
    }

    public static class E{
        int u,v,w;
        public E(int... i){
            u = i[0];
            v = i[1];
            w = i[2];
        }
    }

    public static void main(String... args) throws IOException {
        StringBuilder out = new StringBuilder();
        int n = sc(),k = sc();
        init(n);
        for(int i=0;i<k;i=-~i) vis[sc()] = true;

        E[] e = new E[n-1];
        for(int i=0;i<n-1;i=-~i) e[i] = new E(sc(),sc(),sc());

        Arrays.sort(e,(o1, o2) -> o2.w-o1.w);
        long ans = 0L;
        for(int i=0;i<n-1;i=-~i) if(!union(e[i].u,e[i].v)) ans += e[i].w;
        System.out.println(ans);
    }
}