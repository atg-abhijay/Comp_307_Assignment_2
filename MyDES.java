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
}