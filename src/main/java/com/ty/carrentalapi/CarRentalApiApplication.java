package com.ty.carrentalapi;

import java.util.ArrayList;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
@EnableSwagger2
@SpringBootApplication
public class CarRentalApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarRentalApiApplication.class, args);
	}
	
	
	
	List<VendorExtension> vendorExtensions=new ArrayList<VendorExtension>();
	
	springfox.documentation.service.Contact contact=new springfox.documentation.service.Contact("chimgond","https://testyantra.com", "nagurechimgond@gmail.com");
		

		ApiInfo apiInfo=new ApiInfo("CAR RENTAL API", "This project is designed to aid the vehicle rental company to enable renting of vehicles through an online system",
				
	"Snapshoot-0.0.1",
				
	"https://testyantraglobal.com/",contact,"www.ty.com", "sdfghj",vendorExtensions);
		
		
	@Bean
		
	public Docket myDocket() {
			
			
	return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.basePackage("com.ty")).build().apiInfo(apiInfo);
		
	}



}
