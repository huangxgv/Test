var TableList = function() {
}

var tableList = new TableList();

TableList.prototype.cleanTableList = function() {
	var list = document.getElementById("bodyList");
	var childs = list.childNodes;
	for (var j = 0, length = childs.length; j < length; j++) {
		list.removeChild(childs[j]);
	}
}

TableList.prototype.showList = function(resultStr) {
	var list = document.getElementById("bodyList");
	// tableList.cleanTableList();
	var resultArr = resultStr.split(":");
	var i = 0, length = resultArr.length
	//去除根目录下的$RECYCLE.BIN
	if ("$RECYCLE.BIN" == resultArr[0]) {
		i++;
	}
	for (; i < length; i++) {
		var trNode = document.createElement("tr");
		var tdNode = document.createElement("td");
		var textNode = document.createTextNode(resultArr[i]);
		tdNode.appendChild(textNode);
		trNode.appendChild(tdNode);
		list.appendChild(trNode);
	}
}

/**
 * 得到ajax对象
 */
TableList.prototype.getajaxHttp = function() {
	var xmlHttp;
	try {
		xmlHttp = new XMLHttpRequest();
	}
	catch (e) {
		try {
			xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
		}
		catch (e) {
			try {
				xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
			}
			catch (e) {
				alert("您的浏览器不支持AJAX！");
				return false;
			}
		}
	}
	return xmlHttp;
}
/**
 * 发送ajax请求,获取服务器处理数据
 * url--url
 * methodtype(post/get)
 * con (true(异步)|false(同步))
 * parameter(参数)
 * state_change(回调方法名，不需要引号,这里只有成功的时候才调用)
 */
TableList.prototype.ajaxRequest = function(url, methodtype, parameter, sycn) {
	var xhr = tableList.getajaxHttp();
	var stringParameter = JSON.stringify(parameter);
	xhr.onreadystatechange = state_change;
	xhr.open(methodtype, url + "/" + stringParameter, sycn);
	xhr.send();
	function state_change() {
		if (xhr.readyState == 4 && status == 0) {
			tableList.showList(xhr.responseText);
		}
	};
}

TableList.prototype.init = function() {
	var jsonParame = {
		"type" : "watch",
		"name" : "",
		"path" : "D:/",
		"isFile" : "",
		"context" : ""
	}
	var resultStr = tableList.ajaxRequest("http://127.0.0.1:8080", "GET", jsonParame, true);
	if (!!resultStr) {
		tableList.showList(resultStr)
	}
}

window.onload = function() {
	var tableList = new TableList();
	tableList.init();
}
