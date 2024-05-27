function populateTable() {
    const tableBody = document.querySelector(".custom-table tbody");

    fetch('https://localhost:9090/api/v1/pickle')
        .then(response => response.json())
        .then(data => {
            console.log(data);
            data.forEach((item, index) => {
                const row = tableBody.insertRow();
                const cell0 = row.insertCell(0);
                const cell1 = row.insertCell(1);
                const cell2 = row.insertCell(2);
                // Add more cells as needed

                cell0.textContent = index + 1; // Row number starts from 1
                cell1.textContent = item.name;
                cell2.textContent = item.price;
                // Set cell values for additional columns
            });
        })
        .catch(error => console.error('Error fetching data:', error));

    /*const data = fetchData();

    data.forEach((item, index) => {
        const row = tableBody.insertRow();
        const cell0 = row.insertCell(0);
        const cell1 = row.insertCell(1);
        const cell2 = row.insertCell(2);
        // Add more cells as needed

        cell0.textContent = index + 1; // Row number starts from 1
        cell1.textContent = item.id;
        cell2.textContent = item.name;
        // Set cell values for additional columns
    });*/
}

// Call the function to populate the table when the page loads
window.onload = populateTable;

// Simulating data fetching from a service
function fetchData() {
    // Replace this with your actual service call
    // For now, generating dummy data
    const data = [];
    for (let i = 1; i <= 50; i++) {
        data.push({ id: i, name: `Row ${i}` });
    }
    return data;
}