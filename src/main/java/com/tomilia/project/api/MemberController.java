package com.tomilia.project.api;

import com.tomilia.project.model.Member;
import com.tomilia.project.model.Prod_Loc;
import com.tomilia.project.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RequestMapping("api/v1")
@RestController
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping
    public void addMember(@Valid @NonNull @RequestBody Member member) {
        memberService.addMember(member);
    }

    @GetMapping
    public List<Member> getAllMember() {
        return memberService.getAllPeople();
    }

    @RequestMapping(value="/search", method=RequestMethod.POST)
    public List<Prod_Loc> getMemberByID(@RequestParam("p_code") String p_code) {
        return memberService.getMemberByID(p_code);
    }
    @RequestMapping(value="/transfer", method=RequestMethod.POST)
    public String transferInventory(@RequestParam("p_code") String p_code,
                                    @RequestParam("amount") int amount,
                                    @RequestParam("from_location") String from,
                                    @RequestParam("to_location") String to) {
        return memberService.transferInventory(p_code,amount,from,to);
       // return memberService.getMemberByID(p_code);
    }

    @DeleteMapping(path = "{p_code}")
    public void deleteMemberByID(@PathVariable("p_code") String p_code) {
        memberService.deleteMemberByID(p_code);
    }

    @PutMapping(path = "{p_code}")
    public void updateMemberByID(@PathVariable("p_code") String p_code, @Valid @NonNull @RequestBody Member memberUpdate) {
        System.out.println(p_code);
        memberService.updateMemberByID(p_code, memberUpdate);
    }
}
