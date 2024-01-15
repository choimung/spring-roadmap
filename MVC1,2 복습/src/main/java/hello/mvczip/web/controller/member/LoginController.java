package hello.mvczip.web.controller.member;

import hello.mvczip.domain.login.LoginService;
import hello.mvczip.domain.member.Member;
import hello.mvczip.web.controller.member.form.LoginForm;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.net.http.HttpRequest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("loginForm", new LoginForm());
        return "login/loginForm";
    }

    @PostMapping("login")
    public String login(@ModelAttribute LoginForm form, BindingResult bindingResult, HttpServletRequest request,
                        @RequestParam(value = "redirectURL", required = false, defaultValue = "/") String redirectURL) {

        if(bindingResult.hasErrors()) {
            return "login/loginForm";
        }

        Member member = loginService.login(form.getLoginId(), form.getPassword());

        if(member == null) {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 일치하지 않습니다.");
            return "login/loginForm";
        }

        //성공 처리
        HttpSession session = request.getSession();
        session.setAttribute("loginMember", member);

        return "redirect:"+redirectURL;
    }

    @PostMapping("/logout")
    public String logOut(HttpServletRequest request) {

        HttpSession session = request.getSession(false);

        if(session != null) {
            session.invalidate();
        }

        return "redirect:/";

    }
}
