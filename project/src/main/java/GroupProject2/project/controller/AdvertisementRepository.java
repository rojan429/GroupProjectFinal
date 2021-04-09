package GroupProject2.project.controller;

import GroupProject2.project.Entity.Advertisement;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AdvertisementRepository extends MongoRepository<Advertisement,Integer> {
}
