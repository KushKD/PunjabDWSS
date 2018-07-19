
var gAutoPrint = true; 
String.prototype.trim = function() {
	return this.replace(/^\s+|\s+$/g,"");
};
String.prototype.ltrim = function() {
	return this.replace(/^\s+/,"");
};
String.prototype.rtrim = function() {
	return this.replace(/\s+$/,"");
};
function triggerEvent(el, type){if ((el[type] || false) && typeof el[type] == 'function'){el[type](el);}}

// usage: BetterInnerHTML(element, HTML, clearfirst)
// element	- the DOM element where the HTML will be loaded
// HTML		- the string of valid HTML code
// clearfirst	- if false, existing child nodes of the element will be retained

function BetterInnerHTML(o, p, q) {
	function r(a) {
		var b;
		if (typeof DOMParser != "undefined")
			b = (new DOMParser()).parseFromString(a, "application/xml");
		else {
			var c = [ "MSXML2.DOMDocument", "MSXML.DOMDocument",
					"Microsoft.XMLDOM" ];
			for ( var i = 0; i < c.length && !b; i++) {
				try {
					b = new ActiveXObject(c[i]);
					b.loadXML(a);
				} catch (e) {
				}
			}
		}
		return b;
	}
	function s(a, b, c) {
		a[b] = function() {
			return eval(c);
		};
	}
	function t(b, c, d) {
		if (typeof d == "undefined")
			d = 1;
		if (d > 1) {
			if (c.nodeType == 1) {
				var e = document.createElement(c.nodeName);
				var f = {};
				for ( var a = 0, g = c.attributes.length; a < g; a++) {
					var h = c.attributes[a].name, k = c.attributes[a].value, l = (h
							.substr(0, 2) == "on");
					if (l)
						f[h] = k;
					else {
						switch (h) {
						case "class":
							e.className = k;
							break;
						case "for":
							e.htmlFor = k;
							break;
						default:
							e.setAttribute(h, k);
						}
					}
				}
				b = b.appendChild(e);
				for (l in f)
					s(b, l, f[l]);
			} else if (c.nodeType == 3) {
				var m = (c.nodeValue ? c.nodeValue : "");
				var n = m.replace(/^\s*|\s*$/g, "");
				if (n.length < 7
						|| (n.indexOf("") != 0 && n.indexOf("") != (n.length - 3)))
					b.appendChild(document.createTextNode(m));
			}
		}
		for ( var i = 0, j = c.childNodes.length; i < j; i++)
			t(b, c.childNodes[i], d + 1);
	}
	p = "<root>" + p + "</root>";
	var u = r(p);
	if (o && u) {
		if (q != false)
			while (o.lastChild)
				o.removeChild(o.lastChild);
		t(o, u.documentElement);
	}
}
	function replace(s,t,by) {
		s=String(s);
		var sl = s.length, tl = t.length;
		if ((sl == 0) || (tl == 0)) return s;
		var i = s.indexOf(t);
		if ((!i) && (t != s.substring(0,tl))) return s;
		if (i == -1) return s;
		var nwS = s.substring(0,i) + by;
		if (i+tl < sl) nwS += replace(s.substring(i+tl,sl),t,by);
		return nwS;
	}
	function select_innerHTML(objeto,innerHTML){
		/******
		* select_innerHTML - corrige o bug do InnerHTML em selects no IE
		* Veja o problema em: http://support.microsoft.com/default.aspx?scid=kb;en-us;276228
		* Vers�o: 2.1 - 04/09/2007
		* Autor: Micox - N�iron Jos� C. Guimar�es - micoxjcg@yahoo.com.br
		* @objeto(tipo HTMLobject): o select a ser alterado
		* @innerHTML(tipo string): o novo valor do innerHTML
		*******/
		    objeto.innerHTML = "";
		    var selTemp = document.createElement("micoxselect");
		    var opt;
		    selTemp.id="micoxselect1";
		    document.body.appendChild(selTemp);
		    selTemp = document.getElementById("micoxselect1");
		    selTemp.style.display="none";
		    if(innerHTML.toLowerCase().indexOf("<option")<0){//se n�o � option eu converto
		        innerHTML = "<option>" + innerHTML + "</option>";
		    }
		    innerHTML = innerHTML.replace(/<option/g,"<span").replace(/<\/option/g,"</span");
		    selTemp.innerHTML = innerHTML;
		      
		    
		    for(var i=0;i<selTemp.childNodes.length;i++){
		  var spantemp = selTemp.childNodes[i];
		  
		        if(spantemp.tagName){     
		            opt = document.createElement("OPTION");
		    
		   if(document.all){ //IE
		    objeto.add(opt);
		   }else{
		    objeto.appendChild(opt);
		   }       
		    
		   //getting attributes
		   for(var j=0; j<spantemp.attributes.length ; j++){
		    var attrName = spantemp.attributes[j].nodeName;
		    var attrVal = spantemp.attributes[j].nodeValue;
		    if(attrVal){
		     try{
		      opt.setAttribute(attrName,attrVal);
		      opt.setAttributeNode(spantemp.attributes[j].cloneNode(true));
		     }catch(e){}
		    }
		   }
		   //getting styles
		   if(spantemp.style){
		    for(var y in spantemp.style){
		     try{opt.style[y] = spantemp.style[y];}catch(e){}
		    }
		   }
		   //value and text
		   opt.value = spantemp.getAttribute("value");
		   opt.text = spantemp.innerHTML;
		   opt.text=replace(opt.text,"&gt;",">");
		   //IE
		   opt.selected = spantemp.getAttribute('selected');
		   opt.className = spantemp.className;
		  } 
		 }    
		 document.body.removeChild(selTemp);
		 selTemp = null;
		}
function ajaxFunction(url, targetElementName) {
	var xmlHttp;
	var browser = navigator.appName;
	if (window.XMLHttpRequest) {
		 xmlHttp = new XMLHttpRequest();
       } else if (window.ActiveXObject) {
           xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
       }
	xmlHttp.open("POST", url, false);
	xmlHttp.send(null);
	
	if (xmlHttp.readyState == 4) {
		if (xmlHttp.status == 200) {
			var optionContent = xmlHttp.responseText;
			if(browser == 'Microsoft Internet Explorer'){
				var targetOption = document.getElementById(targetElementName);
				//targetOption.options.length = 0;
				
				select_innerHTML(targetOption,optionContent);
				//document.all[targetOption].innerHTML=optionContent;
			}else{
				var targetOption = document.getElementById(targetElementName);
				
				targetOption.innerHTML=optionContent;
			}
		}
	}
}
	
	function ajaxFunctionForText(url, targetElementName) {
		var xmlHttp;
		
		var browser = navigator.appName;
		 if (window.XMLHttpRequest) {
			 xmlHttp = new XMLHttpRequest();
	       } else if (window.ActiveXObject) {
	           xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
	       }
		xmlHttp.open("POST", url, false);
		xmlHttp.send(null);
		
		if (xmlHttp.readyState == 4) {
			if (xmlHttp.status == 200) {
				var optionContent = xmlHttp.responseText;
				if(browser == 'Microsoft Internet Explorer'){
					var targetOption = document.getElementById(targetElementName);
					//targetOption.options.length = 0;
					targetOption.value = optionContent;
					//document.all[targetOption].innerHTML=optionContent;
				}else{
					var targetOption = document.getElementById(targetElementName);
					targetOption.value = optionContent;
				}
			}
		}
	}
	
	function ajaxPopulate(url) {
		var xmlHttp;
		var browser = navigator.appName;

		if (window.XMLHttpRequest) {
			xmlHttp = new XMLHttpRequest();
		} else if (window.ActiveXObject) {
			xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		xmlHttp.open("POST", url, false);
		xmlHttp.send(null);	
		if (xmlHttp.readyState == 4) {
			if (xmlHttp.status == 200) {
				var optionContent = xmlHttp.responseText;
				var fData=optionContent.split('~');					
				for (i=0;i<fData.length;i++){
					//alert(fData[i].split('@!')[0]+":"+fData[i].split('@!')[1]);
					if(document.getElementById(fData[i].split('@!')[0]).type=='radio')
						document.getElementById(fData[i].split('@!')[0]).checked=true;
					else document.getElementById(fData[i].split('@!')[0]).value=fData[i].split('@!')[1];
				}

			}
		}	
	}
function de_enable(d__enable){
	var field_type;
//alert("de_enable:"+d__enable +":"+Req);
	//alert('hiiiiiiiiii');

	if(Req!="rpt"){
		var d__els=null;
		if(document.getElementsByTagName("form")[0]!=null || document.getElementsByTagName("form")[0]!=undefined)
			d__els = document.getElementsByTagName("form")[0].elements;	 
		else return;
		for(d__i=0; d__i<d__els.length; d__i++) {
			if(d__els[d__i].type==null || d__els[d__i].type==undefined)field_type="";
			else field_type = d__els[d__i].type.toLowerCase();
			switch(field_type) {
				case "search":
				case "text":
				case "password":
				case "textarea":
				case "hidden":
					d__els[d__i].disabled=!d__enable;
					break;
				case "radio":
				case "checkbox":d__els[d__i].disabled=!d__enable;
					break;
				case "select-one":
				case "select-multi":
					d__els[d__i].disabled=!d__enable;
					break;
				default:
					break;
			}		
		}
	}
}
function de_enable3(d__enable){
	var field_type;
//alert("de_enable:"+d__enable +":"+Req);
	//alert('hiiiiiiiiii');

	if(Req!="rpt"){
		var d__els=null;
		if(document.getElementsByTagName("form")[0]!=null || document.getElementsByTagName("form")[0]!=undefined)
			d__els = document.getElementsByTagName("form")[0].elements;	 
		else return;
		for(d__i=0; d__i<d__els.length; d__i++) {
			if(d__els[d__i].type==null || d__els[d__i].type==undefined)field_type="";
			else field_type = d__els[d__i].type.toLowerCase();
			switch(field_type) {
				case "search":
				case "text":
					d__els[d__i].disabled=!d__enable;
				
				default:
					break;
			}		
		}
	}
}


function de_enable1(d__enable){
	var field_type;
//alert("de_enable:"+d__enable +":"+Req);
	//alert('ihiiihihihihihi');
	//alert("document.getElementsByTagName[0]-----------------"+document.getElementsByTagName("form")[0]);
		var d__els=null;
		//if(document.getElementsByTagName("form")[0]!=null || document.getElementsByTagName("form")[0]!=undefined){
		//	alert("inside-----")
			d__els = document.getElementsByTagName("form")[0].elements;	 
		//	alert("d__els-------------"+d__els)
		//}
		//else return;
		for(d__i=0; d__i<d__els.length; d__i++) {
			if(d__els[d__i].type==null || d__els[d__i].type==undefined)field_type="";
			else{
				field_type = d__els[d__i].type.toLowerCase();
			
			//alert("field_type--------------"+field_type);
			switch(field_type) {
				case "textarea":
					d__els[d__i].disabled=!d__enable;
					break;
				case "radio":
				case "checkbox":d__els[d__i].disabled=!d__enable;
					break;
				case "select-one":
				case "select-multi":
					d__els[d__i].disabled=!d__enable;
					break;
				default:
					break;
			}
			}
		}
}
function de_kyenable(d__enable,d__key){
	//alert("de_kyenable: "+d__enable+":"+d__key+";"+d__els.id); 
	if(Req!="rpt"){
		var d__ky=d__key.split('@');
		for(d__i=0; d__i<d__ky.length; d__i++) {
			if(document.getElementById(d__ky[d__i])!=null || document.getElementById(d__ky[d__i])!=undefined){
				var d__els=document.getElementById(d__ky[d__i]);
				//alert("de_kyenable: "+d__enable+":"+d__key+";"+d__els.id); 
				d__els.disabled=!d__enable;						
			}
		}
	}
}
function de_autoenable(d__enable,d__auto){
//	alert("de_autoenable: "+d__enable+":"+d__auto);
	//if(Req!="rpt"){
		var d__auto=d__auto.split('@');
		for(d__i=0; d__i<d__auto.length; d__i++) {
	//			alert("1:"+d__auto[d__i]);
			if(document.getElementById(d__auto[d__i])!=null || document.getElementById(d__auto[d__i])!=undefined){
				var d__els=document.getElementById(d__auto[d__i]);
	//			alert("2:"+d__auto[d__i]);
				d__els.disabled=!d__enable;
			}
		}
	//}
}
function de_autoenable2(d__enable,d__auto){
//	alert("de_autoenable: "+d__enable+":"+d__auto);
	//if(Req!="rpt"){
		var d__auto=d__auto.split('@');
		for(d__i=0; d__i<d__auto.length; d__i++) {
	//			alert("1:"+d__auto[d__i]);
			if(document.getElementById(d__auto[d__i])!=null || document.getElementById(d__auto[d__i])!=undefined){
				var d__els=document.getElementById(d__auto[d__i]);
	//			alert("2:"+d__auto[d__i]);
				d__els.disabled=!d__enable;
			}
		}
	//}
}
function de_initStyle(d__ky){
	if(Req!="rpt"){
		var field_type;
		var d__els=null;
		if(document.getElementsByTagName("form")[0]!=null || document.getElementsByTagName("form")[0]!=undefined)
			d__els = document.getElementsByTagName("form")[0].elements;	 
		else return;
		var d__ky=d__ky.split('@');
		for(d__i=0; d__i<d__ky.length; d__i++) {
			if(document.getElementById(d__ky[d__i])!=null || document.getElementById(d__ky[d__i])!=undefined){
				var d__els=document.getElementById(d__ky[d__i]);
				if(d__els.disabled)
					d__els.style.background='#F2F2F2';
				else
					d__els.style.background='#FFFF99';
			}
		}
	//	var d__enable=true;
	//	if(d__enable==undefined || d__enable==null) d__enable=true;
		for(d__i=0; d__i<d__els.length; d__i++) {
			if(d__els[d__i].type==null || d__els[d__i].type==undefined)field_type="";
			else field_type = d__els[d__i].type.toLowerCase();
			switch(field_type) {
				case "search":	
				case "text":
				case "password":
				case "textarea":
				case "hidden":
					d__els[d__i].disabled=!d__enable;
				if(d__els[d__i].disabled)
					d__els[d__i].style.background='#F2F2F2';
				else
					d__els[d__i].style.background='#FFFFFF';
					break;
				case "radio":
				case "checkbox":d__els[d__i].disabled=!d__enable;
					break;
				case "select-one":
				case "select-multi":
					d__els[d__i].disabled=!d__enable;
					if(d__els[d__i].disabled)
						d__els[d__i].style.background='#F2F2F2';
					else
						d__els[d__i].style.background='#FFFFFF';
					break;
				default:
					break;
			}
			//document.getElementByClassName('.select-checkbox').disable=true;
		}
	}	
}

function de_init(d__ky,d__auto){
//	alert("de_init:"+Req+":"+d__ky+":"+d__auto+":"+level2);
	add_optionbutton();
	//alert("-------------d__ky--------------"+d__ky+"------------d__auto----------------"+d__auto);
	
//	alert('de_init---------d__ky-------'+d__ky+"d__auto------------"+d__auto);
	if(d__ky==undefined || d__ky===null || d__auto===null){
			de_enable(false);
		}else{
		if(Req==undefined || Req==null || Req=='ent') {
			de_enable(false);
		}
		if(level2){
			if(Req=='ent_inquire') {de_enable(true);de_kyenable(false,d__ky,d__auto);}
			if(Req=='ent_delete') {de_enable(false);de_kyenable(false,d__ky,d__auto);}
			if(Req=='ent_post') {de_enable(false);de_kyenable(false,d__ky,d__auto);}
			if(Req=='ent_modify'){de_enable(true);de_kyenable(false,d__ky,d__auto);}
			if(Req=='ent_update'){de_enable(true);de_kyenable(false,d__ky,d__auto);}
			if(Req=='ent_repost'){de_enable(true);de_kyenable(false,d__ky,d__auto);}
			if(Req=='prc') {de_enable(true);}
			if(Req=='rpt'||d__mode=='ent_add') {de_enable(true);}
			if(Req=='rpt'||d__mode=='ent_qrcode') {
				de_enable3(true);
				}
		}else{		
			if(Req=='ent_inquire') {de_enable(false);de_kyenable(true,d__ky,d__auto);}
			if(Req=='ent_delete') {de_enable(false);de_kyenable(true,d__ky,d__auto);}
			if(Req=='ent_post') {de_enable(false);de_kyenable(true,d__ky,d__auto);}
			if(Req=='ent_modify') {de_enable(false);de_kyenable(true,d__ky,d__auto);}
			if(Req=='ent_update') {de_enable(false);de_kyenable(true,d__ky,d__auto);}
			if(Req=='ent_repost') {de_enable(false);de_kyenable(true,d__ky,d__auto);}
			//if(Req=='ent_modify' && (!level2 || level2==undefined || level2==null)){de_enable(false);de_kyenable(true,d__ky);}
			if(Req=='prc') {de_enable(true);}
			if(Req=='rpt'||d__mode=='ent_add'||d__mode=='ent_modify') {de_enable(true);de_autoenable(false,d__auto);}
			if(Req=='rpt'||d__mode=='ent_qrcode') {
				de_enable3(true);
				}
			if(d__mode=='ent_frwrd'){
				de_enable1(true);
			}
		}
	}
	de_initStyle(d__ky);
}

function hide_ctrl(ele_nms,d__hdeq){
	var d__lst=ele_nms.split("~"),d__i=0;
	for (d__i=0;d__i<d__lst.length;d__i++)
		document.getElementById(d__lst[d__i]).style.display=(eval(d__hdeq)?"none":"");
}
function disable_ctrl(ele_nms,d__hdeq){
	var d__lst=ele_nms.split("~"),d__i=0;
	for (d__i=0;d__i<d__lst.length;d__i++){
		document.getElementById(d__lst[d__i]).disabled=eval(d__hdeq);
		//document.getElementById(d__lst[d__i]).style.display=(eval(d__hdeq)?"none":"");
	}
}

function null_ctrl(ele_nms){
	var d__1st=ele_nms.split('$'),d__i=0;
	for(d__i=0;d__i<d__1st.length;d__i++){
		document.getElementById(d__1st[d__i]).value="";
	}
	
}
function readOnly_ctrl(ele_nms,d__hdeq){
	var d__lst=ele_nms.split("~"),d__i=0;
	for (d__i=0;d__i<d__lst.length;d__i++){
		var obj=document.getElementById(d__lst[d__i]);
		if(obj==null || obj==undefined)
			obj=document.getElementById(d__lst[d__i]);
			obj.readOnly=eval(d__hdeq);
	}
}

function displayModal(){
	 var html="";
	 html+="<div id='myModal' class='modal fade' role='dialog'>";
	 html+= "<div class='modal-dialog'>";
	 html+="<div class='modal-content'>";
	 html+="<div class='modal-header'>";
	 html+="<button type='button' class='close' data-dismiss='modal'>&times;</button>";
	 html+="<h4 class='modal-title'>Alert</h4>";
	 html+=" </div>";
	 html+="<div class='modal-body'>";
	 html+=" <p id='p1'></p> </div>";
	 html+="<div class='modal-footer'>";
	 html+="<button type='button' class='btn btn-default' data-dismiss='modal'>Close</button>";
	 html+="</div>";
	 html+="</div>";
	 html+="</div>";
	 html+="</div>"; 
	 
	//alert(html);
	 document.getElementById('modalContainer').innerHTML=html;
	// var ss=document.getElementById('modalContainer').innerHTML;
	 
	//	alert(ss);
	// $("#container").html(html);
	 
}

function displayMessage(e){
	
	 if(e===true){
		 displayModal();
	 }
}
function readOnly_ctrl_nb(ele_nms,d__hdeq){
	var d__lst=ele_nms.split("~"),d__i=0;
	for (d__i=0;d__i<d__lst.length;d__i++){
		var obj=document.getElementById(d__lst[d__i]);
		if(obj!='' ||obj!='0.00' )
			obj=document.getElementById(d__lst[d__i]);
		obj.readOnly=eval(d__hdeq);
	}
}



function getCheckedValue(radio) {
	var radioObj=document.getElementsByName(radio);
	if(!radioObj)
		return "";
	var radioLength = radioObj.length;
	if(radioLength == undefined)
		if(radioObj.checked)
			return radioObj.value;
		else
			return "";
	for(var i = 0; i < radioLength; i++) {
		if(radioObj[i].checked) {	
			return radioObj[i].value;
		}
	}
	return "";
}
	
function setCheckedValue(radioObj, newValue) {
	if(!radioObj)
		return;
	var radioLength = radioObj.length;
	if(radioLength == undefined) {
		radioObj.checked = (radioObj.value == newValue.toString());
		return;
	}
	for(var i = 0; i < radioLength; i++) {
		radioObj[i].checked = false;
		if(radioObj[i].value == newValue.toString()) {
			radioObj[i].checked = true;
		}
	}
}
function printSpecial()
{
	if (document.getElementById != null)
	{
		var html = '<HTML>\n<HEAD>\n';

		if (document.getElementsByTagName != null)
		{
			var headTags = document.getElementsByTagName("head");
			if (headTags.length > 0)
				html += headTags[0].innerHTML;
		}
		
		html += '\n</HE' + 'AD>\n<BODY>\n';
		
		var printReadyElem = document.getElementById("printReady");
		
		if (printReadyElem != null)
		{
				html += printReadyElem.innerHTML;
		}
		else
		{
			alert("Could not find the printReady section in the HTML");
			return;
		}
			
		html += '\n</BO' + 'DY>\n</HT' + 'ML>';
		
		var printWin = window.open("","printSpecial");
		printWin.document.open();
		printWin.document.write(html);
		printWin.document.close();
		if (gAutoPrint)
			printWin.print();
	}
	else
	{
		alert("Sorry, the print ready feature is only available in modern browsers.");
	}
}
function tabSwitch_2(active, number, tab_prefix, content_prefix) {   
    
    for (var i=1; i < number+1; i++) {   
      document.getElementById(content_prefix+i).style.display = 'none';   
      document.getElementById(tab_prefix+i).className = '';   
    }   
    document.getElementById(content_prefix+active).style.display = 'block';   
    document.getElementById(tab_prefix+active).className = 'active';       
  
}
function validateKey(d__e,d__ob,d__cm){
//            var d__key=d__e.keyCode? d__e.keyCode : d__e.charCode;

var d__key=d__e.charCode? d__e.charCode : d__e.keyCode;

				var d__dp=d__cm.split("@"),d__f=d__ob.value;
								//alert("a: "+d__cm+":"+d__key+":"+d__cm.indexOf('9@'));
                if(d__cm=="l" || d__cm=='m'){                                
                                if(!(phKey(d__key) || numKey(d__key) || genKey(d__key))) return false;                                                        
                                
                }
                if((d__cm.indexOf('9@')>=0) && ((d__cm.split("@")).length==3)){                         
								 if(!genKey(d__key)){
				 					
									if(!(decKey(d__key))){
										//alert("1a: "+d__cm+":"+d__key);
										return false;                         
									}
									else{
									//alert(d__f.substring(d__f.indexOf('.'), d__f.length)+":"+d__dp[2]);
													if (d__f.indexOf('.') == -1) d__f += ".";   
													if ((d__f.substring(0,d__f.indexOf('.'))).length >d__dp[1]-1) {return false; }
													if((d__f.substring(d__f.indexOf('.'), d__f.length)).length > d__dp[2]) {return false;}                      
									}
					}
                }
                if((d__cm.indexOf('9@')>=0) && ((d__cm.split("@")).length==2)){         
                                if(!genKey(d__key)){
                                                if(!(numKey(d__key))) return false;                       
                                                else if (d__f.length+1>d__dp[1]) return false; 
                                }
                                
                }
                if(d__cm.indexOf('c@')>=0 || d__cm.indexOf('C@')>=0){
                                if(!genKey(d__key)){
                                                if(!(charKey(d__key))) return false;        
                                                else if (d__f.length+1>d__dp[1]) return false; 
                                }
                                
                }
                if(d__cm.indexOf('a@')>=0 || d__cm.indexOf('A@')>=0){          
                                if(!genKey(d__key)){
                                                if(!(charKey(d__key) || numKey(d__key))) return false;              
                                                else if (d__f.length+1>d__dp[1]) return false; 
                                }
                                
                }
                if(d__cm.indexOf('a+@')>=0 || d__cm.indexOf('A+@')>=0){                     
                                if(!genKey(d__key)){
                                                if(!(charKey(d__key) || numKey(d__key) || splKey(d__key))) return false;       
                                                else if (d__f.length+1>d__dp[1]) return false; 
                                }
                                
                }
                if(d__cm.indexOf('c+@')>=0 || d__cm.indexOf('C+@')>=0){                      
                                if(!genKey(d__key)){
                                                if(!(charKey(d__key) || splKey(d__key))) return false; 
                                                else if (d__f.length+1>d__dp[1]) return false; 
                                }
                                
                }              
                
}

function genKey(d__key){if(d__key==8 || d__key==9 || (d__key>=37 && d__key<=40) || d__key==46) return true;return false;}
function numKey(d__key){if(d__key>=48 && d__key<=57) return true;return false;}
function decKey(d__key){if((d__key>=48 && d__key<=57) || d__key==46) return true;return false;}
function phKey(d__key){if((d__key>=65 && d__key<=90) || (d__key>=43 && d__key<=46)) return true;return false;}
function charKey(d__key){
							var d__p=d__key.charCode? d__key.charCode : d__key.keyCode;
							if((d__p>=65 && d__p<=90) || (d__p>=97 && d__p<=122) || (d__p==32))
								return true;
							alert("Enter Character Only");
							return false;
						}
function splKey(d__key){if((d__key>=33 && d__key <=47) || (d__key>=59 && d__key<=64) || (d__key>=91 && d__key<=96) || (d__key>=123 && d__key<=126) || d__key==32) return true;return false;}
function validateEmail(obj){
var x=obj.value;
var atpos=x.indexOf("@");
var dotpos=x.lastIndexOf(".");
if (atpos<1 || dotpos<atpos+2 || dotpos+2>=x.length)
  {
  alert("Not a valid e-mail address");
  return false;
  }
}
function isleap(id)
{
 var yr=id;
 if ((parseInt(yr)%4) == 0)
 {
  if (parseInt(yr)%100 == 0)
  {
    if (parseInt(yr)%400 != 0)
    {
//    alert("Not Leap");
    return "false";
    }
    if (parseInt(yr)%400 == 0)
    {
    //alert("Leap");
    return "true";
    }
  }
  if (parseInt(yr)%100 != 0)
  {
    //alert("Leap");
    return "true";
  }
 }
 if ((parseInt(yr)%4) != 0)
 {
    //alert("Not Leap");
    return "false";
 } 
}
function inList(psString, psList){
	var laList = psList.split(',');
	var i = laList.length;
	while (i--){
		if (laList[i] === psString) return true;
	}
	return false; 
} 
function addEvent( obj, type, fn ) {
	  if ( obj.attachEvent ) {
	    obj['e'+type+fn] = fn;
	    obj[type+fn] = function(){obj['e'+type+fn]( window.event );}
	    obj.attachEvent( type, obj[type+fn] );
	  } else
	    obj.addEventListener( type, fn, false );
	}
function removeEvent( obj, type, fn ) {
	  if ( obj.detachEvent ) {
	    obj.detachEvent( type, obj[type+fn] );
	    obj[type+fn] = null;
	  } else
	    obj.removeEventListener( type, fn, false );
}
function getBrowType() {
  if (document.layers) return "NS";
  if (document.all) return "IE";
  if (document.getElementById) return "MOZ";
  return "OTHER";
}
function getXMLDoc(sXML) {
	var bt=getBrowType(),xmlDoc;	
	if(bt=='IE'){
		xmlDoc = new ActiveXObject("Microsoft.XMLDOM");
		xmlDoc.loadXML(sXML);
	}else{
		var parser = new DOMParser();
		xmlDoc = parser.parseFromString(sXML, "application/xml");	
	}		
	return xmlDoc;
}
function send_request(url) {
//	alert("URL: "+url);
	var xmlHttp;
	var browser = navigator.appName;
	if (window.XMLHttpRequest) {
		 xmlHttp = new XMLHttpRequest();
       } else if (window.ActiveXObject) {
           xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
       }
	xmlHttp.open("POST", url, false);
	xmlHttp.send(null);
	
	if (xmlHttp.readyState == 4) {
		if (xmlHttp.status == 200) {
			callBack_screen(xmlHttp);
		}
	}	
}
function dateWorkFlowValidation(dLbl,dVal){
	//alert(1);
	var i=0;
	for(i=0;i<dVal.length-1;i++){
	
		var previous = dVal[i].split("-");
		var next = dVal[i+1].split("-");
		//Compare year 
		if(next[2]<previous[2]){
			//alert('year are not equals');
			alert(dLbl[i]+" should be older than "+dLbl[i+1]);
			return false;
			// other wise comparing month
		}else if(next[2]==previous[2]){
			
			if(next[1]<previous[1]){
				
			//	alert('month are not  equals');
				alert(dLbl[i]+" should be older than "+dLbl[i+1]);
				return false;
			}else if(next[1]==previous[1]){   // if month are equal
				//alert('month are equals');
				if(next[0]<previous[0]){ // comparing dates 
				//	alert('dates are not  equals');
					alert(dLbl[i]+" should be older than "+dLbl[i+1]);
					return false;
				}
			}
		}
	}
	return true;
}

function dateWorkFlowValidationForcons(dLbl,dVal){
	//alert(1);
	var i=0;
	var j=2;
	
	
		//
	for(i=0;i<dVal.length-1;i++){
		if(dLbl[i+1]=="Bank NOC Date For Shortlisting"){
	      
		  var previous = dVal[i].split("-");
		  var next = dVal[i+2].split("-"); 
		  if(next[2]<previous[2]){
			//alert('year are not equals');
			alert(dLbl[i]+" should be older than "+dLbl[i+2]);
			return false;
			// other wise comparing month
		}else if(next[2]==previous[2]){
			
			if(next[1]<previous[1]){
				
			//	alert('month are not  equals');
				alert(dLbl[i]+" should be older than "+dLbl[i+2]);
				return false;
			}else if(next[1]==previous[1]){   // if month are equal
				//alert('month are equals');
				if(next[0]<previous[0]){ // comparing dates 
				//	alert('dates are not  equals');
					alert(dLbl[i]+" should be older than "+dLbl[i+2]);
					return false;
				}
			 }
		   }
	      }
		
		else {
			var previous = dVal[i].split("-");
			var next = dVal[i+1].split("-");
				//Compare year 
				if(next[2]<previous[2]){
					//alert('year are not equals');
					alert(dLbl[i]+" should be older than "+dLbl[i+1]);
					return false;
					// other wise comparing month
				}
				else if(next[2]==previous[2]){
					
					if(next[1]<previous[1]){
						
					//	alert('month are not  equals');
						alert(dLbl[i]+" should be older than "+dLbl[i+1]);
						return false;
					}else if(next[1]==previous[1]){   // if month are equal
						//alert('month are equals');
						if(next[0]<previous[0]){ // comparing dates 
						//	alert('dates are not  equals');
							alert(dLbl[i]+" should be older than "+dLbl[i+1]);
							return false;
						}
					}
				}
			  }
			}
		
	return true;
	
}
function callBack_screen(xmlHttp){
	var d__i=0, d__iu, d__Nd,d__resp; 		
	d__resp=xmlHttp.responseText;
	xmlDeDoc=getXMLDoc(d__resp);
	d__iu=xmlDeDoc.documentElement.childNodes.length;	
	for(d__i=0;d__i<d__iu;d__i++){
		d__Nd=xmlDeDoc.documentElement.childNodes[d__i];
		if(d__Nd.nodeName=='SCREEN')ode.addscreen(d__Nd);
		if(d__Nd.nodeName=='SCREEN-ELEMENT') odesel.addscreen_element(d__Nd);
	}
	for(var i=1;i<=ode.screen["SCREEN"].no;i++){
		var obj=document.getElementById(odesel.screen_element[String(i)].id);
		//alert(i+":"+odesel.screen_element[String(i)].fn);
		var x=replace(odesel.screen_element[String(i)].fn,'this',obj);
		//alert("x: "+x);
		if(obj.attachEvent){
			obj.attachEvent(odesel.screen_element[String(i)].type,function(){
			//eval(x);
				eval(validateKey(event,	obj,'9@50@2'));
				//validateKey(event,	obj,'a@50');
				}
				);
		}else {
			//alert("Firefox");
			obj.addEventListener(odesel.screen_element[String(i)].type,
				function(){
					eval(validateKey(event,	obj,'9@50@2'));
				}
				,false
			);
		}/*
		
		obj.onkeypress=function(){
					eval(validateKey(event,	obj,'9@50@2'));
				};*/
	}
	
}

 function isNumber(evt) {
    var iKeyCode = (evt.which) ? evt.which : evt.keyCode
            if (iKeyCode != 46 && iKeyCode > 31 && (iKeyCode < 48 || iKeyCode > 57))
                return false;

            return true;
        }  
 function validateKey1(d__e,d__ob,d__cm){
//   var d__key=d__e.keyCode? d__e.keyCode : d__e.charCode;

var d__key=d__e.charCode? d__e.charCode : d__e.keyCode;

		var d__dp=d__cm.split("@"),d__f=d__ob.value;
						//alert("a: "+d__cm+":"+d__key+":"+d__cm.indexOf('9@'));
       if(d__cm=="l" || d__cm=='m'){                                
                       if(!(phKey(d__key) || numKey(d__key) || genKey1(d__key))) return false;                                                        
                       
       }
       if((d__cm.indexOf('9@')>=0) && ((d__cm.split("@")).length==3)){                         
						 if(!genKey1(d__key)){
		 					
							if(!(decKey(d__key))){
								//alert("1a: "+d__cm+":"+d__key);
								return false;                         
							}
							else{
							//alert(d__f.substring(d__f.indexOf('.'), d__f.length)+":"+d__dp[2]);
											if (d__f.indexOf('.') == -1) d__f += ".";   
											if ((d__f.substring(0,d__f.indexOf('.'))).length >d__dp[1]-1) {return false; }
											if((d__f.substring(d__f.indexOf('.'), d__f.length)).length > d__dp[2]) {return false;}                      
							}
			}
       }
       if((d__cm.indexOf('9@')>=0) && ((d__cm.split("@")).length==2)){         
                       if(!genKey1(d__key)){
                                       if(!(numKey(d__key))) return false;                       
                                       else if (d__f.length+1>d__dp[1]) return false; 
                       }
                       
       }
       if(d__cm.indexOf('c@')>=0 || d__cm.indexOf('C@')>=0){
                       if(!genKey1(d__key)){
                                       if(!(charKey(d__key))) return false;        
                                       else if (d__f.length+1>d__dp[1]) return false; 
                       }
                       
       }
       if(d__cm.indexOf('a@')>=0 || d__cm.indexOf('A@')>=0){          
                       if(!genKey1(d__key)){
                                       if(!(charKey(d__key) || numKey(d__key))) return false;              
                                       else if (d__f.length+1>d__dp[1]) return false; 
                       }
                       
       }
       if(d__cm.indexOf('a+@')>=0 || d__cm.indexOf('A+@')>=0){                     
                       if(!genKey1(d__key)){
                                       if(!(charKey(d__key) || numKey(d__key) || splKey(d__key))) return false;       
                                       else if (d__f.length+1>d__dp[1]) return false; 
                       }
                       
       }
       if(d__cm.indexOf('c+@')>=0 || d__cm.indexOf('C+@')>=0){                      
                       if(!genKey1(d__key)){
                                       if(!(charKey(d__key) || splKey(d__key))) return false; 
                                       else if (d__f.length+1>d__dp[1]) return false; 
                       }
                       
       }              
       
}
 
 
 function genKey1(d__key){if(d__key==8 || d__key==9  || d__key==46) return true;return false;}
 
 