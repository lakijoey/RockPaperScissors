import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static Profile one = new Profile();      //always rock
    public static Profile two = new Profile();      //always random
    public static Profile three = new Profile();    //what two showed last round
    public static ArrayList<Profile> scores = new ArrayList<>();

    public static int askHowManyRounds() {
        Scanner sc = new Scanner(System.in);
        System.out.println("How many rounds?");
        return sc.nextInt();
    }


    public static void forcingFirstHand() {
        one.setHand(RockPaperScissors.Rock);
        two.setHand(RockPaperScissors.Rock);
        three.setHand(RockPaperScissors.Rock);
    }

    public static void setHands() {
        three.setHand(two.getHand());
        two.setHand(RockPaperScissors.values()[new Random().nextInt(RockPaperScissors.values().length)]);
    }

    public static void checkWinner() {
        if (two.getHand() == RockPaperScissors.Scissors && three.getHand() == RockPaperScissors.Scissors) {
            scores.add(one); //Rock Beats Scissors
        } else if (two.getHand() == RockPaperScissors.Paper && three.getHand() == RockPaperScissors.Rock) {
            scores.add(two); //Paper Beats Rocks
        } else if (three.getHand() == RockPaperScissors.Paper && two.getHand() == RockPaperScissors.Rock) {
            scores.add(three); //Paper Beats Rocks
        } else if ((two.getHand() == RockPaperScissors.Paper && three.getHand() == RockPaperScissors.Scissors) ||
                (two.getHand() == RockPaperScissors.Scissors && three.getHand() == RockPaperScissors.Paper)) {
            setHands(); //Full Tie
        } else if (two.getHand() == RockPaperScissors.Rock && three.getHand() == RockPaperScissors.Rock) {
            setHands(); //Full Tie
        } else if (two.getHand() == RockPaperScissors.Rock && three.getHand() == RockPaperScissors.Scissors) {
            do {
                setHands();
            } while (two.getHand() == RockPaperScissors.Rock);
            if (two.getHand() == RockPaperScissors.Paper) {
                scores.add(two);
            } else {
                scores.add(one);
            }

        } else if (two.getHand() == RockPaperScissors.Scissors && three.getHand() == RockPaperScissors.Rock){
            do{
                setHands();
            } while (three.getHand() == RockPaperScissors.Rock);
            if (three.getHand() == RockPaperScissors.Paper){
                scores.add(three);
            } else {
                scores.add(one);
            }
        }
    }

    public static void statistics(){
        System.out.println("Always Rock won                   " + Collections.frequency(scores, one) + " times.");
        System.out.println("Always Random won                 " + Collections.frequency(scores, two) + " times.");
        System.out.println("What Random Showed Last Round won " + Collections.frequency(scores,three) + " times.");
    }


    public static void main(String[] args) {
        forcingFirstHand();
        int x = askHowManyRounds();
        for (int i = 0; i < x; i++) {
            setHands();
            checkWinner();
        }
        statistics();
    }
}
