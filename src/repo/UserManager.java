package repo;

import model.Admin;
import model.User;
import model.YoungAdultUser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserManager {
    private static Map<Long, User> users = new HashMap<>();
    private static Long usercount = 1L;

    private static User currentUser;

    // Intialise Static inside Static blocks
    static{
   // users.put(usercount, new Admin(usercount, "Mark Jensen", "markadmin@gmail.com", "admin@123"));
        users.put(usercount, new Admin(usercount, "Mark Jensen", "m@g", "mmmmmm"));
    usercount++;
    users.put(usercount, new YoungAdultUser(usercount, "Jessica", "jessica@gmail.com", "jess@queen"));
    }

    // Add users
    public static void addUser(String name, String email, String password){
            users.put(usercount, new YoungAdultUser(usercount, name, email, password));
            usercount++;
    }

    // Get all users
    public static List<User> getAllUsers(){
        return new ArrayList<>(users.values());
    }
}
