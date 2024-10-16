package com.todaysplot.springboot.web;

import com.todaysplot.springboot.config.auth.SecurityConfig;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = HelloController.class,
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,
                        classes = SecurityConfig.class)
        })
class HelloControllerTest {

    @Autowired
    private MockMvc mvc;

    @WithMockUser(roles = "USER")
    @Test
    public void appTest1() throws Exception { //진입 잘 되는지 확인

        String hello = "hello";

        mvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string(hello));
    }

    @WithMockUser(roles = "USER")
    @Test
    public void appTest2() throws Exception { // dto 생성 확인
        String name = "hello";
        int amount = 1000;

        mvc.perform(
                        get("/hello/dto")
                                // 요청 파라미터 설정 - String만 가능 -> 문자열로 변경
                                .param("name", name)
                                .param("amount", String.valueOf(amount))
                )
                .andExpect(status().isOk())
                //jsonPath : json 응답값을 필드별로 검증
                // $를 기준으로 필드명 명시
                .andExpect(jsonPath("$.name", Matchers.is(name)))
                .andExpect(jsonPath("$.amount", Matchers.is(amount)));

    }

}