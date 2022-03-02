<%@ page language="java" contentType="text/html; charset=BIG5"
	pageEncoding="BIG5"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

<%@ include file="header.jsp"%>
<!-- 
<head>
	<meta charset="UTF-8">
	<link rel="apple-touch-icon" type="image/png"
		href="https://cpwebassets.codepen.io/assets/favicon/apple-touch-icon-5ae1a0698dcc2402e9712f7d01ed509a57814f994c660df9f7a952f3060705ee.png" />
	<meta name="apple-mobile-web-app-title" content="CodePen">
	
	<link rel="shortcut icon" type="image/x-icon"
		href="https://cpwebassets.codepen.io/assets/favicon/favicon-aec34940fbc1a6e787974dcd360f2c6b63348d4b1f4e06c77743096d55480f33.ico" />
	
	<link rel="mask-icon" type="image/x-icon"
		href="https://cpwebassets.codepen.io/assets/favicon/logo-pin-8f3771b1072e3c38bd662872f6b673a722f4b3ca2421637d5596661b4e2132cc.svg"
		color="#111" />
	
	<title>Rideboard - Dashboard</title>
	<link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400"
		rel="stylesheet">
	<link rel="stylesheet"
		href="https://use.fontawesome.com/releases/v5.4.1/css/all.css"
		integrity="sha384-5sAR7xN1Nv6T6+dT2mhtzEpVJvfS3NScPQTrOxhwjIuvcA67KV2R5Jz6kr4abQsz"
		crossorigin="anonymous">
	<link rel="shortcut icon" type="image/png" href="#">
	
	<link rel="stylesheet" href="resources/css/main.css">
	
	<script>
	  window.console = window.console || function(t) {};
	</script>
	<script>
	  if (document.location.search.match(/type=embed/gi)) {
	    window.parent.postMessage("resize", "*");
	  }
	</script>
</head>
 -->
 
<body translate="no">
	<div class="grid">
		<%@ include file="title.jsp"%>
		<!--
		<aside class="sidenav">
			<div class="sidenav__brand">
				<i class="fas fa-feather-alt sidenav__brand-icon"></i> <a
					class="sidenav__brand-link" href="#">Ux<span class="text-light">Pro</span></a>
				<i class="fas fa-times sidenav__brand-close"></i>
			</div>
			<div class="row row--align-v-center row--align-h-center">
				<ul class="navList">
					<li class="navList__heading">Race<i
						class="far fa-file-alt"></i></li>
					<li>
						<div class="navList__subheading row row--align-v-center">
							<span class="navList__subheading-icon"><i
								class="fas fa-briefcase-medical"></i></span> <span
								class="navList__subheading-title">insurance</span>
						</div>
						<ul class="subList subList--hidden">
							<li class="subList__item">medical</li>
							<li class="subList__item">vision</li>
							<li class="subList__item">dental</li>
						</ul>
					</li>
					<li>
						<div class="navList__subheading row row--align-v-center">
							<span class="navList__subheading-icon"><i
								class="fas fa-plane-departure"></i></span> <span
								class="navList__subheading-title">travel</span>
						</div>
						<ul class="subList subList--hidden">
							<li class="subList__item">domestic</li>
							<li class="subList__item">foreign</li>
							<li class="subList__item">misc</li>
						</ul>
					</li>
					<li>
						<div class="navList__subheading row row--align-v-center">
							<span class="navList__subheading-icon"><i
								class="far fa-angry"></i></span> <span
								class="navList__subheading-title">taxes</span>
						</div>
						<ul class="subList subList--hidden">
							<li class="subList__item">current</li>
							<li class="subList__item">archives</li>
						</ul>
					</li>

					<li class="navList__heading">Sponsor<i
						class="far fa-envelope"></i></li>
					<li>
						<div class="navList__subheading row row--align-v-center">
							<span class="navList__subheading-icon"><i
								class="fas fa-envelope"></i></span> <span
								class="navList__subheading-title">inbox</span>
						</div>
						<ul class="subList subList--hidden">
							<li class="subList__item">primary</li>
							<li class="subList__item">social</li>
							<li class="subList__item">promotional</li>
						</ul>
					</li>
					<li>
						<div class="navList__subheading row row--align-v-center">
							<span class="navList__subheading-icon"><i
								class="fas fa-eye"></i></span> <span class="navList__subheading-title">unread</span>
						</div>
						<ul class="subList subList--hidden">
							<li class="subList__item">primary</li>
							<li class="subList__item">social</li>
							<li class="subList__item">promotional</li>
						</ul>
					</li>
					<li>
						<div class="navList__subheading row row--align-v-center">
							<span class="navList__subheading-icon"><i
								class="fas fa-book-open"></i></span> <span
								class="navList__subheading-title">archives</span>
						</div>
						<ul class="subList subList--hidden">
							<li class="subList__item">primary</li>
							<li class="subList__item">social</li>
							<li class="subList__item">promotional</li>
						</ul>
					</li>

					<li class="navList__heading">events<i
						class="far fa-image"></i></li>
					<li>
						<div class="navList__subheading row row--align-v-center">
							<span class="navList__subheading-icon"><i
								class="fas fa-mountain"></i></span> <span
								class="navList__subheading-title">vacation</span>
						</div>
						<ul class="subList subList--hidden">
							<li class="subList__item">cambodia</li>
							<li class="subList__item">new york</li>
						</ul>
					</li>
					<li>
						<div class="navList__subheading row row--align-v-center">
							<span class="navList__subheading-icon"><i
								class="fas fa-wine-glass-alt"></i></span> <span
								class="navList__subheading-title">anniversary</span>
						</div>
						<ul class="subList subList--hidden">
							<li class="subList__item">dive trip</li>
							<li class="subList__item">hikathon</li>
							<li class="subList__item">buffalo river</li>
						</ul>
					</li>
					<li>
						<div class="navList__subheading row row--align-v-center">
							<span class="navList__subheading-icon"><i
								class="fas fa-graduation-cap"></i></span> <span
								class="navList__subheading-title">university</span>
						</div>
						<ul class="subList subList--hidden">
							<li class="subList__item">wild horse saloon</li>
							<li class="subList__item">service corps</li>
							<li class="subList__item">graduation</li>
							<li class="subList__item">internships</li>
						</ul>
					</li>
				</ul>
			</div>
		</aside>
  -->
		<div class="main">
			<div class="main-overview">
				<div class="overviewCard">
					<div class="overviewCard-icon overviewCard-icon--rank">
						<img src="resources/images/rank.png" height="30" />
					</div>
					<div class="overviewCard-description">
						<h3 class="overviewCard-title text-light">
							<strong>World Ranking</strong>
						</h3>
						<p class="overviewCard-subtitle">${pageObj.worldRank}</p>
					</div>
				</div>
				<div class="overviewCard">
					<div class="overviewCard-icon overviewCard-icon--calendar">
						<i class="far fa-calendar-check"></i>
					</div>
					<div class="overviewCard-description">
						<h3 class="overviewCard-title text-light">
							Upcoming <strong>Event</strong>
						</h3>
						<p class="overviewCard-subtitle">${pageObj.nextEvent}</p>
					</div>
				</div>
				<div class="overviewCard">
					<div class="overviewCard-icon overviewCard-icon--race">
						<img src="resources/images/race.png" height="30" />
					</div>
					<div class="overviewCard-description">
						<h3 class="overviewCard-title text-light">
							<strong>Race</strong>
						</h3>
						<p class="overviewCard-subtitle">${pageObj.raceCnt }</p>
					</div>
				</div>
				<div class="overviewCard">
					<div class="overviewCard-icon overviewCard-icon--sponsor">
						<img src="resources/images/sponsor.png" height="30" />
					</div>
					<div class="overviewCard-description">
						<h3 class="overviewCard-title text-light">
							<strong>Sponsor</strong>
						</h3>
						<p class="overviewCard-subtitle">${pageObj.sponsorCnt }</p>
					</div>
				</div>
			</div>
			<!-- /.main__overview -->
			<div class="main__cards">
				<div class="card">
					<div class="card__header">
						<div class="card__header-title text-light">
							<strong>Events</strong>
						</div>
					</div>
					<div class="card__main">
						<c:forEach var="event" items="${pageObj.eventList}">
							<div class="card__row">
								<div class="card__icon">
									<c:if test="${event.type=='R'}">
										<img src="resources/images/race.png" height="8" />
									</c:if>
									<c:if test="${event.type=='S'}">
										<img src="resources/images/sponsor.png" height="8" />
									</c:if>
									<c:if test="${event.type=='T'}">
										<img src="resources/images/team.png" height="8" />
									</c:if>
								</div>
								<div class="card__time">
									<div>${event.dateStr}</div>
								</div>
								<div class="card__detail">
									<div class="card__source text-bold">${event.title }</div>
									<%-- 									<div class="card__description">${event.desc }</div> --%>
									<div class="card__note">${event.location }</div>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>
				<div class="card">
					<div class="card__header">
						<div class="card__header-title text-light">
							<strong>Race</strong>
						</div>
					</div>
					<div class="card">
						<c:if test="${pageObj.raceList!=null}">
							<div class="divgrid">
								<div class="tbl-tr">
									<div class="tbl-td">Date</div>
									<div class="tbl-td">Title</div>
									<div class="tbl-td">Location</div>
								</div>
								<c:forEach var="race" items="${pageObj.raceList}">
									<div class="tbl-tr">
										<div class="tbl-td">${race.dateStr }</div>
										<div class="tbl-td">${race.title }</div>
										<div class="tbl-td">${race.location }</div>
									</div>
								</c:forEach>
							</div>
						</c:if>
					</div>
				</div>
				<div class="card card--finance">
					<div class="card__header">
						<div class="card__header-title text-light">
							<strong>Sponsor</strong>
							<!-- 							<a href="#" class="card__header-link text-bold">View All</a> -->
						</div>
						<!-- 						<div class="settings"> -->
						<!-- 							<div class="settings__block"> -->
						<!-- 								<i class="fas fa-edit"></i> -->
						<!-- 							</div> -->
						<!-- 							<div class="settings__block"> -->
						<!-- 								<i class="fas fa-cog"></i> -->
						<!-- 							</div> -->
					</div>
				</div>
				<div id="chartdiv"></div>
			</div>
	</div>

	<%@ include file="footer.jsp"%>
	</div>
	<script
		src="https://cpwebassets.codepen.io/assets/common/stopExecutionOnTimeout-1b93190375e9ccc259df3a57c1abc0e64599724ae30d7ea4c6877eb615f89387.js"></script>

	<script src='https://code.jquery.com/jquery-3.3.1.min.js'></script>
	<script src='https://www.amcharts.com/lib/3/amcharts.js'></script>
	<script src='https://www.amcharts.com/lib/3/serial.js'></script>
	<script src='https://www.amcharts.com/lib/3/themes/light.js'></script>
	<script id="rendered-js">
/* Scripts for css grid dashboard */

$(document).ready(() => {
  addResizeListeners();
  //setSidenavListeners();
  setUserDropdownListener();
  renderChart();
  setMenuClickListener();
  //setSidenavCloseListener();
});

// Set constants and grab needed elements
const sidenavEl = $('.sidenav');
const gridEl = $('.grid');
const SIDENAV_ACTIVE_CLASS = 'sidenav--active';
const GRID_NO_SCROLL_CLASS = 'grid--noscroll';

function toggleClass(el, className) {
  if (el.hasClass(className)) {
    el.removeClass(className);
  } else {
    el.addClass(className);
  }
}

// User avatar dropdown functionality
function setUserDropdownListener() {
  const userAvatar = $('.header__avatar');

  userAvatar.on('click', function (e) {
    const dropdown = $(this).children('.dropdown');
    toggleClass(dropdown, 'dropdown--active');
  });
}

// Sidenav list sliding functionality
function setSidenavListeners() {
  const subHeadings = $('.navList__subheading');console.log('subHeadings: ', subHeadings);
  const SUBHEADING_OPEN_CLASS = 'navList__subheading--open';
  const SUBLIST_HIDDEN_CLASS = 'subList--hidden';

  subHeadings.each((i, subHeadingEl) => {
    $(subHeadingEl).on('click', e => {
      const subListEl = $(subHeadingEl).siblings();

      // Add/remove selected styles to list category heading
      if (subHeadingEl) {
        toggleClass($(subHeadingEl), SUBHEADING_OPEN_CLASS);
      }

      // Reveal/hide the sublist
      if (subListEl && subListEl.length === 1) {
        toggleClass($(subListEl), SUBLIST_HIDDEN_CLASS);
      }
    });
  });
}

// Draw the chart
function renderChart() {
  const chart = AmCharts.makeChart("chartdiv", {
    "type": "serial",
    "theme": "light",
    "dataProvider": [{
      "month": "Jan",
      "visits": 2025 },
    {
      "month": "Feb",
      "visits": 1882 },
    {
      "month": "Mar",
      "visits": 1809 },
    {
      "month": "Apr",
      "visits": 1322 },
    {
      "month": "May",
      "visits": 1122 },
    {
      "month": "Jun",
      "visits": 1114 },
    {
      "month": "Jul",
      "visits": 984 },
    {
      "month": "Aug",
      "visits": 711 },
    {
      "month": "Sept",
      "visits": 665 },
    {
      "month": "Oct",
      "visits": 580 }],

    "valueAxes": [{
      "gridColor": "#FFFFFF",
      "gridAlpha": 0.2,
      "dashLength": 0 }],

    "gridAboveGraphs": true,
    "startDuration": 1,
    "graphs": [{
      "balloonText": "[[category]]: <b>[[value]]</b>",
      "fillAlphas": 0.8,
      "lineAlpha": 0.2,
      "type": "column",
      "valueField": "visits" }],

    "chartCursor": {
      "categoryBalloonEnabled": false,
      "cursorAlpha": 0,
      "zoomable": false },

    "categoryField": "month",
    "categoryAxis": {
      "gridPosition": "start",
      "gridAlpha": 0,
      "tickPosition": "start",
      "tickLength": 20 },

    "export": {
      "enabled": false } });


}

function toggleClass(el, className) {
  if (el.hasClass(className)) {
    el.removeClass(className);
  } else {
    el.addClass(className);
  }
}

// If user opens the menu and then expands the viewport from mobile size without closing the menu,
// make sure scrolling is enabled again and that sidenav active class is removed
function addResizeListeners() {
  $(window).resize(function (e) {
    const width = window.innerWidth;console.log('width: ', width);

    if (width > 750) {
      //sidenavEl.removeClass(SIDENAV_ACTIVE_CLASS);
      gridEl.removeClass(GRID_NO_SCROLL_CLASS);
    }
  });
}

// Menu open sidenav icon, shown only on mobile
function setMenuClickListener() {
  $('.header__menu').on('click', function (e) {console.log('clicked menu icon');
    toggleClass(sidenavEl, SIDENAV_ACTIVE_CLASS);
    toggleClass(gridEl, GRID_NO_SCROLL_CLASS);
  });
}

// Sidenav close icon
function setSidenavCloseListener() {
  $('.sidenav__brand-close').on('click', function (e) {
    toggleClass(sidenavEl, SIDENAV_ACTIVE_CLASS);
    toggleClass(gridEl, GRID_NO_SCROLL_CLASS);
  });
}
//# sourceURL=pen.js
    </script>
</body>
</html>
