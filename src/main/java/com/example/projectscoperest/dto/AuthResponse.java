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
public class AuthResponse {

    private String token;
    private String name;
    private String surname;
    private Type type;
}
