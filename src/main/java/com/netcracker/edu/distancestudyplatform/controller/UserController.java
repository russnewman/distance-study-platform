package com.netcracker.edu.distancestudyplatform.controller;

import com.netcracker.edu.distancestudyplatform.dto.ChangePasswordRequest;
import com.netcracker.edu.distancestudyplatform.dto.UserDto;
import com.netcracker.edu.distancestudyplatform.exception.DifferentPasswordsException;
import com.netcracker.edu.distancestudyplatform.security.SecurityUtils;
import com.netcracker.edu.distancestudyplatform.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{email}")
    public ResponseEntity<?> getUser(@PathVariable String email) {
        SecurityUtils.checkHasRights(email);
        UserDto dto = userService.getInfoByEmail(email);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PutMapping("/{email}/password")
    public ResponseEntity<?> changePassword(@PathVariable String email, @Validated @RequestBody ChangePasswordRequest request) {
        SecurityUtils.checkHasRights(email);
        try {
            userService.changePassword(email, request);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (DifferentPasswordsException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
