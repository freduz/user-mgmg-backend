package com.decadis.task.usermgmt.service;

import com.decadis.task.usermgmt.entity.User;
import com.decadis.task.usermgmt.payload.ActionDto;
import com.decadis.task.usermgmt.payload.UserDto;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User create(UserDto userDto);
    List<User> findAll();
    User findOne(long userId);
    void delete(long userId);
    User update(long userId,UserDto userDto);
    String makeAction(ActionDto actionDto);
}
