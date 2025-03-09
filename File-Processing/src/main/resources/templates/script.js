const uploadForm = document.getElementById('uploadForm');
const fileList = document.getElementById('fileList');

uploadForm.addEventListener('submit', async(event) => {
    event.preventDefault();
    const fileInput = document.getElementById('fileInput');
    const formData = new FormData();
    formData.append('file', fileInput.files[0]);

    try{
        const response = await fetch('http://localhost:8084/fileProcessing/files/api/fil/v1/upload',{
            method: 'POST',
            body: formData
        });

        if(response.ok){
            const fileDocument = await response.json();
            addFileToList(fileDocument);
        } else {
            alert('Ошибка загрузки файла')
        }
    } catch (error){
        console.error('Ошибка', error);
    }
});

function addFileToList(fileDocument){
    const li = document.createElement('li');
    li.textContent = '${fileDocument.fileName} (${fileDocument.size} bytes)';
    fileList.appendChild(li);
}