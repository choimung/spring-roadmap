package hello.mvczip.web.controller.member;

import hello.mvczip.domain.member.Member;
import hello.mvczip.domain.member.MemberRepository;
import hello.mvczip.web.controller.member.form.MemberSaveForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/members")
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberRepository memberRepository;

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("member", new Member());
        return "member/addMemberForm";
    }

    @PostMapping("/add")
    public String addMember(@ModelAttribute MemberSaveForm form) {
        log.info("id = {}, password = {}, name = {}", form.getLoginId(), form.getPassword(), form.getName());

        Member member = new Member(form.getLoginId(), form.getPassword(), form.getName());
        memberRepository.save(member);

        return "redirect:/";
    }

}
