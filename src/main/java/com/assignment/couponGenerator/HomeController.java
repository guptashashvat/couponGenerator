package com.assignment.couponGenerator;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/")
public class HomeController {

	@GetMapping
	public String home(){
		return "home";
	}
	@GetMapping("/loginPage")
	public String login(@RequestParam(value = "login", required = false) String login){
		log.debug("login");
		if(login.equalsIgnoreCase("admin"))
			return "redirect:admin";
		else if(login.equalsIgnoreCase("newUser"))
			return "redirect:user";
		else if(login.equalsIgnoreCase("existingUser"))
			return "redirect:/user/existingUser";
		else if(login.equalsIgnoreCase("addMerchant"))
			return "redirect:merchants/newMerchant";
		else if(login.equalsIgnoreCase("merchantList"))
			return "redirect:merchants";
		else
			return "/";
	}
	@GetMapping("/userLogin")
	public String userLogin(@RequestParam(value = "userId", required = false) String userId){
		return "redirect:user/"+userId;
	}
}
