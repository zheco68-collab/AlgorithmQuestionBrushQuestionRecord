//https://leetcode.cn/problems/closest-subsequence-sum/description/


import java.util.Arrays;
public class 最接近目标值的子序列和 {

    public int n;
    public int[] ls,rs;

    public int minAbsDifference(int[] nums, int goal) {
        n = nums.length;
        ls = new int[1<<(n>>1)];
        rs = new int[1<<(n-(n>>1))];
        return compute(nums,goal);
    }

    public int compute(int[] nums,int w){
        int l = f(0,n>>1,0,0,ls,nums);
        int r = f(n>>1,n,0,0,rs,nums);

        Arrays.sort(ls,0,l);
        Arrays.sort(rs,0,r);

        int ans = Math.abs(w);
        for(int i=0,j=r-1;i<l;i=-~i){
            while(j>0&&Math.abs(w-ls[i]-rs[j-1])<=Math.abs(w-ls[i]-rs[j]))j--;
            ans = Math.min(ans,Math.abs(w-ls[i]-rs[j]));
        }

        return ans;
    }

    public int f(int strat,int end,int j,int sum,int[] cur,int[] nums){
        if(strat==end) cur[j++] = sum;
        else{
             j = f(strat+1,end,j,sum,cur,nums);
             j = f(strat+1,end,j,sum+nums[strat],cur,nums);
        }
        return j;
    }
}
