function screen_element_de(id,neve){
	this.id=id;	
	this.neve=neve;
}
function screen_element(){this.screen_element = new Object();}
screen_element.prototype.addscreen_element = function(nd){
	if(typeof(nd) != "undefined"){
		if( typeof(this.des["SCREEN-ELEMENT"]) == "undefined"){
			this.screen_element["SCREEN-ELEMENT"] = new screen_element_de(nd.getAttribute("id"),nd.getAttribute("neve"));			
		}
	}
}