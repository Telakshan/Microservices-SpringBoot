package com.thilakshan.MobileApplication.ui.controller;

import com.thilakshan.MobileApplication.ui.controller.model.request.UpdateUserDetailsRequestModel;
import com.thilakshan.MobileApplication.ui.controller.model.request.UserDetailsRequestModel;
import com.thilakshan.MobileApplication.ui.controller.model.response.UserRest;
import com.thilakshan.MobileApplication.userservice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public ResponseEntity<List<UserRest>> getUsers(@RequestParam(value="page", defaultValue = "1") int page,
                                   @RequestParam(value="limit", defaultValue = "50") int limit,
                                   @RequestParam(value="sort", defaultValue = "desc", required = false) String sort)
    {
        List<UserRest> users = userService.getUsers();
        if(users == null) return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping(consumes = {
            MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE
            }, produces = {
            MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<UserRest> createUser(@Valid @RequestBody UserDetailsRequestModel userDetails){
        UserRest returnValue = userService.createUser(userDetails);
        return new ResponseEntity<UserRest>(returnValue, HttpStatus.OK);
    }


    @GetMapping(path="/{userId}", produces = {
            MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<UserRest> getUser(@PathVariable String userId){
        UserRest returnValue = userService.getUser(userId);

        if(returnValue != null){
            return new ResponseEntity<>(returnValue, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping(path="/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable String userId){

        try {
            userService.deleteUser(userId);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping(path="/{userId}", consumes = {
            MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE
    }, produces = {
            MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<UserRest> updateUser(@PathVariable String userId, @Valid @RequestBody UpdateUserDetailsRequestModel userDetails){

        UserRest userRest = userService.updateUser(userId, userDetails);

        return new ResponseEntity<UserRest>(userRest, HttpStatus.OK);
    }
}
