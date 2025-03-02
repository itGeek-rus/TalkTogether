const socket = new SockJS("/ws");
const stompClient = Stomp.over(socket);

stompClient.connect({}, function(frame){
	console.log('Connected: ' + frame);
	stompClient.subscribe('/topic/notifications', function(notification){
		console.log('Recived notification: ' + notification.body);
	});
});

function sendNotification(userId, message, type){
	stompClient.send("/app/sendNotification", {}, JSON.stringify({userId, message, type}));
}