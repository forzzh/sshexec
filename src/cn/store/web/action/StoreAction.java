package cn.store.web.action;

import java.util.List;

import cn.store.domain.Store;
import cn.store.service.StoreService;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class StoreAction extends ActionSupport implements ModelDriven<Store>{
	private Store store = new Store();
	private List<Store> stores;
	
	public List<Store> getStores() {
		return stores;
	}

	private StoreService storeService;
	
	public void setStoreService(StoreService storeService) {
		this.storeService = storeService;
	}

	@Override
	public Store getModel() {
		return store;
	}
	
	public String add() {
		storeService.add(store);
		return "addSUCCESS";
	}
	
	public String list() {
		//���浽action��Ա�����У�����ֵջ
		stores = storeService.findAll();
		return "listSUCCESS";
	}
	
	public String delete() {
		//id�������ڵ�modeldriven������
		storeService.delete(store);
		return "deleteSUCCESS";
	}
	
	public String editview() {
		store = storeService.find(store.getId());
		return "editviewSUCCESS";
	}
	
	public String edit() {
		storeService.edit(store);
		return "editSUCCESS";
	}

	public String ajaxlist() {
		//����action����ֵջ
		stores = storeService.findAll();
		return "ajaxlistSUCCESS";
	}
}
