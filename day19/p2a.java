package adventofcode;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class p2a {

    static ArrayList<String> reactions = new ArrayList<>();

    static int reduce(ArrayList<String> input){
        ArrayList<String> target;
        int reductions = 0;
        for(String s : reactions){
            String[] parts = s.split(" ");
            target = parse(parts[2]);
            for(int i = 0; i <= input.size() - target.size(); i++){
                if(target.equals(input.subList(i, i+target.size()))){
                    input.set(i, parts[0]);
                    if(target.size() > 1){
                        for(int j = 1; j < target.size(); j++){
                            input.remove(i+1);
                        }
                    }
                    reductions++;
                }
            }
        }
        return reductions;
    }

    static ArrayList<String> parse(String s){
        char[] c = s.toCharArray();
        ArrayList<String> output = new ArrayList<>();

        for(int i = 0; i < c.length; i++){
            try{
                if(c[i+1] > 96){
                    output.add("" + c[i] + c[i+1]);
                    i++;
                }
                else if(c[i+1] < 91){
                    output.add("" + c[i]);
                }
            }
            catch(ArrayIndexOutOfBoundsException e){
                output.add("" + c[i]);
            }
        }

        return output;
    }

    public static void main(String[] args) throws FileNotFoundException {        
        Scanner s = new Scanner(new File("input"));
        ArrayList<String> target;
        String input;
        int total = 0;
        int reductions;

        for(int i = 0; i < 43; i++){
            reactions.add(s.nextLine());
        }

        s.nextLine();

        input = s.nextLine();
        target = parse(input);

        while(!(target.size() == 1 && target.get(0).equals("e"))){
            Collections.shuffle(reactions);
            reductions = reduce(target);
            if(reductions == 0){
                target = parse(input);
                total = 0;
            }
            else{
                total += reductions;
            }
        }

        System.out.println("The reaction takes " + total + " steps.");
    }
}
