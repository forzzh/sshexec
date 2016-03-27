package cn.store.service;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import cn.store.dao.HistoryDao;
import cn.store.domain.History;

@Transactional
public class HistoryService {
	private HistoryDao historyDao;

	public void setHistoryDao(HistoryDao historyDao) {
		this.historyDao = historyDao;
	}

	public void add(History history) {
		historyDao.add(history);
	}

	public List<History> historyList(DetachedCriteria criteria) {
		List<History> historyList = historyDao.fingByCriteria(criteria);
		for (History history : historyList) {
			Hibernate.initialize(history.getGoods());
			Hibernate.initialize(history.getGoods().getStore());
		}
		return historyList;
	}
	
	
}
