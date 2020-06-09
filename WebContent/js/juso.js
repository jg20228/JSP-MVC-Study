// footer보다는 밑에 두지 말아야한다.
function goPopup() {
	var pop = window.open("/blog5/juso/jusoPopup.jsp", "pop",
			"width=570,height=420, scrollbars=yes, resizable=yes");
}
function jusoCallBack(address) {
	// document.form.address.value = address; - 구방식
	// document.getElementById("address").value = address; - 내가한거

	// 요즘 방식
	var tfAddress = document.querySelector("#address");
	tfAddress.value = address;
}