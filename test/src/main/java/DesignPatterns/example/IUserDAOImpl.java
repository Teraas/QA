package DesignPatterns.example;

/**
 * @author harish.kumar-mbp
 * @created 21/02/23
 */
public class IUserDAOImpl implements IUserDAO { // is-a relation with IUserDAO
    User user;


    @Override
    public void insert(String client, User user) {

    }

    @Override
    public User getUserDetail(String client, String id) {
        return user;
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
