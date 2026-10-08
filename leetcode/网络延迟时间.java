//https://leetcode.cn/problems/network-delay-time/description/

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class 网络延迟时间 {
    public class E{
        int v,w;
        public E(int... i){
            v = i[0];
            w = i[1];
        }
    }

    public int networkDelayTime(int[][] times, int n, int k) {
        List<E>[] lists = new List[n+1];
        int[] cu = new int[n+1];

        for(int i=1;i<=n;i=-~i){
            lists[i] = new ArrayList<>();
            cu[i] = Integer.MAX_VALUE;
        }

        for(int[] cur:times) lists[cur[0]].add(new E(cur[1],cur[2]));

        PriorityQueue<Integer> pr = new PriorityQueue<>((o1, o2) -> cu[o1]-cu[o2]);
        cu[k] = 0;pr.add(k);
        int max = 0;

        while(!pr.isEmpty()){
            int u = pr.poll();
            max = Math.max(max,cu[u]);
            for(E next:lists[u]) if(cu[next.v]>cu[u]+next.w){
                cu[next.v] = cu[u]+next.w;
                pr.add(next.v);
            }
        }

        for(int i=1;i<=n;i=-~i) if(cu[i]==Integer.MAX_VALUE) return -1;
        return max;
    }
}
