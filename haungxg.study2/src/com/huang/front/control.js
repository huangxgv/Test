window.onload = function() {
	var TableList = function() {
	}

	var tableList = new TableList();

	TableList.prototype.init = function() {
		var jsonParame = {
			"type" : "watch",
			"name" : "",
			"path" : "D:/",
			"isFile" : "",
			"context" : ""
		}
		var resultStr = ajaxRequest("http://127.0.0.1:8080","GET",jsonParame);
//		alert(resultStr);
		// showList(resultStr);
	}
	tableList.init();
	TableList.prototype.showList = function(resultStr) {
		var resultArr = resultStr.split(":");
		for (var i = 0, length = resultArr.length; i < length; i++) {

		}
	}
}
