import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        UserRepo userRepo = new UserRepo();

        System.out.println("Welcome to the User Platform!");
        int choice;

        do {
            System.out.println("Please enter one of the following options!");
            System.out.println("1 - Create a user account.");
            System.out.println("2 - Check all users information!");
            System.out.println("3 - Exit!");

            Scanner scanner = new Scanner(System.in);
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    createUser(userRepo);
                    break;
                case 2:
                    userRepo.printUsers();
                    break;
                case 3:
                    break;
                default:
                    System.out.println("Choice must be a value between 1 and 3.");
            }

        }while (choice != 3);
    }

    private static void createUser(UserRepo userRepo) {
        System.out.println("Please enter the user information!");
        boolean error = false;

        do {
            if (error)
                System.out.println("Try again!");
            System.out.print("Name -> ");
            Scanner scanner = new Scanner(System.in);
            String name = scanner.next();

            System.out.print("Username -> ");
            String username = scanner.next();

            System.out.print("Password -> ");
            String password = scanner.next();

            System.out.print("Age -> ");
            String age = scanner.next();

            error = !checkFieldExists(name, "Name") ||
                    !checkFieldExists(username, "Username") ||
                    !checkFieldExists(password, "Password") ||
                    !checkFieldExists(age, "Age");

            if (!error)
                error = !checkFieldIsType(name, "Name") ||
                        !checkFieldIsType(username, "Username") ||
                        !checkFieldIsType(password, "Password") ||
                        !checkFieldIsType(age, "Age");
            if(!error) {
                int ageToInt = Integer.parseInt(age);
                User user = new User(name, username, password, ageToInt);
                userRepo.createUser(user);
            }
        } while(error);
    }

    private static boolean checkFieldExists(String field, String fieldName) {
        if(field == null)
            System.out.println(fieldName + " is a required field!");
        return field != null;
    }

    private static<T> boolean checkFieldIsType(T field, String name_field) {
        if (name_field.equals("Age")) {
            try {
                Integer.parseInt(String.valueOf(field));
                return true;
            } catch(Exception e) {
                System.out.println(name_field + " is not a valid value!");
                return false;
            }
        } else if (name_field.equals("Name")) {
            boolean allLetters = String.valueOf(field).chars().allMatch(Character::isLetter);

            if(!allLetters)
                System.out.println(name_field + " must be all letters!");

            return allLetters;
        } else {
            boolean containsLetters = String.valueOf(field).matches(".*[a-zA-Z]+.*");

            if(!containsLetters)
                System.out.println(name_field + " must have letters!");

            return containsLetters;
        }
    }
}
