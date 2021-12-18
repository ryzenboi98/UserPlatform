import java.util.ArrayList;

public class UserRepo {
    public static ArrayList<User> users = new ArrayList<>();

    public UserRepo() {}

    public void createUser(User user) {
        if (!usersListIsEmpty() && userAlreadyExists(user)) {
            System.out.println("Username is already in use!");
            return;
        } else if(!usersListIsEmpty()) {
            User lastUser = users.get(users.size() - 1);
            user.id = lastUser.id + 1;
        } else user.id = 1;

        users.add(user);
        System.out.println("User created with success!");
    }

    private boolean usersListIsEmpty() {
        return users.size() == 0;
    }

    private boolean userAlreadyExists(User user) {
        for (User usr: users)
            if(usr.username.equals(user.username))
                return true;
        return false;
    }

    public void printUsers() {
        for (User user: users) {
            System.out.println("Name -> " + user.getId());
            System.out.println("Name -> " + user.getName());
            System.out.println("Username -> " + user.getUsername());
            System.out.println("Age -> " + user.getAge());
            System.out.println();
        }
    }

}
