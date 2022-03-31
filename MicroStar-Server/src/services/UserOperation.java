package services;

import model.User;

public class UserOperation {
    //private static final Logger logger = LogManager.getLogger(UserOperation.class);


    public static boolean loginAuth(User user) {

        if (LoginAuth.authLoginUser(user))
        {
            return true;
        }
        return false;

    }
}