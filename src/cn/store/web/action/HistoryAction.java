package cn.store.web.action;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import cn.store.domain.Goods;
import cn.store.domain.History;
import cn.store.service.GoodsService;
import cn.store.service.HistoryService;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class HistoryAction extends ActionSupport implements ModelDriven<History>{
	private History history = new History();
	
	@Override
	public History getModel() {
		return history;
	}

	private HistoryService historyService;

	public void setHistoryService(HistoryService historyService) {
		this.historyService = historyService;
	}
	
	private GoodsService goodsService;
	
	public void setGoodsService(GoodsService goodsService) {
		this.goodsService = goodsService;
	}

	public String list() {
		DetachedCriteria criteria = DetachedCriteria.forClass(History.class);
		if(history.getGoods().getNm()!=null && !history.getGoods().getNm().isEmpty()) {
			Goods goods = goodsService.findByNm(history.getGoods().getNm());
			criteria.add(Restrictions.eq("goods", goods));
		}
		if(history.getGoods().getName()!=null && !history.getGoods().getName().isEmpty()) {
			Goods goods = goodsService.findByName(history.getGoods().getName());
			criteria.add(Restrictions.eq("goods", goods));
		}
		if(history.getGoods().getStore()!=null && !history.getGoods().getStore().getId().isEmpty()) {
			List<Goods> goodsList = goodsService.findByStore(history.getGoods().getStore());
			criteria.add(Restrictions.in("goods", goodsList));
		}
		
		historyList = historyService.historyList(criteria);
		return "listSUCCESS";
	}
	
	private List<History> historyList;

	public List<History> getHistoryList() {
		return historyList;
	}
	
	
	
}
