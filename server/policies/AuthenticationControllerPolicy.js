/*
* JOI: Object schema description language and validator for JavaScript objects
* */

const Joi = require('joi');

module.exports = {
    register(req, res, next) {
        const schema = {
            email: Joi.string().email(),
            password: Joi.string().regex(
                new RegExp('^[a-zA-Z0-9]{4,10}$')
            )
        }

        const {error} = Joi.validate(req.body, schema)

        if (error) {
            switch (error.details[0].context.key) {
                case 'email':
                    res.status(400).send({
                        error: 'Debe proporcionar una dirección de correo electrónico válida'
                    });
                    break;
                case 'password':
                    res.status(400).send({
                        error: `La contraseña proporcionada no coincide con las siguientes reglas:
              <br/>
              1. Debe contener SOLAMENTE los siguientes caracteres: minúsculas, mayúsculas, numéricos.
              <br/>
              2. Debe tener al menos 4 caracteres de longitud y no más de 10 caracteres de longitud.`
                    });
                    break;
                default:
                    res.status(400).send({
                        error: 'Información de registro inválida.'
                    });
            }
        } else {
            next()
        }
    }
};
