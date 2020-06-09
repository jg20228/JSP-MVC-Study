var isCheckedUsername = false;

// 6.4
// 최근에는 함수를 변수화 시켜서 사용함
/*
 * const goPopup = function(){ window.open("/blog/juso/jusoPopup.jsp", "pop",
 * "width=570,height=420, scrollbars=yes, resizable=yes"); }
 */

// 람다식
/*
 * const goPopup = () =>{ window.open("/blog/juso/jusoPopup.jsp", "pop",
 * "width=570,height=420, scrollbars=yes, resizable=yes"); }
 */



function validate() {
	if (!isCheckedUsername) {
		alert('username 중촉 체크를 해 주세요');
	}
	return isCheckedUsername;
}

function usernameCheck() {
	var tfUsername = $('#username').val();

	// ajax
	$.ajax({
		type : 'get',
		url : `/blog5/user?cmd=usernameCheck&username=$tfUsername`
	}).done(function(result) {
		console.log(result);
		if (result == 1) {
			alert('아이디가 중복되었습니다.');
		} else if (result == 0) {
			alert('사용하실 수 있는 아이디 입니다.');
			isCheckedUsername = true;
		} else {
			console.log('develop : 서버 오류');
		}
	}).fail(function(error) {
		console.log(error);
	});
}
