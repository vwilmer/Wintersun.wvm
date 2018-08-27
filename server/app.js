const express = require('express');
const bodyParser = require('body-parser');
const cors = require('cors');
const morgan = require('morgan');

const app = express();

app.use(morgan('combined'));

// parse application/x-www-form-urlencoded
app.use(bodyParser.urlencoded({ extended: false }));

// parse application/json
app.use(bodyParser.json());

// enable all cors requests
app.use(cors());

app.get('/estado', (req, res) => {
    res.send({
        mensaje: 'bua'
    });
});

app.listen(7070, () => {
    console.log('servidor corriendo en 7070')
});