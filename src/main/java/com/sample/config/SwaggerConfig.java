package com.sample.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;

import io.swagger.models.Contact;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	
	private static final String API_NAME = "Study API";
    private static final String API_VERSION = "0.0.1";
    private static final String API_DESCRIPTION = "Study API 명세서";
    
    private String version;
    private String title;
    
    @Bean
    public Docket apiV1() {
        version = "V1";
        title = "Board API " + version;

        /*
        List<Parameter> globalParamters = new ArrayList<>();
        globalParamters.add( new ParameterBuilder() //ParameterBuilder - 전역 파라미터 설정 
    	        .name("webType").description("store type  Ko, Jp, En, Cn")
    	        .defaultValue("Ko")
    	        .modelRef(new ModelRef("string"))
    	        .parameterType("path")
    	        .required(true)
    	        .build());
        */
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .groupName(version)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.sample.web.controller"))
                .paths(PathSelectors.ant("/v1/api/**"))
                .build()
                .apiInfo(apiInfo())
                //.apiInfo(apiInfo(title, version))
                .globalResponseMessage(RequestMethod.GET, responseMessages());

    }

    @Bean
    public Docket apiV2() {
        version = "V2";
        title = "Board API " + version;

        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .groupName(version)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.sample.web.controller"))
                .paths(PathSelectors.ant("/v2/api/**"))
                .build()
                .apiInfo(apiInfo());
        		//.apiInfo(apiInfo(title, version));

    }
    
    @Bean
    public Docket apiV3() {
        version = "V3";
        title = "Order API " + version;

        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .groupName(version)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.sample.web.controller"))
                .paths(PathSelectors.ant("/v3/api/**"))
                .build()
                .apiInfo(apiInfo());
        		//.apiInfo(apiInfo(title, version));

    }
    
    /**
     * 해당 api에 대한 정보 
     * @return
     */
    public ApiInfo apiInfo() {
    	
		return new ApiInfoBuilder()
			.title(API_NAME)
			.version(API_VERSION)
			.description(API_DESCRIPTION)
			.build();
    }

//    private ApiInfo apiInfo(String title, String version) {
//        return new ApiInfo(
//                title,
//                "Swagger Example API Docs",
//                version,
//                "www.example.com",
//                new Contact("Contact Me", "www.example.com", "test@example.com"),
//                "Licenses",
//
//                "www.example.com",
//
    
//                new ArrayList<>());
//    }
    
    private List<ResponseMessage> responseMessages() {
        List<ResponseMessage> responseMessages = new ArrayList<>();
        responseMessages.add(new ResponseMessageBuilder()
                .code(200)
                .message("OK!!!!! ~~")
                .build());
        responseMessages.add(new ResponseMessageBuilder()
                .code(404)
                .message("Not Found ~~")
                .build());
        responseMessages.add(new ResponseMessageBuilder()
                .code(500)
                .message("Internal Server Error ~~")
                .build());
        
        return responseMessages;
    }
}

