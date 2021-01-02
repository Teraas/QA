const express = require('express');
const router = express.Router();
const mongoose = require('mongoose');
const jwt = require('jsonwebtoken');
const User = mongoose.model('User');

router.post('/signup', async (req, res) => {
    console.log( req.body );
    const { email, password, firstName, surName, mobile, motherName, fatherName } = req.body;
    try {
        
        const user = new User({ email, password, firstName, surName, mobile, motherName, fatherName });
        
        await user.save();
        const token = jwt.sign({userId: user._id}, 'MY_FAMILY_KEY');
        res.send({token: token});
        
    } catch (err){
        res.status(422).send(err.message);
    }
});

router.post('/signin', async (req, res) => {
    const { email, password} = req.body;

    if(!email || !password ){
        return res.status(422).send({error: 'Must provide email and password'})
    }
    const user = await User.findOne({email});
    console.log( user );
    if(!user) {
        return res.status(422).send({error: 'Invalid email or password'})
    }
    try {
    //await user.comparePassword(password);
    const token = jwt.sign({userId: user._id}, 'MY_FAMILY_KEY')
    res.send({token});
    } catch (err) {
        return res.status(422).send({error: 'Invalid email or password'})
    }
});
module.exports = router;