package com.example.Form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class PostForm {
    @NotEmpty(message = "내용 입력 必")
    private String content;
}
