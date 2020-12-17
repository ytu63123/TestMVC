package com.lab.jpa.controller;

import com.lab.jpa.repository.CompanyDao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/club")
public class ClubController {

    @Autowired
    private CompanyDao dao;

    @RequestMapping(value = {"/"})
    public String read(Model model) {
        List club_list = dao.quaryAllClubs();
        String club_name1 = dao.quaryClubName(1);
        String club_name2 = dao.quaryClubName(2);
        Long club1 = dao.quaryClubCount(1);
        Long club2 = dao.quaryClubCount(2);
        model.addAttribute("club_list", club_list);
        model.addAttribute("club_name1", club_name1);
        model.addAttribute("club_name2", club_name2);
        model.addAttribute("club1", club1);
        model.addAttribute("club2", club2);
        return "club_page";
    }
}
