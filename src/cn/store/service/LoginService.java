package cn.store.service;

import org.springframework.transaction.annotation.Transactional;

import cn.store.dao.LoginDao;
import cn.store.domain.Userinfo;

/**
 * 登陆业务层
 * @author zzh
 *
 */
@Transactional
public class LoginService {
	private LoginDao loginDao;

	public void setLoginDao(LoginDao loginDao) {
		this.loginDao = loginDao;
	}

	/**
	 * 登陆验证
	 * @param userinfo
	 * @return
	 */
	public Userinfo login(Userinfo userinfo) {
		return loginDao.login(userinfo);
	}
	
	
}
