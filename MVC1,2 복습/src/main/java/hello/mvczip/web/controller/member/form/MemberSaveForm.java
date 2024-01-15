package hello.mvczip.web.controller.member.form;


import lombok.Data;

@Data
public class MemberSaveForm {

    private String loginId;

    private String password;

    private String name;

}
