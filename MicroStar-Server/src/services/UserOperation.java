package services;

import model.Complaints;
import model.User;

public class UserOperation {


    public static boolean loginAuth(User user) {

        if (LoginAuth.authLoginUser(user))
        {
            return true;
        }
        return false;

    }


}