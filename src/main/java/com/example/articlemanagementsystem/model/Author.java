package com.example.articlemanagementsystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Author {
    private int id;
    private String name;
    private String username;
    private String gender;
    private String bio;
    private String profileImg;
    private String coverImg;
    private String email;
    private String address;
}
