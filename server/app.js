const express = require('express');
const bodyParser = require('body-parser');
const cors = require('cors');
const morgan = require('morgan');
// get only sequelize object
const {sequelize} = require('./model')
const config = require('./config/index')
const app = express();

app.use(morgan('combined'));

// parse application/x-www-form-urlencoded
app.use(bodyParser.urlencoded({ extended: false }));

// parse application/json
app.use(bodyParser.json());

// enable all cors requests
app.use(cors());

// define routes
require('./route')(app);

// sync with database postgres
sequelize.sync({
    force: false
}).then(() => {
    app.listen(config.port)
    console.log(`servidor corriendo en ${config.port}`)
}).catch(err => {
    console.error('Unable to connect to the database:', err)
});

