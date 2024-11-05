document.addEventListener('DOMContentLoaded', () => {
    const coursesSection = document.getElementById('courses-section');
    const coursesList = document.getElementById('courses-list');

    // Define your API endpoint
    const apiEndpoint = 'http://localhost:3366/courses?isActive=true'; // Replace with your actual API endpoint

    // Function to fetch and display courses
    async function loadCourses() {
        try {
            // Show a loading message while fetching data
            coursesList.innerHTML = '<p>Loading courses...</p>';

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
                throw new Error('Failed to fetch courses');
            }

            // Parse the JSON data
            const data = await response.json();
            const courses = data.coursesList;

            // Clear the loading message
            coursesList.innerHTML = '';

            // Check if courses exist
            if (courses.length === 0) {
                coursesList.innerHTML = '<p>No active courses found.</p>';
                return;
            }

            // Iterate over the courses and create HTML elements
            courses.forEach(course => {
                const courseItem = document.createElement('div');
                courseItem.className = 'course-item';

                courseItem.innerHTML = `
                    <h4>${course.title.charAt(0).toUpperCase()+course.title.substring(1)}</h4>
                    <p><strong>Title:</strong> ${course.title || 'N/A'}</p>
                    <p><strong>Activity:</strong> ${course.isActive || 'N/A'}</p>
                    <!--<p><strong>Scope:</strong> ${course.scope || 'N/A'}</p>-->
                `;

                // Append each course to the list
                coursesList.appendChild(courseItem);
            });

        } catch (error) {
            // Handle errors and show a message to the user
            coursesList.innerHTML = `<p>Error loading courses: ${error.message}</p>`;
        }
    }

    // Call the function to load courses when the page loads
    loadCourses();
});
