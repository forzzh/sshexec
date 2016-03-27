package cn.store.service;

import java.util.Date;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import cn.store.dao.GoodsDao;
import cn.store.domain.Goods;
import cn.store.domain.History;
import cn.store.domain.Store;

@Transactional
public class GoodsService {

	private GoodsDao goodsDao;

	public void setGoodsDao(GoodsDao goodsDao) {
		this.goodsDao = goodsDao;
	}
	
	public HistoryService historyService;
	
	public void setHistoryService(HistoryService historyService) {
		this.historyService = historyService;
	}

	public Goods findByNm(String nm) {
		
		return goodsDao.findByNm(nm);
	}

	public void save(Goods goods, String userName) {
		//判断是否是新货
		if (goods.getId()==null||goods.getId().isEmpty()) {
			goodsDao.save(goods);
			//历史记录存入
			History history = new History();
			history.setAmount(goods.getAmount());
			history.setRemain(goods.getAmount());
			history.setUser(userName);
			history.setDatetime(new Date().toLocaleString());
			history.setType("1");//入库
			history.setGoods(goods);
			historyService.add(history);
		} else {
			//因为是持久态对象，自动随着事务更改
			Goods oldGoods = goodsDao.findById(goods.getId());
			oldGoods.setAmount(oldGoods.getAmount()+goods.getAmount());
			
			//历史记录
			History history = new History();
			history.setAmount(goods.getAmount()); //本次入库数量
			history.setRemain(oldGoods.getAmount()); //货物操作后剩余数量
			history.setUser(userName);
			history.setDatetime(new Date().toLocaleString());
			history.setType("1");//入库
			//关联持久对象
			history.setGoods(oldGoods);
			historyService.add(history);
			
		}
		
	}

	public List<Goods> listGoods(DetachedCriteria criteria) {
		List<Goods> goodsList =  goodsDao.findByCriteria(criteria);
		//因为是延迟加载的，所以要手动初始化
		for (Goods goods : goodsList) {
			Hibernate.initialize(goods.getStore());
		}
		return goodsList;
	}

	public List<String> findNamesLike(String name) {
		return goodsDao.findNamesLike(name);
	}

	public Goods findByName(String name) {
		Goods goods = goodsDao.findByName(name);
		Hibernate.initialize(goods.getStore());
		return goods;
	}

	public void out(Goods goods, String userName) {
		Goods existGoods = goodsDao.findById(goods.getId());
		//判断货物是否充足
		if(existGoods.getAmount() >= goods.getAmount()) {
			existGoods.setAmount(existGoods.getAmount() - goods.getAmount());
			//历史记录
			History history = new History();
			history.setAmount(goods.getAmount()); //本次出库库数量
			history.setRemain(existGoods.getAmount()); //货物操作后剩余数量
			history.setUser(userName);
			history.setDatetime(new Date().toLocaleString());
			history.setType("2");//出库
			//关联持久对象
			history.setGoods(existGoods);
			historyService.add(history);
		}else {
			throw new RuntimeException("库存不足");
		}
	}

	public List<Goods> findByStore(Store store) {
		return goodsDao.findByStore(store);
	}
	
	
}
