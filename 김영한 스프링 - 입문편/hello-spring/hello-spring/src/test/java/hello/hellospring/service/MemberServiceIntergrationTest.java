package hello.hellospring.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional //롤백해줌
class MemberServiceIntergrationTest {

	@Autowired
	MemberService memberService;
	@Autowired
	MemberRepository memberRepository;

	@Test
	void join() {
		//given
		Member member = new Member();
		member.setName("hello");

		//when
		Long memberId = memberService.join(member);

		//then
		Member findMember = memberService.findOne(memberId).get();
		assertThat(member.getName()).isEqualTo(findMember.getName());
	}

	@Test
	void 중복_회원_예외(){
		//given
		Member member = new Member();
		member.setName("spring");

		Member member2 = new Member();
		member2.setName("spring");


		//when
		memberService.join(member);
//        try{
//            memberService.join(member2);
//            fail();
//        }catch (Exception e){
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        }

		assertThrows(IllegalArgumentException.class, () -> memberService.join(member2));

		//then
	}




}
