// https://leetcode.cn/problems/swim-in-rising-water/

import java.util.Arrays;
import java.util.PriorityQueue;

public class 水位上升的泳池中游泳 {
    public int swimInWater(int[][] grid) {
        int n = grid.length,m = grid[0].length;
        boolean[][] vis = new boolean[n][m];
        int[][] dist = new int[n][m];
        for(int i=0;i<n;i=-~i) Arrays.fill(dist[i],Integer.MAX_VALUE);
        int[] move = new int[]{1,0,-1,0,1};

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[2]-o2[2]);
        pq.add(new int[]{0,0,grid[0][0]});  dist[0][0] = grid[0][0];

        while(!pq.isEmpty()){
            int[] cur = pq.poll();
            int x = cur[0],y = cur[1],w = cur[2];

            if(x==n-1&&y==m-1) return w;
            vis[x][y] = true;

            for(int i=0;i<4;i=-~i){
                int nx = x+move[i],ny = y+move[i+1];
                if(nx<n&&nx>=0&&ny<m&&ny>=0&&!vis[nx][ny]){
                    int nw = Math.max(w,grid[nx][ny]);
                    if(nw<dist[nx][ny]){
                        dist[nx][ny] = nw;
                        pq.add(new int[]{nx,ny,nw});
                    }
                }
            }
        }

        return -1;
    }
}
