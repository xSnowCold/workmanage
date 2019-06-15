package com.nefu.workmanage.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    public static final int UNDISTRIBUTED = 0;
    public static final int DISTRIBUTED = 1;
    public static final int FINISH = 2;
    /*public enum stat{
        UNDISTRIBUTED,DISTRIBUTED,FINISH
    }*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String subject;
    private String site;
    private int needNum;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime beginTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;
    private int status = UNDISTRIBUTED;
    @Column(columnDefinition = "TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP",
            insertable = false
            , updatable = false)
    private LocalDateTime insertTime;

    public Exam(String subject){
        this.subject = subject;
    }
}
