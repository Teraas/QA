const mongoose = require('mongoose');
const bcrypt = require('bcryptjs');

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
        required: false
    },
    familyName: {
        type: String,
        required: false
    },
    mobile: {
        type: String,
        required: false
    },
    motherName: {
        type: String,
        required: false
    },
    fatherName: {
        type: String,
        required: false
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
            resolve(true);
        });

    });
}

mongoose.model('User', userSchema);
