document.addEventListener('DOMContentLoaded', function () {
    const loginForm = document.getElementById('login-form');
    if (loginForm) { // formun varlığını kontrol et
        loginForm.addEventListener('submit', async function (e) {
            e.preventDefault(); // Formun varsayılan davranışını engelle

            const email = document.getElementById('email').value;
            const password = document.getElementById('password').value;

            try {
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
            }
        });
    }
});