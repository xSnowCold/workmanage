package com.nefu.workmanage.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class UserTask {

    //    未完成
    public static final int UNDONE = 0;
    //    已完成
    public static final int DONE = 1;
    //    逾期完成
    public static final int OVERTIME_DONE = 2;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    //    任务答复
    private String reply;

    @ManyToOne
    private User user;
    @ManyToOne
    private Task task;

    private LocalDateTime finishTime;

    //任务状态默认未完成
    private int status = UNDONE;

}
