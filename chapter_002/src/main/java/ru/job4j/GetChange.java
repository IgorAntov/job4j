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
                delta = delta - coin10 * 10;
                coins = coin10;
            }
            if (delta / 5 > 0) {
                coin5 = delta / 5;
                delta = delta - coin5 * 5;
                coins = coins + coin5;
            }
            if (delta / 2 > 0) {
                coin2 = delta / 2;
                delta = delta - coin2 * 5;
                coins = coins + coin2;
            }
            if (delta > 0) {
                coins++;
            }

            result = new int[coins];

            for (int i = 0; i < coins;i++) {
                if (i < coin10) {
                    result[i] = 10;
                }
                if (i >= coin10 && i< (coin10+coin5)) {
                    result[i] = 5;
                }
                if (i >= (coin10 + coin5) && i < (coin10 + coin5 + coin2)) {
                    result[i] = 2;
                }
                if (i >= (coin10 + coin5 + coin2)) {
                    result[i] =1;
                }
            }

        }
        return result;
    }


    public static void main(String[] args) {
        GetChange getChange = new GetChange();

        System.out.println("Changed task.");
        for (int value: getChange.changes(50,35)) {
            System.out.println(String.format("Change: %s",value));
        }
    }

}