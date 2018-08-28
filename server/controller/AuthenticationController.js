// const models = require('../model')
const {usuarios} = require('../model');

// ------------ configuration jwt ------------
const jwt = require('jsonwebtoken');
const config = require('../config/index');

// function helper to generate a token
function jwtSignUser(user) {
    const ONE_WEEK = 60 * 60 * 24 * 7;

    return jwt.sign(user, config.authentication.jwtSecret, {
        expiresIn: ONE_WEEK
    });
}

// ------------ configuration jwt ------------


const bcrypt = require('bcrypt');
const BCRYPT_SALT_ROUNDS = 12;

// export the functions
module.exports = {
    // first version
    // register (req, res) {
    //     res.send({
    //         message: `Hello ${req.body.email}! your are was registred`
    //     })
    // }

    async register(req, res, next) {
        try {
            // const user = await models.usuarios.create(req.body)
            // const user = await usuarios.create(req.body);

            const username = req.body.email;
            const password = req.body.password;

            bcrypt.hash(password, BCRYPT_SALT_ROUNDS)
                .then(function (hashedPassword) {
                    const user = usuarios.create({
                        email: username,
                        password: hashedPassword
                    });
                    return user;
                })
                .then(function (user) {
                    res.send(user.toJSON())
                })
                .catch(function (error) {
                    console.log("Error saving user: ");
                    res.status(400).send({
                        error: 'esta cuenta de correo electrónico ya está en uso',
                        data: req.body,
                        message: error
                    })
                    next();
                });

        } catch (error) {
            res.status(400).send({
                error: 'esta cuenta de correo electrónico ya está en uso',
                data: req.body
            })
        }
    },

    // define second method
    async login(req, res) {
        try {
            const {email, password} = req.body;

            const user = await usuarios.findOne({
                where: {
                    email: email
                }
            });

            if (!user) {
                return res.status(403).send({
                    error: 'La información de inicio de sesión era incorrecta'
                })
            }

            // const isPasswordValid = await user.comparePassword(password)
            // const isPasswordValid = password === user.password;

            const isPasswordValid = await bcrypt.compare(password, user.password);

            if (!isPasswordValid) {
                return res.status(403).send({
                    error: 'La información de inicio de sesión era incorrecta'
                })
            }

            const userJson = user.toJSON();

            res.send({
                user: userJson,
                token: jwtSignUser(userJson)
            });

        } catch (err) {
            res.status(500).send({
                error: 'Se ha producido un error al intentar iniciar sesión'
            })
        }
    }
}