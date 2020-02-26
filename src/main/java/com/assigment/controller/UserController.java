package com.assigment.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.assigment.exception.ResourceNotFoundException;
import com.assigment.model.User;
import com.assigment.payload.ApiResponse;
import com.assigment.repository.UserRepository;
import com.assigment.security.CurrentUser;
import com.assigment.security.UserPrincipal;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;
  
      
    @GetMapping
    public Object getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
    	return userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User Not found", "", userPrincipal.getEmail()));
    }
    
    @PostMapping("/email")
    ResponseEntity<Object> emailExist(@RequestParam("email") String email){
    		
    	if (!userRepository.existsByEmail(email)) {
			return new ResponseEntity<Object>(true, HttpStatus.OK);
		} else {
			return new ResponseEntity<Object>(new ApiResponse(false,
					"email already registered"), HttpStatus.BAD_REQUEST);
		}
	}
        
    @GetMapping("/{userId}")
    public ResponseEntity<Object> findUserDetails(@PathVariable("userId") Long userId){
    	User user = userRepository.findById(userId).orElse(null);
    	if(user!=null){
    		return new ResponseEntity<Object>(user,HttpStatus.OK);
    	}
    	return new ResponseEntity<Object>("User details not found",HttpStatus.BAD_REQUEST);
    }
    
  
    @PutMapping("/subtitle")
    public ResponseEntity<Object> updateSubtitle(@CurrentUser UserPrincipal userPrincipal, @Valid @NotBlank @RequestBody String subtitle){
    	User user = userRepository.getOne(userPrincipal.getId());
    	if(user!=null){
    		user.setSubtitle(subtitle);
    		user = userRepository.save(user);
    		return new ResponseEntity<Object>(user,HttpStatus.OK);
    	}
    	return new ResponseEntity<Object>("Opps! something goes wrong",HttpStatus.BAD_REQUEST);
    }
    
    @PutMapping("/about")
    public ResponseEntity<Object> updateAbout(@CurrentUser UserPrincipal userPrincipal, @Valid @NotBlank @RequestBody String about){
    	User user = userRepository.getOne(userPrincipal.getId());
    	if(user!=null){
    		user.setAbout(about);
    		user = userRepository.save(user);
    		return new ResponseEntity<Object>(user,HttpStatus.OK);
    	}
    	return new ResponseEntity<Object>("Opps! something goes wrong",HttpStatus.BAD_REQUEST);
    }
    
  }
