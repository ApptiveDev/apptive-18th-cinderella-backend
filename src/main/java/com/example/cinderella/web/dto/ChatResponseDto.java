package com.example.cinderella.web.dto;

import com.example.cinderella.domain.chat.Chat;
import lombok.Getter;

@Getter
public class ChatResponseDto {

    private Long id;
    private String host;
    private String start;
    private String dest;
    private String time;
    private int num_of_people;


    public ChatResponseDto(Chat entity) {
        this.id = entity.getId();
        this.host = entity.getHost();
        this.start = entity.getStart();
        this.dest = entity.getDest();
        this.num_of_people = entity.getNum_of_people();
        this.time = entity.getCalcTime();
    }
}
