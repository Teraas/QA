const express = require('express');
const router = express.Router();
const mongoose = require('mongoose');
const jwt = require('jsonwebtoken');
const User = mongoose.model('User');


  router.post('/get_user_relations', async (req, res) => {
    const data = {};
    const { email } = req.body;
    const user = await User.findOne({ email });
    const password = user.password;
    console.log("in user relations");
    console.log(password);
    try {
      await User.updateOne({ email }, { email, password, firstName, familyName, motherName, fatherName });
      res.send({ message: 'success' });
    } catch (err) {
      console.log(err)
    }
    
  });
  
const getUserRelations = async ({email}) => {
  const user = await User.findOne({ email });
  const root = {
    name: "",
    id: 1,
    hidden: true
  }
  root[0].name=user.name;
  console.log(root[0]);
}

  module.exports = router;