package GroupProject2.project.controller;

import GroupProject2.project.Entity.Account;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AccountRepository extends MongoRepository<Account,Integer> {
}
