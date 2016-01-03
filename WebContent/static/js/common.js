function pagination(url,pageNo,pageSize){
	if($('#queryForm')){
		$('#queryForm').attr('action',url+"?pageNo="+pageNo+"&pageSize="+pageSize);
		$('#queryForm').submit();
	}
}

function detail(url) {
	window.location = url;
}

function update(url) {
	window.location = url;
}

function del(url) {
	window.location = url;
}

var _callback ;
function confirmOpt(showCenter,callback){
	_callback = callback;
	$(".confirm_container").show();
	$("#centerShow").html(showCenter);
}
function confirm(){
	_callback();
	close();
}
function close(){
	$('.theme-popover-mask').fadeOut(100);
	$('.theme-popover').slideUp(200);
}
function cancel(){
	close();
}