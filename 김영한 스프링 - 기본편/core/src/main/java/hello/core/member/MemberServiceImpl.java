package hello.core.member;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
// 프로그램의 제어권을 외부에서 제어한다.
// 자신의 로직을 실행하는 담당만 한다.
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    @Autowired // ac.getBean(MemberRepository memberRepository)
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    //테스트용
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
