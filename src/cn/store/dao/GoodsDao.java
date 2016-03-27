package cn.store.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.store.domain.Goods;
import cn.store.domain.Store;

public class GoodsDao extends HibernateDaoSupport{

	public Goods findByNm(String nm) {
		List<Goods> goodsList = getHibernateTemplate().find("from Goods where nm=?",nm);
		return goodsList.isEmpty()?null:goodsList.get(0);
	}

	public void save(Goods goods) {
		getHibernateTemplate().save(goods);
		
	}

	public Goods findById(String id) {
		return getHibernateTemplate().get(Goods.class, id);
	}

	public List<Goods> findByCriteria(DetachedCriteria criteria) {
		return getHibernateTemplate().findByCriteria(criteria);
	}

	public List<String> findNamesLike(String name) {
		return getHibernateTemplate().find("select name from Goods where name like ?",name+"%");
	}

	public Goods findByName(String name) {
		List<Goods> goodsList = getHibernateTemplate().find("from Goods where name = ?",name);
		return goodsList.get(0);
	}

	public List<Goods> findByStore(Store store) {
		return getHibernateTemplate().find("from Goods where store = ?", store);
	}

}
