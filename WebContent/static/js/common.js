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