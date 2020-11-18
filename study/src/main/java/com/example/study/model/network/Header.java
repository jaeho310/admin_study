package com.example.study.model.network;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Header<T> {

    // api 통신시간
    // jsonproperty라는 어노테이션을 사용하면 json으로 들어갈때 해당이름으로 들어간다.
    // resource/ application.properties에서 설정할 수 도 있다.
    @JsonProperty("transaction_time")
    private String transactionTime;

    // api 응답 코드
    private String resultCode;

    // api 부가 설명
    private String description;

    private T data;
}
