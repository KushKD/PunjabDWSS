function screen_de(id,nele){
	this.id=id;	
	this.nele=nele;
}
function screen(){this.screen = new Object();}
screen.prototype.addscreen = function(nd){
	if(typeof(nd) != "undefined"){
		if( typeof(this.screen["SCREEN"]) == "undefined"){
			this.screen["SCREEN"] = new screen_de(nd.getAttribute("id"),nd.getAttribute("nele"));			
		}
	}
}