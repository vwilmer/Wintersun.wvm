module.exports = {
    port: process.env.PORT || 7070,
    db: {
        database: process.env.DB_NAME || 'my_db',
        user: process.env.DB_USER || 'postgres',
        password: process.env.DB_PASS || 'postgres',
        options: {
            dialect: 'postgres',
            host: process.env.HOST || 'localhost',
            operatorsAliases: false
            // ssl: true
            // logging: false,
            // freezeTableName: true
        }
    },

    // to configure the jwt
    authentication: {
        jwtSecret: process.env.JWT_SECRET || 'secret'
    }
};
