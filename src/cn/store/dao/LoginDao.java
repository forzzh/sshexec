package cn.store.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.store.domain.Userinfo;
import cn.store.utils.MD5Utils;

public class LoginDao extends HibernateDaoSupport{

	public Userinfo login(Userinfo userinfo) {
		List<Userinfo> list = getHibernateTemplate().find(
				"from Userinfo where name=?and password=?",
				userinfo.getName(), MD5Utils.md5(userinfo.getPassword()));
		return list.isEmpty()?null:list.get(0);
	}

}
