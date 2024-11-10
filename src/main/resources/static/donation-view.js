document.addEventListener('DOMContentLoaded', function () {
    const donationDetails = document.getElementById('donation-details');
    const editDonationButton = document.getElementById('edit-donation');
    const deleteDonationButton = document.getElementById('delete-donation');

    function showDonationDetails(id) {
        fetch(`http://0.0.0.0:8080/donation/${id}`)
            .then(response => {
                if (response.ok) {
                    return response.json();
                } else {
                    throw new Error('Falha ao obter detalhes da doação');
                }
            })
            .then(donation => {
                donationDetails.innerHTML = `
                    <p><strong>Doação:</strong> ${donation.name}</p>
                    <p><strong>Tipo:</strong> ${donation.type}</p>
                    <p><strong>Quantidade:</strong> ${donation.quantity}</p>
                    <p><strong>Doador:</strong> ${donation.donor}</p>
                    <p><strong>Data de recebimento:</strong> ${donation.receivalDate}</p>
                    <p><strong>Data de validade:</strong> ${donation.expiryDate}</p>
                    <p><strong>Período de validade:</strong> ${donation.validityPeriod} days</p>
                `;
            })
            .catch(error => {
                console.error('Erro ao obter detalhes da doação:', error);
                donationDetails.innerHTML = `<div class="alert alert-danger">Falha ao obter detalhes da doação: ${error.message}</div>`;
            });
    }

    editDonationButton.addEventListener('click', function () {
        console.log('Botão de edição clicado');
        const id = 1;
        window.location.href = `#donation-edit?id=${id}`;
    });

    deleteDonationButton.addEventListener('click', function () {
        const id = 1;
        if (confirm('Tem certeza de que deseja excluir esta doação?')) {
            fetch(`http://0.0.0.0:8080/donation/${id}`, {
                method: 'DELETE'
            })
            .then(response => {
                if (response.ok) {
                    console.log('Doação excluída com sucesso');
                    donationDetails.innerHTML = `<div class="alert alert-success">Doação excluída com sucesso</div>`;
                    window.location.href = '#donation-list';
                } else {
                    throw new Error('Falha ao excluir doação');
                }
            })
            .catch(error => {
                console.error('Erro ao excluir doação:', error);
                donationDetails.innerHTML = `<div class="alert alert-danger">Falha ao excluir doação: ${error.message}</div>`;
            });
        }
    });
    showDonationDetails(1);
});