var TableList = function() {
}

var tableList = new TableList();
/**
 * 清除界面原有信息列表
 */
TableList.prototype.cleanTableList = function() {
	var list = document.getElementById("bodyList");
	var folderList = document.getElementById("folderList");
	list.parentNode.removeChild(list);
	var tbody = document.createElement("tbody");
	tbody.setAttribute("id", "bodyList");
	folderList.appendChild(tbody);
}
/**
 * table中的tr双击事件
 * @param {} tr 列表行
 */
TableList.prototype.doubleClick = function(tr) {
	var type = "";
	var isFile = false;
	tr.ondblclick = function() {
		var fileSizeFlag = tr.childNodes[1].innerHTML;
		var thisName = this.childNodes[0].innerHTML;
		var path = document.getElementById("source").innerHTML.substring(1) + thisName;
		var jsonParame = {
			"type" : "watch",
			"name" : "",
			"path" : path,
			"isFile" : isFile,
			"context" : ""
		}
		if (fileSizeFlag == "") {
			type = "watch";
		}
		else {
			if (!confirm("确认下载文件?")) {
				return;
			}
			else {
				window.open("http://127.0.0.1:8080/%7B%22type%22:%22download%22,%22name%22:%22%22,%22path%22:%22" + path
				    + "%22,%22isFile%22:true,%22context%22:%22%22%7D");
				return;
			}
		}
		tableList.ajaxRequest("http://127.0.0.1:8080", "GET", jsonParame, 1);
		if (!isFile) {
			document.getElementById("source").innerHTML += (thisName + "/");
		}
	}
}
/*
 * 隐藏右键菜单
 */
TableList.prototype.menuHidden = function() {

}
/**
 * 右键功能菜单
 * @param {} menu
 * @param {} trArr
 */
TableList.prototype.menuList = function(menu, trArr) {
	document.onclick = function() {
		var e = e || window.event;
		menu.style.display = "none";
	};
	for (var i = 0, len = trArr.length; i < len; i++) {
		trArr[i].oncontextmenu = function(e) {
			var firstChildTd = this.childNodes[0];
			var thisName = firstChildTd.innerHTML;
			var folderFlag = firstChildTd.getAttribute("name") == "folder" ? true : false;
			var liElementArr = menu.getElementsByTagName("li");
			liElementArr[0].onclick = function() {
				if(folderFlag){
					
				}
			}
			if (folderFlag) {
				liElementArr[1].setAttribute("class", "disabled");
				liElementArr[1].onclick = function(e) {
					var e = e || window.event;
					e.stopPropagation = true;
				}
			}
			else {
				liElementArr[1].setAttribute("class", "");
				liElementArr[1].onclick = function(e) {
					var path = document.getElementById("source").innerHTML.substring(1) + thisName;
					window.open("http://127.0.0.1:8080/%7B%22type%22:%22download%22,%22name%22:%22%22,%22path%22:%22" + path
					    + "%22,%22isFile%22:true,%22context%22:%22%22%7D");
				}
			}
			liElementArr[2].onclick = function() {
				alert(1)
			}
			liElementArr[3].onclick = function() {
				alert(1)
			}
			menu.onclick = function(e) {
				var e = e || window.event;
				e.stopPropagation = true;
			}
			var e = e || window.event;
			var oX = e.clientX;
			var oY = e.clientY;
			menu.style.display = "block";
			menu.style.left = oX + "px";
			menu.style.top = oY + "px";
			return false;
		}
	}
}
/**
 * 返回按钮点击事件
 */
TableList.prototype.retBtn = function() {
	var path = document.getElementById("source").innerHTML;
	if (path == "/") {
		return;
	}
	path = path.substring(0, path.length - 1);
	var lastpath = path.substring(0, path.lastIndexOf("/") + 1);
	var jsonParame = {
		"type" : "watch",
		"name" : "",
		"path" : lastpath,
		"isFile" : "",
		"context" : ""
	}
	tableList.ajaxRequest("http://127.0.0.1:8080", "GET", jsonParame, 1);
	document.getElementById("source").innerHTML = lastpath;
}

/**
 * 向tbody中添加node节点
 * <pre>
 * 	<thead>
 * 		<tr><td>.idea</td><td></td>			   <td>2017-06-18 18:08:14</td>
 * 		</tr>
 * 		<tr><td>text.txt</td> <td>1.616KB</td> <td>2017-07-01 17:52:02</td>
 * 		</tr>
 * 	</thead>
 * </pre>
 * @param {} list
 * @param {} length
 * @param {} index
 * @param {} resultArr
 * @param {} sortType
 */
TableList.prototype.appendNodes = function(list, resultJson) {
	var resultArr = resultJson.callback;
	resultArr.sort()
	var length = resultArr.length;
	for (var index = 0; index < length; index++) {
		var trNode = document.createElement("tr");
		var tdNode1 = document.createElement("td");
		var creadeNodesJson = resultArr[index];
		var creadeNodesJsonName = creadeNodesJson.name;
		if (creadeNodesJson.isFile == "true") {
			var classStyle = creadeNodesJsonName.split(".");
			var fileNameLength = classStyle.length;
			switch (classStyle[fileNameLength - 1]) {
				case "txt" :
					tdNode1.setAttribute("class", "file_type_txt");
					break;
				case "pdf" :
					tdNode1.setAttribute("class", "file_type_pdf");
					break;
				case "zip" :
					tdNode1.setAttribute("class", "file_type_zip");
					break;
				case "docx" :
					tdNode1.setAttribute("class", "file_type_doc");
					break;
				case "js" :
					tdNode1.setAttribute("class", "file_type_js");
					break;
				case "css" :
					tdNode1.setAttribute("class", "file_type_css");
					break;
				case "html" :
					tdNode1.setAttribute("class", "file_type_html");
					break;
				case "png" :
					tdNode1.setAttribute("class", "file_type_png");
					break;
				case "jpg" :
					tdNode1.setAttribute("class", "file_type_jpg");
					break;
				case "mp3" :
					tdNode1.setAttribute("class", "file_type_mp3");
					break;
				default :
					tdNode1.setAttribute("class", "file_type_default");
					break;
			}
		}
		else {
			tdNode1.setAttribute("class", "file_folder");
			tdNode1.setAttribute("name", "folder");
		}
		var tdNode2 = document.createElement("td");
		var tdNode3 = document.createElement("td");
		var textNode1 = document.createTextNode(creadeNodesJsonName);
		var textNode3 = document.createTextNode(creadeNodesJson.date);
		tdNode1.appendChild(textNode1);
		if (creadeNodesJson.size != "") {
			var textNode2 = document.createTextNode(creadeNodesJson.size);
			tdNode2.appendChild(textNode2);
		}
		tdNode3.appendChild(textNode3);
		trNode.appendChild(tdNode1);
		trNode.appendChild(tdNode2);
		trNode.appendChild(tdNode3);
		list.appendChild(trNode);
	}
}

/**
 * 将数据以列表的形式显示出来
 * @param {} resultStr
 * @param {} sortType
 */
TableList.prototype.showList = function(resultStr) {
	var resultJson;
	var list = document.getElementById("bodyList");
	var resultJson = JSON.parse(resultStr);
	if (list.childNodes.length != 0) {
		tableList.cleanTableList();
	}
	if (resultJson.length == 0) {
		return;
	}
	var list = document.getElementById("bodyList");
	tableList.appendNodes(list, resultJson);
	tableList.addEvent();
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
TableList.prototype.ajaxRequest = function(url, methodtype, parameter) {
	var xhr = tableList.getajaxHttp();
	var stringParameter = JSON.stringify(parameter);
	xhr.onreadystatechange = state_change;
	xhr.open(methodtype, url + "/" + stringParameter, true);
	xhr.send();
	function state_change() {
		if (xhr.readyState == 4 && status == 0) {
			if (xhr.responseText != null || xhr.responseText != "") {
				tableList.showList(xhr.responseText);
			}
		}
	};
}
/**
 * 下载文件
 * @param {} url
 * @param {} methodtype
 * @param {} parameter
 */
TableList.prototype.download = function(url, methodtype, parameter) {
	var xhr = tableList.getajaxHttp();
	var stringParameter = JSON.stringify(parameter);
	xhr.open(methodtype, url + "/" + stringParameter, true);
	xhr.send();
}
/**
 * 页面初始化
 */
TableList.prototype.init = function() {
	var jsonParame = {
		"type" : "watch",
		"name" : "",
		"path" : "/",
		"isFile" : "",
		"context" : ""
	}
	var resultStr = tableList.ajaxRequest("http://127.0.0.1:8080", "GET", jsonParame);
}

TableList.prototype.clickToOrder = function(flag) {

}

/**
 * 给table，返回按钮，右键菜单等加点击事件
 */
TableList.prototype.addEvent = function() {
	var menu = document.getElementById("menu");
	var fileNameList = document.getElementById("bodyList");
	var trArr = fileNameList.getElementsByTagName("tr");
	var thead = document.getElementById("fileInfo");
	// 右键菜单
	tableList.menuList(menu, trArr);
	// 双击进入目录下一页
	for (var i = 0, len = trArr.length; i < len; i++) {
		tableList.doubleClick(trArr[i]);
	}
	// 返回按钮点击事件
	document.getElementById("return").onclick = tableList.retBtn;
	// 点击排序

}

window.onload = function() {
	tableList.init();
	setTimeout(tableList.addEvent, 500);
}
