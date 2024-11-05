document.addEventListener('DOMContentLoaded', () => {
    const instructorsSection = document.getElementById('instructors-section');
    const instructorsList = document.getElementById('instructors-list');

    // Define your API endpoint
    const apiEndpoint = 'http://localhost:3366/instructors'; // Replace with your actual API endpoint

    // Function to fetch and display instructors
    async function loadInstructors() {
        try {
            // Show a loading message while fetching data
            instructorsList.innerHTML = '<p>Loading instructors...</p>';

            // Fetch data from the API
            const response = await fetch(apiEndpoint, {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json',
                    // Add Authorization header if needed
                    'Authorization': 'Bearer your-token-here'
                }
            });

            // Check if the request was successful
            if (!response.ok) {
                throw new Error('Failed to fetch instructors');
            }

            // Parse the JSON data
            const data = await response.json();
            const instructors = data.instructorsList;

            // Clear the loading message
            instructorsList.innerHTML = '';

            // Check if instructors exist
            if (instructors.length === 0) {
                instructorsList.innerHTML = '<p>No active instructors found.</p>';
                return;
            }

            // Iterate over the instructors and create HTML elements
            instructors.forEach(instructor => {
                const instructorItem = document.createElement('div');
                instructorItem.className = 'instructor-item';

                instructorItem.innerHTML = `
                    <h4>${instructor.name.charAt(0).toUpperCase()+instructor.name.substring(1)}</h4>
                    <p><strong>Name:</strong> ${instructor.name || 'N/A'}</p>
                    <p><strong>Email:</strong> ${instructor.email || 'N/A'}</p>
                    <p><strong>Phone:</strong> ${instructor.phone || 'N/A'}</p>
                    <p><strong>Scope:</strong> ${instructor.scope || 'N/A'}</p>
                `;

                // Append each instructor to the list
                instructorsList.appendChild(instructorItem);
            });

        } catch (error) {
            // Handle errors and show a message to the user
            instructorsList.innerHTML = `<p>Error loading instructors: ${error.message}</p>`;
        }
    }

    // Call the function to load instructors when the page loads
    loadInstructors();
});
