package model.service;

import org.springframework.transaction.annotation.Transactional;

import model.vo.User;
import model.dao.UserDao;
import model.exception.UserNotFoundException;

public class ChangePasswordService {

	private UserDao userDao;

	public ChangePasswordService(UserDao userDao) {
		this.userDao = userDao;
	}

	@Transactional
	public void changePassword(String email, String oldPwd, String newPwd) {
		User user = userDao.selectByEmail(email);
		if (user == null)
			throw new UserNotFoundException();
		
		user.changePassword(oldPwd, newPwd);
		
		userDao.update(user);
	}
}
