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
		if (!(this.childNodes[0].getAttribute("name") == "folder")) {
			if (!confirm("确认下载文件?")) {
				return;
			}
			else {
				window.open("http://127.0.0.1:8080/{\"type\":\"download\",\"name\":\"\",\"path\":\"" + path + "\",\"isFile\":true,\"context\":\"\"}");
				return;
			}
		}
		tableList.init(path);
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
 * 编辑界面返回按钮功能
 */
TableList.prototype.editBtnReturn = function() {
	var contextBackground = document.getElementById("file_background");
	var fileContext = document.getElementById("file_context");
	contextBackground.style.display = "none";
	fileContext.style.display = "none";
}

/**
 * 编辑界面确认修改功能
 */
TableList.prototype.editBtnCommit = function(parentPath, thisName) {
	var newName = document.getElementsByClassName("title")[0].getElementsByTagName("input")[0].value;
	var content = document.getElementById("file_txt").value;
	content = content.replace("\n", "");
	var newPath = parentPath + newName;
	var oldPath = parentPath + thisName;
	var jsonParame = {
		"type" : "update",
		"name" : newPath,
		"path" : oldPath,
		"isFile" : "",
		"context" : content
	}
	tableList.ajaxRequest("http://127.0.0.1:8080", "GET", jsonParame, "update");
	var contextBackground = document.getElementById("file_background");
	var fileContext = document.getElementById("file_context");
	contextBackground.style.display = "none";
	fileContext.style.display = "none";
}

TableList.prototype.menuPosition = function(menu) {
	var e = e || window.event;
	var oX = e.clientX;
	var oY = e.clientY;
	menu.style.display = "block";
	menu.style.left = oX + "px";
	menu.style.top = oY + "px";
}

TableList.prototype.editPageShow = function(textArea, contextBackground, fileContext, path, thisName, inputTitle, disEdit) {
	contextBackground.style.display = "block";
	fileContext.style.display = "block";
	var btnGroup = document.getElementById("btn_group");
	var jsonParame = {
		"type" : "file",
		"name" : "",
		"path" : path,
		"isFile" : "",
		"context" : ""
	}
	if (disEdit) {
		textArea.setAttribute("readOnly", disEdit);
		btnGroup.setAttribute("class", "");
		btnGroup.setAttribute("class", "see");
	}
	else {
		textArea.removeAttribute("readOnly");
		btnGroup.setAttribute("class", "");
		btnGroup.setAttribute("class", "edit");
	}
	document.getElementById("file_txt").value = "信息加载中......";
	tableList.ajaxRequest("http://127.0.0.1:8080", "POST", jsonParame, "file");
	inputTitle.value = thisName;
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
			var textArea = document.getElementById("file_txt");
			var firstChildTd = this.childNodes[0];
			var thisName = firstChildTd.innerHTML;
			var parentPath = document.getElementById("source").innerHTML.substring(1);
			var path = parentPath + thisName;
			var folderFlag = firstChildTd.getAttribute("name") == "folder" ? true : false;
			var contextBackground = document.getElementById("file_background");
			var fileContext = document.getElementById("file_context");
			var inputTitle = fileContext.getElementsByTagName("input")[0];
			var liElementArr = menu.getElementsByTagName("li");
			liElementArr[0].onclick = function() {
				if (folderFlag) {
					tableList.init(path);
					document.getElementById("source").innerHTML += (thisName + "/");
				}
				else {
					tableList.editPageShow(textArea, contextBackground, fileContext, path, thisName, inputTitle, true);
				}
			}
			if (folderFlag) {
				liElementArr[1].setAttribute("class", "disabled");
				liElementArr[1].onclick = function(e) {
				}
			}
			else {
				liElementArr[1].setAttribute("class", "");
				liElementArr[1].onclick = function(e) {
					var path = document.getElementById("source").innerHTML.substring(1) + thisName;
					window.open("http://127.0.0.1:8080/{\"type\":\"download\",\"name\":\"\",\"path\":\"" + path + "\",\"isFile\":true,\"context\":\"\"}");
				}
			}
			liElementArr[2].onclick = function() {
				var jsonParame = {
					"type" : "delete",
					"name" : "",
					"path" : path,
					"isFile" : "",
					"context" : ""
				}
				if (folderFlag) {
					if (confirm("确认删除整个文件夹?")) {
						tableList.ajaxRequest("http://127.0.0.1:8080", "GET", jsonParame, "delete");
					}
				}
				else {
					tableList.ajaxRequest("http://127.0.0.1:8080", "GET", jsonParame, "delete");
				}
			}
			liElementArr[3].onclick = function() {
				if (folderFlag) {
					contextBackground.style.display = "block";
					fileContext.style.display = "block";
					textArea.value = "";
					textArea.setAttribute("readOnly", "true");
					inputTitle.value = thisName;
				}
				else {
					tableList.editPageShow(textArea, contextBackground, fileContext, path, thisName, inputTitle, false);
				}
			}
			var jParame = {
				"type" : "watch",
				"name" : "",
				"path" : parentPath,
				"isFile" : "",
				"context" : ""
			}
			for (var i = 1; i < 6; i += 2) {
				var spanElement = liElementArr[4].childNodes[1].childNodes[i];
				spanElement.index = i;;
				spanElement.onclick = function() {
					var spanValue = this.childNodes[1].innerHTML;
					var orderType = spanValue == "↓" ? -this.index : this.index;
					tableList.ajaxRequest("http://127.0.0.1:8080", "GET", jParame, "watch", orderType);
					return false;
				}
			}
			document.getElementsByClassName("update_return")[0].onclick = tableList.editBtnReturn;
			document.getElementsByClassName("update_commit")[0].onclick = function() {
				tableList.editBtnCommit(parentPath, thisName);
			}
			tableList.menuPosition(menu);
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
	tableList.init(lastpath)
	document.getElementById("source").innerHTML = lastpath;
}
/**
 * 设置文件图标
 * @param {} creadeNodesJson
 * @param {} tdNode1
 */
TableList.prototype.setFileFlag = function(creadeNodesJson, tdNode1) {
	if (creadeNodesJson.isFile == "true") {
		var classStyle = (creadeNodesJson.name).split(".");
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
				tdNode1.style.backgroundImage = "url(folderImg.jpg)";
				break;
		}
	}
	else {
		tdNode1.style.backgroundImage = "url(folderImg.jpg)";
		tdNode1.setAttribute("name", "folder");
	}
}

TableList.prototype.fileLength = function(len) {
	var company = ["B", "KB", "M", "G"];
	var index = 0;
	if (len < 1024) {
		return len + company[0];
	}
	else {
		while (len > 1024 && index < 4) {
			len /= 1024;
			index++;
		}
		return len.toFixed(3) + company[index];
	}
}
/**
 * 排序标准
 * @param {} resultJson
 * @param {} orderType
 * @param {} attr
 */
TableList.prototype.mySort = function(resultJson, orderType, attr) {
	if (orderType < 0) {
		resultJson.sort(function(parmA, parmB) {
			    if (attr == "size") {
				    parmA = isNaN(parseFloat(parmA[attr])) ? 0 : parseFloat(parmA[attr]);
				    parmB = isNaN(parseFloat(parmB[attr])) ? 0 : parseFloat(parmB[attr]);
				    return parmB - parmA;
			    }
			    return (parmA[attr]) > (parmB[attr]) ? -1 : 1;
		    });
	}
	else {
		resultJson.sort(function(parmA, parmB) {
			    if (attr == "size") {
				    parmA = isNaN(parseFloat(parmA[attr])) ? 0 : parseFloat(parmA[attr]);
				    parmB = isNaN(parseFloat(parmB[attr])) ? 0 : parseFloat(parmB[attr]);
				    return parmA - parmB;
			    }
			    return (parmA[attr]) > (parmB[attr]) ? 1 : -1;
		    });
	}
}

/**
 * 对传进的json数组按json属性排序
 * @param {} list
 * @param {} resultJson
 * @param {} orderType
 */
TableList.prototype.sortByType = function(resultJson, orderType) {
	var spanOne = document.getElementById("childMenu").childNodes[1].childNodes[1];
	var spanTwo = document.getElementById("childMenu").childNodes[3].childNodes[1];
	var spanThree = document.getElementById("childMenu").childNodes[5].childNodes[1];
	switch (Math.abs(orderType)) {
		case 1 :
			resultJson.sort(tableList.mySort(resultJson, orderType, "name"));
			if (orderType < 0) {
				spanTwo.innerHTML = "";
				spanThree.innerHTML = "";
				spanOne.innerHTML = "&#8593;";
			}
			else {
				spanTwo.innerHTML = "";
				spanThree.innerHTML = "";
				spanOne.innerHTML = "&#8595;";
			}
			break;
		case 3 :
			resultJson.sort(tableList.mySort(resultJson, orderType, "size"));
			if (orderType < 0) {
				spanOne.innerHTML = "";
				spanThree.innerHTML = "";
				spanTwo.innerHTML = "&#8593;";
			}
			else {
				spanOne.innerHTML = "";
				spanThree.innerHTML = "";
				spanTwo.innerHTML = "&#8595;";
			}
			break;
		case 5 :
			resultJson.sort(tableList.mySort(resultJson, orderType, "date"));
			if (orderType < 0) {
				spanOne.innerHTML = "";
				spanTwo.innerHTML = "";
				spanThree.innerHTML = "&#8593;";
			}
			else {
				spanOne.innerHTML = "";
				spanTwo.innerHTML = "";
				spanThree.innerHTML = "&#8595;";
			}
			break;
		default :
			break;
	}
	return resultJson;
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
TableList.prototype.appendNodes = function(list, resultJson, orderType) {
	var resultArr = resultJson.callback;
	resultArr = tableList.sortByType(resultArr, orderType);
	var length = resultArr.length;
	for (var index = 0; index < length; index++) {
		var trNode = document.createElement("tr");
		var tdNode1 = document.createElement("td");
		var creadeNodesJson = resultArr[index];
		var creadeNodesJsonName = creadeNodesJson.name;
		tableList.setFileFlag(creadeNodesJson, tdNode1)
		var tdNode2 = document.createElement("td");
		var tdNode3 = document.createElement("td");
		var textNode1 = document.createTextNode(creadeNodesJsonName);
		var textNode3 = document.createTextNode(creadeNodesJson.date);
		tdNode1.appendChild(textNode1);
		if (creadeNodesJson.size != "") {
			var textNode2 = document.createTextNode(tableList.fileLength(creadeNodesJson.size));
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
TableList.prototype.showList = function(resultStr, orderType) {
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
	tableList.appendNodes(list, resultJson, orderType);
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
TableList.prototype.ajaxRequest = function(url, methodtype, parameter, funType, orderType) {
	var xhr = tableList.getajaxHttp();
	var data = null;
	var stringParameter = JSON.stringify(parameter);
	xhr.onreadystatechange = state_change;
	url = url + "/" + stringParameter;
	// if (methodtype == "GET") {
	// }else{
	// data=stringParameter;
	// }
	xhr.open(methodtype, url, true);
	xhr.send(data);
	function state_change() {
		if (xhr.readyState == 4 && status == 0) {
			if (xhr.responseText != null || xhr.responseText != "") {
				switch (funType) {
					case "watch" :
						tableList.showList(xhr.responseText, orderType);
						break;
					case "delete" :
						var path = document.getElementById("source").innerHTML;
						alert(xhr.responseText);
						tableList.init(path);
						break;
					case "file" :
						document.getElementById("file_txt").value = xhr.responseText;
						break;
					case "update" :
						var path = document.getElementById("source").innerHTML;
						alert(xhr.responseText);
						tableList.init(path);
						break;
					case "create" :
						var path = document.getElementById("source").innerHTML;
						alert(xhr.responseText);
						tableList.init(path);
						break;
					default :
						alert("未知错误！");
						break;
				}
			}
		}
	};
}

/**
 * 页面初始化
 */
TableList.prototype.init = function(path) {
	if (path == "" || path == null) {
		path = "";
	}
	var jsonParame = {
		"type" : "watch",
		"name" : "",
		"path" : path,
		"isFile" : "",
		"context" : ""
	}
	tableList.ajaxRequest("http://127.0.0.1:8080", "GET", jsonParame, "watch");
}

TableList.prototype.fileCreate = function(flag) {
	var fileNameNode = document.getElementById("createFileName");
	var fileNameValue = fileNameNode.value;
	if (fileNameValue.indexOf("/") != -1) {
		alert("名称不合格");
		return;
	}
	var select = document.getElementById("select");
	var index = select.selectedIndex;
	var value = select.options[index].value;
	var path = document.getElementById("source").innerHTML.substring(1);
	var jsonParame = {
		"type" : "create",
		"name" : fileNameValue,
		"path" : path,
		"isFile" : value,
		"context" : ""
	}
	tableList.ajaxRequest("http://127.0.0.1:8080", "GET", jsonParame, "create");
	fileNameNode.value="";
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
	// 点击创建文件（夹）
	document.getElementById("fileCreateBtn").onclick = tableList.fileCreate;
}

window.onload = function() {
	tableList.init();
	setTimeout(tableList.addEvent, 500);
}
