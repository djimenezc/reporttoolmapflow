function ExposureUtils(){

	function isFloat(value) {
		if(isNaN(value) || value.toString().indexOf(".") < 0) {
			return false;
		} else {
			if(parseFloat(value)) {
				return true;
			} else {
				return false;
			}
		}
	}

	function addCommas(nStr) {
		nStr += '';
		x = nStr.split('.');
		x1 = x[0];
		x2 = x.length > 1 ? '.' + x[1] : '';
		var rgx = /(\d+)(\d{3})/;
		while(rgx.test(x1)) {
			x1 = x1.replace(rgx, '$1' + ',' + '$2');
		}
		return x1 + x2;
	}

	function parseLatLng(str) {
		if(str && str.indexOf(',') > 0) {
			var commaArray = str.trim().split(',');
			if(commaArray.length == 2) {
				var lat = commaArray[0];
				var lng = commaArray[1];
				if(isFloat(lat) && isFloat(lng)) {
					return new OpenLayers.LonLat(lng, lat);
				}
			}
		}
		return null;
	}

	function printDate() {
		var now = new Date();
		var Weekday = new Array("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday");
		var Month = new Array("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December");
		document.write(Weekday[now.getDay()] + ", " + Month[now.getMonth()] + " " + now.getDate() + ", " + now.getFullYear());
	}

	function getDate() {
		var now = new Date();
		var Weekday = new Array("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday");
		var Month = new Array("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December");
		return Weekday[now.getDay()] + ", " + Month[now.getMonth()] + " " + now.getDate() + ", " + now.getFullYear();
	}

	function getDateStamp() {
		var now = new Date();
		return now.getDate() + "_" + now.getMonth() + 1 + "_" + now.getFullYear();
	}

	function calculateDateWithFormat() {
		var d = new Date();
		var curr_date = d.getDate();
		var curr_month = d.getMonth();
		curr_month++;
		var curr_year = d.getFullYear();
		curr_date = curr_date + "-" + curr_month + "-" + curr_year;

		return curr_date;
	}

	// for creating PDF/Excel files
	function post(URL, PARAMS, target) {

		var form = document.createElement("form");

		form.target = target;
		form.action = URL;
		form.method = "POST";
		form.style.display = "none";

		for(var x in PARAMS) {
			var opt = document.createElement("textarea");
			opt.name = x;
			opt.value = PARAMS[x];
			form.appendChild(opt);
		}
		document.body.appendChild(form);

		form.submit();

		return form;
	}

	function addCommas(nStr) {
		nStr += '';
		x = nStr.split('.');
		x1 = x[0];
		x2 = x.length > 1 ? '.' + x[1] : '';
		var rgx = /(\d+)(\d{3})/;
		while(rgx.test(x1)) {
			x1 = x1.replace(rgx, '$1' + ',' + '$2');
		}
		return x1 + x2;
	}

}