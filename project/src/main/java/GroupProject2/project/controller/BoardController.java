package GroupProject2.project.controller;

import GroupProject2.project.Entity.Account;
import GroupProject2.project.Entity.Board;
import GroupProject2.project.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

@Controller
@RequestMapping("/")
public class BoardController {
    @Autowired
    private BoardService boardService;

    @RequestMapping("/board")
    public String board(@ModelAttribute("message")MessageForm messageForm, Model model){
        Collection<Board> allData = boardService.getBoards();
        List<Board> all = new ArrayList<>();
        for(Iterator<Board> itor = allData.iterator(); itor.hasNext();){
            Board acc = itor.next();
            all.add(acc);
        }
        model.addAttribute("allBoardDetails", all);
        return "board";
    }

}
