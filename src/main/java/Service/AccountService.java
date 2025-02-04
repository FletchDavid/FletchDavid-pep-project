package Service;

import Model.Account;
import DAO.AccountDAO;

public class AccountService {
    private AccountDAO accountDAO;

    public AccountService(){
        accountDAO = new AccountDAO();
    }

    public AccountService(AccountDAO accountDAO){
        this.accountDAO = accountDAO;
    }

    /*
     * Requirement 1: New User Registrations
     */
    public Account addUser(Account user){
        if((user.getUsername() == null || user.getUsername() == "") || user.getPassword().length() < 4){
            return null;
        } else if(accountDAO.searchUsername(user)==null){
            return accountDAO.addUser(user);
        }
        return null;
    }

    /*
     * Requirement 2: User Login
     */
    public Account loginUser(Account user){
        return accountDAO.loginUser(user);
    }
}
