<%@ page language="java" contentType="text/html; charset=BIG5"
	pageEncoding="BIG5"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<%@ include file="header.jsp"%>

<body translate="no">
	<div class="grid">
		<%@ include file="title.jsp"%>
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
				<div class="card">
					<div class="card__header">
						<div class="card__header-title text-light">
							<strong>Sponsor</strong>
						</div>
					</div>
					<div class="card">
						<c:if test="${pageObj.sponsorList!=null}">
							<div class="divgrid">
								<div class="tbl-tr">
									<div class="tbl-td">Date</div>
									<div class="tbl-td">Company</div>
									<div class="tbl-td">Job</div>
									<div class="tbl-td">Amount</div>
								</div>
								<c:forEach var="sponsor" items="${pageObj.sponsorList}">
									<div class="tbl-tr">
										<div class="tbl-td">${sponsor.requestDateStr }</div>
										<div class="tbl-td">${sponsor.companyName }</div>
										<div class="tbl-td">${sponsor.jobTitle }</div>
										<div class="tbl-td">${sponsor.amount }</div>
									</div>
								</c:forEach>
							</div>
						</c:if>
					</div>
				</div>
				<div class="card card--finance">
					<div class="card__header">
						<div class="card__header-title text-light">
							<strong>Revenue</strong>
						</div>
					</div>
				</div>
				<div id="chartdiv"></div>
			</div>
	</div>
	<%@ include file="footer.jsp"%>
	</div>
	
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
  const userAvatar = $('[class^=header__avatar]');
//  const userAvatar = $('.header__avatar');
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
