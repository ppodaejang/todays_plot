package com.todaysplot.springboot.web;

import com.todaysplot.springboot.web.dto.HelloResponseDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController  // @ResponseBody 한번에 사용. json으로 반환하는 컨트롤러
public class HelloController {

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    @GetMapping("/hello/dto")
    public HelloResponseDto helloDto (@RequestParam("name") String name,
                                      @RequestParam("amount") int amount){
        return new HelloResponseDto(name, amount);
    }
}
