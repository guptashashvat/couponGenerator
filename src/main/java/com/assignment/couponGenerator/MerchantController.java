package com.assignment.couponGenerator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.assignment.couponGenerator.beans.MerchantData;
import com.assignment.couponGenerator.data.MerchantRepository;

@Controller
@RequestMapping("/merchants")
public class MerchantController {
	private final MerchantRepository merchantRepo;
	@Autowired
	public MerchantController(MerchantRepository merchantRepo){
		this.merchantRepo=merchantRepo;
	}
	
	@ModelAttribute(name="merchantData")
	public MerchantData merchantData(){
		return new MerchantData();
	}
	@GetMapping
	public String merchantsList(Model model){
		List<MerchantData> merchantList=new ArrayList<>();
		merchantRepo.findAll().forEach(j->merchantList.add(j));
		model.addAttribute("merchantList", merchantList);
		model.addAttribute("isSelect", false);
		return "merchantPage";
	}	
	@GetMapping("/newMerchant")
	public String newMerchant(){
		return "addMerchant";
	}
	@PostMapping("/newMerchant")
	public String createMerchant(@ModelAttribute MerchantData merchantData){
		merchantRepo.save(merchantData);
		return "redirect:/merchants";
	}
}
