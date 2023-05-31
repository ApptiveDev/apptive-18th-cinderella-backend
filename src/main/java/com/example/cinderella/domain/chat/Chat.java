package com.example.cinderella.domain.chat;

import com.example.cinderella.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Chat extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String host;
    @Column(nullable = false)
    private String start;
    @Column(nullable = false)
    private String dest;
    @Column(nullable = false)
    private int time;
    @Column(nullable = false)
    private int num_of_people;
    @Column
    private String calcTime;

    @Builder
    public Chat(String host, String start, String dest, int time, int num_of_people) {
        this.host = host;
        this.start = start;
        this.dest = dest;
        this.time = time;
        this.num_of_people = num_of_people;
    }

    public void setStartAndTime(String changedStart ,String calcTime) {
        this.start = changedStart;
        this.calcTime = calcTime;
    }

    public void update() {
        this.num_of_people++;
    }

    /**
     * dest추가기능 : 업데이트 예정
     */
//    public void updateDest(String destination) {
//        this.dest.add(destination);
//    }

}
