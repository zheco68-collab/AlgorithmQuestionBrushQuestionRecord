//https://codeforces.com/problemset/problem/1245/D


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.Arrays;

public class ShichikujiAndPowerGrid {
    public static StreamTokenizer sz = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    public static int sc() throws IOException {
        sz.nextToken();
        return (int) sz.nval;
    }

    public static class Jd{
        int x,y;
        public Jd(int... i){
            x = i[0];
            y = i[1];
        }
    }

    public static class E{
        int u,v;
        long w;
        public E(int i1,int i2,long l1){
            u = i1;
            v = i2;
            w = l1;
        }
    }

    public static int[] fa;
    public static int count;
    public static void init(int n){
        fa = new int[-~n];
        count = n;
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
        count--;
        return true;
    }

    public static void main(String... args) throws IOException {
        StringBuilder out = new StringBuilder();
        int n = sc();init(n);
        Jd[] jd = new Jd[n+1];
        for(int i=1;i<=n;i=-~i) jd[i] = new Jd(sc(),sc());

        E[] e = new E[((int)1e6<<2)+2005];
        int cnt = 0;
        for(int i=1;i<=n;i=-~i) e[cnt++] = new E(0,i,sc());

        int[] k = new int[n+1];
        for(int i=1;i<=n;i=-~i) k[i] = sc();

        for(int i=1;i<=n;i=-~i) for(int j=i+1;j<=n;j=-~j){
             int c = Math.abs(jd[i].x-jd[j].x)+Math.abs(jd[i].y-jd[j].y);
             long w = (long) c *(k[i]+k[j]);
             e[cnt++] = new E(i,j,w);
        }

        Arrays.sort(e,0,cnt,(o1, o2) -> Long.compare(o1.w,o2.w));
        StringBuilder j = new StringBuilder();
        int jc = 0;
        StringBuilder b = new StringBuilder();
        int bc = 0;
        long ans = 0L;
        for(int i=0;i<cnt;i=-~i){
            if(union(e[i].u,e[i].v)){
                ans+=e[i].w;
                if(e[i].u==0){
                    j.append(e[i].v).append(" ");
                    jc=-~jc;
                }else{
                    b.append(e[i].u).append(" ").append(e[i].v).append("\n");
                    bc=-~bc;
                }
            }
            if(count==0) break;
        }

        out.append(ans).append("\n")
           .append(jc).append("\n")
           .append(j).append("\n")
           .append(bc).append("\n").append(b);
        System.out.print(out);

    }
}
