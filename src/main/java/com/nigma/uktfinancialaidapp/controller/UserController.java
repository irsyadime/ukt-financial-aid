package com.nigma.uktfinancialaidapp.controller;

import com.nigma.uktfinancialaidapp.constant.AppPath;
import com.nigma.uktfinancialaidapp.model.entity.User;
import com.nigma.uktfinancialaidapp.model.request.UserRequest;
import com.nigma.uktfinancialaidapp.model.response.CommonResponse;
import com.nigma.uktfinancialaidapp.model.response.UserResponse;
import com.nigma.uktfinancialaidapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(AppPath.USER)
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user){
        UserResponse response = userService.create(user);
        CommonResponse<UserResponse> commonResponse = CommonResponse.<UserResponse>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message("user created")
                .data(response)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(commonResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable String id){
        UserResponse response = userService.getById(id);
        CommonResponse<UserResponse> commonResponse = CommonResponse.<UserResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Success get user")
                .data(response)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(commonResponse);
    }

    @GetMapping
    public ResponseEntity<?> getAll(){
        List<UserResponse> responses = userService.getAllUser();
        CommonResponse<List<UserResponse>> commonResponse = CommonResponse.<List<UserResponse>>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Get all user")
                .data(responses)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(commonResponse);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody UserRequest request){
        UserResponse response = userService.update(request);
        CommonResponse<UserResponse> commonResponse = CommonResponse.<UserResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .message("user updated")
                .data(response)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(commonResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id){
        userService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
