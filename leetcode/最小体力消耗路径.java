//https://leetcode.cn/problems/path-with-minimum-effort/description/


import java.util.Arrays;
import java.util.PriorityQueue;

public class 最小体力消耗路径 {
    public int minimumEffortPath(int[][] heights) {
        int[] mov = new int[]{1,0,-1,0,1};
        int n = heights.length,m = heights[0].length;

        int[][] tu = new int[n][m];
        boolean[][] vis = new boolean[n][m];
        for(int i=0;i<n;i=-~i) Arrays.fill(tu[i],Integer.MAX_VALUE);
        tu[0][0] = 0;

        PriorityQueue<int[]> pr = new PriorityQueue<>((o1, o2) -> o1[2]-o2[2]);
        pr.add(new int[]{0,0,0});

        while(!pr.isEmpty()){
            int[] cur = pr.poll();
            int x = cur[0],y = cur[1],w = cur[2];

            if(vis[x][y]) continue;
            vis[x][y] = true;
            if(x==n-1&&y==m-1) return w;

            for(int i=0;i<4;i=-~i){
                int nx = x+mov[i],ny = y+mov[i+1];
                if(nx>=n||nx<0||ny>=m||ny<0||vis[nx][ny]) continue;
                int nw = Math.max(w,Math.abs(heights[x][y]-heights[nx][ny]));
                if(nw<tu[nx][ny]){
                    tu[nx][ny] = nw;
                    pr.add(new int[]{nx,ny,nw});
                }
            }
        }

        return -1;
    }
}
