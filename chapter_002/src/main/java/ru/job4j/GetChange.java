package ru.job4j;

public class GetChange {

    /*
     * Метод по определению сдачи
     * @return массив монет по сдаче
     */
    public int[] changes(int value, int price) {
        int delta;
        int[] result = new int[0];
        delta = value - price;

        if (delta > 0) {

            int coins = 0;
            int coin10 = 0;
            int coin5 = 0;
            int coin2 = 0;

            if (delta / 10 > 0) {
                coin10 = delta / 10;
                }
            if ((delta - coin10 * 10) / 5 > 0) {
                coin5 = (delta - coin10 * 10) / 5;
                }
            if ((delta - coin10 * 10 - coin5 * 5) / 2 > 0) {
                coin2 = (delta - coin10 * 10 - coin5 * 5) / 2;
               }
            if ((delta - coin10 * 10 - coin5 * 5 - coin2 * 2) > 0) {
                coins++;
            }
            coins += coin10 + coin5 + coin2;
            result = new int[coins];

            for (int i = 0; i < coins; i++) {
                if (i < coin10) {
                    result[i] = 10;
                }
                if (i >= coin10 && i < (coin10 + coin5)) {
                    result[i] = 5;
                }
                if (i >= (coin10 + coin5) && i < (coin10 + coin5 + coin2)) {
                    result[i] = 2;
                }
                if (i >= (coin10 + coin5 + coin2)) {
                    result[i] = 1;
                }
            }

        }
        return result;
    }


    public static void main(String[] args) {
        GetChange getChange = new GetChange();

        System.out.println("Changed task.");
        for (int value: getChange.changes(50, 35)) {
            System.out.println(String.format("Change: %s", value));
        }
    }

}