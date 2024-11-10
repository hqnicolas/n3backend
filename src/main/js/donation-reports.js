document.addEventListener('DOMContentLoaded', function () {
    const form = document.getElementById('report-form');
    const messages = document.getElementById('report-messages');
    const reportTableBody = document.getElementById('report-table-body');
    const exportCsvButton = document.getElementById('export-csv');
    const exportPdfButton = document.getElementById('export-pdf');

    form.addEventListener('submit', function (event) {
        event.preventDefault();
        const dateRange = document.getElementById('dateRange').value;
        const reportType = document.getElementById('reportType').value;
        const reportDonor = document.getElementById('reportDonor').value;

        fetch('http://0.0.0.0:8080/donation/report', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                dateRange: dateRange,
                type: reportType,
                donor: reportDonor
            })
        })
        .then(response => {
            if (response.ok) {
                return response.json();
            } else {
                throw new Error('Falha ao gerar relatório');
            }
        })
        .then(data => {
            console.log('Relatório gerado com sucesso:', data);
            messages.innerHTML = '<div class="alert alert-success">Relatório gerado com sucesso!</div>';
            reportTableBody.innerHTML = '';
            data.forEach(donation => {
                const row = `<tr>
                    <td>${donation.name}</td>
                    <td>${donation.type}</td>
                    <td>${donation.quantity}</td>
                    <td>${donation.donor}</td>
                    <td>${donation.receivalDate}</td>
                    <td>${donation.expiryDate}</td>
                    <td>${donation.validityPeriod}</td>
                </tr>`;
                reportTableBody.innerHTML += row;
            });
        })
        .catch(error => {
            console.error('Erro ao gerar relatório:', error);
            messages.innerHTML = '<div class="alert alert-danger">' + error.message + '</div>';
        });
    });

    exportCsvButton.addEventListener('click', function () {
        fetch('http://0.0.0.0:8080/donation/report/csv', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                dateRange: document.getElementById('dateRange').value,
                type: document.getElementById('reportType').value,
                donor: document.getElementById('reportDonor').value
            })
        })
        .then(response => {
            if (response.ok) {
                return response.blob();
            } else {
                throw new Error('Falha ao exportarCSV');
            }
        })
        .then(blob => {
            const url = window.URL.createObjectURL(blob);
            const a = document.createElement('a');
            a.href = url;
            a.download = 'donation_report.csv';
            a.click();
            window.URL.revokeObjectURL(url);
            console.log('CSV exportação bem-sucedida');
        })
        .catch(error => {
            console.error('Erro ao exportar CSV:', error);
            messages.innerHTML = '<div class="alert alert-danger">' + error.message + '</div>';
        });
    });

    exportPdfButton.addEventListener('click', function () {
        fetch('http://0.0.0.0:8080/donation/report/pdf', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                dateRange: document.getElementById('dateRange').value,
                type: document.getElementById('reportType').value,
                donor: document.getElementById('reportDonor').value
            })
        })
        .then(response => {
            if (response.ok) {
                return response.blob();
            } else {
                throw new Error('Falha ao exportarPDF');
            }
        })
        .then(blob => {
            const url = window.URL.createObjectURL(blob);
            const a = document.createElement('a');
            a.href = url;
            a.download = 'donation_report.pdf';
            a.click();
            window.URL.revokeObjectURL(url);
            console.log('PDF exportação bem-sucedida');
        })
        .catch(error => {
            console.error('Erro ao exportar PDF:', error);
            messages.innerHTML = '<div class="alert alert-danger">' + error.message + '</div>';
        });
    });
});