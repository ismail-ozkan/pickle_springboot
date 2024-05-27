const BASE_URL = 'http://localhost:8252';
fetch(`${BASE_URL}/api/pickle/list`)
    .then(response => response.json())
    .then(data => {
        const tableBody = document.querySelector(".custom-table tbody");
        console.log(data);
        data.forEach((item, index) => {
            const row = tableBody.insertRow();
            const cell0 = row.insertCell(0);
            const cell1 = row.insertCell(1);
            const cell2 = row.insertCell(2);
            const cell3 = row.insertCell(3);
            // Add more cells as needed

            cell0.textContent = index + 1; // Row number starts from 1
            cell1.textContent = item.name;
            cell2.textContent = item.price;
            cell3.textContent = item.sellerId;


        });


    })
    .catch(error => {
        console.error('Veri alınamadı:', error);
    });
