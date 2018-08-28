// require controller
const AuthenticationController = require('../controller/AuthenticationController');

// policies for users registration, middleware
const AuthenticationControllerPolicy = require('../policies/AuthenticationControllerPolicy');

module.exports = (app) => {
    // create register end point
    app.post('/register',
        AuthenticationControllerPolicy.register,
        AuthenticationController.register);

    // create login end point
    app.post('/login',
        AuthenticationController.login)
};
