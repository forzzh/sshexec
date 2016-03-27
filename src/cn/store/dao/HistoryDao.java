package cn.store.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.store.domain.History;

public class HistoryDao extends HibernateDaoSupport{

	public void add(History history) {
		getHibernateTemplate().save(history);
	}

	public List<History> fingByCriteria(DetachedCriteria criteria) {
		return getHibernateTemplate().findByCriteria(criteria);
	}

}
