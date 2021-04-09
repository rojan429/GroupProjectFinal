package GroupProject2.project.service;

import GroupProject2.project.DAO.BoardDAO;
import GroupProject2.project.Entity.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class BoardService {
    @Autowired
    private BoardDAO boardDAO;
    public Collection<Board> getBoards(){
        return boardDAO.getBoards();
    }
    public Board createBoard(Board board){
        return boardDAO.createBoard(board);
    }
    public void saveBoard(Board board){
        boardDAO.persistBoard(board);
    }
}
