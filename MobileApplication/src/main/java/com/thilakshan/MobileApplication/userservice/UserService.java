package com.thilakshan.MobileApplication.userservice;

import com.thilakshan.MobileApplication.ui.controller.model.request.UpdateUserDetailsRequestModel;
import com.thilakshan.MobileApplication.ui.controller.model.request.UserDetailsRequestModel;
import com.thilakshan.MobileApplication.ui.controller.model.response.UserRest;

import java.util.List;

public interface UserService {

     UserRest createUser(UserDetailsRequestModel userDetails);

     UserRest getUser(String userId);

     List<UserRest> getUsers();

     UserRest updateUser(String userId, UpdateUserDetailsRequestModel userDetails);

     void deleteUser(String userId);

}
