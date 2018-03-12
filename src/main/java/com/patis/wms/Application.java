package com.patis.wms;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.patis.wms.converter.gson.LocalDateAdapter;
import com.patis.wms.converter.gson.LocalDateTimeAdapter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
@EnableSwagger2
public class Application extends WebMvcConfigurerAdapter {

    public static void main(String[] args){
        SpringApplication.run(Application.class, args);
    }

//    @Override
//    public void configureMessageConverters(List<HttpMessageConverter< ? >> converters) {
//        GsonHttpMessageConverter msgConverter = new GsonHttpMessageConverter();
//        Gson gson = new GsonBuilder()
//                .setPrettyPrinting()
//                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
//                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
//                .create();
//        msgConverter.setGson(gson);
//        converters.add(msgConverter);
//
//    }


    @Bean
    public Docket swaggerSettings() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Warehouse managment system",
                "https://github.com/dannik27/wms",
                "1",
                "",
                "",
                "",
                ""
        );
    }



}
