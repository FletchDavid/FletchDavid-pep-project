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
        //TODO
        return null;
    }

    /*
     * Requirement 2: User Login
     */
    public Account loginUser(Account user){
        //TODO
        return null;
    }
}
