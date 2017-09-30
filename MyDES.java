import java.util.Scanner;

public class MyDES {
    public static void main(String[] args){
        // Scanner reader = new Scanner();
        // String numberMessage = "Please enter a two-digit positive number";
        // System.out.println(numberMessage);
        // int key = reader.nextInt();
        // if (key <= 9 || key >= 100) {
        //     System.out.println("The number you have entered is incorrect." + numberMessage);
        //     return;
        // }
        // System.out.println("Please enter a single sentence");
        // String message = reader.next();
        // if (message.length() == 0 || message.equals("")){
        //     System.out.println("You have not entered a valid sentence");
        //     return;
        // }
        // System.out.println("You entered: " + message);
        String foo = "abc";
        char[] A = foo.toCharArray();
        char[] B = new char[A.length];
        for (int i = 0; i < B.length; i++) {
            int newChar = A[i] + 5;
            B[i] = (char)newChar;
        }
        for (int i = 0; i < B.length; i++) {
            System.out.print(B[i]);
        }

    }

    public static char[][] populateCharArray(char[] stringAsChars, int useKey) {
        char[][] stringBlock = new char[useKey][1000];
        int charIndex = 0;
        loop:
        for (int i = 0; i < stringBlock.length; i++) {
            for (int j = 0; j < stringBlock[i].length; j++) {
                stringBlock[i][j] = stringAsChars[charIndex];
                charIndex++;
                if (charIndex == stringAsChars.length){
                    break loop;
                }
            }
        }
        return stringBlock;
    }

    public static char[] makeCharFromTranspose(char[][] transpose, int arrayLength) {
        int index = 0;
        char[] charArray = new char[arrayLength];
        for (int i = 0; i < transpose.length; i++) {
            for (int j = 0; j < transpose[0].length; j++) {
                charArray[index] = transpose[i][j];
                index++;
            }
        }
        return charArray;
    }


    public static String encrypt(String toEncrypt, int key) {
        // populate char[][] with user's input string
        char[] userStringAsChar = toEncrypt.toCharArray();
        char[][] T = populateCharArray(userStringAsChar, key/10);
        // take tranpose of char[][]
        char[][] transposeOfT = transpose(T);
        /* making a char[] from the
            transpose of char[][] T */
        char[] A = makeCharFromTranspose(transposeOfT, toEncrypt.length());
        /* making a new char[] B, where
            B is the Caesar cipher of A */
        char[] B = new char[A.length];
        for (int i = 0; i < B.length; i++) {
            int newChar = A[i] + key;
            B[i] = (char) newChar;
        }

        /* making a char[][] using
            the char[] B */
        char[][] T2 = populateCharArray(B, (key/10)+1);
        // taking transpose of T2
        char[][] transposeT2 = transpose(T2);


        return "Done";
    }

    public static char[][] transpose(char[][] T) {
        char[][] transposeOfT = new char[T[0].length][T.length];
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