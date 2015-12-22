package adventofcode;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class p1a {

    static ArrayList<String> molecules = new ArrayList<>();
    static ArrayList<String> reactions = new ArrayList<>();
    static ArrayList<String> input = new ArrayList<>();

    static void react(String formula){
        String[] parts = formula.split(" ");

        boolean duplicate = false;
        String output = "";

        for(int i = 0; i < input.size(); i++){
            if(input.get(i).equals(parts[0])){
                input.set(i, parts[2]);

                for(String t : input){
                    output += t;
                }

                for(String t : molecules){
                    if(t.equals(output)){
                        duplicate = true;
                    }
                }
                if(!duplicate){
                    molecules.add(output);
                }

                duplicate = false;
                output = "";
                input.set(i, parts[0]);
            }
        }
    }

    static void parse(String s){
        char[] c = s.toCharArray();

        for(int i = 0; i < c.length; i++){
            try{
                if(c[i+1] > 96){
                    input.add("" + c[i] + c[i+1]);
                    i++;
                }
                else if(c[i+1] < 91){
                    input.add("" + c[i]);
                }
            }
            catch(ArrayIndexOutOfBoundsException e){
                input.add("" + c[i]);
            }
        }
    }

    public static void main(String[] args) throws FileNotFoundException {        
        Scanner s = new Scanner(new File("input"));

        for(int i = 0; i < 43; i++){
            reactions.add(s.nextLine());
        }

        s.nextLine();

        parse(s.nextLine());

        for(String t : reactions){
            react(t);
        }

        System.out.println("There are " + molecules.size() + " different molecules.");
    }
}
