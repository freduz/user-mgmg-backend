package com.decadis.task.usermgmt.payload;

import com.decadis.task.usermgmt.entity.Action;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class UserResponseDto {
    private long id;
    private String firstName;
    private String lastName;
    private String email;

    private Set<Action> actions;
}
