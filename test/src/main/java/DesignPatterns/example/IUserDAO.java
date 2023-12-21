package DesignPatterns.example;

/**
 * @author harish.kumar-mbp
 * @created 21/02/23
 */
public interface IUserDAO {
    public void insert(String client, User user) throws Exception;
    public User getUserDetail(String client, String id);
    public void deleteUser(String client, String id);
    public void updateUserName(String client, String id, String name);
    public void updateUserPassword(String client, String id, String password);
    public void updateUserEmail(String client, String id, String email);
}
