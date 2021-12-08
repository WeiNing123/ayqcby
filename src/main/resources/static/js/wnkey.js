/**
 * 字符串加密
 */

/**
 * 获得0-9随机数
 * @returns
 */
function random1(){
	return Math.floor(Math.random()*10);
}

/**
 * 获得伪字符
 * @returns
 */
function key(num){
	var ss = "";
	if(num == 0){
		ss = "7|#|4|c|$|]|2|s|;|q";
	}else if(num == 1){
		ss = "?|0|7|@|h|j|!|6|y|/";
	}else if(num == 2){
		ss = "1|<|8|s|`|p|i|-|=|2";
	}else if(num == 3){
		ss = "9|*|2|b|^|m|&|6|%|d";
	}else if(num == 4){
		ss = "3|?|4|+|l|s|>|8|m|_";
	}else if(num == 5){
		ss = "5|%|b|(|*|2|m|x|3|^";
	}else if(num == 6){
		ss = "l|2|m|j|`|6|f|~|9|{";
	}else if(num == 7){
		ss = "0|>|z|}|v|8|/|?|4|p";
	}else if(num == 8){
		ss = "3|h|r|.|#|s|$|1|,|8";
	}else if(num == 9){
		ss = "=|3|9|c|{|q|2|#|a|!";
	}
	return ss.split("|");
}
/**
 * 加密
 */
function encryption(){
	var yhm = $("#uname").val();
	var kl = $("#upass").val();
	if(yhm != "" && kl != ""){
		$("#uname").val(encryp(yhm));
		$("#upass").val(encryp(kl));
	}
}

/**
 * 加密
 */
function encryp(str){
	if(str != ""){
		var arr1 =  key(random1());
		var arr2 =  str.split("");
		str = "";
		for (var i = 0; i < arr2.length; i++) {
			str += arr2[i] + arr1[random1()];
		}
		var arr3 =  str.split("");
		str = "";
		for (var i = (arr3.length-1); i >= 0; i--) {
			str += arr3[i];
		}
		var arr4 =  str.split("");
		var arr5 =  key(random1());
		str = "";
		for (var i = 0; i < arr4.length; i++) {
			str += arr4[i] + arr5[random1()];
		}
	}
	return str;
}