
let data = [];

function fetchData() {
    // Substitua a URL com a URL real do seu backend
    fetch('http://localhost:8088/api/website/dashboard')
        .then(response => {
            if (!response.ok) {
                throw new Error('Erro na solicitação');
            }
            return response.json();
        })
        .then(responseData  => {
            // Limpa a tabela antes de preenchê-la
            const tableBody = document.querySelector('#data-table tbody');
            tableBody.innerHTML = '';
            data = responseData;
            // Preenche a tabela com os dados obtidos
            data.forEach(post => {

                const row = tableBody.insertRow();
                const userIdCell = row.insertCell(0);
                const titleCell = row.insertCell(1);
                const bodyCell = row.insertCell(2);

                userIdCell.textContent = post.userId;
                titleCell.textContent = post.title;
                bodyCell.textContent = post.body;
            });
            renderChart()
        })
        .catch(error => {
            console.error('Erro ao buscar dados do backend:', error);
        });
}

// Função para renderizar o gráfico de visualização de dados
function renderChart() {
    // Use uma biblioteca de visualização de dados (por exemplo, Chart.js, D3.js)
    // para criar e renderizar o gráfico com os dados obtidos do backend

    // Assuming you have already fetched the data and it's in the 'data' variable
    const userIds = data.map(post => post.userId);
    const postCounts = {};

    userIds.forEach(userId => {
        if (!postCounts[userId]) {
            postCounts[userId] = 1;
        } else {
            postCounts[userId]++;
        }
    });

    const chartData = {
        labels: Object.keys(postCounts),
        datasets: [{
            label: 'Number of Posts',
            data: Object.values(postCounts),
            backgroundColor: [
                'rgba(75, 192, 192, 0.5)',  // Verde-água claro
                'rgba(255, 99, 132, 0.5)',  // Rosa claro
                'rgba(255, 205, 86, 0.5)',  // Amarelo claro
                'rgba(54, 162, 235, 0.5)',  // Azul claro
                'rgba(153, 102, 255, 0.5)', // Roxo claro
                'rgba(255, 159, 64, 0.5)'   // Laranja claro
            ],
            borderColor: [
                'rgba(75, 192, 192, 1)',   // Verde-água
                'rgba(255, 99, 132, 1)',   // Rosa
                'rgba(255, 205, 86, 1)',   // Amarelo
                'rgba(54, 162, 235, 1)',   // Azul
                'rgba(153, 102, 255, 1)',  // Roxo
                'rgba(255, 159, 64, 1)'    // Laranja
            ],
            borderWidth: 1,
        }]
    };

    const ctx = document.getElementById('chart-container').getContext('2d');

    const chart = new Chart(ctx, {
        type: 'bar',
        data: chartData,
        options: {
            scales: {
                y: {
                    beginAtZero: true,
                    title: {
                        display: true,
                        text: 'Number of Posts',
                    },
                },
                x: {
                    title: {
                        display: true,
                        text: 'User ID',
                    },
                },
            },
        },
    });

}
function reloadData() {
    // Substitua a URL com a URL real do seu backend
    fetch('http://localhost:8088/api/website/fetch-posts')
        .catch(error => {
            console.error('Erro ao buscar dados do backend:', error);
        });
}
// Event listener para o botão "Fetch Data"
document.getElementById('fetchData').addEventListener('click', function() {
    fetchData();
});

document.getElementById('reloadData').addEventListener('click', function() {
    reloadData();
});