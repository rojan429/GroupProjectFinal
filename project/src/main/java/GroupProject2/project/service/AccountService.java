package GroupProject2.project.service;

import GroupProject2.project.DAO.AccountDAO;
import GroupProject2.project.Entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class AccountService {
    @Autowired
    private AccountDAO accountDAO;
    public Collection<Account> getAccounts(){
        return accountDAO.getAccounts();
    }
    public void deleteAccount(Account account){
        accountDAO.deleteAccount(account);
    }
    public Account createAccount (Account account){
        return accountDAO.createAccount(account);
    }
    public void saveAccount(Account account){
        accountDAO.persistAccount(account);
    }
}
