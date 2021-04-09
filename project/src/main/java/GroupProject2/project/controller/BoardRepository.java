package GroupProject2.project.controller;

import GroupProject2.project.Entity.Board;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BoardRepository extends MongoRepository<Board,Integer> {
}