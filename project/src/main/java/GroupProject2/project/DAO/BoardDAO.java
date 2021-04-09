package GroupProject2.project.DAO;

import GroupProject2.project.Entity.Advertisement;
import GroupProject2.project.Entity.Board;
import GroupProject2.project.controller.AdvertisementRepository;
import GroupProject2.project.controller.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class BoardDAO {
    @Autowired
    private BoardRepository repository;
    public Collection<Board> getBoards(){
        return repository.findAll();
    }
    public Board createBoard(Board board){
        return repository.insert(board);
    }
    public void persistBoard(Board board) { repository.save(board);}
}
