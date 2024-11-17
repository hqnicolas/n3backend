document.addEventListener('DOMContentLoaded', function () {
    const form = document.getElementById('donation-form');
    const messages = document.getElementById('form-messages');

    form.addEventListener('submit', function (event) {
        event.preventDefault();
        const name = document.getElementById('name').value;
        const type = document.getElementById('type').value;
        const quantity = document.getElementById('quantity').value;
        const donor = document.getElementById('donor').value;
        const receiverDate = document.getElementById('receiverDate').value;
        const expiryDate = document.getElementById('expiryDate').value;
        const validityPeriod = document.getElementById('validityPeriod').value;

        if (new Date(expiryDate) < new Date()) {
            messages.innerHTML = '<div class="alert alert-danger">Data de validade não pode ser expirada!</div>';
            return;
        }

        if (validityPeriod < 1 || validityPeriod > 999) {
            messages.innerHTML = '<div class="alert alert-danger">Data de validade deve ser menor que 3 anos.</div>';
            return;
        }

        const allowedTypes = ['Material', 'Financeira'];
        if (!allowedTypes.includes(type)) {
            messages.innerHTML = '<div class="alert alert-danger">As doações devem ser materiais ou financeiras.</div>';
            return;
        }

        if (quantity < 1) {
            messages.innerHTML = '<div class="alert alert-danger">A quantidade deve ser positiva!</div>';
            return;
        }

        fetch('http://0.0.0.0:8080/donation', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                name: name,
                type: type,
                quantity: quantity,
                donor: donor,
                receiverDate: receiverDate,
                expiryDate: expiryDate,
                validityPeriod: validityPeriod
            })
        })
        .then(response => {
            if (response.ok) {
                return response.json();
            } else {
                throw new Error('Falha ao registrar doação!');
            }
        })
        .then(data => {
            console.log('Doação registrada com sucesso:', data);
            messages.innerHTML = '<div class="alert alert-success">Doação registrada com sucesso!</div>';
            form.reset();
        })
        .catch(error => {
            console.error('Erro ao registrar sua doação:', error);
            messages.innerHTML = '<div class="alert alert-danger">' + error.message + '</div>';
        });
    });
});