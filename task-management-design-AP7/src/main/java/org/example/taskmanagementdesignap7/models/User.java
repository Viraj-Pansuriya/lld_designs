package org.example.taskmanagementdesignap7.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@Setter
@NoArgsConstructor
public class User {

    private Long id;
    private String name;
    private String email;
    private String password;
}
