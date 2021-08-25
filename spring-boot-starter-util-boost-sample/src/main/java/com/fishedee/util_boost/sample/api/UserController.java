package com.fishedee.util_boost.sample.api;

import com.fishedee.util_boost.utils.ValidatorUtil;
import com.fishedee.web_boost.WebBoostException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {

    @PreAuthorize("hasAuthority('admin')")
    @GetMapping("/checkAuthority")
    public String checkAuthority(){
        return "123";
    }

    @GetMapping("/checkJsonDecimal")
    public BigDecimal checkJsonDecimal(){
        return new BigDecimal("123");
    }

    @GetMapping("/checkValidation")
    public void checkValidation(){
        UserDTO userDTO = new UserDTO();
        ValidatorUtil.check(userDTO);
    }

    @GetMapping("/checkUser")
    public User checkUser(User user){
        return user;
    }
}
