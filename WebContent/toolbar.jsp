<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" autoFlush="true"%>
<%@page
	import="org.apache.taglibs.standard.lang.jstl.test.PageContextImpl"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.TreeMap"%>

<%@taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@page import="java.awt.print.Pageable"%>
<%
//try{
	String Rpt = (String) request.getAttribute("Rpt");
	String op = (String) request.getAttribute("op");
	String menu_id=(String)request.getAttribute("menuId");
	
	if(menu_id==null)menu_id=(String)session.getAttribute("menuId");
	

	System.out.println("menu_id: "+menu_id);
	@SuppressWarnings("unchecked")
	Map<String, String> loginUserPermissionBeans = (TreeMap<String,String>)session.getAttribute("menuPermission");
	if(op==null)op=loginUserPermissionBeans.get(menu_id);
	String d__mode = (String) request.getAttribute("d__mode");
	String d__ky = (String) request.getAttribute("d__ky");
	String d__auto = (String) request.getAttribute("d__auto");
	String level2 = (String) request.getAttribute("level2");
	if (d__mode == null || d__mode.equals(""))
		d__mode = "";
	
	
//}
//catch(Exception e){
//e.printStackTrace();
//}
%>
<SCRIPT LANGUAGE=javascript>
//alert("d__mode------------------"+d__mode);

Req='<%=Rpt%>';
menu_id='<%=menu_id%>';
//alert(menu_id);
level2=<%=level2%>;
d__mode="<%=d__mode%>";
if(d__mode!="")Req=d__mode;
op="<%=op%>";

d__ky='<%=d__ky%>';
d__auto='<%=d__auto%>';
function setModeDisplay(){
	
	var d__modedisplay="";
	if (d__mode == null || d__mode == undefined)
		d__modedisplay = "";
	else {
		
		if (d__mode == "ent_inquire")
			d__modedisplay = "Mode:<B> Inquire</B>";
		if (d__mode == "ent_add")
			d__modedisplay = "Mode: <B>Add</B>";
		if (d__mode == "ent_modify")
			d__modedisplay = "Mode: <B>Modify</B>";
		if (d__mode == "ent_delete")
			d__modedisplay = "Mode:<B> Delete</B>";
		if (d__mode == "ent_post")
			d__modedisplay = "Mode:<B> Post</B>";
		if (d__mode == "ent_repost")
			d__modedisplay = "Mode:<B>Re-Post</B>";
		if (d__mode == "ent_qrcode")
			d__modedisplay = "Mode:<B> Generate QR</B>";
			
		if (d__mode == "ent_update"){
			d__modedisplay = "Mode:<B> Modify</B>";
		}
		
		if (d__mode == "ent_forward"){
			d__modedisplay = "Mode:<B> Forward</B>";
		}
	}
		BetterInnerHTML(document.getElementById("td_mode"),d__modedisplay);
}
function de_fresh(){
	document.getElementsByClassName('.select-checkbox').disable=true;
	var elements=document.getElementsByTagName("form")[0];
	for(i=0; i<elements.length; i++) {
		if(elements[i].type==null || elements[i].type==undefined)field_type="";
		else field_type = elements[i].type.toLowerCase();
		switch(field_type) {
		
		case "text":
			case "password":
			case "textarea":
			case "hidden":
				elements[i].value = "";
				break;
			case "radio":
			case "checkbox":
				if (elements[i].checked) 
				elements[i].checked = false;
				break;
			case "select-one":
			case "select-multi":
				elements[i].selectedIndex = -1;
				break;
			default:
				break;
		}

	} 
}
function add_optionbutton(){

	m__tlbr="<div class='table_scrbtnclk pull-right' ><div id='l__tlbr' border='1' class='td_scrbtnclk'>";
	/* <div class='table_scrbtnclk pull-right' ><div id='l__tlbr' border='1' class='td_scrbtnclk'>" */
	if(Req=="rpt"){
		if(can_view(op)) m__tlbr+=add_button("javascript:requestHandler('rpt_show');de_view();","view","View");
		if(can_export(op)) m__tlbr+=get_rpttlbrFile();
		//if(can_graph(op)) m__tlbr+=add_button("javascript:requestHandler('rpt_graph');","graph","Graph");
		m__tlbr+=get_rtlbr();
	}
	if (Req=="ent") {		
		
		if(can_inquire(op)) m__tlbr+=add_button("javascript:requestHandler('ent_inquire'); accesskey='1' ","inquire","Inquire");
		if(can_add(op)) m__tlbr+=add_button("javascript:requestHandler('ent_add'); accesskey='2' ","add","Add");
		if(can_modify(op)) m__tlbr+=add_button("javascript:requestHandler('ent_modify'); accesskey='3' ","modify","Modify");
		if(can_delete(op)) m__tlbr+=add_button("javascript:requestHandler('ent_delete'); accesskey='4' ","delete","Delete");
		if(can_post(op)) m__tlbr+=add_button("javascript:requestHandler('ent_post'); accesskey='5' ","post","Authorised");
		if(can_repost(op)) m__tlbr+=add_button("javascript:requestHandler('ent_repost'); accesskey='6' ","repost","Authorised");
		if(can_qrcode(op)) m__tlbr+=add_button("javascript:requestHandler('ent_qrcode'); accesskey='7' ","qrcode","Generate QR");
		if(can_update(op)) m__tlbr+=add_button("javascript:requestHandler('ent_update'); accesskey='8' ","modify","Modify");
		if(can_forward(op)) m__tlbr+=add_button("javascript:requestHandler('ent_forward'); accesskey='9' ","forward","Forward");
		m__tlbr+=get_rtlbr();
	}	
	if(!level2){		
		if (Req=="prc") m__tlbr+=get_proctlbr();
		if (Req=="rpt_file" || Req=="rpt_graph") m__tlbr+=get_rpttlbrFile();
		if (Req=="rpt_show" ) m__tlbr+=get_rpttlbrShow();
		if (Req=="ent_inqfind") m__tlbr+=get_inqftlbr();
		if (Req=="ent_inquire") m__tlbr+=get_inqtlbr();	
		if (Req=="ent_add") m__tlbr+=get_addtlbr();
		if (Req=="ent_modify") m__tlbr+=get_modtlbr();	
		if (Req=="ent_delete") m__tlbr+=get_deltlbr();	
		if (Req=="ent_post") m__tlbr+=get_posttlbr();
		if (Req=="ent_repost") m__tlbr+=get_reposttlbr();
		if (Req=="ent_qrcode") m__tlbr+=get_qrcodetlbr();
		if (Req=="oth") m__tlbr+=get_rtlbr();
		if (Req=="ent_update") m__tlbr+=get_updatetlbr();
		if (Req=="ent_forward") m__tlbr+=add_forwardbtn();
		
	}else{		
		if (Req=="prc") m__tlbr+=get_proctlbr(true);
		if (Req=="rpt_file" || Req=="rpt_graph") m__tlbr+=get_rpttlbrFile(true);
		if (Req=="rpt_show" ) m__tlbr+=get_rpttlbrShow(true);
		if (Req=="ent_inqfind") m__tlbr+=get_inqftlbr();
		if (Req=="ent_inquire") m__tlbr+=get_inqtlbr();	
		if (Req=="ent_add") m__tlbr+=get_addtlbr();
		if (Req=="ent_modify") m__tlbr+=get_modtlbr(true);	
		if (Req=="ent_delete") m__tlbr+=get_deltlbr(true);	
		if (Req=="ent_post") m__tlbr+=get_posttlbr(true);
		if (Req=="ent_repost") m__tlbr+=get_reposttlbr(true);
		if (Req=="ent_qrcode") m__tlbr+=get_qrcodetlbr();
		
		if (Req=="oth") m__tlbr+=get_rtlbr(true);	
		if (Req=="ent_update") m__tlbr+=get_updatetlbr(true);
		if (Req=="ent_forward") m__tlbr+=add_forwardbtn();
	}	
	m__tlbr+="</div></div>";
	document.getElementById("dispTag")!=undefined?document.getElementById("dispTag").style.visibility="Visible":"";
	document.getElementById("dv_btn").innerHTML=m__tlbr;
	setModeDisplay();
}
function chTbar(d__mode){
	Req=d__mode;
	level2=true;
	if(d__ky==undefined || d__ky===null){}else de_init(d__ky);
	setModeDisplay();
	de_init();
	document.getElementById("dispTag").style.visibility="Hidden";
}
function get_inqtlbr(){return add_backbtn()+add_findbtn()+add_freshbtn()+get_rtlbr();}
function get_inqftlbr(){return add_backbtn()+get_rtlbr();}
function get_qrcodetlbr(){return add_backbtn()+add_generatebtn()+add_freshbtn()+get_rtlbr();}
function get_qrcodetlbr(){return add_backbtn()+add_generatebtn()+add_freshbtn()+get_rtlbr();}


function get_addtlbr(){ return add_backbtn()+add_savebtn(false)+add_freshbtn()+get_rtlbr();}
function get_modtlbr(m__isfind){
/* 	if (m__isfind==undefined) return add_backbtn()+add_findbtn()+add_freshbtn()+get_rtlbr();
	else 		return add_backbtn()+(!m__isfind?add_findbtn():add_savebtn(true))+add_freshbtn()+get_rtlbr();	 */	
	return add_backbtn()+(add_savebtn(true))+add_freshbtn()+get_rtlbr();
	}
function get_updatetlbr(m__isfind){
		if (m__isfind==undefined) return add_backbtn()+add_findbtn()+add_freshbtn()+get_rtlbr();
		else 		return add_backbtn()+(!m__isfind?add_findbtn():add_savebtn(true))+add_freshbtn()+get_rtlbr();	 	
		return add_backbtn()+(add_savebtn(true))+add_freshbtn()+get_rtlbr();
		}
function get_deltlbr(m__isfind){
	if (m__isfind==undefined) return add_backbtn()+add_findbtn()+add_freshbtn()+get_rtlbr();
	else 		return add_backbtn()+(!m__isfind?add_findbtn():add_delbtn())+add_freshbtn()+get_rtlbr();		
	}
function get_posttlbr(m__isfind){
	if (m__isfind==undefined) return add_backbtn()+add_findbtn()+add_freshbtn()+get_rtlbr();
	else 		return add_backbtn()+(!m__isfind?add_findbtn():add_confbtn())+add_freshbtn()+get_rtlbr();		
	}
function get_proctlbr(m__isfind){
	if (m__isfind==undefined) return add_backbtn()+add_retbtn()+add_freshbtn()+get_rtlbr();
	else return add_backbtn()+(!m__isfind?add_retbtn():add_procbtn())+add_freshbtn()+get_rtlbr();		
}
function get_reposttlbr(m__isfind){
	if (m__isfind==undefined) return add_backbtn()+add_findbtn()+add_freshbtn()+get_rtlbr();
	else 		return add_backbtn()+(!m__isfind?add_findbtn():add_savebtn(true))+add_freshbtn()+get_rtlbr();		
	}

function get_rpttlbrFile(){
	if(menu_id=='RPT003')
		return add_button("javascript:de_fileExcel();","excel","Excel")+get_rtlbr();
	else if(menu_id=='RPT004')
		return add_button("javascript:de_filePDF();","pdf","PDF")+get_rtlbr();
	else if(menu_id=='WQ010')
		return add_button("javascript:de_filePDF();","pdf","PDF")+get_rtlbr();
	else 
		return add_button("javascript:de_fileExcel();","excel","Excel")+add_button("javascript:de_filePDF();","pdf","PDF")+get_rtlbr();
}
function get_rpttlbrShow(){
	return add_button("javascript:history.go(-1);","back","Back")+add_button("javascript:de_filePrint();","print","Print")+get_rtlbr();
}
function get_rtlbr(){//return add_button("zentools/jsp/LogOut.jsp","logout","Logout","R")+add_button("zentools/jsp/Profile.jsp","profile","Profile","R")+add_button("<%=request.getContextPath()%>/mLayout.do?&Rpt=oth~Screen","home","Home","R");
		return "";
	}
	function add_backbtn() {
			return add_button(			
				"javascript:document.getElementsByTagName('form')[0].reset();history.go(-1);",
				"back", "Back");
	}
	function add_generatebtn() {
		return add_button("javascript:de_qrcode(); accesskey='11' ", "download",
				"Download");
	}
	
	function add_forwardbtn() {
		return add_button("javascript:de_forward(); accesskey='12' ", "forward",
				"Forward");
	}
	function add_findbtn() {
		return add_button("javascript:de_find(); accesskey='9' ", "find",
				"Find");
	}
	function add_freshbtn() {
		return add_button("javascript:de_fresh();", "fresh", "fresh");
	}
	function add_savebtn(isDel) {
		if(d__mode=='ent_repost'){
			return add_button("javascript:de_repost(); accesskey='10'", "save",
			"Save");
		}else{
			if (isDel)
				return add_button("javascript:de_modify(); accesskey='10'", "save",
						"Save");
			else
				return add_button("javascript:de_add(); accesskey='6'", "save",
						"Save");
		}
	}
	function add_delbtn() {
		return add_button("javascript:de_remove(); accesskey='7'", "delete",
				"Delete");
	}
	function add_confbtn() {
		return add_button("javascript:de_confirm(); accesskey='8'", "confirm",
				"Confirm");
	}
	function add_button(m__href, m__img, m__alt, m__align) {
		//if(m__img=='pdf') return '';//to be remove later
	
		if (m__img == 'mail')
			return '';//to be remove later
		//if(m__img=='excel') return '';//to be remove later
		if (m__img == 'profile')
			return '';//to be remove later
		if (m__align == undefined)
			m__align = "left";
		else
			m__align = "right";
			//return "<A href="+m__href+"><IMG SRC='images/"+m__img+".gif' border='0' ALIGN="+m__align+" ALT='"+m__alt+"'></A>";
			//alert("m__img----------"+m__img);
		return "<A href="+m__href+"><button type='button' value='"+m__alt+"' id='"+m__alt+"' class='btn btn-default'>"+m__alt+"</button></A>";
		
	/* 	<A href="+m__href+"><IMG SRC='images/"+m__img+".gif' border='0' ALIGN="+m__align+" ALT='"+m__alt+"'></A>";
	 */
	 }
	function add_retbtn() {
		return add_button("javascript:de_retrieve();", "retrieve", "Retrieve");
	}
	function add_procbtn() {
		return add_button("javascript:de_proc();", "process", "Process");
	}
	function add_repostbtn() {
		return add_button("javascript:de_repost();", "repost", "Re-Post");
	}
	function can_inquire(op) {
		if (op.charAt(0) == "t")
			return true;
	}
	function can_add(op) {
		if (op.charAt(1) == "t")
			return true;
	}
	function can_modify(op) {
		if (op.charAt(2) == "t")
			return true;
	}
	function can_delete(op) {
		if (op.charAt(3) == "t")
			return true;
	}
	function can_post(op) {
		if (op.charAt(4) == "t")
			return true;
	}
	function can_view(op) {
		if (op.charAt(5) == "t")
			return true;
	}
	function can_file(op) {
		if (op.charAt(6) == "t")
			return true;
	}
	function can_print(op) {
		if (op.charAt(7) == "t")
			return true;
	}
	function can_email(op) {
		if (op.charAt(8) == "t")
			return true;
	}
	function can_export(op) {
		if (op.charAt(9) == "t")
			return true;
	}
	function can_graph(op) {
		if (op.charAt(10) == "t")
			return true;
	}	
	function can_repost(op) {
		//alert("Repost:"+op);
		if (op.charAt(11) == "t"){
			//alert("Repost 22:"+op);
			return true;
		}
	}
	function can_qrcode(op) {
		if (op.charAt(12) == "t")
			return true;
	}
	function can_update(op) {
		if (op.charAt(13) == "t")
			return true;
	}
	function can_forward(op) {
		if (op.charAt(14) == "t")
			return true;
	}
	function requestHandler(s) {
		Req = s;
		if(Req==='ent_update'){
			$("#gridMonthlyProgress").hide();
			$("#addDetails").hide();
		}
		d__mode = s;
		de_init(d__ky,d__auto);
		if(s==='ent_modify'){
			  $('.select-checkbox').prop('disabled', false);
		}
		//add_optionbutton();
	}
	//vfnRegOnLoad("add_optionbutton();");
</SCRIPT>
<html>
<body>

<table>

	<tr id="tr_logo">
		<td id="dv_btn" style="width: 100%; vertical-align: bottom; bottom: 0; "></td>
	</tr>
</table>
</body>
</html>