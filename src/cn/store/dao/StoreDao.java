package cn.store.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.store.domain.Store;

public class StoreDao extends HibernateDaoSupport{

	public void add(Store store) {
		getHibernateTemplate().save(store);
		
	}

	public List<Store> findAll() {
		return getHibernateTemplate().find("from Store");
	}

	public void delete(Store store) {
		//直接删除托管态对象做不到级联效果
		getHibernateTemplate().delete(store);
	}

	//根据id查询
	public Store findById(String id) {
		return getHibernateTemplate().get(Store.class, id);
	}

	public void update(Store store) {
		getHibernateTemplate().update(store);
		
	}

}
