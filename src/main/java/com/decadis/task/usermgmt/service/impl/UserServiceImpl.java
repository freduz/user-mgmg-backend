package com.decadis.task.usermgmt.service.impl;

import com.decadis.task.usermgmt.entity.Action;
import com.decadis.task.usermgmt.entity.User;
import com.decadis.task.usermgmt.exception.CommonException;
import com.decadis.task.usermgmt.exception.ResourceNotFoundException;
import com.decadis.task.usermgmt.payload.ActionDto;
import com.decadis.task.usermgmt.payload.UserDto;
import com.decadis.task.usermgmt.repository.ActionRepository;
import com.decadis.task.usermgmt.repository.UserRepository;
import com.decadis.task.usermgmt.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private ActionRepository actionRepository;

    public UserServiceImpl(UserRepository userRepository,ActionRepository actionRepository){
        this.userRepository = userRepository;
        this.actionRepository = actionRepository;
    }
    @Override
    public User create(UserDto userDto) {
        List<String >ALLOWED_ACTIONS = List.of("create-item","delete-item","view-item","move-item");
        Consumer<String> matchAction = action -> {
            if(!ALLOWED_ACTIONS.contains(action)){
                throw new CommonException(HttpStatus.BAD_REQUEST,"Invalid action provided");
            }
        };
        userDto.getActions().forEach(matchAction);
        if(userRepository.existsByEmail(userDto.getEmail())){
            throw new CommonException(HttpStatus.BAD_REQUEST,String.format("The provided email %s is already taken",userDto.getEmail()));
        }


        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());


        List<Action> actions = new ArrayList<>();

        for(String action:userDto.getActions()){
            actions.add(
                    Action.builder()
                            .action(action)
                            .user(user)
                            .build()
            );
        }
        user.setActions(actions);
        return this.userRepository.save(user);
    }



    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findOne(long userId) {
        return this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User","ID",String.format("%s",userId)));
    }

    @Override
    public void delete(long userId) {
       User user = this.findOne(userId);
       this.userRepository.delete(user);
    }

    @Override
    public User update(long userId, UserDto userDto) {
        User existingUser = this.findOne(userId);
        existingUser.setFirstName(userDto.getFirstName());
        existingUser.setLastName(userDto.getLastName());
        existingUser.setEmail(userDto.getEmail());

        // Remove actions not present in userDto
        List<Action> actionsToRemove = new ArrayList<>();
        for (Action action : existingUser.getActions()) {
            if (!userDto.getActions().contains(action.getAction())) {
                actionsToRemove.add(action);
            }
        }
        existingUser.getActions().removeAll(actionsToRemove);
        actionRepository.deleteAll(actionsToRemove);

        // Add new actions
        for (String action : userDto.getActions()) {
            boolean match = existingUser.getActions().stream()
                    .anyMatch(existingAction -> existingAction.getAction().equals(action));
            if (!match) {
                Action newAction = new Action();
                newAction.setUser(existingUser);
                newAction.setAction(action);
                existingUser.getActions().add(newAction);
            }
        }

        return userRepository.save(existingUser);
    }

    @Override
    public String makeAction(ActionDto actionDto) {
        User user = this.findOne(actionDto.getUserId());
        for(String action:actionDto.getActions()){
           boolean match = user.getActions().stream().anyMatch(existingAction -> existingAction.getAction().equals(action));
           if(!match) throw new CommonException(HttpStatus.UNAUTHORIZED,String.format("You are not allowed to do this %s action",action));
        }

        return "executed";
    }

}
