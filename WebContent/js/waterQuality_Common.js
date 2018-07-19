var labName = {

	getLabName : function() {
		alert('---------------getLabName-----');

		$.ajax({
					type : "POST",
					url : "GetFilterValues.to?lab='+labName+'&method=fetchLabName','lab'",
					success : function(data) {
						alert('data-------------'+data);
						setLabName(data);
					},
					error : function(e) {
						// showStickyErrorToast(e);
					}
				});
	}
}

function setLabName(data){
	 // $('#labid').val('sdfsdfsd');
	document.getElementById('labid').value = "gjg";

	var fff=document.getElementById('labid').value;
console.log('document.getElementByI.value----'+fff)
	alert('lab name=----------'+fff);
}
