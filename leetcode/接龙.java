//https://leetcode.cn/problems/word-ladder/description/


import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class 接龙 {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> ci = new HashSet<>(wordList);
        if(!ci.contains(endWord)) return 0;

        Set<String> strat = new HashSet<>();
        Set<String> end = new HashSet<>();
        Set<String> next = new HashSet<>();
        strat.add(beginWord);end.add(endWord);

        for(int len = 2;!strat.isEmpty();len=-~len){
            for(String w:strat){
                char[] ws = w.toCharArray();
                for(int i=0;i<ws.length;i=-~i){
                    char old = ws[i];
                    for(char c='a';c<='z';c=(char)(c+1)){
                        if(c==old) continue;
                        ws[i] = c;
                        String ns = String.valueOf(ws);
                        if(end.contains(ns)) return len;

                        if(ci.contains(ns)){
                            ci.remove(ns);
                            next.add(ns);
                        }
                    }
                    ws[i] = old;
                }
            }

            if(next.size()<=end.size()){
                Set<String> temp = strat;
                strat = next;
                next = temp;
            }else{
                Set<String> temp = strat;
                strat = end;
                end = next;
                next = temp;
            }

            next.clear();
        }

        return 0;
    }
}
