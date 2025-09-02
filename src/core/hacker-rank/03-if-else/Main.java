package ifelse;

import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args){

        try {
            
            int numberInput = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            // TODO: Handler number input;
            String output = Handler(numberInput);
            System.out.println(output);
        
        } catch (Exception ex) {
            // TODO: handle exception
            System.out.println("Error: " + ex.getMessage());
        }
        finally{
            scanner.close();

        }
    }

    private static String Handler(int numberInput) {

        String weird = "Weird";
        String notWeird = "Not Weird";

        if(numberInput < 1){
            throw new IllegalArgumentException("Invalid number input");
        }

        if(numberInput % 2 != 0){
            return weird;
        }

        if(numberInput >= 2 && numberInput <= 5){
           return notWeird; 
        }

        if(numberInput >= 6 && numberInput <= 20){
            return weird;
        }

        return notWeird; 
    }
}
