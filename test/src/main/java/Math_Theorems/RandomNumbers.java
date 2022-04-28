package Math_Theorems;

public class RandomNumbers {


    public static int rand7Table() {

        int[][] results = new int[][] {
                {1, 2, 3, 4, 5},
                {6, 7, 1, 2, 3},
                {4, 5, 6, 7, 1},
                {2, 3, 4, 5, 6},
                {7, 0, 0, 0, 0},
        };

        while (true) {

            // do our die rolls
            int row = rand5() - 1;
            int column = rand5() - 1;

            // case: we hit an extraneous outcome
            // so we need to re-roll
            if (row == 4 && column > 0) {
                continue;
            }

            // our outcome was fine. return it!
            return results[row][column];
        }
    }
    public static int rand7() {

        while (true) {

            // do our die rolls
            int roll1 = rand5();
            int roll2 = rand5();

            int outcomeNumber = (roll1-1) * 5 + (roll2-1) + 1;

            // if we hit an extraneous
            // outcome we just re-roll
            if (outcomeNumber > 21) continue;

            // our outcome was fine. return it!
            return outcomeNumber % 7 + 1;
        }
    }
    private static int rand5() {
        //write logic to generate number 1-5
        return 1;
    }
}
