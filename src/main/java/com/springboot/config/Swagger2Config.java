package com.springboot.config;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicate;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2Config {
	
	//http://localhost:8081/v2/api-docs
	//http://localhost:8081/swagger-ui.html

	@Bean
	public Docket postsApi() {
		//return new Docket(DocumentationType.SWAGGER_2)
				//.groupName("public-api")
				//.apiInfo(apiInfo())
				//.select()
				//.paths(postPaths())
				//.build();
		
		//OR
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.any())
				//.apis(RequestHandlerSelectors.basePackage("com.springboot.restcontroller"))
				.paths(PathSelectors.any())
				//.paths(regex("/api/rest/*"))
				.build();
		
		//OR
		
		//return new Docket(DocumentationType.SWAGGER_2)
			//	.apiInfo(apiInfo())
				//.produces(new HashSet<String>(Arrays.asList("application/json","application/xml")))
				//.consumes(new HashSet<String>(Arrays.asList("application/json","application/xml")));
		
		//OR
		
		/*return new Docket(DocumentationType.SWAGGER_2)
	            .select()
	            .apis(RequestHandlerSelectors.basePackage("com.springboot.controller"))
	            .paths(regex("/v1.*"))
	            .build();*/
	}

	@SuppressWarnings({ "unused", "unchecked" })
	private Predicate<String> postPaths() {
		return or(
				regex("/api/posts.*"), 
				regex("/api/rest.*"),
				regex("/v1.*")
				);
	}

	@SuppressWarnings({ "unused", "deprecation" })
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Rest API")
				.description("Rest API reference for developers")
				.termsOfServiceUrl("http://javainuse.com")
				.contact("punyasmruti@gmail.com").license("Rest API License")
				.licenseUrl("punyasmruti@gmail.com")
				.version("1.0")
				.build();
	}
}