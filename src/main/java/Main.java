import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bitte das zu validiernede Passwort eingeben:");
        String password = scanner.next();
        int minLength = 8;

        //Validate length
        boolean correctLength = TestLength(password, minLength);

        //Validate if password contains digits
        boolean containsDigits = TestDigits(password);

        //Validate if password contains lowercase and capital letters
        boolean correctLetters = TestLetters(password);

        //Compare password with blacklist
        boolean testBlacklist = TestBlacklist(password);
    }

    public static boolean TestLength(String pwd, int length) {
        if(pwd.length() >= length) {
            return true;
        }
        System.out.println("Das Password muss mindestens 8 Zeichen lang sein !!!");
        return false;
    }

    public static boolean TestDigits(String pwd) {
        for(int i = 0; i < pwd.length(); i++) {
            if(pwd.charAt(i) > 47 && pwd.charAt(i) < 58) {
            //if(Character.isDigit(pwd.charAt(i)))  {
                return true;
            }
        }
        System.out.println("Das Password muss mindestens 1 Ziffer enthalten !!!");
        return false;
    }

    public static boolean TestLetters(String pwd) {
        boolean upperCase = false;
        boolean lowerCase = false;
        for(int i = 0; i < pwd.length(); i++) {
            if(Character.isUpperCase(pwd.charAt(i)))  {
                upperCase = true;
                break;
            }
        }
        for(int i = 0; i < pwd.length(); i++) {
            if(Character.isLowerCase(pwd.charAt(i)))  {
                lowerCase = true;
                break;
            }
        }
        if(upperCase && lowerCase) {
            return true;
        }
        System.out.println("Das Password muss sowohl Klein-, als auch Großbuchstaben enthalten !!!");
        return false;
    }

    public static boolean TestBlacklist(String pwd) {
        File blacklist = new File("Blacklist")
        return false;
    }

}
