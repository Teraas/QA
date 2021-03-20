import requests
import mysql.connector
import script
import random,string
DBA="jdbc:mysql://localhost:3306/users"

addQuery='insert into users.usertable (user_id,relationWith,relationName,marriageStatus,partner,name,type,description,address,phone,current_contact_person) values (%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s)'
relations=["Brother","Sister","Brother In-Law","Sister In-Law","Father In-Law","Mother In-Law","Mother","Father","Son","Daughter","Son In-Law","Daughter In-Law"]
def requestTest(booking_id):
	resp = requests.get('http://127.0.0.1/post'.format(booking_id))
	print (resp)
def test():
	i=1
	while i<100:
		addUser(random.randint(2, 9999),random.randint(2, 9999),random.choice(relations),"Married",random.randint(2, 9999),''.join(random.sample(string.ascii_lowercase, 5)),"New","Test","","8142210004","Self")
		i=i+1
def addUser(user_id,relationWith,relationName,marriageStatus,partner,name,type,description,address,phone,current_contact_person):
	db = mysql.connector.connect(host="localhost",user="root",passwd="Paggo@1312")
	cursor = db.cursor(prepared=True)
	
	val=(user_id,relationWith,relationName,marriageStatus,partner,name,type,description,address,phone,current_contact_person)
	print (val)
	print (addQuery)
	cursor.execute(addQuery,val)
	db.commit()
	print ("User is inserted successfully")
	data = cursor.fetchone()
	db.close()
	user=name;
	print (data)
def getUser():
	db = mysql.connector.connect(host="localhost",user="root",passwd="Paggo@1312")
	cursor = db.cursor()
	cursor.execute("SELECT * from users.usertable")
	data = cursor.fetchone()
	db.close()
	print (data)
if __name__=="__main__":
	test()