package com.example.cinderella.web.dto;

import com.example.cinderella.domain.user.Gender;
import lombok.Getter;

@Getter
public class SignUpRequestDto {
    private String name;
    private Gender gender;
}
