import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bitte das zu validierende Passwort eingeben:");
        String password = scanner.next();
        int minLength = 4;
        String acceptedSpecialCharacters = "!?@()[]{}=+-$%&";
        boolean[] testsPassed = new boolean[5];
        String safetyLevel = "sicher";

        //Validate length
        testsPassed[0] = TestLength(password, minLength);

        //Validate if password contains digits
        testsPassed[1] = TestDigits(password);

        //Validate if password contains lowercase and capital letters
        testsPassed[2] = TestLetters(password);

        //Compare password with blacklist
        testsPassed[3] = TestBlacklist(password);

        //Validate if password contains a special character
        testsPassed[4] = TestSpecialCharacters(password, acceptedSpecialCharacters);

        for(boolean test : testsPassed) {
            if(!test) {
                System.out.println("\nDein Passwort hat nicht alle Tests bestanden. Soll ich ein sicheres für dich generieren?");
                String answer = "";
                while(!answer.equals("ja") && !answer.equals("nein")) {
                    answer = scanner.next();
                }
                if(answer.equals("ja")) {
                    password = generatePassword(acceptedSpecialCharacters);
                } else {
                    System.out.println("Ok, aber auf dein Risiko.");
                    safetyLevel = "unsicher";
                }
                break;
            }
        }
        System.out.println(password + " ist ein " + safetyLevel + "es Password");
    }

    public static boolean TestLength(String pwd, int length) {
        if(pwd.length() >= length) {
            return true;
        }
        System.out.println("Das Password muss mindestens " + length + " Zeichen lang sein !!!");
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
            System.out.println("Blacklist nicht gefunden, kein Test auf häufige Passwörter erfolgt !!! ");
            }
        return true;
    }

    public static boolean TestSpecialCharacters(String pwd, String specialCharacters) {
        for(int i = 0; i < specialCharacters.length(); i++) {
            if(pwd.contains(Character.toString(specialCharacters.charAt(i)))) {
                return true;
            }
        }
        System.out.println("Das Password muss mindestens 1 Sonderzeichen " + specialCharacters + " enthalten !!!");
        return false;
    }

    public static String generatePassword(String specialCharacters) {
        Random randomNumber = new Random();
        List<String> pwd = new ArrayList<String>();
        for(int i = 0; i < 3; i++) {
            int lowerCase = randomNumber.nextInt(25) + 97;
            pwd.add(Character.toString((char) (lowerCase)));
            int upperCase = randomNumber.nextInt(25) + 65;
            pwd.add(Character.toString((char) (upperCase)));
            int digit = randomNumber.nextInt(9);
            pwd.add(Character.toString((char) (48 + digit)));
            int specialCharacter = randomNumber.nextInt(14);
            pwd.add(Character.toString(specialCharacters.charAt(specialCharacter)));
        }
        Collections.shuffle(pwd);
        StringBuilder builder = new StringBuilder();
        for(String character : pwd) {
            builder.append(character);
        }
        return builder.toString();
    }
}
