document.addEventListener('DOMContentLoaded', function () {
    const donationDetailsSection = document.getElementById('donation-details');
    const editButton = document.getElementById('edit-donation');
    const deleteButton = document.getElementById('delete-donation');

    if (!donationDetailsSection || !editButton || !deleteButton) {
        console.error('One or more necessary DOM elements not found.');
        return;
    }

    function showDonationDetails(donationId) {
        fetch(`http://0.0.0.0:8080/donation/${donationId}`)
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
                    <p><strong>Data de recebimento:</strong> ${donation.receivalDate}</p>
                    <p><strong>Data de validade:</strong> ${donation.expiryDate}</p>
                    <p><strong>Período de validade:</strong> ${donation.validityPeriod} dias</p>
                `;
            })
            .catch(error => {
                console.error('Erro ao buscar doação:', error);
                donationDetailsSection.innerHTML = '<div class="alert alert-danger">Falha ao buscar doação: ' + error.message + '</div>';
            });
    }

    editButton.addEventListener('click', function () {
        console.log('Botão de edição clicado');
        const id = 1;
        window.location.href = `#donation-edit?id=${id}`;
    });

    deleteButton.addEventListener('click', function () {
        const id = 1;
        if (confirm('Tem certeza de que deseja excluir esta doação?')) {
            fetch(`http://0.0.0.0:8080/donation/${id}`, {
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
    });

    showDonationDetails(1);
});