<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>收货登记</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value='/css/maple.css'/>"></link>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
<style type="text/css">
	.tx td{
		padding:3px;
	}
</style>

<script type="text/javascript">
	$(function(){
		$.post("${pageContext.request.contextPath }/store_ajaxlist.action",function(data){
			for(var i = 0; i<data.stores.length; i++) {
				var store = data.stores[i];
				var $option = $("<option value='"+store.id+"'>"+store.name+"</option>");
				$("#storeSelect").append($option);
			}
		})
		
		$("#nm").blur(function(){
			$.post("${pageContext.request.contextPath}/goods_findByNm.action",{"nm":$(this).val()},function(data){
				if(data.model==null) {
					$("#id").val("");
					$("#name").val("");
					$("#unit").val("");
					$("#storeSelect option").removeAttr("selected");
				}else{
					$("#id").val(data.model.id);
					$("#name").val(data.model.name);
					$("#unit").val(data.model.unit);
					$("#storeSelect").val(data.model.store.id);
				}
			})
		})
	})
</script>
</head>
<body>
	<!-- 中间内容（开始） -->
	<table border="0" class="tx" width="100%">
		<tr>
			<td>当前位置&gt;&gt;首页&gt;&gt;入库</td>
		</tr>
	</table>
	<br>
	<table border="0" width="100%" cellpadding="0" cellspacing="0">
		<tr valign="top">
			<td rowspan="2">
				<s:form action="goods_save" method="post" theme="simple" name="select" namespace="/">
					<table width="100%" border="0" cellpadding="0" cellspacing="0" class="tx" align="center">
						<colgroup>
							<col width="20%" align="right">
							<col width="*%" align="left">
						</colgroup>
						<tr>
							<td bgcolor="a0c0c0" style="padding-left:10px;" colspan="2" align="left">
								<b>货物入库登记：</b>
							</td>
						</tr>
						<tr>
							<td>
								简记码：
							</td>
							<td>
								<s:hidden cssClass="tx" name="id" id="id"></s:hidden>
								<s:textfield cssClass="tx" name="nm" id="nm"></s:textfield>
							</td>
						</tr>
						<tr>
							<td>
								货物名称：
							</td>
							<td>
								<s:textfield cssClass="tx" name="name" id="name"></s:textfield>
							</td>
						</tr>
						<tr>
							<td>
								计量单位：
							</td>
							<td>
								<s:textfield cssClass="tx" name="unit" id="unit"></s:textfield>
							</td>
						</tr>
						<tr>
							<td>
								入库数量：
							</td>
							<td>
								<s:textfield cssClass="tx" name="amount"></s:textfield>
							</td>
						</tr>
						<tr>
							<td>
								选择仓库：
							</td>
							<td>
								<!-- 封装数据提交到model -->
								<select class="tx" style="width:120px;" name="store.id" id="storeSelect">
									
								</select>
								(此信息从数据库中加载)
							</td>
						</tr>
						<tr>
							<td colspan="2" align="center" style="padding-top:10px;">
								<input class="tx" style="width:120px;margin-right:30px;" type="button" onclick="document.forms[0].submit();" value="入库">
								<input class="tx" style="width:120px;margin-right:30px;" type="reset" value="取消">
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
							1.输入简记码从数据库<br/>查询是否已经存在此
							<br/>货物
							<br/>
							2.没有则直接输入货物名称
							<br>
							3.从数据库选择仓库
							<br>
							4.向仓库中新添加或是追加货物
							<br/>
							5.记录入库历史记录
						</td>
					</tr>
					<tr>
						<td><img src="<c:url value='/picture/bottom.jpg'/>"></td>
					</tr>
				</table>
		</tr>
	</table>
</body>
</html>

