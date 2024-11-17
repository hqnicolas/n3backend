document.addEventListener('DOMContentLoaded', function () {
    const form = document.getElementById('report-form');
    const messages = document.getElementById('report-messages');
    const reportTableBody = document.getElementById('report-table-body');
    const exportCsvButton = document.getElementById('export-csv');
    const exportPdfButton = document.getElementById('export-pdf');

    if (!form || !messages || !reportTableBody || !exportCsvButton || !exportPdfButton) {
        console.error('One or more necessary DOM elements not found.');
        return;
    }

    function formatDate(dateString) {
        const date = new Date(dateString);
        return `${date.getFullYear()}-${String(date.getMonth() + 1).padStart(2, '0')}-${String(date.getDate()).padStart(2, '0')}`;
    }

    function isValidDate(dateString) {
        const date = new Date(dateString);
        return date instanceof Date && !isNaN(date);
    }

    function downloadCsv(filter) {
        fetch('http://0.0.0.0:8080/donation/reports/csv', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(filter)
        })
        .then(response => {
            if (response.ok) {
                return response.blob();
            } else {
                throw new Error('Falha ao exportar CSV');
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
            console.error('Error exporting CSV:', error);
            messages.innerHTML = '<div class="alert alert-danger">' + error.message + '</div>';
        });
    }

    function downloadPdf(filter) {
        fetch('http://0.0.0.0:8080/donation/reports/pdf', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(filter)
        })
        .then(response => {
            if (response.ok) {
                return response.blob();
            } else {
                throw new Error('Falha ao exportar PDF');
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
            console.error('Error exporting PDF:', error);
            messages.innerHTML = '<div class="alert alert-danger">' + error.message + '</div>';
        });
    }

    form.addEventListener('submit', (event) => {
        event.preventDefault();
        const dateRange = document.getElementById('dateRange').value;
        const reportType = document.getElementById('reportType').value;
        const reportDonor = document.getElementById('reportDonor').value;

        if (!dateRange) {
            messages.innerHTML = '<div class="alert alert-danger">A data de intervalo é necessária!</div>';
            return;
        }

        const [startDate, endDate] = dateRange.split(' - ');

        if (!isValidDate(startDate) || !isValidDate(endDate)) {
            messages.innerHTML = '<div class="alert alert-danger">Datas inválidas. Por favor, verifique as datas de início e fim.</div>';
            return;
        }

        if (new Date(startDate) > new Date(endDate)) {
            messages.innerHTML = '<div class="alert alert-danger">A data de início não pode ser posterior à data de fim.</div>';
            return;
        }

        const formattedStartDate = formatDate(startDate);
        const formattedEndDate = formatDate(endDate);

        fetch('http://0.0.0.0:8080/donation/reports/generate', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                startDate: formattedStartDate,
                endDate: formattedEndDate,
                donationType: reportType,
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
            console.log('Relatório gerado com sucesso', data);
            messages.innerHTML = '<div class="alert alert-success">Relatório gerado com sucesso!</div>';
            reportTableBody.innerHTML = '';
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
                reportTableBody.innerHTML += row;
            });
        })
        .catch(error => {
            console.error('Erro ao gerar relatório:', error);
            messages.innerHTML = '<div class="alert alert-danger">' + error.message + '</div>';
        });
    });

    exportCsvButton.addEventListener('click', () => {
        const dateRange = document.getElementById('dateRange').value;
        const reportType = document.getElementById('reportType').value;
        const reportDonor = document.getElementById('reportDonor').value;

        const [startDate, endDate] = dateRange.split(' - ');

        if (!isValidDate(startDate) || !isValidDate(endDate)) {
            messages.innerHTML = '<div class="alert alert-danger">Datas inválidas. Por favor, verifique as datas de início e fim.</div>';
            return;
        }

        if (new Date(startDate) > new Date(endDate)) {
            messages.innerHTML = '<div class="alert alert-danger">A data de início não pode ser posterior à data de fim.</div>';
            return;
        }

        const formattedStartDate = formatDate(startDate);
        const formattedEndDate = formatDate(endDate);

        downloadCsv({
            startDate: formattedStartDate,
            endDate: formattedEndDate,
            donationType: reportType,
            donor: reportDonor
        });
    });

    exportPdfButton.addEventListener('click', () => {
        const dateRange = document.getElementById('dateRange').value;
        const reportType = document.getElementById('reportType').value;
        const reportDonor = document.getElementById('reportDonor').value;

        const [startDate, endDate] = dateRange.split(' - ');

        if (!isValidDate(startDate) || !isValidDate(endDate)) {
            messages.innerHTML = '<div class="alert alert-danger">Datas inválidas. Por favor, verifique as datas de início e fim.</div>';
            return;
        }

        if (new Date(startDate) > new Date(endDate)) {
            messages.innerHTML = '<div class="alert alert-danger">A data de início não pode ser posterior à data de fim.</div>';
            return;
        }

        const formattedStartDate = formatDate(startDate);
        const formattedEndDate = formatDate(endDate);

        downloadPdf({
            startDate: formattedStartDate,
            endDate: formattedEndDate,
            donationType: reportType,
            donor: reportDonor
        });
    });
});