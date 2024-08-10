package com.mdghu.blog;
/*
* end points reference :
* http://localhost:8080/swagger-ui/index.html
*
* need to include
* commending function ,
* likes ,
* security (auth ,authra),
* confirm password via email,
* reset password via email
*
* */
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BlogApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogApplication.class, args);
	}

}
