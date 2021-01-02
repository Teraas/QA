const mongoose = require('mongoose');
const bcrypt = require('bcrypt');

const userSchema = new mongoose.Schema({
    email: {
        type: String,
        unique: true,
        required: true
    },
    password: {
        type: String,
        required: true
    },
    firstName: {
        type: String,
        required: true
    },
    surName: {
        type: String,
        required: true
    },
    mobile: {
        type: String,
        required: true
    },
    motherName: {
        type: String,
        required: true
    },
    fatherName: {
        type: String,
        required: true
    }
});

userSchema.pre('save', function(next)  {
    const user = this;

    if(!user.isModified('password')){
        return next();
    }

    bcrypt.genSalt(10, (err, salt) => {
        if (err){
            return next(err);
        }
    bcrypt.hash(user.password, salt, (err, hash) => {
        if (err){
            return next(err);
        }
        user.password = hash;
        next();
        });
    }); 
});

userSchema.methods.comparePassword = function(userPassword) {
    const user = this;

    return new Promise((resolve, reject) => {
        bcrypt.compare(userPassword, user.password, (err, isMatch) => {
            if (err){
                return reject(err);
            }

            if (!isMatch) {
                return reject(false);
            }

        });

    });
}

mongoose.model('User', userSchema);
