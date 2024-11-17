document.addEventListener('DOMContentLoaded', function () {
    const donationDetailsSection = document.getElementById('donation-details');
    const editButton = document.getElementById('edit-donation');
    const deleteButton = document.getElementById('delete-donation');
    const consultarButton = document.getElementById('consultar-button');
    const donationIdInput = document.getElementById('donation-id');

    if (!donationDetailsSection || !editButton || !deleteButton || !consultarButton || !donationIdInput) {
        console.error('One or more necessary DOM elements not found.');
        return;
    }

    function showDonationDetails(donationId) {
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
            donationDetailsSection.innerHTML = `
                <p><strong>Doação:</strong> ${donation.name}</p>
                <p><strong>Tipo:</strong> ${donation.type}</p>
                <p><strong>Quantidade:</strong> ${donation.quantity}</p>
                <p><strong>Doador:</strong> ${donation.donor}</p>
                <p><strong>Data de recebimento:</strong> ${donation.receiverDate}</p>
                <p><strong>Data de validade:</strong> ${donation.expiryDate}</p>
                <p><strong>Período de validade:</strong> ${donation.validityPeriod} dias</p>
            `;
        })
        .catch(error => {
            console.error('Erro ao buscar doação:', error);
            donationDetailsSection.innerHTML = '<div class="alert alert-danger">Falha ao buscar doação: ' + error.message + '</div>';
        });
    }

    consultarButton.addEventListener('click', function () {
        const donationId = donationIdInput.value;
        if (donationId) {
            showDonationDetails(donationId);
        } else {
            donationDetailsSection.innerHTML = '<div class="alert alert-danger">Por favor, insira um ID de doação válido.</div>';
        }
    });

    editButton.addEventListener('click', function () {
        const donationId = donationIdInput.value;
        if (donationId) {
            window.location.href = `#donation-edit?id=${donationId}`;
        } else {
            donationDetailsSection.innerHTML = '<div class="alert alert-danger">Por favor, insira um ID de doação válido.</div>';
        }
    });

    deleteButton.addEventListener('click', function () {
        const donationId = donationIdInput.value;
        if (donationId) {
            if (confirm('Tem certeza de que deseja excluir esta doação?')) {
                fetch(`http://0.0.0.0:8080/donation/${donationId}`, {
                    method: 'DELETE'
                })
                .then(response => {
                    if (response.ok) {
                        console.log('Doação excluída com sucesso');
                        donationDetailsSection.innerHTML = '<div class="alert alert-success">Doação excluída com sucesso</div>';
                        window.location.href = '#donation-list';
                    } else {
                        throw new Error('Falha ao excluir doação');
                    }
                })
                .catch(error => {
                    console.error('Erro ao excluir doação:', error);
                    donationDetailsSection.innerHTML = '<div class="alert alert-danger">Falha ao excluir doação: ' + error.message + '</div>';
                });
            }
        } else {
            donationDetailsSection.innerHTML = '<div class="alert alert-danger">Por favor, insira um ID de doação válido.</div>';
        }
    });

    const idFromURL = new URLSearchParams(window.location.search).get('id');
    if (idFromURL) {
        showDonationDetails(idFromURL);
    } else {
        showDonationDetails(1);
    }
});