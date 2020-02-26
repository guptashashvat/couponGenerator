package com.assignment.couponGenerator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.assignment.couponGenerator.beans.CouponData;
import com.assignment.couponGenerator.beans.MerchantData;
import com.assignment.couponGenerator.beans.UserData;
import com.assignment.couponGenerator.data.CouponRepository;
import com.assignment.couponGenerator.data.MerchantRepository;
import com.assignment.couponGenerator.data.UserRepository;

@Controller
@RequestMapping("/user")
public class UserController {
	private final UserRepository userRepo;
	private final CouponRepository couponRepo;
	private final MerchantRepository merchantRepo;
	private String userId;
	List<CouponData> assignedCoupon=new ArrayList<>();
	@Autowired
	public UserController(UserRepository userRepo, CouponRepository couponRepo, MerchantRepository merchantRepo){
		this.userRepo=userRepo;
		this.couponRepo=couponRepo;
		this.merchantRepo=merchantRepo;
	}
	@ModelAttribute(name="userData")
	public UserData userData(){
		return new UserData();
	}
	@GetMapping
	public String userForm(){
		return "createUser";
	}
	@GetMapping("/existingUser")
	public String existingUser(){
		return "existingUser";
	}
	@GetMapping("/userLogin")
	public String userLogin(@RequestParam(value = "userId", required = false) String userId){
		return "redirect:"+userId;
	}
	@GetMapping("/merchantPage")
	public String merchantPage(Model model){
		List<MerchantData> merchantList=new ArrayList<>();
		merchantRepo.findAll().forEach(j->merchantList.add(j));
		model.addAttribute("merchantList", merchantList);
		model.addAttribute("isSelect", assignedCoupon.size()>0);
		return "merchantPage";
	}
	@GetMapping("/redeemPage")
	public String redeemPage(){
		return "redeemCoupon";
	}
	@GetMapping("/redeemCoupon")
	public String login(@RequestParam(value = "couponId", required = false) String couponId){
		couponRepo.redeemCoupon(couponId);
		return "redirect:"+userId;
	}
	@PostMapping
	public String userCreate(@ModelAttribute UserData userData){
		userRepo.save(userData);
		return "redirect:"+userData.getUserId();
	}
	@GetMapping("/{param1}")
	public String userPage(@PathVariable(value = "param1") String param1, Model model){
		userId=param1;
		assignedCoupon=new ArrayList<>();
		couponRepo.findAssignedCoupons(param1).forEach(j->assignedCoupon.add(j));
		model.addAttribute("userData", userRepo.findOne(param1));
		model.addAttribute("assignedCoupon", assignedCoupon);
		return "userPage";
	}

}
