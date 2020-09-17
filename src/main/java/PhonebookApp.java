import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.regex.Pattern;

public class PhonebookApp {
    public static void main(String[] args) {
        Hashtable phonebook = new Hashtable();
        Scanner scanner = new Scanner(System.in);
        Pattern patternPhoneNumber = Pattern.compile("\\d\\d-\\d\\d-\\d\\d");
        Pattern patternName = Pattern.compile("[a-zA-Z][a-zA-Z0-9]{0,14}");

        System.out.println("Enter command [add, find, del, list, exit]:");

        while (true) {
            String command = scanner.nextLine();
            String[] commandWords = command.split(" ");
            switch (commandWords[0]) {
                case "add":
                    if (patternPhoneNumber.matcher(commandWords[1]).matches()) {
                        if (patternName.matcher(commandWords[2]).matches()) {
                            phonebook.put(commandWords[1], commandWords[2]);
                            System.out.println("Added phone number: " + commandWords[1] + " Name: " + commandWords[2]);
                        } else {
                            System.out.println("Name is not valid");
                        }
                    } else {
                        System.out.println("Phone number is not valid.");
                    }
                    break;
                case "find":
                    if (patternPhoneNumber.matcher(commandWords[1]).matches()) {
                        if (phonebook.get(commandWords[1]) == null) {
                            System.out.println("Phone number not found.");
                        } else {
                            System.out.println(phonebook.get(commandWords[1]));
                        }
                    } else {
                        System.out.println("Phone number is not valid.");
                    }
                    break;
                case "del":
                    if (patternPhoneNumber.matcher(commandWords[1]).matches()) {
                        phonebook.remove(commandWords[1]);
                        System.out.println("Phone number deleted");
                    } else {
                        System.out.println("Phone number is not valid.");
                    }
                    break;
                case "list":
                    Enumeration phoneNumbers = phonebook.keys();
                    String record;
                    while (phoneNumbers.hasMoreElements()) {
                        record = (String) phoneNumbers.nextElement();
                        System.out.println(record + " " + phonebook.get(record));
                    }
                    break;
                case "exit":
                    return;
                default:
                    System.out.println("Entered command wrong!");
                    break;
            }
        }
    }
}
