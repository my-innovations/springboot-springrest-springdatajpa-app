package com.springboot;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


//import com.springboot.service.UserService;
//------
//@Configuration
//@EnableAutoConfiguration
//@ComponentScan
//@ComponentScan({"","",""})
//@Import(MvcConfig.class)
//@Import(JpaConfig.class)
//@Import(HbConfig.class)
//@Import(SecurityConfig.class)
//@Import({SecurityConfig.class,HbConfig.class})
//@ImportResource("classpth:app-root-config.xml") //when we are using xml based configuration with springboot.
//@EntityScan(basePackages = { "com.springboot.entity" })
//@EnableJpaRepositories(basePackages = { "com.springboot.repository" })
//------
//OR
//@SpringBootApplication(scanBasePackages={"com.springboot"})// same as @Configuration @EnableAutoConfiguration @ComponentScan
@SpringBootApplication
@EntityScan(basePackages = { "com.springboot.entity" })
@EnableJpaRepositories(basePackages = { "com.springboot.repository" })
@EnableJpaAuditing
public class SpringBootSpringRestAppWithSpringDataJPA { // implements CommandLineRunner{
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootSpringRestAppWithSpringDataJPA.class, args);
		//OR
		//SpringApplication.run(new Object[] { SpringBootSpringRestApp.class }, args);
	}

	//commented temporarily for running junit test cases, but working
	/*
	 //@Autowired
	//UserService userService;
	@Override
	public void run(String... args) throws Exception {
	
		//######## pagination concept. ##########
		List<User> list = userService.findByLastname("nayak",new PageRequest(0,5,Direction.ASC,"name"));
		//List<User> list = userService.findByLastname4("nayak",new PageRequest(1,3,Direction.ASC,"name"));//OK
		//List<User> list = userService.findByLastname4("nayak",new PageRequest(2,2,Direction.ASC,"name"));//OK
		list.forEach(System.out::println);
		
		//######### Async Demo #############
		CompletableFuture<User> f= userService.findOneByName("punya");
		User user=f.get(20, TimeUnit.SECONDS);
		System.out.println(user);
	}*/
	
	//This below code has moved to ValidationConfig.java for entity class properties validation.
	/*@Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
    
    @Bean
    public LocalValidatorFactoryBean validator() {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource());
        return bean;
    }*/
}
