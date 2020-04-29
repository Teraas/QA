#bank API
#API registeration
from flask import Flask, jsonify, request
from flask_restful import Api, Resource
from pymongo import MongoClient
import bcrypt

app = Flask(__name__)
api = Api(app)
client = MongoClient("mongodb://db:27017")
db = client.BankAPI

users = db["Users"]

def UserExists(username):
    if (users.find({"Username":username}).count == 0):
        return False
    else:
        return True
        

class Register(Resource):
    def post(self):
        postedData=request.get_json()
        username = postedData["username"]
        password = postedData["passord"]
        
        if UserExists(username):
            retJson = {
                "status":301,
                "msg":"Invalid Username"
            }
            return jsonify(retJson)
            
            hashed_pwd = bcrypt.hashpw(password.encode('utf8'),bcrypt.gansalt())
        
        users.insert({
            "Username":username,
            "Password":hashed_pw,
            "Own":0,
            "Debt":0
        })
        retJson = {
                "status":200,
                "msg":"Successfully signed up"
            }
        return jsonify(retJson)
            

def verifyPW(username, password):
    if not UserExists(username):
        return False
    hashed_pw = users.find({"Username":username})[0]["Password"]
    
    if bcrypt.hashpw(password.encode('utf8'),password)==hashed_pw:
        return True
    else:
        return False
        
def cashWithUser(username):
    return users.find({"Username":username})[0]["Own"]
    
def debtWithUser(username):
    return users.find({"Username":username})[0]["Debt"]
    
def returnDictionary(status, msg):
    retJson={
        "Status":status,
        "msg":msg
    }
    return retJson
    
def verifyCredentials(username,password):
    if not UserExists(username):
        return returnDictionary(301,"Invalid Username"), True
    correct_pw= verifyPW(username,password)
    if not correct_pw:
        return returnDictionary(302,"Invalid Password"), True
    return None, True
    
def updateAccount(username,balance):
    users.update({
        "Username":username
    },{
        "$set":{
            "Own":balance
        }
    }
    )
    
def updateDebt(username,balance):
    users.update({
        "Username":username
    },{
        "$set":{
            "Debt":balance
        }
    }
    )
    
class Add(Resource):
    def post(self):
        postedData=request.get_json()
        username = postedData["username"]
        password = postedData["passord"]
        money = postedData["amount"]
        
        retJson, error = verifyCredentials(username,password)
        if error:
            return jsonify(retJson)
            
        if money <=0:
            return jsonify(returnDictionary(304,"Amount entered should more than 0"))
        cash = cashWithUser(username)
        money -=1
        bank_cash = cashWithUser("BANK")
        updateAccount("Bank",bank_cash+1)
        updateAccount(username,cash+money)
        
        return jsonify(returnDictionary(200,"Amount added successfully"))
        
        
class Transfer(Resource):
    def post(self):
        postedData=request.get_json()
        username = postedData["username"]
        password = postedData["passord"]
        money = postedData["amount"]
        to = postedData["to"]
        
        retJson, error = verifyCredentials(username,password)
        if error:
            return jsonify(retJson)
        cash = cashWithUser(username)
        if cash <=0:
            return jsonify(returnDictionary(304,"You are out of money, please add"))
        cash = cashWithUser(username)
        
        if not UserExists(username):
            return jsonify(returnDictionary(301,"Invalid username"))
            
        cash_from = cashWithUser(username)
        cash_to = cashWithUser(to)
        bank_cash = cashWithUser("BANK")
        updateAccount("Bank",bank_cash+1)
        updateAccount(to,cash_to+money-1)
        updateAccount(username,cash_from-money)
        return jsonify(returnDictionary(200,"Amount transferred successfully"))
        
        
class Balance(Resource):
    def post(self):
        postedData=request.get_json()
        username = postedData["username"]
        password = postedData["passord"]
        
        retJson, error = verifyCredentials(username,password)
        if error:
            return jsonify(retJson)
        
        
        retJson = users.find({
            "Username":username
        },{
            "Password":0,
            "_id": 0
        })[0]
        
        return jsonify(retJson)
        

class TakeLoan(Resource):
    def post(self):
        postedData=request.get_json()
        username = postedData["username"]
        password = postedData["passord"]
        amount = postedData["amount"]
        
        retJson, error = verifyCredentials(username,password)
        if error:
            return jsonify(retJson)  
        
        cash = cashWithUser(username)
        debt = debtWithUser(username)
        updateAccount(username, cash+amount)
        updateDebt(username, debt+amount)
        return jsonify(returnDictionary(200,"Load added successfully"))

class PayLoan(Resource):
    def post(self):
        postedData=request.get_json()
        username = postedData["username"]
        password = postedData["passord"]
        amount = postedData["amount"]
        
        retJson, error = verifyCredentials(username,password)
        if error:
            return jsonify(retJson)  
        
        cash = cashWithUser(username)
        debt = debtWithUser(username)
        if cash<amount:
            return jsonify(returnDictionary(303,"Not enough money in account"))
        updateAccount(username, cash-amount)
        updateDebt(username, debt-amount)
        return jsonify(returnDictionary(200,"Load paid successfully"))
        
        
api.add_resource(Register,'/register')
api.add_resource(Add,'/add')
api.add_resource(Transfer,'/transfer')
api.add_resource(Balance,'/balance')
api.add_resource(TakeLoan,'/takeloan')
api.add_resource(PayLoan,'/payloan')

if(__name__=="__main__"):
    app.run(host='0.0.0.0')