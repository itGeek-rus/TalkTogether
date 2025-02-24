const chatId = " ";
const senderId = " ";

document.getElementById("sendButton").addEventListener("click", sendMessage);

function sendMessage(){
    const messageInput = document.getElementById("messageInput");
    const messageContent = messageInput.value;

    if (messageContent.trim() == " "){
        return;
    }

    //Отправка сообщений на сервер
    fetch("http://localhost:8082/communication/api/com/v1/messages",{
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify({
            chatId: chatId,
            senderId: senderId,
            content: messageContent,
            mediaUrl: null
        }),
    })
    .then(response => {
        if(response.ok){
            messageInput.value = " "; //Очищение поле ввода
            fetchMessages(); //Обновление списка сообщений
        } else {
            console.error("Ошибка отправки сообщения");
        }
    })
    .catch(error => console.error("Ошибка", error));
}

function fetchMessages() {
    fetch(`http://localhost:8082/communication/api/com/v1/messages/${chatId}`)
        .then(response => response.json())
        .then(messages => {
        const messagesContainer = document.getElementById("messages");
        messagesContainer.innerHTML = ""; // Очистить предыдущие сообщения

        messages.forEach(message => {
            const messageElement = document.createElement("div");
            messageElement.textContent = message.content;
            messagesContainer.appendChild(messageElement);
        });

        // Прокрутка вниз
        messagesContainer.scrollTop = messagesContainer.scrollHeight;
    })
        .catch(error => console.error("Error fetching messages:", error));
}

// Получение сообщений при загрузке страницы
fetchMessages();