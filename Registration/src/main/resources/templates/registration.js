document.getElementById('registrationForm').addEventListener('submit', function(event){
	event.preventDefault();
	
	const newUserName = document.getElementById('newUserName').value;
	const newPassword = document.getElementById('newPassword').value;
	const email = document.getElementById('email').value;
	
	fetch('http://localhost:8081/registration/api/reg/v1/registr',{
		method: 'POST',
		headers: {
			'Content-Type': 'application/json'
		},
		body: JSON.stringify({
			userName: newUserName,
			passwordHash: newPassword,
			email: email
		})
	})
	.then(response => {
		if(response.ok){
			alert('Регистрация прошла успешно!');
			window.location.href = '/authentication/api/auth/v1/login';
		} else {
			alert('Ошибка регистрации. Пожалуйста, проверьте данные');
		}
	})
	.catch(error => console.error('Ошибка', error))
});