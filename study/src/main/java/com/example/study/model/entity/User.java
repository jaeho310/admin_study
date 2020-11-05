package com.example.study.model.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

//@Data // 게터세터
//테이블이름과 같다면 안써도 된다.
//@Table(name ="user")
@Entity
@NoArgsConstructor
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 전략옵션 설정
    private Long id;


//    @Column(name = "account")
    // 컬럼의 이름이 같으므로 적어주지 않아도 JPA가 매칭시켜준다.
    private String account;

    private String email;

    private String phoneNumber;

    // JPA가 알아서 매칭해주기에 created_at으로 만들어주지 않아도 된다.
    private LocalDateTime createdAt;

    private String createdBy;

    private LocalDateTime updatedAt;

    private String updatedBy;
}
