package GroupProject2.project.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="Board")
public class Board {
    @Id
    private String boardID;
    private String location;
    private int space;
    private int boardRID;

    public int getBoardRID() {
        return boardRID;
    }

    public void setBoardRID(int boardRID) {
        this.boardRID = boardRID;
    }

    public String getBoardID() {
        return boardID;
    }

    public void setBoardID(String boardID) {
        this.boardID = boardID;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getSpace() {
        return space;
    }

    public void setSpace(int minus) {
        this.space -= minus;
    }
}
