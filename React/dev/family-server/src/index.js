require('./models/User');
const express = require('express')
const mongoose = require('mongoose');
const app = express();
const authRoutes = require('./routes/authRoutes')
const userDataRoutes = require('./routes/userDataRoutes')
const bodyParser = require('body-parser');
const requireAuthorization = require('./middleware/requireAuthorization');
var cors = require('cors');

app.use(bodyParser.json());
app.use(cors());
app.use(authRoutes);
app.use(userDataRoutes);

const mongoUri = 'mongodb+srv://admin:Pj0Z3Vcw3uSBooGF@cluster0.snkhw.mongodb.net/<dbname>?retryWrites=true&w=majority'
mongoose.connect(mongoUri, {
    useNewUrlParser: true,
    useCreateIndex: true
});

mongoose.connection.on('connected', () => {
    console.log('connected to mongo instance');
})

mongoose.connection.on('error', (err) => {
    console.error('error connecting to mongo instance', err);
});

app.get('/',requireAuthorization, (req, res) => {

    res.send(`Your email: ${req.user.email}`);
})

app.listen(3000, () => {
    console.log('listening on port 3000')
});

//Pj0Z3Vcw3uSBooGF
