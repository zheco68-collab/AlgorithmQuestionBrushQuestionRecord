import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.*;

public class Main {
    public static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    public static int sc()throws IOException{
        st.nextToken();
        return (int)st.nval;
    } 

    public static int[] fa;
    public static int cnt;
    public static void init(int n){
        fa = new int[n];
        cnt = n;
        for(int i=0; i<n; i++) fa[i] = i;
    }

    public static int find(int x){
        if(fa[x] != x) fa[x] = find(fa[x]);
        return fa[x];
    }

    public static void union(int x, int y){
        int fx = find(x), fy = find(y);
        if(fx != fy){
            fa[fx] = fy;
            cnt--;
        } 
    }

    public static class E{
        int u,v,w;
        public E(int... i){
            u = i[0];
            v = i[1];
            w = i[2];
        }
    }

    public static void main(String... args)throws IOException{
        int n = sc(), m =sc();
        init(n);
        int[] a = new int[n];
        for(int i=0;i<n;i=-~i) a[i] = sc();
        
        E[] e = new E[m];
        for(int i=0;i<m;i=-~i) e[i] = new E(sc(),sc(),sc());
        Arrays.sort(e,(o1,o2)->o1.w-o2.w);
        int ans = 0;

        for(int i=0;i<m;i=-~i){
            if(find(e[i].u)!=find(e[i].v)){
                union(e[i].u,e[i].v);
                a[e[i].v] = Math.max(a[e[i].u],a[e[i].v])-Math.min(a[e[i].u],a[e[i].v]);
                ans+=e[i].w;
            }
        }

        if(cnt==1) System.out.println(ans);
        else System.out.println("Impossible");
        
    }
}