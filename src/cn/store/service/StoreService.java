package cn.store.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.store.dao.StoreDao;
import cn.store.domain.Store;

@Transactional
public class StoreService {
	private StoreDao storeDao;

	public void setStoreDao(StoreDao storeDao) {
		this.storeDao = storeDao;
	}

	public void add(Store store) {
		storeDao.add(store);
		
	}

	public List<Store> findAll() {
		return storeDao.findAll();
	}

	//ɾ���ֿ�
	public void delete(Store store) {
		
		store = storeDao.findById(store.getId());
		if (store.getGoodses().isEmpty()) {
			storeDao.delete(store);
		} else {
			throw new RuntimeException("�ֿ����л��ﲻ��ɾ��");
		}
		
	}

	public Store find(String id) {
		return storeDao.findById(id);
	}

	public void edit(Store store) {
		storeDao.update(store);
		
	}
	
	
}
