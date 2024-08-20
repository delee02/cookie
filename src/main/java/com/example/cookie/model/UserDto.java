package com.example.cookie.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder

public class UserDto {

    private String id;

    private String name;

    private String password;

}
