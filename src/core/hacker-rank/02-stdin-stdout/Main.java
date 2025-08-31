package stdinstdout;

import java.util.*;

public class Main {

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int scanInt1 = scanner.nextInt();
        int scanInt2 = scanner.nextInt();
        int scanInt3 = scanner.nextInt();

        scanner.close();

        System.out.println(scanInt1);
        System.out.println(scanInt2);
        System.out.println(scanInt3);

    }           
}
