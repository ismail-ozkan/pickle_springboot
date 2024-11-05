fetch('navigator.html')
    .then(response => response.text())
    .then(data => {
        document.getElementById('nav-menu-container').innerHTML = data.charAt(0).toUpperCase() + data.substring(1);
    });
