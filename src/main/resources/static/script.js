```javascript
const api_url = 'http://localhost:8080';

function loadDonations() {
    console.log('Loading donations...');
    fetch(`${api_url}/donation`)
        .then(response => {
            console.log('Donations response:', response);
            if (response.ok) {
                return response.json();
            } else {
                return response.text().then(text => {
                    console.log('Donations response body:', text);
                    throw new Error(`Error loading donations: ${response.statusText}`);
                });
            }
        })
        .then(data => {
            console.log('Donations data:', data);
            console.log('Donations loaded successfully.');
            const donationsHtml = data.map(donation => `
                <div>
                    <h2>${donation.name}</h2>
                    <p>Type: ${donation.type}</p>
                    <p>Quantity: ${donation.quantity}</p>
                    <p>Donor: ${donation.donor}</p>
                    <p>Receiver Date: ${donation.receiverDate}</p>
                    <p>Expiry Date: ${donation.expiryDate}</p>
                    <p>Validity Period: ${donation.validityPeriod}</p>
                </div>
            `).join('');
            document.querySelector('main').innerHTML = donationsHtml;
        })
        .catch(error => {
            console.error('Error loading donations:', error);
        });
}

function loadMessages() {
    console.log('Loading messages...');
    fetch(`${api_url}/api/mensagens`)
        .then(response => {
            console.log('Messages response:', response);
            if (response.ok) {
                return response.json();
            } else {
                return response.text().then(text => {
                    console.log('Messages response body:', text);
                    throw new Error(`Error loading messages: ${response.statusText}`);
                });
            }
        })
        .then(data => {
            console.log('Messages data:', data);
            console.log('Messages loaded successfully.');
            const messagesHtml = data.map(message => `
                <div>
                    <h2>${message.remetenteId}</h2>
                    <p>Content: ${message.conteudo}</p>
                    <p>Send Date: ${message.dataEnvio}</p>
                </div>
            `).join('');
            document.querySelector('main').innerHTML = messagesHtml;
        })
        .catch(error => {
            console.error('Error loading messages:', error);
        });
}

function loadUsers() {
    console.log('Loading users...');
    fetch(`${api_url}/users`)
        .then(response => {
            if (response.ok) {
                return response.json();
            } else {
                throw new Error(`Error loading users: ${response.statusText}`);
            }
        })
        .then(data => {
            console.log('Users loaded successfully.');
            const usersHtml = data.map(user => `
                <div>
                    <h2>${user.name}</h2>
                    <p>Email: ${user.email}</p>
                    <p>Password: ${user.password}</p>
                    <p>Date of Birth: ${user.dateOfBirth}</p>
                    <p>Gender: ${user.gender}</p>
                    <p>Location: ${user.location}</p>
                    <p>Preferences: ${user.preferences}</p>
                    <p>Biography: ${user.biography}</p>
                    <p>Photos: ${user.photos}</p>
                    <p>Interests: ${user.interests}</p>
                </div>
            `).join('');
            document.querySelector('main').innerHTML = usersHtml;
        })
        .catch(error => {
            console.error('Error loading users:', error);
        });
}

function loadReports() {
    console.log('Loading reports...');
    fetch(`${api_url}/donation/reports/generate`)
        .then(response => {
            console.log('Reports response:', response);
            if (response.ok) {
                return response.json();
            } else {
                return response.text().then(text => {
                    console.log('Reports response body:', text);
                    throw new Error(`Error loading reports: ${response.statusText}`);
                });
            }
        })
        .then(data => {
            console.log('Reports data:', data);
            console.log('Reports loaded successfully.');
            const reportsHtml = data.map(report => `
                <div>
                    <h2>${report.name}</h2>
                    <p>Type: ${report.type}</p>
                    <p>Quantity: ${report.quantity}</p>
                    <p>Donor: ${report.donor}</p>
                    <p>Receiver Date: ${report.receiverDate}</p>
                    <p>Expiry Date: ${report.expiryDate}</p>
                    <p>Validity Period: ${report.validityPeriod}</p>
                </div>
            `).join('');
            document.querySelector('main').innerHTML = reportsHtml;
        })
        .catch(error => {
            console.error('Error loading reports:', error);
        });
}

document.addEventListener('DOMContentLoaded', () =>