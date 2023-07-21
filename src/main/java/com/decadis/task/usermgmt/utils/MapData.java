package com.decadis.task.usermgmt.utils;

import com.decadis.task.usermgmt.entity.User;
import com.decadis.task.usermgmt.payload.UserResponseDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class MapData {

    private ModelMapper modelMapper;

    @Autowired
    public MapData(){
        this.modelMapper = new ModelMapper();
    }

    public UserResponseDto mapToDto(User user){
        return this.modelMapper.map(user,UserResponseDto.class);
    }

    public List<UserResponseDto> mapToListDto(List<User> users){
        return users.stream().map(user -> this.modelMapper.map(user,UserResponseDto.class)).collect(Collectors.toList());
    }
}
