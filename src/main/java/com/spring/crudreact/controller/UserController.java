package com.spring.crudreact.controller;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.spring.crudreact.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user/{id}")

    public ResponseEntity<User> getUserById(@PathVariable(("id")) String id) {

        System.out.println(userRepository.getById(Integer.valueOf(id)).toString());
        return new ResponseEntity<User>(
                userRepository.getById(Integer.valueOf(id)),HttpStatus.OK);
    }
    @GetMapping("/fetchAll")
    public List<User> fetchAllUser()
    {
       return userRepository.findAll();

    }
    @PostMapping("/addUser")
    public User createUser(@RequestBody User user)
    {
      return    userRepository.save(user);
    }


    @GetMapping("/editUser/{id}")

    public User getauser(@PathVariable("id")int id)
    {
        User u=userRepository.getById(id);
        if(u==null)
            return null;
        else
            return u;
    }
    @DeleteMapping("/" +
            "/{id}")
    public ResponseEntity<String> delete(@PathVariable("id")int id)
    {
            try{
                userRepository.delete(userRepository.getById(id));
                return new ResponseEntity<>(HttpStatus.OK);
            }
            catch (Exception e)
            {
                return  new ResponseEntity<String>("No data avaulable",HttpStatus.BAD_GATEWAY);
            }

    }

}
