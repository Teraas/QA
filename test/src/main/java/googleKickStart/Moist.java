package googleKickStart;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
public class Moist {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int testCases = in.nextInt();
        //String s = in.nextLine();
        //System.out.println(testCases);

        for(int i=1;i<=testCases;i++){
            int cards = in.nextInt();
            List<String> cardNames = new ArrayList<>();
            for(int j=1;j<=cards;j++){
                System.out.println("here ");
                String name = in.nextLine();
                cardNames.add(name);
                System.out.println("this " + name);
            }
            getCost(cards, cardNames);
        }

    }
    static void getCost(int cards, List<String> cardNames){
        String temp = "Case #%s: %s ";
        System.out.println(cardNames);
        System.out.printf(temp,"1","1");

    }
}

