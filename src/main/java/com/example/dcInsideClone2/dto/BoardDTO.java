package com.example.dcInsideClone2.dto;

import lombok.*;

import java.time.LocalDateTime;

// DTO(Data Transfer Object)
@Getter
@Setter
@ToString //필드 값 확인용
@NoArgsConstructor //기본 생성자
@AllArgsConstructor //모든 필드를 매개변수로 하는 생성자
public class BoardDTO {
    private Long id;
    private String boardWriter;
    private String boardPass;
    private String boardTitle;
    private String boardContents;
    private int boardHits; //조회수
    private LocalDateTime boardCreatedTime;
    private LocalDateTime boardUpdatedTime;


}