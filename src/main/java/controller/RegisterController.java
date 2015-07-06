package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import model.exception.AlreadyExistingUserException;
import model.service.UserRegisterService;
import model.spring.RegisterRequest;

@Controller
public class RegisterController {
	private UserRegisterService userRegisterService;
	private boolean isException = false;

	public void setUserRegisterService(
			UserRegisterService userRegisterService) {
		this.userRegisterService = userRegisterService;
	}

	/////////// customer
	@RequestMapping("/register/customerSignUpStep1")
	public String customerHandleStep1() {
		return "register/customerSignUpStep1";
	}

	@RequestMapping(value = "/register/customerSignUpStep2", method = RequestMethod.POST)
	public String customerHandleStep2(
			@RequestParam(value = "agree", defaultValue = "false") Boolean agree,
			Model model) {
		if (!agree) {
			return "register/customerSignUpStep1";
		}
		model.addAttribute("registerRequest", new RegisterRequest());
		return "register/customerSignUpStep2";
	}

	@RequestMapping(value = "/register/customerSignUpStep2", method = RequestMethod.GET)
	public String customerHandleStep2Get() {
		return "redirect:customerSignUpStep1";
	}
	
	@RequestMapping(value = "/register/customerSignUpStep3", method = RequestMethod.GET)
	public String customerHandleStep3Get() {
		if(isException) {
			isException = false;
		return "register/customerSignUpStep2";
		}
		return "redirect:customerSignUpStep2";
	}

	@RequestMapping(value = "/register/customerSignUpStep3", method = RequestMethod.POST)
	public String customerHandleStep3(RegisterRequest regReq, Model model) {
		try {
			userRegisterService.regist(regReq);
			return "register/customerSignUpStep3";
		} catch (AlreadyExistingUserException ex) {
			model.addAttribute("msg", "아이디 중복"); 
			model.addAttribute("url", "customerSignUpStep3"); 
			isException = true;
			return "register/AlreadyExistingUser";
		}
	}
	
	////////////// seller
	@RequestMapping("/register/sellerSignUpStep1")
	public String sellerHandleStep1() {
		return "register/sellerSignUpStep1";
	}

	@RequestMapping(value = "/register/sellerSignUpStep2", method = RequestMethod.POST)
	public String sellerHandleStep2(
			@RequestParam(value = "agree", defaultValue = "false") Boolean agree,
			Model model) {
		if (!agree) {
			return "register/sellerSignUpStep1";
		}
		model.addAttribute("registerRequest", new RegisterRequest());
		return "register/sellerSignUpStep2";
	}

	@RequestMapping(value = "/register/sellerSignUpStep2", method = RequestMethod.GET)
	public String sellerHandleStep2Get() {
		return "redirect:sellerSignUpStep1";
	}

	@RequestMapping(value = "/register/sellerSignUpStep3", method = RequestMethod.POST)
	public String sellerHandleStep3(RegisterRequest regReq) {
		try {
			userRegisterService.regist(regReq);
			return "register/sellerSignUpStep3";
		} catch (AlreadyExistingUserException ex) {
			
			return "register/sellerSignUpStep2";
		}
	}
}
