//Original problem at https://leetcode.com/problems/minimum-genetic-mutation/#/description

public class Solution {
    public int minMutation(String start, String end, String[] bank) {
        //if 2 Strings are equal, no need to continue
        if (start.equals(end)) {
            return 0;
        }
        
        //create a HashSet for the gene pool
        Set<String> bankSet = new HashSet<>();
        for (String b: bank) {
            bankSet.add(b);
        }
        
        //ACGT
        char[] charSet = {'A', 'C', 'G', 'T'};
        
        //Gene Queue
        int level = 0;
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        //add the starting point to the queue
        queue.offer(start);
        //add it to the visited list of gene strings
        visited.add(start);
        
        while(!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                //take the current head of the queue
                String current = queue.poll();
                //if current string equals the end result, return the number of mutation
                if (current.equals(end)) {
                    return level;
                }
                char[] currentArray = current.toCharArray();
                for (int i = 0; i < currentArray.length; i++) {
                    char old = currentArray[i];
                    for (char c: charSet) {
                        currentArray[i] = c;
                        String next = new String(currentArray);
                        //check if the new gene string is valid (in the bank)
                        //if it is valid, add it to the visited list
                        if (!visited.contains(next) && (bankSet.contains(next))) {
                            visited.add(next);
                            queue.offer(next);
                        }
                    }
                    currentArray[i] = old;
                }
            }
            level++;
        }
        //if the loop ends and there is no available solutions, return -1
        return -1;
    }
}