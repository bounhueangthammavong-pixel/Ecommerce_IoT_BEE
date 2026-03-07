package com.example.test.dto.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.example.test.util.PageInfo;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {

    boolean success;
    Integer code;
    String message;
    T content;
    PageInfo pageDetails;
}
