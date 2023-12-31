package hello.springmvc.basic.requestmapping;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mapping/users")
public class MappingClassController {

    @GetMapping()
    public String user(){
        return "get users";
    }

    @PostMapping()
    public String addUser() {
        return "post user";
    }

    @GetMapping("/{usersId}")
    public String findUser(@PathVariable("usersId") String usersId) {
        return "get userId = " + usersId;
    }

    @PatchMapping("/{usersId}")
    public String updateUser(@PathVariable("usersId") String usersId) {
        return "update userId = " + usersId;
    }

    @DeleteMapping("/{usersId}")
    public String deleteUser(@PathVariable("usersId") String usersId) {
        return "delete userId = " + usersId;
    }
}
