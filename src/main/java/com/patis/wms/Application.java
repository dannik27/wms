package com.patis.wms;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.patis.wms.converter.gson.LocalDateAdapter;
import com.patis.wms.converter.gson.LocalDateTimeAdapter;
import com.patis.wms.converter.gson.SwaggerGsonAdapter;
import com.patis.wms.dto.create.RequestCreateDTO;
import java.util.HashMap;
import java.util.Map;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.json.Json;
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

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter< ? >> converters) {
        GsonHttpMessageConverter msgConverter = new GsonHttpMessageConverter();
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                .registerTypeAdapter(Json.class, new SwaggerGsonAdapter())
                .addSerializationExclusionStrategy(new ExclusionStrategy() {
                    @Override
                    public boolean shouldSkipField(FieldAttributes fieldAttributes) {
                        final Expose ignore = fieldAttributes.getAnnotation(Expose.class);
                        return ignore != null;
                    }

                    @Override
                    public boolean shouldSkipClass(Class<?> aClass) {
                        return false;
                    }
                })
                .create();
        msgConverter.setGson(gson);
        converters.add(msgConverter);

    }


//  @Bean
//  CommandLineRunner init( final RepositoryService repositoryService,
//      final RuntimeService runtimeService,
//      final TaskService taskService) {
//
//    return strings -> {
//
//      RequestCreateDTO requestCreateDTO = new RequestCreateDTO();
//      requestCreateDTO.setId_author(2);
//
//      Map<String, Object> variables = new HashMap<>();
//      variables.put("requestCreateDTO", requestCreateDTO);
//      runtimeService.startProcessInstanceByKey("GetFromCustomer", variables);
//    };
//  }

//  @Bean
//  InitializingBean usersAndGroupsInitializer(final IdentityService identityService) {
//
//    return new InitializingBean() {
//      public void afterPropertiesSet() throws Exception {
//
//        Group group = identityService.newGroup("user");
//        group.setName("users");
//        group.setType("security-role");
//        identityService.saveGroup(group);
//
//        User admin = identityService.newUser("admin");
//        admin.setPassword("admin");
//        identityService.saveUser(admin);
//
//      }
//    };
//  }


}
