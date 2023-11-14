package password;

import java.util.Scanner;

public class PasswordControl {
    public static void main(String[] args) {
        char array[];
        boolean first = true ;
        int number=0;
        Scanner input = new Scanner(System.in);
       do {
            System.out.print("\nŞifre: ");
            String password = input.next();
            array = password.toCharArray();

            if (array.length < 8)
                System.out.println("8 karakterden küçük şifre girilemez.");

            for (int ascii : array) {
                if (!(48 <= ascii && ascii <= 57 || 65 <= ascii && ascii <= 90 || 97 <= ascii && ascii <= 122))
                    System.out.println("Sifre sadece sayı ve harflerden oluşabilir.");
                if (48 <= ascii && ascii <= 57)
                    number++;
                if (number < 2)
                    System.out.println("Şifre en az 2 sayıdan oluşmalıdır.");
            }


        }while (first);
    }

}
