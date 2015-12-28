function checkCategory(categoryID) {
	var ctx = $('#ctx').val();
	$.post(ctx + "/back/interface/getInterfaces", {
		Action : "get",
		cid : categoryID
	}, function(data, textStatus) {
		var options = "<option value='0'>请选择接口名称</option>";
		var jsondatas = eval("(" + data + ")");
		$.each(jsondatas, function(i, n) {
			str = "";
			str = "<option value='" + n.id + "'>" + n.name + "</option>";
			options = options + str;
		})
		$('#itemID').html(options);
	});
}

function checkItem(itemID) {
	var ctx = $('#ctx').val();
	if (itemID == '0') {
		$('#iForm').html("");
	} else {
		$.post(ctx + "/back/interface/getInterface", {
			Action : "get",
			id : itemID
		}, function(data, textStatus) {
			$('#iForm').html(data);
		});
		$.post(ctx + "/back/interface/getIItemDescn", {
			id : itemID
		}, function(data, textStatus) {
			var html = "";
			var fields = eval(data.fieldDescn);
			var errorResult = data.errorResult;
			var successResult = data.successResult;
			if(errorResult && errorResult.length > 0){
				$('#errorResult').html(errorResult);
			}
			
			if(successResult && successResult.length > 0){
				$('#successResult').html(successResult);
			}
			
			
			if(fields && fields.length > 0){
				for(var i = 0; i < fields.length; i++){
					var field = fields[i];
					html += "<tr>" +
								"<td>"+field.fieldName+"</td>" +
								"<td>"+field.fieldType+"</td>" +
								"<td>"+field.fieldDescn+"</td>" +
							"</tr>";
				}
			}else{
				html = "<tr align='center'>"+
				"<td colspan='3'>暂无数据</td>"+
				"</tr>";
			}
			$('#fieldDescn').html(html);
		},'json');
		
	}
	// else {
	//		
	//		
	// }
	// $.post(ctx+"/i/getIItem", {Action:"get",id:itemID}, function (data,
	// textStatus){
	// $('#iForm').html(data);

	// });

}

function formatJson(json, options) {
    var reg = null,
        formatted = '',
        pad = 0,
        PADDING = '    '; // one can also use '\t' or a different number of spaces
  
    // optional settings
    options = options || {};
    // remove newline where '{' or '[' follows ':'
    options.newlineAfterColonIfBeforeBraceOrBracket = (options.newlineAfterColonIfBeforeBraceOrBracket === true) ? true : false;
    // use a space after a colon
    options.spaceAfterColon = (options.spaceAfterColon === false) ? false : true;
  
    // begin formatting...
    if (typeof json !== 'string') {
        // make sure we start with the JSON as a string
        json = JSON.stringify(json);
    } else {
        // is already a string, so parse and re-stringify in order to remove extra whitespace
        json = JSON.parse(json);
        json = JSON.stringify(json);
    }
  
    // add newline before and after curly braces
    reg = /([\{\}])/g;
    json = json.replace(reg, '\r\n$1\r\n');
  
    // add newline before and after square brackets
    reg = /([\[\]])/g;
    json = json.replace(reg, '\r\n$1\r\n');
  
    // add newline after comma
    reg = /(\,)/g;
    json = json.replace(reg, '$1\r\n');
  
    // remove multiple newlines
    reg = /(\r\n\r\n)/g;
    json = json.replace(reg, '\r\n');
  
    // remove newlines before commas
    reg = /\r\n\,/g;
    json = json.replace(reg, ',');
  
    // optional formatting...
    if (!options.newlineAfterColonIfBeforeBraceOrBracket) {        
        reg = /\:\r\n\{/g;
        json = json.replace(reg, ':{');
        reg = /\:\r\n\[/g;
        json = json.replace(reg, ':[');
    }
    if (options.spaceAfterColon) {         
        reg = /\:/g;
        json = json.replace(reg, ':');
    }
  
    $.each(json.split('\r\n'), function(index, node) {
        var i = 0,
            indent = 0,
            padding = '';
  
        if (node.match(/\{$/) || node.match(/\[$/)) {
            indent = 1;
        } else if (node.match(/\}/) || node.match(/\]/)) {
            if (pad !== 0) {
                pad -= 1;
            }
        } else {
            indent = 0;
        }
  
        for (i = 0; i < pad; i++) {
            padding += PADDING;
        }
  
        formatted += padding + node + '\r\n';
        pad += indent;
    });
    return formatted;
}


function submitParameters() {
	var url = $('#url').val();
	var rest = $(".rest");
	if(rest!=null&&rest.length>0){
		rest.each(function(i,e){
			url += "/"+$(this).val()
		});
	}
	var method = $("input[name='requestMethod']:checked").val();
	if(method=="get"){
		$.get(url, $("#interfaceForm").serialize(), function(data, textStatus) {
			if(typeof(data)=='object')
				$('#result').html(formatJson(JSON.stringify(data)));
			else
				$('#result').html(data);
		});
	}else{
		$.post(url, $("#interfaceForm").serialize(), function(data, textStatus) {
			if(typeof(data)=='object')
				$('#result').html(formatJson(JSON.stringify(data)));
			else
				$('#result').html(data);
		});
	}
	

}
