package service;

import java.util.Date;

import org.springframework.transaction.annotation.Transactional;

import spring.RegisterRequest;
import vo.Member;
import dao.MemberDao;
import exception.AlreadyExistingMemberException;

public class MemberRegisterService {
	private MemberDao memberDao;

	public MemberRegisterService(MemberDao memberDao) {
		this.memberDao = memberDao;
	}

	@Transactional
	public void regist(RegisterRequest req) {
		Member member = memberDao.selectByEmail(req.getEmail());
		if (member != null) {
			throw new AlreadyExistingMemberException("dup email " + req.getEmail());
		}
		Member newMember = new Member(
				req.getEmail(), req.getPassword(), req.getName(), req.getPhone(), new Date(),
				new Date(), req.getAccountNum(), req.getAccountName());
		memberDao.insert(newMember);
	}
}
