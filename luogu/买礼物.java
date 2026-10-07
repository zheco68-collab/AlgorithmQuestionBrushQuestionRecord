//https://www.luogu.com.cn/problem/P1194


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.Arrays;

public class 买礼物 {
    public static StreamTokenizer sz = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    public static int sc() throws IOException {
        sz.nextToken();
        return (int) sz.nval;
    }

    public static int[] fa;
    public static void init(int n){
        fa = new int[-~n];
        for(int i=0;i<=n;i=-~i) fa[i] = i;
    }

    public static int find(int x){
        if(x!=fa[x]) fa[x] = find(fa[x]);
        return fa[x];
    }

    public static boolean union(int a,int b){
        a = find(a);b = find(b);
        if(a==b) return false;
        fa[a] = b;
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
        int a = sc(),b = sc(),cnt = 0;
        init(b);
        E[] e = new E[b+(b*b)];
        for(int i=1;i<=b;i=-~i){
            e[cnt++] = new E(0,i,a);
            e[cnt++] = new E(i,0,a);
        }

        for(int i=1;i<=b;i=-~i) for(int j=1;j<=b;j=-~j) {
            int w = sc();
            if(w!=0) e[cnt++] = new E(i,j,w);
        }

        int ans = 0;
        Arrays.sort(e,0,cnt,(o1, o2) -> o1.w-o2.w);
        for(int i=0;i<cnt;i=-~i) if(union(e[i].u,e[i].v)){
            ans+=e[i].w;
        }


        System.out.println(ans);
    }
}
