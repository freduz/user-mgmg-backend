package com.decadis.task.usermgmt.payload;


import com.decadis.task.usermgmt.entity.Action;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class UserDto {
    private long id;

    @NotEmpty(message = "First name should be provided")
    private String firstName;
    @NotEmpty(message = "Last name should be provided")
    private String lastName;
    @NotEmpty(message = "Email name should be provided")
    @Email(message = "Please provide the valid email")
    private String email;
    @NotNull(message = "Atleast one action should be provided")
    private List<String> actions;
}
