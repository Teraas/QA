package DesignPatterns.Structural.Facade;

import DesignPatterns.example.User;
import DesignPatterns.example.UserDAO;

/**
 * @author harish.kumar-mbp
 * This "hides system complexity" and user/client does not need to know internal implimentation
 * Also expose only required info for the client
 */
public class FasadeUser {
    UserDAO userDAO;

    public String getUserName(String id){
        User user = userDAO.getUserDetail(id);
        return user.getUserName();
    }

}
