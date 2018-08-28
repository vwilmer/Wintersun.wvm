
module.exports = (sequelize, DataTypes) => {
    const UserModel = sequelize.define('usuarios', {
        email: {
            type: DataTypes.STRING,
            unique: true
        },
        password: DataTypes.STRING
    })
    return UserModel
}
