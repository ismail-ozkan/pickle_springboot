document.addEventListener('DOMContentLoaded', function () {
    const registerForm = document.getElementById('register-form');

    if (registerForm) {
        registerForm.addEventListener('submit', async function (e) {
            e.preventDefault();

            const firstname = document.getElementById('firstname').value;
            const email = document.getElementById('email').value;
            const lastname = document.getElementById('lastname').value;
            const password = document.getElementById('password').value;
            const confirmPassword = document.getElementById('confirm-password').value;

            if (password.value !== confirmPassword.value) {
                e.preventDefault();
                alert('Passwords do not match. Please re-enter.');
            }
            try {
                const response = await fetch(`${config.baseUrl}/api/users/register`, {
                    method: 'POST',
                    headers: {'Content-Type': 'application/json'},
                    body: JSON.stringify({
                        firstName: firstname,
                        lastName: lastname,
                        email: email,
                        password: password
                    })
                });

                if (response.ok) {
                    const data = await response.json();
                    const token = data.result.token;
                    localStorage.setItem('token', token);
                    window.location.href = 'homepage.html';
                } else {
                    alert('Login failed. Please check your credentials.');
                }
            } catch (error) {
                console.error('Error:', error);
            }

        });
    }
    registerForm.addEventListener('submit', (e) => {
        if (password.value !== confirmPassword.value) {
            e.preventDefault();
            alert('Passwords do not match. Please re-enter.');
        }
    });
});
