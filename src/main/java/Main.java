import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bitte das zu validierende Passwort eingeben:");
        String password = scanner.next();
        int minLength = 4;

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
            if(Character.isDigit(pwd.charAt(i)))  {
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

    public static boolean TestBlacklist(String pwd) throws FileNotFoundException {
        try {
            File blacklist = new File("Blacklist");
            Scanner testFileReader = new Scanner(blacklist);
            while (testFileReader.hasNextLine()) {
                if (pwd.toLowerCase().equals(testFileReader.nextLine())) {
                    testFileReader.close();
                    System.out.println("Das Password ist zu einfach und steht auf der Blacklist !!!");
                    return false;
                }
            }
            testFileReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Blacklist not found !!!");
            e.printStackTrace();
        }
        return true;
    }

}
