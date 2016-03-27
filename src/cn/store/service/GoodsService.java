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
		//�ж��Ƿ����»�
		if (goods.getId()==null||goods.getId().isEmpty()) {
			goodsDao.save(goods);
			//��ʷ��¼����
			History history = new History();
			history.setAmount(goods.getAmount());
			history.setRemain(goods.getAmount());
			history.setUser(userName);
			history.setDatetime(new Date().toLocaleString());
			history.setType("1");//���
			history.setGoods(goods);
			historyService.add(history);
		} else {
			//��Ϊ�ǳ־�̬�����Զ������������
			Goods oldGoods = goodsDao.findById(goods.getId());
			oldGoods.setAmount(oldGoods.getAmount()+goods.getAmount());
			
			//��ʷ��¼
			History history = new History();
			history.setAmount(goods.getAmount()); //�����������
			history.setRemain(oldGoods.getAmount()); //���������ʣ������
			history.setUser(userName);
			history.setDatetime(new Date().toLocaleString());
			history.setType("1");//���
			//�����־ö���
			history.setGoods(oldGoods);
			historyService.add(history);
			
		}
		
	}

	public List<Goods> listGoods(DetachedCriteria criteria) {
		List<Goods> goodsList =  goodsDao.findByCriteria(criteria);
		//��Ϊ���ӳټ��صģ�����Ҫ�ֶ���ʼ��
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
		//�жϻ����Ƿ����
		if(existGoods.getAmount() >= goods.getAmount()) {
			existGoods.setAmount(existGoods.getAmount() - goods.getAmount());
			//��ʷ��¼
			History history = new History();
			history.setAmount(goods.getAmount()); //���γ��������
			history.setRemain(existGoods.getAmount()); //���������ʣ������
			history.setUser(userName);
			history.setDatetime(new Date().toLocaleString());
			history.setType("2");//����
			//�����־ö���
			history.setGoods(existGoods);
			historyService.add(history);
		}else {
			throw new RuntimeException("��治��");
		}
	}

	public List<Goods> findByStore(Store store) {
		return goodsDao.findByStore(store);
	}
	
	
}
