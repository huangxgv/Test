var TableList = function() {
}

var SortType = function() {
}

var sortType = new SortType();
sortType.SORTBYNAMEASC = 0;
sortType.SORTBYNAMEDESC = 1;
sortType.SORTBYSIZEASC = 2;
sortType.SORTBYSIZEDESC = 3;
sortType.SORTBYDATEASC = 4;
sortType.SORTBYDATEDESC = 5;
var tableList = new TableList();

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
 * @param {} trArr
 */
TableList.prototype.doubleClick = function(tr) {
	var type = "";
	var isFile = false;
	tr.ondblclick = function() {
		var fileSizeFlag = tr.childNodes[1].innerHTML;
		if (fileSizeFlag == "") {
			type = "watch";
		}
		else {
			if (!confirm("确认下载文件?")) {
				return;
			}
			else {
				type = "download";
				isFile = true;
			}
		}
		var thisName = this.childNodes[0].innerHTML;
		var jsonParame = {
			"type" : type,
			"name" : "",
			"path" : thisName,
			"isFile" : isFile,
			"context" : ""
		}
		tableList.ajaxRequest("http://127.0.0.1:8080", "GET", jsonParame, 1);
		if (!isFile) {
			document.getElementById("source").innerHTML += (thisName + "/");
		}
	}
}
/**
 * 右键菜单
 * @param {} menu
 * @param {} trArr
 */
TableList.prototype.menuList = function(menu, trArr) {
	for (var i = 0, len = trArr.length; i < len; i++) {
		trArr[i].oncontextmenu = function(e) {
			var thisName = this.childNodes[0].innerHTML;
			var liElementArr = menu.getElementsByTagName("li");
			liElementArr[0].onclick = function() {
			}
			liElementArr[1].onclick = function() {
				alert(1)
			}
			liElementArr[2].onclick = function() {
				alert(1)
			}
			liElementArr[3].onclick = function() {
				alert(1)
			}
			menu.onclick = function() {
				var e = e || window.event;
				menu.style.display = "none";
			};
			var e = e || window.event;
			var oX = e.clientX;
			var oY = e.clientY;
			menu.style.display = "block";
			menu.style.left = oX + "px";
			menu.style.top = oY + "px";
			return false;
		}
		trArr[i].onclick = function() {
			var e = e || window.event;
			menu.style.display = "none";
		};
	}
}
/**
 * 返回按钮点击事件
 */
TableList.prototype.retBtn = function() {
	var menu = document.getElementById("menu");
	menu.style.display = "none";
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
TableList.prototype.appendNodes = function(list, resultJson, sortType) {
	var tdArr = new Array();
	switch (sortType) {
		case 0 :
			tdArr.sort(function(pramA, pramB) {
				    return pramB[0].localeCompare(pramA[0]);
			    });
			break;
		case 1 :
			tdArr.sort(function(pramA, pramB) {
				    return pramA[0].localeCompare(pramB[0]);
			    });
			break;
		case 2 :
			tdArr.sort(function(pramA, pramB) {
				    return pramB[1].localeCompare(pramA[1]);
			    });
			break;
		case 3 :
			tdArr.sort(function(pramA, pramB) {
				    return pramA[1].localeCompare(pramB[1]);
			    });
			break;
		case 4 :
			tdArr.sort(function(pramA, pramB) {
				    return pramB[2].localeCompare(pramA[2]);
			    });
			break;
		case 5 :
			tdArr.sort(function(pramA, pramB) {
				    return pramA[2].localeCompare(pramB[2]);
			    });
			break;
		default :
			break;
	}
	var resultArr = resultJson.callback;
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
TableList.prototype.showList = function(resultStr, sortType) {
	var resultJson;
	var list = document.getElementById("bodyList");
	var resultJson = JSON.parse(resultStr);
	// if (resultStr != "") {
	// var fso = new ActiveXObject(Scripting.FileSystemObject);
	// var f = fso.createtextfile("C:\a.txt", 2, true);
	// f.writeLine(resultStr);
	// f.close();
	// }
	// else {
	// return;
	//	}
	if (list.childNodes.length != 0) {
		tableList.cleanTableList();
	}
	if(resultJson.length==0){
		return;
	}
	var list = document.getElementById("bodyList");
	tableList.appendNodes(list, resultJson, sortType);
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
TableList.prototype.ajaxRequest = function(url, methodtype, parameter, orderType) {
	var xhr = tableList.getajaxHttp();
	var stringParameter = JSON.stringify(parameter);
	xhr.onreadystatechange = state_change;
	xhr.open(methodtype, url + "/" + stringParameter, true);
	xhr.send();
	function state_change() {
		if (xhr.readyState == 4 && status == 0) {
			if (xhr.responseText != null || xhr.responseText != "") {
				tableList.showList(xhr.responseText, orderType);
			}
		}
	};
}
/**
 * 页面初始化
 */
TableList.prototype.init = function(orderType) {
	var jsonParame = {
		"type" : "watch",
		"name" : "",
		"path" : "/",
		"isFile" : "",
		"context" : ""
	}
	var resultStr = tableList.ajaxRequest("http://127.0.0.1:8080", "GET", jsonParame, orderType);
}

TableList.prototype.clickToOrder = function(flag) {
	switch (flag) {
		case 0 :
			tableList.init(sortType.SORTBYNAMEASC);
			flag = sortType.SORTBYNAMEASC;
			break;
		case 1 :

			break;
		case 2 :
			tableList.init(sortType.SORTBYSIZEASC);
			flag = sortType.SORTBYSIZEASC;
			break;
		case 3 :
			tableList.init(sortType.SORTBYSIZEDESC);
			flag = sortType.SORTBYSIZEDESC;
			break;
		case 4 :
			tableList.init(sortType.SORTBYDATEASC);
			flag = sortType.SORTBYDATEASC;
			break;
		case 5 :
			tableList.init(sortType.SORTBYDATEDESC);
			flag = sortType.SORTBYDATEDESC;
			break;
		default :
			break;
	}
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
	var flag = sortType.SORTBYNAMEASC;
	tableList.init(sortType.SORTBYNAMEASC);
	setTimeout(tableList.addEvent, 500);
}
