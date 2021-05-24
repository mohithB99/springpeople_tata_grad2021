package com.tata.webstoreapp.services;

import com.tata.webstoreapp.models.Address;
import com.tata.webstoreapp.models.UserAccount;
import com.tata.webstoreapp.repositories.UserRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    public UserAccount addUserAccount(UserAccount userAccount){
        if(userAccount.getAddressList()!=null){
            for(Address address : userAccount.getAddressList()){
                address.setUserAccount(userAccount);
            }
        }
        return  this.userRepository.save(userAccount);
    }

    public List<UserAccount> getAllUserAccounts(){
        return this.userRepository.findAll();
    }

    public UserAccount getUserByUserId(long id){
        return this.userRepository.findById(id).orElse(null);
    }

    public List<UserAccount> getUserByPhoneNo(long phoneNo){
        return this.userRepository.findByPhoneNo(phoneNo);
    }

    public boolean deleteUserById(long userId){
        this.userRepository.deleteById(userId);
        boolean status=true;
        if(getUserByUserId(userId)!=null)
            status=false;
        return status;
    }
}
