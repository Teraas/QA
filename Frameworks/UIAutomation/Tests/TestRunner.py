
import time
from selenium import webdriver
import unittest
from PageObjects import *
from Utilities import Driver

URL = "http://automationpractice.com/"
QueryText = "Printed"
class Test(unittest.TestCase):
    def setUp(self):
        self.driver = Driver.getDriver("Chrome")
        # name the tests beginning with "test" prefix, for unittest to identify any test
    def test_order_creation():
        HomePage.Navigate(URL)
        HomePage.MaximizeBrowser()

        SignIn.signIn(self.driver)

        HomePage.Search(QueryText)  
        HomePage.wait(10)
        
    def tearDown(self):
        self.driver.close()
        self.driver.quit()

if __name__ == '__main__':
    unittest.main()