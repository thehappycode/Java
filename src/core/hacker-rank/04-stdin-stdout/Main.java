package stdinstdout;

import java.util.Scanner;

public class Main{


    public static void main(String[] args){
        
        Scanner scanner = new Scanner(System.in);
        try {
            
            int inputInt = scanner.nextInt();
            scanner.nextLine();
            double inputDouble = scanner.nextDouble();
            scanner.nextLine();
            String inputString = scanner.nextLine();

            System.out.println("String: " + inputString);
            System.out.println("Double: " + inputDouble);
            System.out.println("Int: " + inputInt);
        }
        catch (Exception ex) {
            // TODO: handle exception
            System.out.println("Error: " + ex.getMessage());
        }
        finally{
            scanner.close();
        }
    }
}
