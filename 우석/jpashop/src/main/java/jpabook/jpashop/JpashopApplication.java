package jpabook.jpashop;

import com.fasterxml.jackson.datatype.hibernate5.jakarta.Hibernate5JakartaModule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JpashopApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpashopApplication.class, args);
	}

	// 스프링 빈에 Hibernate5JakartaModule 등록
	@Bean
	Hibernate5JakartaModule hibernate5JakartaModule(){
		Hibernate5JakartaModule hibernate5JakartaModule = new Hibernate5JakartaModule();
		// json 생성하는 시점에 강제 Lazy 로딩
//		hibernate5JakartaModule.configure(Hibernate5JakartaModule.Feature.FORCE_LAZY_LOADING, true);
		return hibernate5JakartaModule;
	}
}
