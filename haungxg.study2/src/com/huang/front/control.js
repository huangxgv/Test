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

// TableList.prototype.sort = function(pramA, pramB, orderBy) {
// if (orderBy) {
// return pramA - pramB;
// }
// else {
// return pramB - pramB;
// }
// }
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
TableList.prototype.appendNodes = function(list, length, resultArr, sortType) {
	var tdArr = new Array();
	for (var i = 0; i < length; i++) {
		tdArr[i] = resultArr[i].split("|");
	}
	switch (sortType) {
		case 0 :
			tdArr.sort(function(pramA, pramB) {
				    return pramA[0].localeCompare(pramB[0]);
			    });
			break;
		case 1 :
			tdArr.sort(function(pramA, pramB) {
				    return pramB[0].localeCompare(pramA[0]);
			    });
			break;
		case 2 :
			tdArr.sort(function(pramA, pramB) {
				    return pramA[1].localeCompare(pramB[1]);
			    });
			break;
		case 3 :
			tdArr.sort(function(pramA, pramB) {
				    return pramB[1].localeCompare(pramA[1]);
			    });
			break;
		case 4 :
			tdArr.sort(function(pramA, pramB) {
				    return pramA[2].localeCompare(pramB[2]);
			    });
			break;
		case 5 :
			tdArr.sort(function(pramA, pramB) {
				    return pramB[2].localeCompare(pramA[2]);
			    });
			break;
		default :
			break;
	}
	for (var index = 0; index < length; index++) {
		var trNode = document.createElement("tr");
		var tdNode1 = document.createElement("td");
		if (tdArr[index][3] == "true") {
			var classStyle = tdArr[index][0].split(".");
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
		var textNode1 = document.createTextNode(tdArr[index][0]);
		var textNode3 = document.createTextNode(tdArr[index][2]);
		tdNode1.appendChild(textNode1);
		if (tdArr[index][1] != "\0") {
			var textNode2 = document.createTextNode(tdArr[index][1]);
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
	var list = document.getElementById("bodyList");
	if (list.childNodes.length != 0) {
		tableList.cleanTableList();
	}
	var list = document.getElementById("bodyList");
	var resultArr = resultStr.split("*");
	var length = resultArr.length;
	tableList.appendNodes(list, length, resultArr, sortType)
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
		"path" : "",
		"isFile" : "",
		"context" : ""
	}
	var resultStr = tableList.ajaxRequest("http://127.0.0.1:8080", "GET", jsonParame, orderType);
}

TableList.prototype.clickToOrder = function(flag) {
	if (flag % 2 == 0) {
		tableList.init(sortType.SORTBYNAMEDESC);
		flag = sortType.SORTBYNAMEDESC;
	}
	else {
		tableList.init(sortType.SORTBYNAMEASC);
		flag = sortType.SORTBYNAMEASC;
	}
}

window.onload = function() {
	var flag = sortType.SORTBYNAMEASC;
	tableList.init(sortType.SORTBYNAMEASC);
	var thName = document.getElementsByTagName("thead")[0].childNodes;
//	alert(thName[1].childNodes[1])
	for (var i = 1; i <= thName.length; i++) {
		thName[i].childNodes[1].onclick = tableList.clickToOrder(i);
	}
}
