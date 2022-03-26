package com.thilakshan.MobileApplication.userservice.impl;

import com.thilakshan.MobileApplication.shared.Utils;
import com.thilakshan.MobileApplication.ui.controller.model.request.UpdateUserDetailsRequestModel;
import com.thilakshan.MobileApplication.ui.controller.model.request.UserDetailsRequestModel;
import com.thilakshan.MobileApplication.ui.controller.model.response.UserRest;
import com.thilakshan.MobileApplication.userservice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImplementation implements UserService {

    Map<String, UserRest> users;
    Utils utils;

    public UserServiceImplementation(){}

    @Autowired
    public UserServiceImplementation(Utils utils){
        this.utils = utils;
    }

    @Override
    public UserRest createUser(UserDetailsRequestModel userDetails) {
        UserRest returnValue = new UserRest();
        returnValue.setEmail(userDetails.getEmail());
        returnValue.setFirstName(userDetails.getFirstName());
        returnValue.setLastName(userDetails.getLastName());

        if(users == null) users = new HashMap<>();
        String userId = utils.generateUserId();
        returnValue.setUserId(userId);
        users.put(userId, returnValue);

        return returnValue;
    }

    @Override
    public UserRest getUser(String userId){

        if(users.containsKey(userId)){
            return users.get(userId);
        }
        return null;
    }

    @Override
    public List<UserRest> getUsers(){

        return new ArrayList<>(users.values());

    }

    @Override
    public void deleteUser(String userId){

        users.remove(userId);

    }

    @Override
    public UserRest updateUser(String userId, UpdateUserDetailsRequestModel userDetails){

        UserRest storedUserDetails = users.get(userId);
        storedUserDetails.setFirstName(userDetails.getFirstName());
        storedUserDetails.setLastName(userDetails.getLastName());

        users.put(userId, storedUserDetails);

        return storedUserDetails;

    }
}
