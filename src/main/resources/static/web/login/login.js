const BASE_URL = 'http://localhost:8252';  // Base URL'nizi buraya yazın

document.getElementById('loginForm').addEventListener('submit', function(event) {
    event.preventDefault(); // Prevent the form from submitting the traditional way

    // Get the form data
    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;

    // Prepare the request payload
    const payload = {
        username: username,
        password: password,
    };

    // Send the login request
    fetch(`${BASE_URL}/api/authenticate`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(payload)
    })
        .then(response => response.json())
        .then(data => {
            if (data.token) {
                // Store the token in localStorage
                localStorage.setItem('token', data.token);

                // Redirect to the homepage or another page
                window.location.href = 'homepage.html';
            } else {
                // Handle login failure (e.g., display an error message)
                alert('Login failed: ' + data.message);
            }
        })
        .catch(error => {
            console.error('Error:', error);
            alert('An error occurred during login.');
        });
});
