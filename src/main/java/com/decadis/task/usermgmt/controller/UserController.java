package com.decadis.task.usermgmt.controller;


import com.decadis.task.usermgmt.entity.User;
import com.decadis.task.usermgmt.payload.ActionDto;
import com.decadis.task.usermgmt.payload.UserDto;
import com.decadis.task.usermgmt.payload.UserResponseDto;
import com.decadis.task.usermgmt.service.UserService;
import com.decadis.task.usermgmt.utils.MapData;
import com.fasterxml.jackson.databind.util.BeanUtil;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private UserService userService;
    private MapData mapData;

    public UserController(UserService userService){
        this.userService = userService;
        this.mapData = new MapData();
    }
    @PostMapping
    public ResponseEntity<UserResponseDto> createUser(@Valid @RequestBody UserDto userDto){
        UserResponseDto createdUser =  this.mapData.mapToDto(this.userService.create(userDto));
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAllUsers(){
        List<UserResponseDto> users = this.mapData.mapToListDto(this.userService.findAll());
        return new ResponseEntity<>(users,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUser(@PathVariable("id") long userId){
       UserResponseDto user = this.mapData.mapToDto(this.userService.findOne(userId));
       return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") long userId){
      this.userService.delete(userId);
      return new ResponseEntity<>("User deleted",HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable("id") Long userId,@RequestBody UserDto userDto){
        UserResponseDto user = this.mapData.mapToDto(this.userService.update(userId,userDto));
        return new ResponseEntity<>(user,HttpStatus.OK);
    }


}
