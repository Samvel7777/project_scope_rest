package com.example.projectscoperest.dto;

import com.example.projectscoperest.model.Type;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private String name;
    private String surname;
    private String email;
    private String password;
    private Type type;
}
