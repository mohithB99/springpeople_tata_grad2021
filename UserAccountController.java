package com.tata.webstoreapp.controllers;

import com.tata.webstoreapp.models.UserAccount;
import com.tata.webstoreapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserAccountController {
    @Autowired
    private UserService userService;
    @PostMapping("/UserAccounts")
    public ResponseEntity<?> addUserAccount(@RequestBody UserAccount userAccount){

       UserAccount data= this.userService.addUserAccount(userAccount);
       if(data!=null){
           return ResponseEntity.status(HttpStatus.ACCEPTED).body(data);
       }
       else
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User Account Not Added");
    }

    @GetMapping("/UserAccounts")
    public List<UserAccount> getAllUserAccounts(){
        return this.userService.getAllUserAccounts();
    }

    @GetMapping("/UserAccounts/{userId}")
    public ResponseEntity<?> getUserByUserId(@PathVariable("userId") long userId){

        UserAccount data= this.userService.getUserByUserId(userId);
        if(data!=null){
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(data);
        }
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User Account Not found");
    }

    @GetMapping("/UserAccounts/{phoneNo}")
    public ResponseEntity<?> getUserByPhoneNo(@PathVariable("phoneNo") long phoneNo){

        List<UserAccount> data= this.userService.getUserByPhoneNo(phoneNo);
        if(data!=null){
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(data);
        }
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User Accounts Not found");
    }


    @DeleteMapping("/UserAccounts/{userId}")
    public ResponseEntity<?> deleteUserByUserId(@PathVariable("userId") long userId){

        boolean status= this.userService.deleteUserById(userId);
        if(status){
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("UserId"+userId+"deleted");
        }
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User Account Not deleted");
    }
}
