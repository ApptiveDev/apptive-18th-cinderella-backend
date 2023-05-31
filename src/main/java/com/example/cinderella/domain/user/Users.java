package com.example.cinderella.domain.user;

import com.example.cinderella.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Users extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Column
    private Long chatid;

    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private Gender gender;

    @Builder
    public Users(String name, String email, Role role, Gender gender) {
        this.name = name;
        this.email = email;
        this.role = role;
        this.gender = gender;
    }

    public Users update(String name) {
        this.name = name;
        return this;
    }

    public Users updateChatid(Long chatid) {
        this.chatid = chatid;
        return this;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }

    public void signUp(String name, Gender gender) {
        this.name = name;
        this.gender = gender;
    }
}
