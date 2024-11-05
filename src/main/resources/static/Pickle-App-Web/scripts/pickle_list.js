document.addEventListener('DOMContentLoaded', function () {
    //const coursesSection = document.getElementById('courses-section');
    const pickleList = document.getElementById('pickle-list');

    const token = localStorage.getItem('token');
    console.log(token);
    console.log("ok ??");

    // Define your API endpoint
    //const apiEndpoint = `${config.baseUrl}/api/pickle`; // Replace with your actual API endpoint
    //const apiEndpoint = "http://localhost:8252/api/pickle"; // Replace with your actual API endpoint
    // Function to fetch and display pickles
    async function loadPickles() {
        try {
            // Show a loading message while fetching data
            pickleList.innerHTML = '<p>Loading pickles...</p>';

            // Fetch data from the API
            const response = await fetch(`${config.baseUrl}/api/pickle`, {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json',
                    // Add Authorization header if needed
                    'Authorization': `Bearer ${token}`
                }
            });

            if (!response.ok) {
                throw new Error('Failed to fetch pickles');
            }


            /*try {
                const response = await fetch(`${config.baseUrl}/api/authenticate`, {
                    method: 'POST',
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify({ email, password })
                });

                if (response.ok) {
                    const data = await response.json();
                    const token = data.result.token;
                    localStorage.setItem('token', token);
                    window.location.href = 'pickles.html';
                } else {
                    alert('Login failed. Please check your credentials.');
                }
            } catch (error) {
                console.error('Error:', error);
            }*/

            // Check if the request was successful
            if (!response.ok) {
                throw new Error('Failed to fetch pickles');
            }

            // Parse the JSON data
            const pickles = await response.json();
            //const pickles = data.pickleList;

            // Clear the loading message
            pickleList.innerHTML = '';

            // Check if pickle exist
            if (pickles.length === 0) {
                pickleList.innerHTML = '<p>No active pickle found.</p>';
                return;
            }

            const sellerListReponse = await fetch(`${config.baseUrl}/api/accounts`, {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json',
                    // Add Authorization header if needed
                    'Authorization': `Bearer ${token}`
                }
            });

            if (!sellerListReponse.ok) {
                throw new Error('Failed to fetch sellers');
            }

            const sellerList = await sellerListReponse.json();

            function getSellerTitleById(id) {
                const seller = sellerList.find(seller => seller.id === id);
                return seller ? seller.title : 'N/A';
            }

            // Iterate over the pickles and create HTML elements
            pickles.forEach(pickle => {
                const row = document.createElement('tr');

                row.innerHTML = `
            <td>${pickle.name.charAt(0).toUpperCase() + pickle.name.substring(1) || 'N/A'}</td>
            <td>${pickle.price || 'N/A'}</td>
            <td>${getSellerTitleById(pickle.sellerId) || 'N/A'}</td>
        `;

                // Append each row to the table body
                pickleList.appendChild(row);
            });
            /*pickles.forEach(pickle => {
                /!*const pickleItem = document.createElement('div');
                pickleItem.className = 'pickle-item';

                pickleItem.innerHTML = `
                    <h4>${pickle.name.charAt(0).toUpperCase()+pickle.name.substring(1)}</h4>
                    <p><strong>Name:</strong> ${pickle.name || 'N/A'}</p>
                    <p><strong>Price:</strong> ${pickle.isActive || 'N/A'}</p>
                    <!--<p><strong>Scope:</strong> ${pickle.scope || 'N/A'}</p>-->
                `;

                // Append each pickle to the list
                pickleList.appendChild(pickleItem);*!/
            });*/

        } catch (error) {
            // Handle errors and show a message to the user
            pickleList.innerHTML = `<p>Error loading pickles: ${error.message}</p>`;
        }
    }

    // Call the function to load pickles when the page loads
    loadPickles();
});
