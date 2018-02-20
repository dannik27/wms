package com.patis.wms.controller;

import com.patis.wms.entity.Person;
import com.patis.wms.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;

@Controller
@RequestMapping("demo")
public class DemoController {

    @Autowired
    PersonService personService;

    @GetMapping({" ","/"})
    @ResponseBody
    String hello(){
        return "Hello World";
    }

    @GetMapping("/init")
    @ResponseBody
    String init(){


        Person kekov = new Person("Семён",
                "Кеков",
                "Петрович",
                LocalDate.of(1995,6,12),
                "kekoff@mail.ru");
        Person valerich = new Person("Валерий",
                "Иванов",
                "Иванович",
                LocalDate.of(2009,7,7),
                "valeriy.i@mail.ru");

        personService.save(kekov);
        personService.save(valerich);

        return "Ok";
    }

}
