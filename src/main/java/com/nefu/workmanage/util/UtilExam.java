package com.nefu.workmanage.util;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@ToString
public class UtilExam {
    public static final int UNDISTRIBUTED = 0;
    public static final int DISTRIBUTED = 1;
    public static final int FINISH = 2;

    private int id;
    private String subject;
    private String site;
    private int needNum;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime beginTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;
    private int status = UNDISTRIBUTED;
    private LocalDateTime insertTime;
    private StringBuffer teacher;
}