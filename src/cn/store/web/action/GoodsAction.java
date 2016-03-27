package cn.store.web.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import cn.store.domain.Goods;
import cn.store.domain.Userinfo;
import cn.store.service.GoodsService;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;

public class GoodsAction extends ActionSupport implements ModelDriven<Goods>{
	private Goods goods = new Goods();
	
	@Override
	public Goods getModel() {
		return goods;
	}

	private GoodsService goodsService;
	
	public void setGoodsService(GoodsService goodsService) {
		this.goodsService = goodsService;
	}

	//简记码查询返回	
	public String findByNm() {
		goods = goodsService.findByNm(goods.getNm());
		return "findByNmSUCCESS";
	}
	
	public String save() {
		Userinfo user = (Userinfo) ServletActionContext.getRequest().getSession().getAttribute("user");
		goodsService.save(goods, user.getName());
		return "saveSUCCESS";
	}
	
	public String list() {
		//在web下构造离线查询
		DetachedCriteria criteria = DetachedCriteria.forClass(Goods.class);
		if(goods.getNm()!=null && !goods.getNm().trim().isEmpty()) {
			criteria.add(Restrictions.eq("nm", goods.getNm()));
		}
		if(goods.getName()!=null && !goods.getName().trim().isEmpty()) {
			criteria.add(Restrictions.like("name", "%"+goods.getName()+"%"));
		}
		if(goods.getStore()!=null && !goods.getStore().getId().trim().isEmpty()) {
			criteria.add(Restrictions.eq("store", goods.getStore()));
		}
		//传递查询条件
		goodsList = goodsService.listGoods(criteria);
		return "listSUCCESS";
	}
	
	private List<Goods> goodsList;

	public List<Goods> getGoodsList() {
		return goodsList;
	}
	
	public String findNamesLike() {
		names = goodsService.findNamesLike(goods.getName());
		return "findNamesLikeSUCCESS";
	}
	
	private List<String> names;

	public List<String> getNames() {
		return names;
	}
	
	public String findByName() {
		goods = goodsService.findByName(goods.getName());
		return "findByNameSUCCESS";
	}
	
	//出库
	@InputConfig(resultName="outInput")
	public String out() {
		Userinfo user = (Userinfo) ServletActionContext.getRequest().getSession().getAttribute("user");
		goodsService.out(goods, user.getName());
		return "outSUCCESS";
	}
}
