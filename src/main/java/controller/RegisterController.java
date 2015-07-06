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

	public void setUserRegisterService(
			UserRegisterService userRegisterService) {
		this.userRegisterService = userRegisterService;
	}

	@RequestMapping("/register/step1")
	public String handleStep1() {
		return "register/step1";
	}

	@RequestMapping(value = "/register/step2", method = RequestMethod.POST)
	public String handleStep2(
			@RequestParam(value = "agree", defaultValue = "false") Boolean agree,
			Model model) {
		if (!agree) {
			return "register/step1";
		}
		model.addAttribute("registerRequest", new RegisterRequest());
		return "register/step2";
	}

	@RequestMapping(value = "/register/step2", method = RequestMethod.GET)
	public String handleStep2Get() {
		return "redirect:step1";
	}

	@RequestMapping(value = "/register/step3", method = RequestMethod.POST)
	public String handleStep3(RegisterRequest regReq) {
		try {
			userRegisterService.regist(regReq);
			return "register/step3";
		} catch (AlreadyExistingUserException ex) {
			return "register/step2";
		}
	}
}
