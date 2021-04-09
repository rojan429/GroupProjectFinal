package GroupProject2.project.controller;

import GroupProject2.project.Entity.Account;
import GroupProject2.project.Entity.Advertisement;
import GroupProject2.project.Entity.Board;
import GroupProject2.project.service.AdvertisementService;
import GroupProject2.project.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

@Controller
@RequestMapping("/")
public class AdvertisementController {
    @Autowired
    private AdvertisementService advertisementService;
    @Autowired
    private BoardService boardService;
    private String holdBoardID;
    @RequestMapping("/advertisement")
    public String advertisement(@ModelAttribute("message")MessageForm messageForm, Model model){
        //find a ad by id, poster, boarder id
        String enter = messageForm.getText();
        Collection<Advertisement> allData = advertisementService.getAdvertisements();
        List<Advertisement> searchResult = new ArrayList<>();
        for(Iterator<Advertisement> it = allData.iterator(); it.hasNext();) {
            Advertisement a = it.next();
            if (a.getAdID().equals(enter)) {
                searchResult.add(a);
            }
        }

        model.addAttribute("advertisementDetails",searchResult);

        //list all ad
        List<Advertisement> all = new ArrayList<>();
        for(Iterator<Advertisement> itor = allData.iterator();itor.hasNext();){
            Advertisement ad = itor.next();
            all.add(ad);
        }
        model.addAttribute("allAdvertisementDetails", all);
        return "advertisement";
    }

//move to add ad page
    @RequestMapping("/addAdvertisement")
    public ModelAndView addAdvertisement(@ModelAttribute("advertisement") Advertisement advertisement, ModelMap model){
        //list all board
        Collection<Board> allData = boardService.getBoards();
        List<Board> all = new ArrayList<>();
        for(Iterator<Board> itor = allData.iterator();itor.hasNext();){
            Board b = itor.next();
            if(b.getSpace()!=0){
                all.add(b);
            }
        }
        model.addAttribute("allBoardOption", all);
        if(all.isEmpty()){
            return new ModelAndView("redirect:/advertisement", model);
        }
        
        return new ModelAndView("addAdvertisement", model);
    }
//save the information of new ad
    @RequestMapping("/saveAdvertisement")
    public ModelAndView saveAccount(@ModelAttribute("advertisement")Advertisement advertisement, ModelMap model){
        //get board >> get ID >> set advertisement board
        //set advertisement's board
        advertisement.setBoard(toTheBoard(advertisement.getBoard()).getBoardID());
        //set boardRID
        String boardID = advertisement.getBoard();
        Board b = findByBoardId(boardID);
        advertisement.setBoardRID(b.getBoardRID());
        //set location
        advertisement.setLocation(b.getLocation());
        //minus the board space
        useBoard(advertisement.getBoard());
        advertisementService.createAdvertisement(advertisement);
        return new ModelAndView("redirect:/advertisement", model);
    }
//save the change
    @RequestMapping("/saveExistAdvertisement")
    public ModelAndView saveExistAccount(@ModelAttribute("advertisement")Advertisement advertisement, ModelMap model){
        //get board >> get ID >> set advertisement board
        //set advertisement's board
        advertisement.setBoard(toTheBoard(advertisement.getBoard()).getBoardID());
        //minus the board space
        if(!advertisement.getBoard().equals(holdBoardID)){
            addBoard(holdBoardID);
            useBoard(advertisement.getBoard());
        }
        //set boardRID
        String boardID = advertisement.getBoard();
        Board b = findByBoardId(boardID);
        advertisement.setBoardRID(b.getBoardRID());
        //set location
        advertisement.setLocation(b.getLocation());
        advertisementService.saveAdvertisement(advertisement);

        return new ModelAndView("redirect:/advertisement", model);
    }
//move to edit ad page
    @RequestMapping("/editAdvertisement")
    public String editAdvertisement(@RequestParam String advertisementId, Model model){
        Advertisement a = findByAdvertisementId(advertisementId);
        holdBoardID = a.getBoard();
        //list all board
        Collection<Board> allData = boardService.getBoards();
        List<Board> all = new ArrayList<>();
        //add the using board to the first
        all.add(findByBoardId(holdBoardID));
        for(Iterator<Board> itor = allData.iterator();itor.hasNext();){
            Board b = itor.next();
            if(b.getBoardID().equals(holdBoardID)){
                continue;
            }
            all.add(b);
        }
        
        model.addAttribute("allBoardOption", all);
        
        model.addAttribute("advertisement",a);
        return "editAdvertisement";
    }

//delete ad
    @RequestMapping("/deleteAdvertisement")
    public ModelAndView deleteAdvertisement(@RequestParam String advertisementId, ModelMap model){
        Advertisement a = findByAdvertisementId(advertisementId);
        // add the board space
        addBoard(findUsedBoard(advertisementId).getBoardID());
        advertisementService.deleteAdvertisement(a);
        return new ModelAndView("redirect:/advertisement", model);
    }

    private Advertisement findByAdvertisementId(String advertisementId){
        Collection<Advertisement> allData = advertisementService.getAdvertisements();
        for(Iterator<Advertisement> iterator = allData.iterator();iterator.hasNext();){
            Advertisement a = iterator.next();
            if(a.getAdID().equals(advertisementId)){
                return a;
            }
        }
        return null;
    }
    //if add
    private Board useBoard(String boardID){
        Collection<Board> allBoardData = boardService.getBoards();
        for(Iterator<Board> iterator = allBoardData.iterator();iterator.hasNext();){
            Board b = iterator.next();
            if(b.getBoardID().equals(boardID) && b.getSpace()!=0){
                b.setSpace(1);
                boardService.saveBoard(b);
                return b;
            }
        }
        return null;
    }
    //if delete
    private Board addBoard(String boardID){
        Collection<Board> allBoardData = boardService.getBoards();
        for(Iterator<Board> iterator = allBoardData.iterator();iterator.hasNext();){
            Board b = iterator.next();
            if(b.getBoardID().equals(boardID)){
                b.setSpace(-1);
                boardService.saveBoard(b);
                return b;
            }
        }
        return null;
    }
    private Board findUsedBoard(String advertisementId){
        Collection<Advertisement> allData = advertisementService.getAdvertisements();
        for(Iterator<Advertisement> iterator = allData.iterator();iterator.hasNext();){
            Advertisement a = iterator.next();
            if(a.getAdID().equals(advertisementId)){
                String boardID = a.getBoard();
                return findByBoardId(boardID);
            }
        }
        return null;
    }
    private Board findByBoardId(String boardId){
        Collection<Board> allData = boardService.getBoards();
        for(Iterator<Board> iterator = allData.iterator();iterator.hasNext();){
            Board a = iterator.next();
            if(a.getBoardID().equals(boardId)){
                return a;
            }
        }
        return null;
    }
    // get the board in the certain position
    private Board toTheBoard(String position){
        Collection<Board> allData = boardService.getBoards();
        Iterator<Board> iterator = allData.iterator();
        Board a = new Board();
        for(int i =0; i<Integer.parseInt(position);i++){
            a = iterator.next();
        }
        return a;
    }
}
