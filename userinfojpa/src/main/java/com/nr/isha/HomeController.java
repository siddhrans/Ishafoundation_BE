package com.nr.isha;

import java.util.Arrays;
import java.util.List;

import org.apache.log4j.spi.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.nr.isha.hibernate.repository.UserInterface;
import com.nr.isha.service.UserService;

 

@RestController
public class HomeController {

	@Autowired
	UserService userService;
	/*@Autowired
	UserInterface userInterface;*/
	 
	 @RequestMapping(value = "/hello1")  
	 	 public ResponseEntity<List<User>> getTopic(){
		 
		  List<User> listofuser=userService.getAlltopic();
		  if(listofuser==null){
			  return new ResponseEntity("Unable to get data from. User  not found.",
						HttpStatus.NOT_FOUND);
			}else{
				return new ResponseEntity<List<User>>(listofuser,HttpStatus.OK);
		  }
		  
	 }
	 
	/* 
	 @RequestMapping(value="/hello1/{id}")
	 public User getTopicid(@PathVariable("id")Long id){
		return  userService.gettopic(id);
	 }*/
	 
	 @RequestMapping(method=RequestMethod.POST, value="/hello1/")
	 public ResponseEntity<?>  addUser(@RequestBody User user, UriComponentsBuilder ucBuilder){
		 System.out.println("HomeController->addUse->"+user);
		 boolean verify=userService.isUserExist(user);
		 if(verify){
			 return new ResponseEntity("Unable to create. A User with name " + 
						user.getName() + " already exist.",HttpStatus.CONFLICT);
		 }else{
			 userService.add(user);
		 }
		 HttpHeaders headers = new HttpHeaders();
			headers.setLocation(ucBuilder.path("/hello1/{id}").buildAndExpand(user.getId()).toUri());
			return new ResponseEntity<String>(headers, HttpStatus.CREATED);
		 
		  
	 }
	 
	 
	 @RequestMapping(method=RequestMethod.GET, value="/hello1/{name}/{password}")
	 public ResponseEntity<?> isUserExist(@PathVariable("name")String name,@PathVariable("password")String password){
		 //System.out.println("HomeController->"+user);
		 
		/*User u= userService.isUserExistName(name,password);
		 
		System.out.println("user and passwod is existed");
		 
		*/
		  User u=userService.findUser(name, password);
		 
		if(u==null){
			 
		 return new ResponseEntity("Unable to upate. User with id " + name+password + " not found.",HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<User>(u,HttpStatus.OK); 
		
	 }
	 
	 
	/* @RequestMapping(method=RequestMethod.GET, value="/hello1/{user}")
	 public ResponseEntity<?> isUserExist(@RequestBody User user){
		 System.out.println("HomeController->"+user);
		boolean find= userService.isUserExist(user);
		if(!find){
			return new ResponseEntity("Unable to upate. User with id " + user.getId() + " not found.",HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<User>(user, HttpStatus.OK);
	 }
	 */
	 
	 
	  @RequestMapping(method=RequestMethod.PUT, value="/hello1/{id}")
	 public ResponseEntity<?> update(@RequestBody User user,@PathVariable("id")Long id){
		 System.out.println(user);
		 User usertemp=new User();
		 User currentuser=userService.getUserById(id);
		 if (currentuser == null) {
				//logger.error("Unable to update. User with id {} not found.", id);
				return new ResponseEntity("Unable to upate. User with id " + id + " not found.",
						HttpStatus.NOT_FOUND);
			}else{
		 
		 
		System.out.println();
		
		 userService.update(user);
			}
		 return new ResponseEntity<User>(usertemp, HttpStatus.OK);
	 } 
	 
	 @RequestMapping(method=RequestMethod.DELETE,value="/hello1/{id}")
	 public ResponseEntity<?>  delete(@PathVariable("id")Long id){
		 
	 
		  
		  User user = userService.getUserById(id);
			if (user == null) {
			//	logger.error("Unable to delete. User with id {} not found.", id);
				return new ResponseEntity("Unable to delete. User with id " + id + " not found.",
						HttpStatus.NOT_FOUND);
			}
			if(user!=null){
			userService.delete(id);
			}
			return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
		  
	 }
}
