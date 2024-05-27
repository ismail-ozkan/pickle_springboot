fetch('http://localhost:9090/api/v1/pickle')
    .then(response => response.json())
    .then(data => {
        console.log(data);
        /*data.forEach((item, index) => {
            const row = tableBody.insertRow();
            const cell0 = row.insertCell(0);
            const cell1 = row.insertCell(1);
            const cell2 = row.insertCell(2);
            // Add more cells as needed

            cell0.textContent = index + 1; // Row number starts from 1
            cell1.textContent = item.name;
            cell2.textContent = item.price;


        });*/


    })
    .catch(error => {
        console.error('Veri alınamadı:', error);
    });