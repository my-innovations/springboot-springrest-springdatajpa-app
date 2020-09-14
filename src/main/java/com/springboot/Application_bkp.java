package com.springboot;
/* package com.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//@Configuration
//@EnableAutoConfiguration
//@EnableCaching
//@ComponentScan
//@ComponentScan(basePackages = "com.springboot")
//@EnableJpaRepositories

//OR

//@SpringBootApplication
//@EnableAutoConfiguration
//@EnableCaching
//@ComponentScan(basePackages = "com.springboot")
//@EnableJpaRepositories
//@RestController
//@EnableEurekaClient

public class Application {
	
  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
    //SpringApplication.run(new Object[] { Application.class }, args);
  }
  
  //we can write here , but a good practice.
  
 @RequestMapping("/")
  String home1() {
      return "Hello World!";
  }
	
	@RequestMapping("/hello")
  @ResponseBody
  String home2() {
      return "Hello World!";
  }
}
*/