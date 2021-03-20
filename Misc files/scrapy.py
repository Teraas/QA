import urllib
import requests
import BeautifulSoup
def crawl():
	urllib.request.urlopen("http://www.python.org/");
	print ("here")
	url = 'https://www.tutorialspoint.com/How-I-can-convert-a-Python-Tuple-into-Dictionary'
	print ("here1")
	response = requests.get(url)
	print ("here2")
	html = response.content
	print (html)
	soup = BeautifulSoup(html)
	table = soup.find('tbody', attrs={'href': 'stripe'})
	print (table)
	f= open("test1.txt","w+")
	f.write(str(html))
	

if __name__ == "__main__":
	crawl()