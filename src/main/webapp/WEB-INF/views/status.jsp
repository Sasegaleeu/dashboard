<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <title>Tracking</title>
    <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=d60985de854147ffdf3a155671095138"></script>
    <link rel="stylesheet" href="/status.css">
</head>
<body>
    <div class="header">
        <h1>상단바</h1>
    </div>
    <div class="content">
        <div class="sidebar sidebar-left">
            <h1>왼쪽 사이드바</h1>
        </div>
        <div class="main-content">
            <div class="map" style="width: 100%; height: 100%"></div>
        </div>
        <div class="sidebar sidebar-right">
            <h1>오른쪽 사이드바</h1>
        </div>
    </div>
    <div class="footer">
        <h1>하단바</h1>
    </div>
    <script>
        let container = document.querySelector(".map");
        let options = {
            center: new kakao.maps.LatLng(37.50264, 126.98046),
            level: 3,
        };
        let map = new kakao.maps.Map(container, options);
    </script>
</body>
</html>
