package com.tomilia.project.api;

import com.tomilia.project.model.Member;
import com.tomilia.project.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequestMapping("/")
@Controller
public class ACController {

    private final MemberService memberService;

    @Autowired
    public ACController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping
    public String mainPage(HttpServletRequest request, Model model)
    {
        model.addAttribute("message",memberService.getAllPeople());
       return "MainPage";
    }
}
