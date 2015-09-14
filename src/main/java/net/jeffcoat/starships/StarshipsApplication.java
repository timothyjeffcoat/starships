package net.jeffcoat.starships;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;

@SpringBootApplication
public class StarshipsApplication {

	@Bean
	public EmbeddedServletContainerCustomizer containerCustomizer() {
		// error pages downloaded from http://www.sporcic.org/2014/05/custom-error-pages-with-spring-boot/
	   return (container -> {
	        ErrorPage error401Page = new ErrorPage(HttpStatus.UNAUTHORIZED, "/401.html");
	        ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/404.html");
	        ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/500.html");
	 
	        container.addErrorPages(error401Page, error404Page, error500Page);
	   });
	}

    public static void main(String[] args) {
        SpringApplication.run(StarshipsApplication.class, args);
    }
}
