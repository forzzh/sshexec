package cn.store.service;

import org.springframework.transaction.annotation.Transactional;

import cn.store.dao.LoginDao;
import cn.store.domain.Userinfo;

/**
 * ��½ҵ���
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
	 * ��½��֤
	 * @param userinfo
	 * @return
	 */
	public Userinfo login(Userinfo userinfo) {
		return loginDao.login(userinfo);
	}
	
	
}
