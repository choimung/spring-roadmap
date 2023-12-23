package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 프로그램의 흐름의 제어권을 AppConfig 클래스가 가져간다 -> ioc 컨테이너
// 객체를 생성하고 관리하면서 의존관계를 주입하는 컨테이너 -> ioc, di 컨테이너

@Configuration
public class AppConfig {

    //@Bean MemberService -> return new MemoryMemberRepository()
    //@Bean orderService -> return new MemoryMemberRepository()
    // 싱글톤이 깨지는것이 아닌가?

    @Bean
    public MemberService memberService(){
        System.out.println("AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public OrderService orderService(){
        System.out.println("AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }
    @Bean
    public MemberRepository memberRepository(){
        System.out.println("AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }
    @Bean
    public DiscountPolicy discountPolicy(){
        System.out.println("AppConfig.discountPolicy");
        return new RateDiscountPolicy();
    }

}
