package model.service;

import java.util.Date;

import org.springframework.transaction.annotation.Transactional;

import model.spring.RegisterRequest;
import model.vo.User;
import model.dao.UserDao;
import model.exception.AlreadyExistingUserException;

public class UserRegisterService {
	private UserDao userDao;

	public UserRegisterService(UserDao memberDao) {
		this.userDao = memberDao;
	}

	@Transactional
	public void regist(RegisterRequest req) {
		User user = userDao.selectByEmail(req.getEmail());
		if (user != null) {
			throw new AlreadyExistingUserException("dup email " + req.getEmail());
		}
		User newUser = new User(
				req.getEmail(), req.getPassword(), req.getName(), req.getPhone(), new Date(),
				new Date(), req.getAccountNum(), req.getAccountName());
		userDao.insert(newUser);
	}
}
