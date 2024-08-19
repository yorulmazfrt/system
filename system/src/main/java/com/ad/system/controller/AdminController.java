package com.ad.system.controller;

import com.ad.system.entity.User;
import com.ad.system.service.AdminService;
import com.ad.system.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/admin")
public class AdminController {

    private final AdminService adminService;
    private final UserService userService;

    public AdminController(AdminService adminService, UserService userService) {
        this.adminService = adminService;
        this.userService = userService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> saveUser(@RequestBody User user) {
        if (userService.findByUsername(user.getUsername()).isPresent()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(adminService.saveUser(user), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        return new ResponseEntity<>(adminService.updateUser(user), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {
        this.adminService.deleteUserById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("getUser/{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(this.adminService.getUserById(id), HttpStatus.OK);
    }

    @GetMapping("/getUsers")
    public ResponseEntity<?> getUsers() {
        return ResponseEntity.ok(this.adminService.getUsers());
    }
}
