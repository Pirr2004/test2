import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.regex.Pattern;

public class PhonebookApp {
    private static final Hashtable<String, String> phonebookDB = new Hashtable<>();
    private static final Pattern patternPhoneNumber = Pattern.compile("\\d\\d-\\d\\d-\\d\\d");
    private static final Pattern patternName = Pattern.compile("[a-zA-Z][a-zA-Z0-9]{0,14}");

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter command [add, find, del, list, exit]:");

        while (true) {
            String command = scanner.nextLine();
            String[] commandWords = command.split(" ");
            if (commandInterpreter(commandWords)) return;
        }
    }

    private static boolean commandInterpreter(String[] commandWords) {
        switch (commandWords[0]) {
            case "add":
                add(commandWords);
                break;
            case "find":
                find(commandWords);
                break;
            case "del":
                del(commandWords);
                break;
            case "list":
                list();
                break;
            case "exit":
                return true;
            default:
                System.out.println("Entered command wrong!");
                break;
        }
        return false;
    }

    private static void list() {
        Enumeration phoneNumbers = phonebookDB.keys();
        String record;
        while (phoneNumbers.hasMoreElements()) {
            record = (String) phoneNumbers.nextElement();
            System.out.println(record + " " + phonebookDB.get(record));
        }
    }

    private static void del(String[] commandWords) {
        if (patternPhoneNumber.matcher(commandWords[1]).matches()) {
            phonebookDB.remove(commandWords[1]);
            System.out.println("Phone number deleted");
        } else {
            System.out.println("Phone number is not valid.");
        }
    }

    private static void find(String[] commandWords) {
        if (patternPhoneNumber.matcher(commandWords[1]).matches()) {
            if (phonebookDB.get(commandWords[1]) == null) {
                System.out.println("Phone number not found.");
            } else {
                System.out.println(phonebookDB.get(commandWords[1]));
            }
        } else {
            System.out.println("Phone number is not valid.");
        }
    }

    private static void add(String[] commandWords) {
        if (patternPhoneNumber.matcher(commandWords[1]).matches()) {
            if (patternName.matcher(commandWords[2]).matches()) {
                phonebookDB.put(commandWords[1], commandWords[2]);
                System.out.println("Added phone number: " + commandWords[1] + " Name: " + commandWords[2]);
            } else {
                System.out.println("Name is not valid");
            }
        } else {
            System.out.println("Phone number is not valid.");
        }
    }
}
