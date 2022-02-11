package ru.barysheva.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Builder
@AllArgsConstructor
@Data
public class UserForm {

    private String firstName;
    private String lastName;
    private Integer age;
    private boolean isDeleted;

}
