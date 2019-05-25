package com.nefu.workmanage.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@Entity
@ToString
public class Exam {
    public enum stat{
        UNDISTRIBUTED,DISTRIBUTED,FINISH
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String subject;
    private String site;
    private int needNum;
    private LocalDateTime beginTime;
    private LocalDateTime endTime;
    private stat status = stat.UNDISTRIBUTED;
    @Column(columnDefinition = "TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP",
            insertable = false
            , updatable = false)
    private LocalDateTime insertTime;

    public Exam(String subject){
        this.subject = subject;
    }
}
