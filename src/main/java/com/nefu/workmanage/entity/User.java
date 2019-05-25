package com.nefu.workmanage.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class User {
    public enum roles{
        TEACHER,MANAGER,SUPER
    }
    public enum titles{
        LECTURER,ASSOCIATEPROFESSOR,PROFESSOR
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private titles title;
    private String intro;
    private String tel;
    @Column(unique = true,nullable = false)
    private String account;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private roles role = roles.TEACHER;
    private int executeTimes = 0;
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP",
            updatable = false,
            insertable = false)
    private LocalDateTime insertTime;

    public User(String account) {
        this.account = account;
    }
}
