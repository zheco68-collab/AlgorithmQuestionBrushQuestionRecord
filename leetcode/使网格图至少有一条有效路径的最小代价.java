//https://leetcode.cn/problems/minimum-cost-to-make-at-least-one-valid-path-in-a-grid/description/


import java.util.ArrayDeque;
import java.util.Deque;

public class 使网格图至少有一条有效路径的最小代价 {
    public int minCost(int[][] grid) {
        int[] dx = new int[]{0,0,1,-1};
        int[] dy = new int[]{1,-1,0,0};

        int n = grid.length,m = grid[0].length;
        int[][] dp = new int[n][m];

        for(int i=0;i<n;i=-~i) for(int j=0;j<m;j=-~j) dp[i][j] = Integer.MAX_VALUE;
        dp[0][0] = 0;
        Deque<int[]> qu = new ArrayDeque<>();
        qu.add(new int[]{0,0});

        while(!qu.isEmpty()){
            int[] u = qu.poll();
            int x = u[0],y = u[1];

            if(x==n-1&&y==m-1) return dp[x][y];

            for(int i=1;i<=4;i=-~i){
                int nx = x+dx[i-1];
                int ny = y+dy[i-1];
                int w = i==grid[x][y]?0:1;
                if(nx>=0&&nx<n&&ny>=0&&ny<m&&dp[x][y]+w<dp[nx][ny]){
                    dp[nx][ny] = dp[x][y]+w;
                    if(grid[x][y]==i) qu.offerFirst(new int[]{nx,ny});
                    else qu.offerLast(new int[]{nx,ny});
                }
            }
        }

        return -1;
    }
}
