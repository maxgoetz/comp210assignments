package assn07;


import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Main {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Master Password");
        String mPassword = scanner.nextLine();


        Map<String, String> passwordManager = new PasswordManager<>();
        boolean mPasswordValue = passwordManager.checkMasterPassword(mPassword);
        while (!mPasswordValue) {
            System.out.println("Enter Master Password");
            mPassword = scanner.nextLine();
            mPasswordValue = passwordManager.checkMasterPassword(mPassword);
            // your code below
        }
        if (mPasswordValue) {
            String input;
            while (true) {
                input = scanner.nextLine();
                if (input.equals("New password")) {
                    String website = scanner.nextLine();
                    String password = scanner.nextLine();
                    passwordManager.put(website, password);
                    System.out.println("New password added");
                } else if (input.equals("Get password")) {
                    String website = scanner.nextLine();
                    String output = passwordManager.get(website);
                    if (output != null) {
                        System.out.println(output);
                    } else {
                        System.out.println("Account does not exist");
                    }
                } else if (input.equals("Delete account")) {
                    String website = scanner.nextLine();
                    passwordManager.remove(website);
                } else if (input.equals("Check duplicate password")) {
                    String password = scanner.nextLine();
                    List<String> output = passwordManager.checkDuplicate(password);
                    if (output.size() != 0) {
                        System.out.println("Websites using that password:");
                        for (int i = 0; i < output.size(); i++) {
                            System.out.println(output.get(i));
                        }
                    } else {
                        System.out.println("No account uses that password");
                    }
                } else if (input.equals("Get accounts")) {
                    Set output = passwordManager.keySet();
                    System.out.println("Your accounts:");
                    Object[] print = output.toArray(new String[output.size()]);
                    for (int i = 0; i < output.size(); i++) {
                        System.out.println(print[i]);
                    }

                } else if (input.equals("Generate random password")) {
                    System.out.println("Enter length");
                    int length = Integer.parseInt(scanner.nextLine());
                    String output = passwordManager.generateRandomPassword(length);
                    System.out.println(output);
                } else if (input.equals("Exit")) {
                    System.out.println(passwordManager.size());
                    return;
                } else {
                    System.out.println("Command not found");
                }
            }
        }
    }
}
