package DesignPatterns.Structural.Proxy;

import DesignPatterns.example.IUserDAO;
import DesignPatterns.example.IUserDAOImpl;
import DesignPatterns.example.User;

/**
 * @author harish.kumar-mbp
 * @created 21/02/23
 */
public class IUserDAOProxy implements IUserDAO  {
    IUserDAOImpl userDAO;// has-a relation with IUserDAOImpl

    public IUserDAOProxy(IUserDAOImpl userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public void insert(String client, User user) throws Exception {
        if(client.equals("Google")){
            userDAO.insert(client, user);
        }
        else
            throw new Exception("Client not supported");

    }

    @Override
    public User getUserDetail(String client,String id) {
        return null;
    }

    @Override
    public void deleteUser(String client, String id) {

    }

    @Override
    public void updateUserName(String client, String id, String name) {

    }

    @Override
    public void updateUserPassword(String client, String id, String password) {

    }

    @Override
    public void updateUserEmail(String client, String id, String email) {

    }
}
