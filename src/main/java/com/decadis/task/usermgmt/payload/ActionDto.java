package com.decadis.task.usermgmt.payload;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Setter
@Getter
public class ActionDto {
    private Long userId;
    private List<String> actions;
}
