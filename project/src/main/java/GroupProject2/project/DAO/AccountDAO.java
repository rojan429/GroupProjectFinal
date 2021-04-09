package GroupProject2.project.DAO;

import GroupProject2.project.Entity.Account;
import GroupProject2.project.controller.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
@Component
public class AccountDAO {
    @Autowired
    private AccountRepository repository;
    public Collection<Account> getAccounts(){
        return repository.findAll();
    }
    public Account createAccount(Account account){
        return repository.insert(account);
    }
    public void deleteAccount(Account account) { repository.delete(account);}
    public void persistAccount(Account account) {repository.save(account);}
}
