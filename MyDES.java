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
        System.out.println("\n\nYou entered: " + message);
        String encryptedString = encrypt(message, key);
        System.out.println("Encrypted string: " + encryptedString + "\n");
        String decryptedString = decrypt(encryptedString, key);
        reader.close();

        // char[][] array = new char[5][4];
        // for(int i = 0; i < array.length; i++) {
        //     for (int j = 0; j < array[0].length; j++) {
        //         array[i][j] = 'a';
        //         System.out.print(array[i][j]);
        //     }
        //     System.out.println();
        // }

        // char[] foo = {'a', 'b', 'c', 'd', 'e'};
        // char[] low = new char[foo.length];
        // for (int i = 0; i < foo.length; i++) {
        //     int newChar = (foo[i] + 20) % 128;
        //     low[i] = (char) newChar;
        //     System.out.print(low[i]);
        // }
    }

    public static char[][] populateCharArray(char[] stringAsChars, int index1, int index2) {
        char[][] stringBlock = new char[index1][index2]; //TODO change 20 back to 1000
        int charIndex = 0;
        System.out.println(stringBlock.length + ", " + stringBlock[0].length);
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
        // print("transpose");
        // printArray(transpose);
        // print("array length");
        // System.out.println(arrayLength);
        // System.out.println(transpose.length * transpose[0].length);
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
            // System.out.println(height);
            // System.out.println(width);
            if(height == transposeOfT.length) {
              height = 0;
            //   System.out.println(width);
              width++;
            }
          }
        }
        return transposeOfT;


        // char[][] transposeOfT = new char[T[0].length][T.length];
        // int height = 0;
        // int width = 0;
        // for(int i = 0; i < T.length; i++) {
        //     for(int j = 0; j < T[0].length; j++) {
        //         transposeOfT[height][width] = T[i][j];
        //         height++;
        //         if(height == transposeOfT.length) {
        //             height = 0;
        //             width++;
        //         }
        //     }
        // }
        // return transposeOfT;
    }

    public static void print(String foo) {
        System.out.println(foo);
    }

    public static String encrypt(String toEncrypt, int key) {
        // populate char[][] with user's input string
        // System.out.println(toEncrypt.length());
        char[] userStringAsChar = toEncrypt.toCharArray();
        // print("userStringAsChar:");
        // System.out.println(new String(userStringAsChar));
        char[][] T = populateCharArray(userStringAsChar, key/10, 20);
        // System.out.println("Populate array: ");
        // printArray(T);
        // take tranpose of char[][]
        char[][] transposeOfT = transpose(T);
        // System.out.println("transpose ");
        // printArray(transposeOfT)
        // printArray(transposeOfT);
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
        char[][] T2 = populateCharArray(B, (key/10)+1, 20);
        // taking transpose of T2
        char[][] transposeT2 = transpose(T2);
        /* making a char[] from the
            char[][] transposeT2 */
        char[] C = makeCharFromTranspose(transposeT2, B.length);
        String encryptedString = new String(C);
        return encryptedString;
    }

    public static String decrypt(String toDecrypt, int key) {
        int length = toDecrypt.length();
        // print("length");
        // System.out.println(length);

        char[] encryptedStringAsChar = toDecrypt.toCharArray();
        // print("encr str");
        // print(new String(encryptedStringAsChar));

        char[][] D = populateCharArray(encryptedStringAsChar, (key/10)+1, 20);
        // print("array D");
        // printArray(D);

        char[][] D2 = transpose(D);
        // print("array D2");
        // printArray(D2);

        char[] interim = makeCharFromTranspose(D2, length);
        // print("interim");
        // print(new String(interim));

        char[] reverseCaesar = new char[length];
        for(int i = 0; i < reverseCaesar.length; i++) {
            int newChar = Math.abs((interim[i] - key) % 128);
            System.out.println(newChar);
            reverseCaesar[i] = (char) newChar;
        }
        // print("reverse Caesar");
        // print(new String(reverseCaesar));

        char[][] D3 = populateCharArray(reverseCaesar, (key/10), 20);
        // print("D3");
        // printArray(D3);

        char[][] D4 = transpose(D3);
        // print("D4");
        // printArray(D4);

        char[] decrypted = makeCharFromTranspose(D4, length);
        print(new String(decrypted));
        return "done";
    }

    public static void printArray(char[][] array) {
        for(int i = 0; i < array.length; i++) {
            // System.out.println(i);
            for (int j = 0; j < array[0].length; j++) {
                int location = 10 * i + j;
                System.out.println(location + ". Char:" + array[i][j]);
                // System.out.print(j);
            }
        }
    }

}