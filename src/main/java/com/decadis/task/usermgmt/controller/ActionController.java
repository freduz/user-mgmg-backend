package com.decadis.task.usermgmt.controller;


import com.decadis.task.usermgmt.payload.ActionDto;
import com.decadis.task.usermgmt.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/action")
public class ActionController {
    private UserService userService;

    public ActionController(UserService userService){
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<String> doAction(@RequestBody ActionDto actions){
        String response = this.userService.makeAction(actions);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
