<!DOCTYPE html>

<! taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<! taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!-- SomeImages taken from York College of Pennsylvania. The image links directly to the src it was taken from -->


<html>
	<head>
		<title>Individual Student View</title>
		<link rel="stylesheet" href="styling/style.css">
		
		<!-- chart script from developers.google.com -->
		<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
		<script type="text/javascript">
 			google.charts.load('current', {'packages': ['bar']});
 			google.charts.setOnLoadCallback(drawChart);
 			
 			function drawChart() {
  	 		// Create the data table.			
  	 		
  	 		
        	var data = google.visualization.arrayToDataTable(${model.data});
        	
       		// Set chart options
        	var options = {
       			title: '${model.title}',
       			subtitle: '${model.student}',
       			colors: ['#008000'],
       			vAxis: {
       				title: "Time (Hours)",
       				viewWindowMode: 'explcit',
       				viewWindow: {
       					min:0
       				}
       			},
       			hAxis: {
       				title: "Date"
       			}
       		};
        
        	var chart = new google.charts.Bar(document.getElementById('columnchart_hours'));
        	chart.draw(data, google.charts.Bar.convertOptions(options));
 		}
		</script>
	</head>

	<body>
		<!-- Header -->
		<div class="header">
  			<h1>Capstone Activity Tracker</h1>
  			<a href="https://www.ycp.edu/">
  				<img src="https://www.ycp.edu/media/york-website/style-assets-2016/images/york-college-logo-white.svg"
  				id="banner"></img>
			</a>
		</div>

<!-- Navigation Bar -->
		<div class="navbar">
  			<a href="http://localhost:8081/CapstoneActivityTracker/studentView">Home</a>
  			<a href="#">Team</a>
  			<a href="http://localhost:8081/CapstoneActivityTracker/teamView">SubTeam</a>
  			<a href="">Account</a>
  			<a href="http://localhost:8081/CapstoneActivityTracker/index">Sign Out</a>
  			<img src="https://www.ycp.edu/media/york-website/style-assets-2016/images/york-college-shield.svg"
  			  id="logo"></img>
		</div>

<!-- The flexible grid (content) -->
		<div class="row">
  			<div class="side">
    			<h2>${account.firstname}<br>${account.lastname}</h2>
    			<h3>TopTeamName</h3>
    			<div class="nameList">
    				<a href="#">SubTeam1</a>
    				<a href="#">SubTeam2</a>
            		<a href="#">SubTeam3</a>
        			<a href="#">SubTeam4</a>
    			</div>
    			<h4>SubTeam Name</h4>
    			<div class="nameList">
    				<a href="#">Student1</a>
    				<a href="#">Student2</a>
            		<a href="#">Student3</a>
        			<a href="#">Student4</a>
        		</div>
  			</div>
  			<div class="main">
  				<div class="flex-container">
  					<div class="button"><</div>
    				<div class="chart" id="columnchart_hours">CHART HERE</div>
    				<div class="button">></div>
    			</div> 
    			<p>Week Log notes:</p>
    			<div>
    				<p>DATE: Lognote1</p>
        			<p>DATE: Lognote2</p>
    				<p>DATE: Lognote3</p>
    				<p>DATE: Lognote4</p>
        			<p>DATE: Lognote5</p>
        			<p>DATE: Lognote6</p>
        			<p>DATE: Lognote7</p>
    			</div>
  			</div>
  			<!-- Footer -->
			<div class="footer">
 				<div class="flex-container">
  					<div class="footnote">Designed by Jason Steinberg, Travis Wetzel, and William Taylor</div>
    				<div class="footnote">Developed by Jason Steinberg and Travis Wetzel</div>
    				<div class="footnote">York College of PA CS320 Spring 2019</div>
  				</div>
			</div>
		</div>
	</body>
</html>