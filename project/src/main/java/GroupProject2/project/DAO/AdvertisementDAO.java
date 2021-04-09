package GroupProject2.project.DAO;

import GroupProject2.project.Entity.Account;
import GroupProject2.project.Entity.Advertisement;
import GroupProject2.project.Entity.Board;
import GroupProject2.project.controller.AdvertisementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class AdvertisementDAO {
    @Autowired
    private AdvertisementRepository repository;
    public Collection <Advertisement> getAdvertisements(){
        return repository.findAll();
    }
    public Advertisement createAdvertisement(Advertisement advertisement){
        return repository.insert(advertisement);
    }
    public void persistAccount(Advertisement advertisement) {repository.save(advertisement);}
    public void deleteAdvertisement(Advertisement advertisement) { repository.delete(advertisement);}
}
