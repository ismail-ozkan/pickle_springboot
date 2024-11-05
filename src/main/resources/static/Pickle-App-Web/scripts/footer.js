fetch('../pages/footer.html')
    .then(response => response.text())
    .then(data => {
        document.getElementById('footer-container').innerHTML = data.charAt(0).toUpperCase() + data.substring(1);
    });
