package com.msa.usermanagement.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@DynamicInsert // null 값 무시
@Entity // Spring boot가 실핼될 때 자동으로 mysql에 table이 생성된다.
public class User { //Table을 정의하기 위한 class

    @Id  // primary key로 설정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 프로젝트에서 연결된 DB의 넘버링 전략을 따라간다는 의미임
    private int id; // 시퀀스, auto_increment

    @Column(nullable = false, length = 30)
    private String username; // 아이디

    @Column(nullable = false, length = 100)
    private String password; // 패스워드

    @Enumerated(EnumType.STRING)
    private RoleType role; // enum을 쓰는게 좋다

    @ColumnDefault("0")
    private int count;

    @CreationTimestamp // 시간이 자동으로 입력
    private Timestamp createDate;



}

//ORM -> 객체를 DB의 테이블로 만들어주는 기술
