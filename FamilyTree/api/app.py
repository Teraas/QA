from flask import Flask, jsonify, request
from flask_restful import Api, Resource
from pymongo import MongoClient
import bcrypt

app = Flask(__name__)
api = Api(app)
client = MongoClient("mongodb://db:27017")
db = client.FamilyTreeDB
users= db["Users"]

def UserExists(username):
    if (users.find({"Username":username}).count == 0):
        return True
    else:
        return False
        
class GetUserDetails(Resource):
    def post(self):
        postedData=request.get_json()
        username=postedData["username"]
        if UserExists(username):
            retJson = {
                "status":301,
                "msg":"Invalid Username"
            }
            return jsonify(retJson)
        resp = users.find({
            "Username":username
        })[0]
        return jsonify(resp)

class Register(Resource):
    def post(self):
        postedData=request.get_json()
        username = postedData["username"]
        
        if UserExists(username):
            retJson = {
                "status":301,
                "msg":"Invalid Username"
            }
            return jsonify(retJson)
        
        users.insert({"Username":postedData["username"],"Brother":postedData["brother"],"Sister":postedData["sister"],"Brother-In-Law":postedData["brother-in-law"],
            "Sister-In-Law":postedData["sister-in-law"],"Father-In-Law":postedData["father-in-law"],"Mother-In-Law":postedData["mother-in-law"],"Mother":postedData["mother"],
            "Father":postedData["father"],"Son":postedData["son"],"Daughter":postedData["daughter"],
            "Son-In-Law":postedData["son-in-law"],"Daughter In-Law":postedData["daughter-in-law"],"Name":postedData["name"]
        })
        retJson = {
                "status":200,
                "msg":"Successfully registered Username"
            }
        return jsonify(retJson)

class Update(Resource):
    def post(self):
        postedData=request.get_json()
        username = postedData["username"]
        
        if not UserExists(username):
            retJson = {
                "status":301,
                "msg":"Invalid Username"
            }
            return jsonify(retJson)
        users.update({
            "Username":username
        },{
            "$set":{
                "Brother":postedData["brother"],"Sister":postedData["sister"],"Brother-In-Law":postedData["brother-in-law"],
                "Sister-In-Law":postedData["sister-in-law"],"Father-In-Law":postedData["father-in-law"],"Mother-In-Law":postedData["mother-in-law"],"Mother":postedData["mother"],
                "Father":postedData["father"],"Son":postedData["son"],"Daughter":postedData["daughter"],
                "Son-In-Law":postedData["son-in-law"],"Daughter In-Law":postedData["daughter-in-law"]
        }
        })
        retJson = {
                "status":200,
                "msg":"Successfully updated Username"
            }
        return jsonify(retJson)

api.add_resource(Register,'/register')
api.add_resource(Update,'/update')
api.add_resource(GetUserDetails,'/getuserdetails')
if __name__=="__main__":
	app.run(debug=True,host='0.0.0.0',port='5001')