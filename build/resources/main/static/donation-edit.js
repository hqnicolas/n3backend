document.addEventListener('DOMContentLoaded', function () {
    const form = document.getElementById('edit-donation-form');
    const messages = document.getElementById('edit-form-messages');

    form.addEventListener('submit', function (event) {
        event.preventDefault();
        const id = document.getElementById('donation-id').value;
        const name = document.getElementById('edit-name').value;
        const type = document.getElementById('edit-type').value;
        const quantity = document.getElementById('edit-quantity').value;
        const donor = document.getElementById('edit-donor').value;
        const receivalDate = document.getElementById('edit-receivalDate').value;
        const expiryDate = document.getElementById('edit-expiryDate').value;
        const validityPeriod = document.getElementById('edit-validityPeriod').value;

        if (new Date(expiryDate) < new Date()) {
            messages.innerHTML = '<div class="alert alert-danger">Data de validade não pode estar expirada!</div>';
            return;
        }

        if (validityPeriod < 1 || validityPeriod > 999) {
            messages.innerHTML = '<div class="alert alert-danger">Periodo de validade deve ser menor que 3 anos</div>';
            return;
        }

        fetch(`http://0.0.0.0:8080/donation/${id}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                name: name,
                type: type,
                quantity: quantity,
                donor: donor,
                receivalDate: receivalDate,
                expiryDate: expiryDate,
                validityPeriod: validityPeriod
            })
        })
        .then(response => {
            if (response.ok) {
                return response.json();
            } else {
                throw new Error('Falha ao Atualizar a doação!');
            }
        })
        .then(data => {
            console.log('Doação atualizada com sucesso:', data);
            messages.innerHTML = '<div class="alert alert-success">Doação atualizada com sucesso!</div>';
            form.reset();
        })
        .catch(error => {
            console.error('Erro ao atualizar a doação:', error);
            messages.innerHTML = '<div class="alert alert-danger">' + error.message + '</div>';
        });
    });
});