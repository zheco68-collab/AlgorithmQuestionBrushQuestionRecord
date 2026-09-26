//https://leetcode.cn/problems/stickers-to-spell-word/


import java.util.*;

public class 贴纸拼词 {
        public List<String>[] lists = new List[26];

        public int minStickers(String[] stickers, String target) {
            for(int i=0;i<26;i=-~i) lists[i] = new ArrayList<String>();

            int n = stickers.length;
            for(int i=0;i<n;i=-~i){
                stickers[i] = sort(stickers[i]);
                for(int j=0;j<stickers[i].length();j=-~j)
                    if(j==0||stickers[i].charAt(j)!=stickers[i].charAt(j-1))
                        lists[stickers[i].charAt(j)-'a'].add(stickers[i]);
            }

            target = sort(target);
            HashSet<String> vis = new HashSet<>();
            Queue<String> qu = new ArrayDeque<>();
            vis.add(target);qu.add(target);
            int ans = 1;

            while(!qu.isEmpty()){
                int size = qu.size();
                for(int i=0;i<size;i=-~i){
                    String cur = qu.poll();
                    for(String str:lists[cur.charAt(0)-'a']){
                        String next = toNext(cur,str);
                        if(next.isEmpty()) return ans;

                        if(!vis.contains(next)){
                            vis.add(next);
                            qu.add(next);
                        }
                    }
                }
                ans=-~ans;
            }
            return -1;
        }


        public String sort(String s){
            char[] cs = s.toCharArray();
            Arrays.sort(cs);
            return new String(cs);
        }


        public String toNext(String s,String s1){
            int x = 0,y = 0;
            StringBuilder bf = new StringBuilder();
            for(;x<s.length()&&y<s1.length();){
                if(s.charAt(x)==s1.charAt(y)) {
                    x = -~x;
                    y = -~y;
                }else if(s.charAt(x)<s1.charAt(y)){
                    bf.append(s.charAt(x));
                    x=-~x;
                }else{
                    y=-~y;
                }
            }
            bf.append(s.substring(x));
            return bf.toString();
        }

}
