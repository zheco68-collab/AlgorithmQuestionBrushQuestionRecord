
//https://www.luogu.com.cn/problem/P1967

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class 货车运输 {
    public static StreamTokenizer sz = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    public static int sc() throws IOException {
        sz.nextToken();
        return (int) sz.nval;
    }

    public static int[] fa;
    public static Set<Integer>[] set;
    public static void init(int n){
        fa = new int[n+1];
        set = new Set[n+1];
        for(int i=1;i<=n;set[i] = new HashSet<>(),i=-~i) fa[i] = i;
    }

    public static int find(int x){
        if(x!=fa[x]) fa[x] = find(fa[x]);
        return fa[x];
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
        int n = sc(),m = sc();
        init(n);

        E[] e = new E[m];
        for(int i=0;i<m;i=-~i) e[i] = new E(sc(),sc(),sc());
        Arrays.sort(e,(o1, o2) -> o2.w-o1.w);

        int g = sc();
        int[] ans= new int[g];
        Arrays.fill(ans,-1);

        for(int i=0;i<g;i=-~i){
            int u = sc(), v = sc();
            if(u!=v){
                set[u].add(i);
                set[v].add(i);
            }
        }

        for(int i=0;i<m;i=-~i){
            int a = find(e[i].u),b = find(e[i].v);
            if(a!=b){
                if(set[a].size()<set[b].size()){
                    int temp = a;
                    a = b;
                    b = temp;
                }

                for(int qId : set[b]){
                    if(set[a].contains(qId)){
                        ans[qId] = e[i].w;
                        set[a].remove(qId);
                    }else set[a].add(qId);
                }

                set[b].clear();
                fa[b] = a;
            }
        }

        for(int i=0;i<g;i=-~i) out.append(ans[i]).append("\n");
        System.out.print(out);
    }
}
