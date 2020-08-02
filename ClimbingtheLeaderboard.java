import java.io.*;
import java.util.*;
import java.util.Stack;

public class ClimbingtheLeaderboard{
    
    private static ArrayList<Integer> strTointArr(String[] str){
        ArrayList<Integer> int_list = new ArrayList<Integer>();
        for(String s : str){
            int_list.add(Integer.parseInt(s));
        }
        return int_list;
    }

    private static int[] toIntArray(ArrayList<Integer> arr_list){
        int[] ans = new int[arr_list.size()];
        for(int i = 0; i < arr_list.size(); i++){
            ans[i] = arr_list.get(i);
        }
        return ans;
    }

    private static int[] throwawayDuplicate(int[] scores) {
        Stack<Integer> stack = new Stack<Integer>();
        for (int score : scores) {
            if (!stack.isEmpty()) {
                if (stack.peek() == score)
                    continue;
                stack.push(score);
                continue;
            } else
                stack.push(score);
        }
        int[] arr = new int[stack.size()];
        int index = 0;

        while (!stack.isEmpty()) {
            arr[index] = stack.pop();
            index++;
        }

        return arr;
    }

    private static void makePlacementList(Vector<Vector<Integer>> placementList, int[] scoreList){
        for(int i = 1; i < scoreList.length + 1; i++){
            Vector<Integer> tup = new Vector<Integer>();
            tup.add(0); tup.add(0);
            tup.set(0, scoreList[i-1]);
            tup.set(1, scoreList.length-i+1);
            placementList.add(tup);
        }
    }
   
    private static ArrayList<Integer> makeAnsList(int[] aliceList,Vector<Vector<Integer>> placementList){
        int index = 0;
        ArrayList<Integer> ans_list = new ArrayList<Integer>();
        for(int a_score: aliceList){
            while((index != placementList.size())&&(a_score>=(placementList.get(index)).get(0))){
                index++;
            }
            if(index == placementList.size()){
                ans_list.add(1);
                continue;
            }
            int placement = (placementList.get(index)).get(1) + 1;
            ans_list.add(placement);
        }
        return ans_list;
    }
    public static ArrayList<Integer> climbingLeaderboard(int[] scores, int [] alice){
        int[] scoreList = throwawayDuplicate(scores);
        
        Vector<Vector<Integer>> placementList = new Vector<Vector<Integer>>();
        
        makePlacementList(placementList,scoreList);
        return makeAnsList(alice,placementList);
    }
    public static void main(final String args[]) throws Exception{
        int score_length = 0 ;
        String score_string = "";
        int alice_length = 0;
        String alice_string = "";
        try {
            File myObj = new File("input.txt");
            Scanner myReader = new Scanner(myObj);
            int line = 1;
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if(line == 1)
                    score_length = Integer.parseInt(data);
                if(line == 2)
                    score_string = data;
                if(line == 3)
                    alice_length = Integer.parseInt(data);
                if(line == 4)
                    alice_string = data;
                line++;
            }
            myReader.close();
          } 
          catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }
          String[] alice_list = alice_string.split(" ");
          String[] score_list = score_string.split(" ");
          ArrayList <Integer> alice_arr_list = strTointArr(alice_list);
          ArrayList <Integer> score_arr_list = strTointArr(score_list);
          int[] alice = toIntArray(alice_arr_list);
          int[] score = toIntArray(score_arr_list);
          ArrayList<Integer> ans = climbingLeaderboard(score, alice);
         for(Integer i : ans){
              System.out.println(i.toString());
          }

    }

    
}