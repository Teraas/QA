const express = require('express');
const router = express.Router();
const mongoose = require('mongoose');
const jwt = require('jsonwebtoken');
const User = mongoose.model('User');

router.post('/signup', async (req, res) => {
    console.log( req.body );
    const { email, password, firstName, familyName, mobile, motherName, fatherName } = req.body;
    //const { email, password } = req.body;
    try {
        
        const user = new User({ email, password, firstName, familyName, mobile, motherName, fatherName });
        //const user = new User({ email, password });
        console.log('before user save');
        await user.save();
        console.log('user save');
        const token = jwt.sign({userId: user._id}, 'MY_FAMILY_KEY');
        console.log('get token');
        res.send({token: token});
        console.log('send token');
    } catch (err){
        res.status(422).send(err.message);
    }
});

router.post('/signin', async (req, res) => {
    const { email, password } = req.body;
  
    if (!email || !password) {
      console.log('in pwd compare');
      return res.status(422).send({ error: 'Must provide email and password' });
    }
  
    const user = await User.findOne({ email });
    if (!user) {
      console.log('in user compare');
      return res.status(422).send({ error: 'Invalid password or email' });
    }
  
    try {
      await user.comparePassword(password);
      const token = jwt.sign({ userId: user._id }, 'MY_FAMILY_KEY');
      res.send({ token });
    } catch (err) {
      return res.status(422).send({ error: 'Invalid password or email' });
    }
  });

  router.post('/updateuser', async (req, res) => {
    
    const { email, firstName, familyName, motherName, fatherName } = req.body;
    const user = await User.findOne({ email });
    const password = user.password;
    console.log(user);
    console.log(password);
    // if (!email || !password) {
    //   return res.status(422).send({ error: 'User not authenticated' });
    // }
  
    
    // if (!user) {
    //   console.log('in user compare');
    //   return res.status(422).send({ error: 'User not authenticated' });
    // }
  
    // try {
    //   await user.comparePassword(password);
    //   const token = jwt.sign({ userId: user._id }, 'MY_FAMILY_KEY');
    // } catch (err) {
    //   return res.status(422).send({ error: 'User not authenticated' });
    // }
    try {
      await User.updateOne({ email }, { email, password, firstName, familyName, motherName, fatherName });
      res.send({ message: 'success' });
    } catch (err) {
      console.log(err)
    }
    
  });
  
  module.exports = router;