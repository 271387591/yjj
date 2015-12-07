// JavaScript Document

function animateOrg(){
	var Winwidht=1024,setIn,amLeft = 0,divlength;

	divlength = $('.lb_ul > li').length;
	//鼠标经过停止离开启动
	$('.lb_c').hover(function(){
		clearInterval(setIn);
		$('.lb_left').stop(true,false).animate({'left':'0'},200);
		$('.lb_right').stop(true,false).animate({'right':'0'},200);
	},function(){
		Animate();
		$('.lb_left').stop(true,false).animate({'left':'-48px'},200);
		$('.lb_right').stop(true,false).animate({'right':'-48px'},200);
	});

	//left
	$('.lb_right').click(function(){
		amLeft++;
		if(amLeft >= divlength){amLeft = 0;}
		clickRoll(amLeft);
	});
	//right
	$('.lb_left').click(function(){
		if(amLeft <= 0){amLeft = divlength;}
		amLeft--;
		clickRoll(amLeft);
	});
	//
	function clickRoll(amLeft){
		$('.lb_ul').stop(true,false).animate({'margin-left':-(amLeft*Winwidht)},400);
	}
	//
	function Animate(){
		setIn = setInterval(function(){
			amLeft++;
			if(amLeft >= divlength){
				amLeft = 0 ;
			}
			$('.lb_ul').stop(true,false).animate({'margin-left':-(amLeft*Winwidht)},400);
		},5000);
	}
	Animate();
}
