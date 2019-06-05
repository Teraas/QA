import requests
import pandas as pd
import script

def requestTest(booking_id):
	resp = requests.get('https://restful-booker.herokuapp.com/booking/1/'.format(booking_id))
	print (resp.status)
	
	
#script.test()