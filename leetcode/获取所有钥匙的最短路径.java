//https://leetcode.cn/problems/shortest-path-to-get-all-keys/description/

import java.util.ArrayDeque;
import java.util.Queue;

public class 获取所有钥匙的最短路径 {
    public int shortestPathAllKeys(String[] grid) {
        int n = grid.length,m = grid[0].length();
        Queue<int[]> qu = new ArrayDeque<>();
        int key = 0;

        for(int i=0;i<n;i=-~i) for(int j=0;j<m;j=-~j){
            if(grid[i].charAt(j)=='@') qu.add(new int[]{i,j,0});
            if(grid[i].charAt(j)>='a'&&grid[i].charAt(j)<='f') key |= 1<<(grid[i].charAt(j)-'a');
        }

        boolean[][][] vis = new boolean[n][m][key];
        int[] move = new int[]{1,0,-1,0,1};
        int len = 1;

        while(!qu.isEmpty()){
            int cnt = qu.size();
            for(int p=0;p<cnt;p=-~p){
                int[] cur = qu.poll(); if(cur==null) break;
                int x = cur[0],y = cur[1],k = cur[2];

                for(int i=0;i<4;i=-~i){
                    int nx = x+move[i],ny = y+move[i+1],nk = k;
                    if(nx<0||nx>=n||ny<0||ny>=m) continue;
                    if(grid[nx].charAt(ny)>='A'&&grid[nx].charAt(ny)<='F'&&((k&(1<<(grid[nx].charAt(ny)-'A')))==0)) continue;
                    if(grid[nx].charAt(ny)=='#') continue;
                    if(grid[nx].charAt(ny)>='a'&&grid[nx].charAt(ny)<='f') nk |= 1<<(grid[nx].charAt(ny)-'a');
                    if(nk==key) return len;

                    if(!vis[nx][ny][nk]){
                        vis[nx][ny][nk] = true;
                        qu.add(new int[]{nx,ny,nk});
                    }
                }
            }
            len++;
        }
        return -1;
    }
}
