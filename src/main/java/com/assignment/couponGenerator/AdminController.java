package com.assignment.couponGenerator;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.assignment.couponGenerator.beans.CouponData;
import com.assignment.couponGenerator.beans.UserData;
import com.assignment.couponGenerator.data.CouponRepository;
import com.assignment.couponGenerator.data.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/admin")
public class AdminController {
	
	private final UserRepository userRepo;
	private final CouponRepository couponRepo;
	boolean utilityFlag=true;
	@Autowired
	public AdminController(UserRepository userRepo, CouponRepository couponRepo){
		this.userRepo=userRepo;
		this.couponRepo=couponRepo;
	}
	@GetMapping("/generateCoupon")
	public String startUtil(){
		Timer timer = new Timer();
		timer.schedule(new SayHello(couponRepo), 2000, 60*1000);
		utilityFlag=false;
		return "redirect:/admin";
	}
	@GetMapping
	public String showCouponForm(Model model){
		List<UserData> ud=new ArrayList<>();
		List<CouponData> cd=new ArrayList<>();
		List<CouponData> activeCoupon=new ArrayList<>();
		List<Map<String, String>> couponMapping=new ArrayList<>();
		log.debug("Inside showCouponForm");
		couponRepo.findAll().forEach(j->cd.add(j));
		couponRepo.findActive().forEach(k->activeCoupon.add(k));
		couponRepo.findCouponMapping().forEach(x->couponMapping.add(x));
		userRepo.findAll().forEach(i->ud.add(i));
		model.addAttribute("couponData", cd);
		model.addAttribute("userData", ud);
		model.addAttribute("activeCoupon", activeCoupon);
		model.addAttribute("couponMapping", couponMapping);
		model.addAttribute("utilityFlag", utilityFlag);
		return "admin";
	}
	@PostMapping("/assign")
	public  ResponseEntity<?> login(@RequestBody String data){
		JacksonJsonParser parser = new JacksonJsonParser();
		Map<String, Object> resultMap=parser.parseMap(data);
		log.debug("data:: "+data);
		log.debug("couponId:: "+resultMap.get("couponId").toString());
		log.debug("userId:: "+resultMap.get("userId").toString());
		int i=couponRepo.assignCoupon(resultMap.get("userId").toString(), resultMap.get("couponId").toString());
		return ResponseEntity.ok(i);
	}
}
@Slf4j
class SayHello extends TimerTask {
	private final CouponRepository couponRepo;
	SayHello(CouponRepository couponRepo){
		this.couponRepo=couponRepo;
	}
	public static int getRandom(int from, int to) {
	    if (from < to)
	        return from + new Random().nextInt(Math.abs(to - from));
	    return from - new Random().nextInt(Math.abs(to - from));
	}
    public void run() {
    	log.debug("Generating coupon:: ");
    	int amount=getRandom(100, 500);
    	int rand = getRandom(10000, 99999);
    	log.debug("amount:: "+amount+" rand::"+rand);
    	Date date=new Date();
 	    Calendar cal = Calendar.getInstance();
        cal.setTime(date);
    	String couponCode=cal.getTimeInMillis()+"-"+rand;
    	int month=cal.get(Calendar.MONTH)+1;
    	int endMonth;
    	if(month==12)
    		endMonth=1;
    	else
    		endMonth=month+1;
    	String startDate=cal.get(Calendar.YEAR)+"-"+month+"-"+cal.get(Calendar.DATE);
    	String endDate=cal.get(Calendar.YEAR)+"-"+endMonth+"-"+cal.get(Calendar.DATE);
    	CouponData data=new CouponData(couponCode, startDate, endDate, "ACTIVE", amount);
    	log.debug("data:: "+data.toString());
    	couponRepo.save(data);
    }
}