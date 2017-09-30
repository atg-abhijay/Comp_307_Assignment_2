import java.util.Scanner;

public class MyDES {
    public static void main(String[] args){
        Scanner reader = new Scanner();
        String numberMessage = "Please enter a two-digit positive number";
        System.out.println(numberMessage);
        int key = reader.nextInt();
        if (key <= 9 || key >= 100) {
            System.out.println("The number you have entered is incorrect." + numberMessage);
            return;
        }
        System.out.println("Please enter a single sentence");
        String message = reader.next();
        if (message.length() == 0 || message.equals("")){
            System.out.println("You have not entered a valid sentence");
            return;
        }
        System.out.println("You entered: " + message);

    }

    public static String encrypt(String toEncrypt, int key) {
        /* creating a new 2D array to hold
            the user's input string */
        String[][] T = new String[key/10][1000];
        char[] stringAsChars = toEncrypt.toCharArray();
        int charIndex = 0;
        loop:
        for (int i = 0; i < T.length; i++) {
            for (int j = 0; j < T[i].length; j++) {
                T[i][j] = stringAsChars[charIndex];
                charIndex++;
                if (charIndex == stringAsChars.length){
                    break loop;
                }
            }
        }

        char[] A = new char[toEncrypt.length()]
        int transposedCharIndex = 0;
        String[][] transposeOfT = transpose(T);
        for (int i = 0; i < transposeOfT.length; i++) {
            for (int j = 0; j < transposeOfT[0].length; j++) {
                A[transposedCharIndex] = transposeOfT[i][j];
                transposedCharIndex++;
            }
        }

        return "Done";
    }

    public static String[][] transpose(String[][] T) {
        String[][] transposeOfT = new String[T[0].length][T.length];
        int height = 0;
        int width = 0;
        for(int i = 0; i < T.length; i++) {
            for(int j = 0; j < T[0].length; j++) {
                transposeOfT[height][width] = T[i][j];
                height++;
                if(height == transposeOfT.length) {
                    height = 0;
                    width++;
                }
            }
        }
        return transposeOfT;
    }

    public static String decrypt(String toDecrypt) {
        return null;
    }

}