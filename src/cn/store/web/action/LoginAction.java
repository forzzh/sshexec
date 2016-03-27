package cn.store.web.action;

import org.apache.struts2.ServletActionContext;

import cn.store.domain.Userinfo;
import cn.store.service.LoginService;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class LoginAction extends ActionSupport implements ModelDriven<Userinfo>{
	private Userinfo userinfo = new Userinfo();
	
	@Override
	public Userinfo getModel() {
		return userinfo;
	}
	
	//注入service
	private LoginService loginService;
	
	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}



	@Override
	public String execute() throws Exception {
		Userinfo user = loginService.login(userinfo);
		if(user==null) {
			addActionError(getText("loginfail"));
			return INPUT;
		}else{
			//成功存入到session
			ServletActionContext.getRequest().getSession().setAttribute("user", user);
			return SUCCESS;
		}
		
	}
}
