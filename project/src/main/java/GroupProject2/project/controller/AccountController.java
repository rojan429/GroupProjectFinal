package GroupProject2.project.controller;

import GroupProject2.project.Entity.Account;
import GroupProject2.project.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

@Controller
@RequestMapping("/")
public class AccountController {
    @Autowired
    private AccountService accountService;
    @RequestMapping("/home")
    //identify message attribute
    public String getHomePage(@ModelAttribute("message")MessageForm messageForm){
        //home.html
        return "home";
    }
    @RequestMapping("/all")
    public Collection<Account>getAccounts(){return accountService.getAccounts();}
    @PostMapping
    public Account postBook(@RequestBody Account account){
        return accountService.createAccount(account);
    }

    @RequestMapping("/member")
    public String search(@ModelAttribute("message")MessageForm messageForm, Model model){
        //find a member by first name, last name, account id, title, email, phone number
        String enter = messageForm.getText();
        Collection<Account> allData = accountService.getAccounts();
        List<Account> searchResult = new ArrayList<>();
        for (Iterator<Account> it = allData.iterator(); it.hasNext(); ) {
            Account a = it.next();
            if (stringEquals(a.getAccountID(),enter)) {
                searchResult.add(a);
            }
        }

        model.addAttribute("accountDetails", searchResult);
        //list all account
        List<Account> all = new ArrayList<>();
        for(Iterator<Account> itor = allData.iterator();itor.hasNext();){
            Account acc = itor.next();
            all.add(acc);
        }
        model.addAttribute("allAccountDetails", all);

        return "member";
    }

    @RequestMapping("/addAccount")
    public String addMember(@ModelAttribute("account")Account account, Model model){

        return "addAccount";
    }

    @RequestMapping("/saveAccount")
    public ModelAndView saveAccount(@ModelAttribute("account")Account account, ModelMap model){
        accountService.createAccount(account);
        return new ModelAndView("redirect:/member", model);
    }

    @RequestMapping("/saveExistAccount")
    public ModelAndView saveExistAccount(@ModelAttribute("account")Account account, ModelMap model){
        accountService.saveAccount(account);
        return new ModelAndView("redirect:/member", model);
    }
    @RequestMapping("/edit")
    public String editAccount(@RequestParam String accountId, Model model){
        Account a = findByAccountId(accountId);
        model.addAttribute("account",a);
        return "editAccount";
    }


    @RequestMapping("/delete")
    public ModelAndView deleteAccount(@RequestParam String accountId, ModelMap model){
        Account a = findByAccountId(accountId);
        accountService.deleteAccount(a);
        return new ModelAndView("redirect:/member", model);
    }

    private boolean stringEquals(String str1, String str2){
        if (str1 == null || str2 == null){
            return false;
        }
        return str1.equals(str2);
    }

    private Account findByAccountId(String accountId){
        Collection<Account> allData = accountService.getAccounts();
        for(Iterator<Account> iterator = allData.iterator();iterator.hasNext();){
            Account a = iterator.next();
            if(a.getAccountID().equals(accountId)){
                return a;
            }
        }
        return null;
    }



}
