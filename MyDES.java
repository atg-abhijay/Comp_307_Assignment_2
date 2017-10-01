import java.util.Scanner;
import java.lang.Math;

public class MyDES {
    public static void main(String[] args){
        Scanner reader = new Scanner(System.in);
        String numberMessage = "Please enter a two-digit positive number";
        System.out.println(numberMessage);
        int key = reader.nextInt();
        if (key <= 9 || key >= 100) {
            System.out.println("The number you have entered is incorrect. " + numberMessage);
            reader.close();
            return;
        }
        System.out.println("Please enter a single sentence");
        String message = reader.next();
        if (message.length() == 0 || message.equals("")){
            System.out.println("You have not entered a valid sentence");
            reader.close();
            return;
        }
        System.out.println("\nYou entered: " + message);
        String encryptedString = encrypt(message, key);
        System.out.println("Encrypted string: " + encryptedString);
        String decryptedString = decrypt(encryptedString, key);
        System.out.println("Decrypted string: " + decryptedString);
        reader.close();
    }

    public static char[][] populateCharArray(char[] stringAsChars, int index1, int index2) {
        char[][] stringBlock = new char[index1][index2];
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
                if (transpose[i][j] != '\0') {
                    charArray[index] = transpose[i][j];
                    index++;
                }
            }
        }
        return charArray;
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

    public static String encrypt(String toEncrypt, int key) {
        // populate char[][] with user's input string
        char[] userStringAsChar = toEncrypt.toCharArray();
        char[][] T = populateCharArray(userStringAsChar, key/10, 1000);
        // take tranpose of char[][]
        char[][] transposeOfT = transpose(T);
        /* making a char[] from the
            char[][] transposeOfT */
        char[] A = makeCharFromTranspose(transposeOfT, toEncrypt.length());
        /* making a new char[] B, where
            B is the Caesar cipher of A */
        char[] B = new char[A.length];
        for (int i = 0; i < B.length; i++) {
            int newChar = (A[i] + key) % 128;
            B[i] = (char) newChar;
        }

        /* making a char[][] using
            the char[] B */
        char[][] T2 = populateCharArray(B, (key/10)+1, 1000);
        // taking transpose of T2
        char[][] transposeT2 = transpose(T2);
        /* making a char[] from the
            char[][] transposeT2 */
        char[] C = makeCharFromTranspose(transposeT2, B.length);
        String encryptedString = new String(C);
        return encryptedString;
    }

    public static String decrypt(String toDecrypt, int key) {
        // follows the reverse process of encrypt()
        int length = toDecrypt.length();
        char[] encryptedStringAsChar = toDecrypt.toCharArray();
        char[][] D = populateCharArray(encryptedStringAsChar, (key/10)+1, 1000);
        char[][] D2 = transpose(D);
        char[] interim = makeCharFromTranspose(D2, length);
        char[] reverseCaesar = new char[length];
        for(int i = 0; i < reverseCaesar.length; i++) {
            int newChar = (interim[i] + (128 - key)) % 128;
            reverseCaesar[i] = (char) newChar;
        }
        char[][] D3 = populateCharArray(reverseCaesar, (key/10), 1000);
        char[][] D4 = transpose(D3);
        char[] decrypted = makeCharFromTranspose(D4, length);
        String decryptedString = new String(decrypted);
        return decryptedString;
    }

}