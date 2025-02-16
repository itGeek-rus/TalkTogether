document.getElementById('loginForm').addEventListener('submit', function(event){
    event.preventDefault();

    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;

    fetch('http://localhost:8080/authentication/api/auth/v1/login',{
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            userName: username,
            passwordHash: password
        })
    })
    .then(response =>{
        if(response.ok){
        alert('Успешный вход');
    } else {
        alert('Ошибка при входе');
    }
    })
    .catch(error => console.error('Ошибка', error));
});