package Service;

import DAO.AccountDAO;
import Model.Account;

public class AccountService {
    private AccountDAO accountDAO;

    public AccountService() {
        accountDAO = new AccountDAO();
    }

    public AccountService(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    public Account addAccount(Account account) {
        if (account.getUsername() == "" || account.getPassword().length() < 4) {
            return null;
        }
        return accountDAO.insertAccount(account);
    }

    public Account validateAccount(Account account) {
        Account accountCheck = accountDAO.getAccountByUserPass(account.getUsername(), account.getPassword());
        if (accountCheck == null) {
            return null;
        } else {
            return accountCheck;
        }
    }

    /*
     * may need editing
     */
    public Account findAccountById(int id) {
        Account account = accountDAO.getAccountById(id);
        if (account == null) {
            return null;
        } else {
            return account;
        }
    }
}