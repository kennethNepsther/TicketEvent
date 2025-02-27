package com.ticketevent.controller;

import com.ticketevent.entity.UserEntity;
import com.ticketevent.entity.dto.request.UserCreateRequestDto;
import com.ticketevent.entity.dto.response.UserResponse;
import com.ticketevent.exceptions.exception.ObjectNotFoundException;
import com.ticketevent.service.IUserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static com.ticketevent.constant.Constants.*;
import static com.ticketevent.util.Helper.addIdToCurrentUrlPath;
import static com.ticketevent.util.Helper.stringToUUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@Tag(name = "Administrator")
@RequestMapping("/admin")
@CrossOrigin(origins = "*")
public class AdminController {

    final IUserService userService;


    @GetMapping
    public ResponseEntity<List<UserResponse>> findAll() {
        List<UserEntity> users = userService.getAllUsers();
        return ResponseEntity.ok(users.stream().map(UserResponse::new).toList());

    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserEntity> findById(@PathVariable String userId) {
        Optional<UserEntity> user = userService.getUserById(stringToUUID(userId));
        return ResponseEntity.ok((user).orElseThrow(() -> new ObjectNotFoundException(USER_NOT_FOUND_MESSAGE)));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UserEntity> findByEmail(@PathVariable String email) {
        Optional<UserEntity> user = userService.getUserByEmail(email);
        return ResponseEntity.ok((user).orElseThrow(() -> new ObjectNotFoundException(USER_NOT_FOUND_MESSAGE)));
    }

    @GetMapping("/phone/{phoneNumber}")
    public ResponseEntity<UserEntity> findByPhoneNumber(@PathVariable String phoneNumber) {
        Optional<UserEntity> user = userService.getUserByPhoneNumber(phoneNumber);
        return ResponseEntity.ok((user).orElseThrow(() -> new ObjectNotFoundException(USER_NOT_FOUND_MESSAGE)));
    }

    @PostMapping
    public ResponseEntity<UserEntity> createUserAdmin(@Valid @RequestBody UserCreateRequestDto request) {
        try {
            if (userService.getUserByEmail(request.email()).isPresent()){
                throw new DataIntegrityViolationException("O email ".concat(request.email()) +" já existe");
            }

            if ( userService.getUserByPhoneNumber(request.phoneNumber()).isPresent()) {
                throw new DataIntegrityViolationException("O número de telefone" .concat(request.phoneNumber()) +"já existe");
            }

            var user = new UserEntity();
            BeanUtils.copyProperties(request, user);
            userService.createUserAdmin(user);
            URI uri = addIdToCurrentUrlPath(user.getUserId().toString());
            return ResponseEntity.created(uri).body(user);

        } catch (DataIntegrityViolationException e) {
            log.error(ERROR_ON_CREATE_USER, e);
            throw new DataIntegrityViolationException(USER_ALREADY_EXIST_MESSAGE);
        }

    }


}
