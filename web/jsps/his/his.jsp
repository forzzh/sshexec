<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>历史记录</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value='/css/maple.css'/>"></link>
<style type="text/css">
	.tx td{
		padding:3px;
	}
	.store{
		width:100%;
		border:1px solid gray;
		border-collapse:collapse;
	}
	.store td{
		border:1px solid gray;
		padding:3px;
	}
	.store a{
		text-decoration:underline;
		color:blue;
	}
</style>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
<script type="text/javascript">
$(function(){
	$.post("${pageContext.request.contextPath }/store_ajaxlist.action",function(data){
		for(var i = 0; i<data.stores.length; i++) {
			var store = data.stores[i];
			var $option = $("<option value='"+store.id+"'>"+store.name+"</option>");
			$("#storeSelect").append($option);
		}
	})
})
</script>
</head>
<body>
	<table border="0" class="tx" width="100%">
		<tr>
			<td>当前位置&gt;&gt;首页&gt;&gt;出入库记录</td>
		</tr>
	</table>
	<br>
	<table border="0" width="100%" cellpadding="0" cellspacing="0">
		<tr>
			<td rowspan="1">
				<s:form action="history_list" method="post" namespace="/" name="selest" theme="simple">
					<table width="100%" border="0" cellpadding="0" cellspacing="0" class="tx" align="center">
						<colgroup>
							<col width="20%" align="right">
							<col width="*%" align="left">
						</colgroup>
						<tr>
							<td bgcolor="a0c0c0" style="padding-left:10px;" colspan="2" align="left">
								<b>查询条件：</b>
							</td>
						</tr>
						<tr>
							<td>
								简记码：
							</td>
							<td>
								<s:textfield cssClass="tx" name="goods.nm"></s:textfield>
							</td>
						</tr>
						<tr>
							<td>
								货物名称：
							</td>
							<td>
								<s:textfield cssClass="tx" name="goods.name"></s:textfield>
							</td>
						</tr>
						<tr>
							<td>
								选择仓库：
							</td>
							<td>
								<select class="tx" style="width: 120px;" id="storeSelect" name="goods.store.id">
									<option value="">请选择仓库</option>
								</select>
							</td>
						</tr>
						<tr>
							<td colspan="2" align="right" style="padding-top:10px;">
								<input class="tx" style="width:120px;margin-right:30px;" type="button" onclick="document.forms[0].submit();" value="查询">
							</td>
						</tr>
					</table>
				</s:form>
			</td>
			<td valign="top" width="20%">
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td background="<c:url value='/picture/loginpage.gif'/>" align="center"><br>
						<font color="red">操作步骤</font>
						</td>
					</tr>
					<tr>
						<td background="<c:url value='/picture/bg1.jpg'/>" style="padding-left:10px;">
							1.显示某种货物的出入库记录
							<br/>
							2.根据条件查询某种货的库存情况
						</td>
					</tr>
					<tr>
						<td><img src="<c:url value='/picture/bottom.jpg'/>"></td>
					</tr>
				</table>
			</td>
		</tr>
		<tr valign="top">
			<td rowspan="2">
				<form action="" method="post" name="select">
					<table width="100%" border="0" cellpadding="0" cellspacing="0" class="tx" align="center">
						<colgroup>
							<col width="20%" align="right">
							<col width="*%" align="left">
						</colgroup>
						<tr>
							<td bgcolor="a0c0c0" style="padding-left:10px;" colspan="1" align="left">
								<b>货物库存：</b>
							</td>
						</tr>
						<tr>
							<td>
								<table class="store">
									<tr style="background:#D2E9FF;text-align: center;">
										<td>简记码</td>
										<td>名称</td>
										<td>时间</td>
										<td>类型</td>
										<td>单位</td>
										<td>数量</td>
										<td>库存余量</td>
										<td>仓库</td>
										<td>操作员</td>
									</tr>
									<s:iterator value="historyList" var="history">
									<tr>
										<td><s:property value="#history.goods.nm"/></td>
										<td><s:property value="#history.goods.name"/></td>
										<td><s:property value="#history.datetime"/></td>
<%-- 										<td><s:property value="#history.type"/></td> --%>
										<td>${history.type==1?"入库":"出库" }</td>
<%-- 										<td><s:property value="(#history.type=='1')?'入库':'出库'"/></td> --%>
										<td><s:property value="#history.goods.unit"/></td>
										<td><s:property value="#history.amount"/></td>
										<td><s:property value="#history.remain"/></td>
										<td><s:property value="#history.goods.store.name"/></td>
										<td><s:property value="#history.user"/></td>	
									</tr>
									</s:iterator>
								</table>
							</td>
						</tr>
					</table>
				</form>
			</td>
		</tr>
	</table>
</body>
</html>

