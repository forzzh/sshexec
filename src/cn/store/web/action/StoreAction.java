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
		//保存到action成员变量中，放入值栈
		stores = storeService.findAll();
		return "listSUCCESS";
	}
	
	public String delete() {
		//id将保存在的modeldriven对象中
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
		//随着action放入值栈
		stores = storeService.findAll();
		return "ajaxlistSUCCESS";
	}
}
