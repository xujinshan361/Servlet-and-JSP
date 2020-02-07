/**
	method:
		请求方式，值为get或者post
	url：
		请求地址
	data：
		没有值需要传入null
		有请求数据传入字符串数据，格式为"a=2&b=3"
	deal200:
		接收一个带有一个形参的js函数对象，形参接收的是Ajax引擎对象
	deal404:
		接收js函数对象
	deal500:
		接收一个js函数对象
	*/
	function myAjax(method,url,data,deal200,deal404,deal500,async){
		var ajax=getAjax();
		// 复写onreadystatechange函数
		ajax.onreadystatechange=function(){
			// 判断Ajax 状态码
			if(ajax.readyState==4){
					if(deal200){
						deal200(ajax);
					}
				}else if(ajax.status==404){
					if(deal404){
						deal404();
					}
				}else if(ajax.status==500){
					if(deal202){
						deal500();
					}
				}
			}
			// 发送请求
			if("get"==method){
				ajax.open("get",url+(data==null?"":"?"+data),async);
				ajax.send(null);
			}else if("post"==method){
				ajax.open("post",async);
				ajax.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
				ajax.send(data);
			}
	}

/*******************************************************************************/
	function getAjax(){
		// 创建Ajax引擎对象
		var ajax;
		if(window.XMLHttpRequest){ // 火狐
			ajax=new XMLHttpRequest();
		}else if(window.ActiveXObject){		// ie
			ajax=new ActiveXObject("Msxml2.XMLHTTP"); 
		}
		return ajax;
	}

	function deal200(a){
		// 判断响应状态码
		if(a.status==200){
		// 获取响应内容
		var result=ajax.responseText
		// 处理响应内容
		// 获取元素对象
		var showdiv=document.getElementById("showdiv");
		// 修改元素内容
		showdiv.innerHTML=result;
		}
	}
	
	function deal404(){
		// 获取元素对象
		var showdiv=document.getElementById("showdiv");
		// 修改元素内容
		showdiv.innerHTML="请求资源不存在!";
	}
	
	function deal500(){
		// 获取元素对象
		var showdiv=document.getElementById("showdiv");
		// 修改元素内容
		showdiv.innerHTML="服务器繁忙";
	}