document.addEventListener('DOMContentLoaded', function () {
    const tableBody = document.getElementById('donation-list-body');
    const pagination = document.getElementById('pagination');
    const pageSize = 20;
    let currentPage = 0;



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

    function fetchDonations(page = 0, size = pageSize) {
        console.log(`Buscando doações para a página ${page} com tamanho: ${size}`);
        fetch(`http://0.0.0.0:8080/donation?page=${page}&size=${size}`, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            },   
        })
        .then(response => {
            if (response.ok) {
                return response.json();
            } else {
                throw new Error('Falha ao buscar doações!');
            }
        })
            .then(data => {
                tableBody.innerHTML = '';
                if (Array.isArray(data)) {
                    data.forEach(donation => {
                        const row = `<tr>
                            <td>${donation.name}</td>
                            <td>${donation.type}</td>
                            <td>${donation.quantity}</td>
                            <td>${donation.donor}</td>
                            <td>${donation.receiverDate}</td>
                            <td>${donation.expiryDate}</td>
                            <td>${donation.validityPeriod}</td>
                        </tr>`;
                        tableBody.innerHTML += row;
                    });

                    const totalPages = Math.ceil(data.length / pageSize);

                    createPagination(totalPages);
                } else {
                    console.error('Resposta da API inválida:', data);
                }
            })
            .catch(error => console.error('Erro ao buscar doações:', error));
    }

    function createPagination(totalPages) {
        console.log(`Criando paginação para ${totalPages} páginas`);
        pagination.innerHTML = '';
        for (let i = 0; i < totalPages; i++) {
            const li = document.createElement('li');
            li.classList.add('page-item');
            const a = document.createElement('a');
            a.classList.add('page-link');
            a.href = '#';
            a.textContent = i + 1;
            a.addEventListener('click', function () {
                currentPage = i;
                fetchDonations(currentPage, pageSize);
            });
            li.appendChild(a);
            pagination.appendChild(li);
        }
    }

    fetchDonations();
});