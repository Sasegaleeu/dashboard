<!DOCTYPE html>
<html>
<head>
    <title>Real-time Marker Update</title>
    <!-- 카카오 맵 API와 필요한 라이브러리들을 로드 -->
    <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=d865c67a15044f7517639c54d9a0f65c"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
    <script src="/webjars/sockjs-client/1.5.1/sockjs.min.js"></script>
</head>
<body>
    <h1>Real-time Marker Update with Animation</h1>
    <!-- 지도를 보여줄 컨테이너 -->
    <div id="map" style="width:1200px;height:500px;"></div>

    <script>
        // WebSocket 클라이언트와 마커 정보를 관리할 객체 선언
        var stompClient = null;
        var markers = {};
        var map;

        // WebSocket 연결을 수행하는 함수
        function connect() {
            // SockJS와 Stomp.js를 사용해 WebSocket 연결 설정
            stompClient = Stomp.over(new SockJS('/ws'));
            stompClient.connect({}, function () {
                // 토픽 구독하여 실시간 마커 데이터 수신
                stompClient.subscribe('/topic/basket-temperature-humidity', function (response) {
                    var markerData = JSON.parse(response.body);
                    updateMarkersWithAnimation(markerData);
                });
            });
        }

        // 실시간 마커 업데이트와 애니메이션을 처리하는 함수
        function updateMarkersWithAnimation(newMarkerData) {
            var order_id = newMarkerData.order_id;
            if (markers[order_id] && markers[order_id].animationInterval) {
                clearInterval(markers[order_id].animationInterval);
                markers[order_id].animatedMarker.setMap(null);
            }

            var startPos = markers[order_id]?.animatedMarker.getPosition();
            var endPos = new kakao.maps.LatLng(newMarkerData.delivery_latitude, newMarkerData.delivery_longitude);
            var numSteps = 10;
            var step = 0;
            var deltaLat = (endPos.getLat() - (startPos?.getLat() || endPos.getLat())) / numSteps;
            var deltaLng = (endPos.getLng() - (startPos?.getLng() || endPos.getLng())) / numSteps;

            // 애니메이션 처리를 위한 마커 생성 및 업데이트
            var animatedMarker = new kakao.maps.Marker({ map: map, position: startPos || endPos });

            var animationInterval = setInterval(function () {
                if (step >= numSteps) {
                    clearInterval(animationInterval);
                    animatedMarker.setMap(null);
                    markers[order_id] = { animatedMarker: null, animationInterval: null };
                    markers[order_id].animatedMarker = new kakao.maps.Marker({ map: map, position: endPos });
                    return;
                }
                var fraction = step / numSteps;
                var newPosition = new kakao.maps.LatLng(
                    (startPos?.getLat() || 0) + deltaLat * fraction,
                    (startPos?.getLng() || 0) + deltaLng * fraction
                );
                markers[order_id].animatedMarker.setPosition(newPosition);
                step++;
            }, 95);

            markers[order_id] = { animatedMarker: animatedMarker, animationInterval: animationInterval };
        }

        // 문서가 로딩된 후 실행되는 함수
        $(document).ready(function () {
            // 초기 지도 생성 및 WebSocket 연결
            map = new kakao.maps.Map(document.getElementById('map'), {
                center: new kakao.maps.LatLng(37.523257145020594, 126.9585014746847),
                level: 6
            });
            connect();
        });

        // 키보드 이벤트 리스너를 통한 지도 조작
        document.addEventListener('keydown', function (event) {
            var currentCenter = map.getCenter();
            var currentLevel = map.getLevel();
            var newCenter, newLevel;

            switch (event.key) {
                case 'ArrowUp': newCenter = new kakao.maps.LatLng(currentCenter.getLat() + 0.001, currentCenter.getLng()); break;
                case 'ArrowDown': newCenter = new kakao.maps.LatLng(currentCenter.getLat() - 0.001, currentCenter.getLng()); break;
                case 'ArrowLeft': newCenter = new kakao.maps.LatLng(currentCenter.getLat(), currentCenter.getLng() - 0.001); break;
                case 'ArrowRight': newCenter = new kakao.maps.LatLng(currentCenter.getLat(), currentCenter.getLng() + 0.001); break;
                case '+': case '=': newLevel = currentLevel - 1; break;
                case '-': newLevel = currentLevel + 1; break;
                default: return;
            }

            if (newCenter) map.panTo(newCenter);
            if (newLevel) map.setLevel(newLevel);
        });

        // 줌 컨트롤 추가
        map.addControl(new kakao.maps.ZoomControl(), kakao.maps.ControlPosition.RIGHT);
    </script>
</body>
</html>