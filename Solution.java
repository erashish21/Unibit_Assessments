import java.util.*;

public class Solution {
      // Function to get the pair of integers that sum up to the target value  
    public static int[][] findPair(int[] nums, int target) {
        Map<Integer, Integer> m = new HashMap<>();
        List<int[]> ls = new ArrayList<>();
       // Iterate through the array
        for (int num : nums) {
            int i = target - num;
            if (m.containsKey(i)) {
                 // If a pair is found, add it to the list
                ls.add(new int[]{num, i});
            }
            m.put(num, num);
        }

          // Convert the list to a 2D array
          
        int[][] ans = new int[ls.size()][2];
        for (int i = 0; i < ls.size(); i++) {
            ans[i] = ls.get(i);
        }

        return ans;
    }

     // Function to merge 2D arrays into a single 1D array
    public static int[] mergeArrays(int[][] arrays) {
        int len = 0;
        for (int[] array : arrays) {
            len += array.length;
        }

        int[] mArray = new int[len];
        int currIdx = 0;

         // Copy elements from each array to the merged array
        for (int[] array : arrays) {
            System.arraycopy(array, 0, mArray, currIdx, array.length);
            currIdx += array.length;
        }

        return mArray;
    }
   
 // Recursive function to find multiple valued 2D arrays with a sum equal to doubleTarget
    private static void solve(int ind,int target,int[]candidates,List<List<Integer>>ans,ArrayList<Integer>temp){
        if(target==0){
            // If the target is reached, add the combination to the result list
            ans.add(new ArrayList<>(temp));
            return;
        }

        for(int i=ind;i<candidates.length;i++){
            if(i!=ind && candidates[i]==candidates[i-1]) continue;
            if(candidates[i]>target) 
            break;  // Optimization: stop searching if the current number is greater than the remaining target


            temp.add(candidates[i]);
            solve(i+1,target-candidates[i],candidates,ans,temp);     // Recursively search for the remaining sum
            temp.remove(temp.size()-1);
        }
    }    

    // Function to find multiple valued 2D arrays with a sum equal to doubleTarget
    public static int[][] findDoubleCombinations(int[] mergedArray, int doubleTarget) {
    List<List<Integer>> res = new ArrayList<>();
    solve(0, doubleTarget, mergedArray, res, new ArrayList<>());
     // Convert the list to a 2D array
    int[][] result = new int[res.size()][];

    for (int i = 0; i < res.size(); i++) {
        result[i] = new int[res.get(i).size()]; // Initialize the subarray
        for (int j = 0; j < res.get(i).size(); j++) {
            result[i][j] = res.get(i).get(j);
        }
    }
    return result;
}




    public static void main(String[] args) {
       // Taking input from Terminal
        Scanner scan = new Scanner(System.in);
        int size = scan.nextInt();             // Taking the size of array
        int[] nums = new int[size];
        for(int i = 0; i < size; i++){
            nums[i] = scan.nextInt();          // Taking Array value
        }
        int target = scan.nextInt();          // Taking target value
        int doubleTarget = target * 2;      // Double the target


          // Finding pairs in the array that sum up to the target
        int[][] combinations = findPair(nums, target);
        System.out.println("First Combination For \"" + target + "\":");
        for (int[] combination : combinations) {
            System.out.println(Arrays.toString(combination));
        }

         // Merging the arrays and sorting them
        int[] mergedArray = mergeArrays(combinations);
        Arrays.sort(mergedArray);
        System.out.println("Merge Into a single Array:");
        System.out.println(Arrays.toString(mergedArray));

         // Finding combinations in the merged array that sum up to doubleTarget
        int[][] doubleCombinations = findDoubleCombinations(mergedArray, doubleTarget);
        System.out.println("Second Combination For \"" + doubleTarget + "\":");
        for (int[] combination : doubleCombinations) {
            System.out.println(Arrays.toString(combination));
        }
    }
}