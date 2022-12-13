package assn02;
import java.util.Scanner;
import java.text.DecimalFormat;

public class JavaWarmUp {
    public static void main(String[] args) {


        Scanner s = new Scanner(System.in);
        System.out.println("Enter your total number of transactions: ");
        String transactions_str = s.nextLine();
        int transactions_int = Integer.parseInt(transactions_str);

        String[][] transactionsArray = new String[transactions_int][];
        System.out.println("Enter your transactions: ");
        for (int i = 0; i < transactions_int; i++) {
            String current_transaction = s.nextLine();
            String sorted_transaction[] = current_transaction.split(" ", 0);
            transactionsArray[i] = sorted_transaction;
        }

        getMaxPriceIndex(transactionsArray);
        getMinPriceIndex(transactionsArray);
        averageByBook(transactionsArray);
        averageByJewelry(transactionsArray);
        averageByPhone(transactionsArray);

//        testGetMaxPriceIndex();


    }

//    public static int testGetMaxPriceIndex() {
//        String[][] testValues = {
//                "10/15/21 17:00 phone 85.50 12 4.0 8".split(" "),
//                "11/24/21 13:30 phone 959.90 44 4.5 7".split(" "),
//                "3/18/22 1:16 jewelry 1699.00 1 3.5 19".split(" "),
//                "4/7/22 20:45 phone 1699.00 1 5.0 3".split(" "),
//                "5/17/22 23:45 book 85.50 11 4.1 5".split(" "),
//                "5/19/22 07:16 book 15.50 2 3.0 12".split(" ")
//        }; // sample data
//
//        int result = getMaxPriceIndex(testValues);
//        System.out.println(result);
//        return 0;
//    }

    public static void getMaxPriceIndex(String[][] transactionsArray) {
        float greatestValue = 0;
        int indexOfMaxPrice = 0;
        for (int i = 0; i < transactionsArray.length; i++) {
            String[] currentTransaction = transactionsArray[i];
            float currentPrice = Float.parseFloat(currentTransaction[3]);
            if (currentPrice >= greatestValue) {
                greatestValue = currentPrice;
                indexOfMaxPrice = i;
            }
        }
        String[] highestTransaction = transactionsArray[indexOfMaxPrice];
        System.out.println("Highest per unit sale:");
        System.out.println("\tWhen: " + highestTransaction[0] + " " + highestTransaction[1]);
        System.out.println("\tCategory: " + highestTransaction[2]);
        System.out.println("\tPrice: " + highestTransaction[3]);
        System.out.println("\tRating: " + highestTransaction[5]);

    }

    public static void getMinPriceIndex(String[][] transactionsArray) {
        float lowestValue = Integer.MAX_VALUE;
        int indexOfMinPrice = 0;
        for (int i = 0; i < transactionsArray.length; i++) {
            String[] currentTransaction = transactionsArray[i];
            float currentPrice = Float.parseFloat(currentTransaction[3]);
            if (currentPrice <= lowestValue) {
                lowestValue = currentPrice;
                indexOfMinPrice = i;

            }
        }
        String[] lowestTransaction = transactionsArray[indexOfMinPrice];
        System.out.println("Lowest per unit sale:");
        System.out.println("\tWhen: " + lowestTransaction[0] + " " + lowestTransaction[1]);
        System.out.println("\tCategory: " + lowestTransaction[2]);
        System.out.println("\tPrice: " + lowestTransaction[3]);
        System.out.println("\tRating: " + lowestTransaction[5]);
    }

    public static void averageByBook(String[][] transactionsArray) {
        int totalQuantity = 0;
        double totalPrice = 0;
        double totalRating = 0;
        double totalDuration = 0;
        int totalTransactions = 0;

        for (int i = 0; i < transactionsArray.length; i++) {
            String[] currentTransaction = transactionsArray[i];
            if (currentTransaction[2].equals("book")) {
                int currentQuantity = Integer.parseInt(currentTransaction[4]);
                totalPrice += Double.parseDouble(currentTransaction[3]) * currentQuantity;
                totalQuantity += Integer.parseInt(currentTransaction[4]);
                totalRating += Double.parseDouble(currentTransaction[5]);
                totalDuration += Double.parseDouble(currentTransaction[6]);
                totalTransactions += 1;
            }
        }
        Double averagePrice = (totalPrice / totalQuantity);
        Double averageRating = (totalRating / totalTransactions);
        Double averageDuration = (totalDuration / totalTransactions);
        System.out.println("Averages by book");
        System.out.println("\tQuantity: " + totalQuantity);
        System.out.printf("\tPrice: %.2f\n", averagePrice);
        System.out.printf("\tRating: %.2f\n", averageRating);
        System.out.printf("\tDuration: %.2f\n", averageDuration);

    }

    public static void averageByJewelry(String[][] transactionsArray) {
        int totalQuantity = 0;
        double totalPrice = 0;
        double totalRating = 0;
        double totalDuration = 0;
        int totalTransactions = 0;

        String[] currentTransaction = {};
        for (int i = 0; i < transactionsArray.length; i++) {
            currentTransaction = transactionsArray[i];
            if (currentTransaction[2].equals("jewelry")) {
                int currentQuantity = Integer.parseInt(currentTransaction[4]);
                totalPrice += Double.parseDouble(currentTransaction[3]) * currentQuantity;
                totalQuantity += Integer.parseInt(currentTransaction[4]);
                totalRating += Double.parseDouble(currentTransaction[5]);
                totalDuration += Double.parseDouble(currentTransaction[6]);
                totalTransactions += 1;
            }
        }
        double averagePrice = (totalPrice / totalQuantity);
        double averageRating = (totalRating / totalTransactions);
        double averageDuration = (totalDuration / totalTransactions);
        System.out.println("Averages by jewelry");
        System.out.println("\tQuantity: " + totalQuantity);
        System.out.printf("\tPrice: %.2f\n", averagePrice);
        System.out.printf("\tRating: %.2f\n", averageRating);
        System.out.printf("\tDuration: %.2f\n", averageDuration);

    }

    public static void averageByPhone(String[][] transactionsArray) {
        int totalQuantity = 0;
        double totalPrice = 0;
        double totalRating = 0;
        double totalDuration = 0;
        int totalTransactions = 0;

        String[] currentTransaction = {};
        for (int i = 0; i < transactionsArray.length; i++) {
            currentTransaction = transactionsArray[i];
            if (currentTransaction[2].equals("phone")) {
                int currentQuantity = Integer.parseInt(currentTransaction[4]);
                totalPrice += Double.parseDouble(currentTransaction[3]) * currentQuantity;
                totalQuantity += Integer.parseInt(currentTransaction[4]);
                totalRating += Double.parseDouble(currentTransaction[5]);
                totalDuration += Double.parseDouble(currentTransaction[6]);
                totalTransactions += 1;
            }
        }
        DecimalFormat df = new DecimalFormat("#.00");
        double averagePrice = (totalPrice / totalQuantity);
        double averageRating = (totalRating / totalTransactions);
        double averageDuration = (totalDuration / totalTransactions);
        System.out.println("Averages by phone");
        System.out.println("\tQuantity: " + totalQuantity);
        System.out.printf("\tPrice: %.2f\n", averagePrice);
        System.out.printf("\tRating: %.2f\n", averageRating);
        System.out.printf("\tDuration: %.2f\n", averageDuration);

    }
}





