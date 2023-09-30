
const express = require("express");
const cors = require("cors");
const app = express();
const PORT = 8089;
// Use o middleware cors para configurar as políticas CORS
app.use(cors());

app.use((req, res, next) => {
    res.setHeader("Access-Control-Allow-Origin", "*"); // Permite todas as origens
    res.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE"); // Métodos permitidos
    res.setHeader(
        "Access-Control-Allow-Headers",
        "Content-Type, Authorization"
    ); // Cabeçalhos permitidos
    next();
});
app.get("/api/website/dashboard", (req, res) => {

});

// Resto da configuração do servidor

app.listen(PORT, () => {
    console.log(`Servidor rodando na porta ${PORT}`);
});
