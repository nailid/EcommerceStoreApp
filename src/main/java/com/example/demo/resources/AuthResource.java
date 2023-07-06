package com.example.demo.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.User;
import com.example.demo.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthResource {
	
	@Autowired
	private UserService userService;
	
	 @GetMapping("/index")
	    public String home(){
	        return "index";
	    }
	 
	//get registration form
		@GetMapping("/register")
	    public String showRegistrationForm(Model model){
	        // create model object to store form data
	        User user = new User();
	        model.addAttribute("user", user);
	        return "register";
	    }
		
		//register by form
		 @PostMapping("/register/save")
		    public String registration(@Valid @ModelAttribute("user") User user,
		                               BindingResult result,
		                               Model model){
		        User existingUser = userService.findUserByEmail(user.getEmail());

		        if(existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()){
		            result.rejectValue("email", null,
		                    "There is already an account registered with the same email");
		        }

		        if(result.hasErrors()){
		            model.addAttribute("user", user);
		            return "/register";
		        }

		        userService.addUser(user);
		        return "redirect:/register?success";
		    }
		 
		 @PostMapping("/users/register")
		 public ResponseEntity<User> addUser(@RequestBody User user) throws Exception{
			 if(user.getId() != null) {
				 throw new Exception("User already exists !!");
			 }
			 User addedUser = this.userService.addUser(user);
			 return new ResponseEntity<User>(addedUser, HttpStatus.CREATED);
		 }
		
		 @GetMapping("/login")
		    public String login(){
		        return "login";
		    }
}
