package GroupProject2.project.service;

import GroupProject2.project.DAO.AdvertisementDAO;
import GroupProject2.project.Entity.Account;
import GroupProject2.project.Entity.Advertisement;
import GroupProject2.project.Entity.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class AdvertisementService {
    @Autowired
    private AdvertisementDAO advertisementDAO;
    public Collection<Advertisement> getAdvertisements(){
        return advertisementDAO.getAdvertisements();
    }
    public void deleteAdvertisement(Advertisement advertisement){
        advertisementDAO.deleteAdvertisement(advertisement);
    }
    public Advertisement createAdvertisement(Advertisement advertisement){
        return advertisementDAO.createAdvertisement(advertisement);
    }
    public void saveAdvertisement(Advertisement advertisement){
        advertisementDAO.persistAccount(advertisement);
    }
}
