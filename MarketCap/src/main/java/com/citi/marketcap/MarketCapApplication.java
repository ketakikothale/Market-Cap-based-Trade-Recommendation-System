package com.citi.marketcap;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

//import com.citi.marketcap.dto.User;
//import com.citi.marketcap.service.UserService;
//import com.citi.marketcap.service.UserServiceImpl;

@SpringBootApplication
@ComponentScan("com.citi.marketcap")
public class MarketCapApplication {

	public static void main(String[] args) {
//		ConfigurableApplicationContext applicationContext=
		SpringApplication.run(MarketCapApplication.class, args);
		
		
//		UserService userService=(UserService) applicationContext.getBean("userService");
//		System.out.println(userService);
//		User user=new User(1,"ketaki","123 ");
//		System.out.println(userService.loggedIn(user));
		
	}

}
