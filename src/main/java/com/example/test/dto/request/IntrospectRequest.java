package com.example.test.dto.request;


import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@FieldDefaults(level =  AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class IntrospectRequest {

    String token;
}