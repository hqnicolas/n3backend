document.addEventListener('DOMContentLoaded', function () {
    const form = document.getElementById('edit-donation-form');
    const messages = document.getElementById('edit-form-messages');
    const donationIdInput = document.getElementById('donation-id');
    const nameInput = document.getElementById('edit-name');
    const typeInput = document.getElementById('edit-type');
    const quantityInput = document.getElementById('edit-quantity');
    const donorInput = document.getElementById('edit-donor');
    const receiverDateInput = document.getElementById('edit-receiverDate');
    const expiryDateInput = document.getElementById('edit-expiryDate');
    const validityPeriodInput = document.getElementById('edit-validityPeriod');

    if (!form || !messages || !donationIdInput || !nameInput || !typeInput || !quantityInput || !donorInput || !receiverDateInput || !expiryDateInput || !validityPeriodInput) {
        console.error('One or more necessary DOM elements not found.');
        return;
    }

    function getQueryParameter(param) {
        const urlParams = new URLSearchParams(window.location.search);
        return urlParams.get(param);
    }

    const donationId = getQueryParameter('id');
    if (donationId) {
        fetch(`http://0.0.0.0:8080/donation/${donationId}`, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            },
        })
        .then(response => {
            if (response.ok) {
                return response.json();
            } else {
                throw new Error('Falha ao buscar doação');
            }
        })
        .then(donation => {
            donationIdInput.value = donation.id;
            nameInput.value = donation.name;
            typeInput.value = donation.type;
            quantityInput.value = donation.quantity;
            donorInput.value = donation.donor;
            receiverDateInput.value = donation.receiverDate;
            expiryDateInput.value = donation.expiryDate;
            validityPeriodInput.value = donation.validityPeriod;
        })
        .catch(error => {
            console.error('Erro ao buscar doação:', error);
            messages.innerHTML = '<div class="alert alert-danger">Falha ao buscar doação: ' + error.message + '</div>';
        });
    }

    form.addEventListener('submit', function (event) {
        event.preventDefault();
        const id = donationIdInput.value;
        const name = nameInput.value;
        const type = typeInput.value;
        const quantity = quantityInput.value;
        const donor = donorInput.value;
        const receiverDate = receiverDateInput.value;
        const expiryDate = expiryDateInput.value;
        const validityPeriod = validityPeriodInput.value;

        if (new Date(expiryDate) < new Date()) {
            messages.innerHTML = '<div class="alert alert-danger">Data de validade não pode estar expirada!</div>';
            return;
        }

        if (validityPeriod < 1 || validityPeriod > 999) {
            messages.innerHTML = '<div class="alert alert-danger">Período de validade deve ser menor que 3 anos</div>';
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
                receiverDate: receiverDate,
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