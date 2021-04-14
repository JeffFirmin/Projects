	
	var p1 = document.getElementById("landing-paragraphe1");
	var p2 = document.getElementById("landing-paragraphe2");
	var p3 = document.getElementById("landing-paragraphe3");
	var p4 = document.getElementById("landing-paragraphe4");
	
	window.onscroll = function () {
		if (document.documentElement.scrollTop > 100) {
			document.getElementById("landing-header").style.transform = "translateY(-70px)"; 
		} else {
			document.getElementById("landing-header").style.transform = "translateY(0px)";
		}
		if (document.documentElement.scrollTop > 300) {
			document.getElementById("bg-img").style.filter = "brightness(50%)";
		} else {
			document.getElementById("bg-img").style.filter = "brightness(80%)";
		}
		animateElementWhenVisible( p1 );
		animateElementWhenVisible( p2 );
		animateElementWhenVisible( p3 );
		animateElementWhenVisible( p4 );
	}
	
	// Return boolean when an element is visible on screen
    function isVisible ( element ) {
        var viewPortHeight = window.innerHeight, // Viewport Height
        	scrollTop = document.documentElement.scrollTop, // Scroll Top
            currElementPosY = element.offsetTop,
            elementHeight = element.offsetHeight;
        /*console.log("vph: " + viewPortHeight);
        console.log("st: " + scrollTop);
        console.log("cepy: " + currElementPosY);
        console.log("eh: " + elementHeight);*/
         
        return ( currElementPosY + elementHeight > scrollTop && currElementPosY < ( viewPortHeight + scrollTop ) )
    }
    
    // Animate chart only when you see it
    function animateElementWhenVisible ( element ) {
	    if ( !isVisible( element )  ) {
	        element.classList.add("show-paragraphe");
	    }
    }