package com.todaysplot.springboot.web.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor // final이 없는 필드는 생성자에 포함되지 않음
public class HelloResponseDto {

    private final String name;
    private final int amount;

}
