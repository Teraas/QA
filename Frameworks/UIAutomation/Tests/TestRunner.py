
import time
from selenium import webdriver
import unittest
from PageObjects import *

class Test(unittest.TestCase):
    def setUp(self):
        self.driver = webdriver.Chrome('./chromedriver')
        # name the tests beginning with "test" prefix, for unittest to identify any test
    def test_order_creation():
        HomePage.Navigate("http://automationpractice.com/")
        HomePage.MaximizeBrowser()
        HomePage.DoAGlobalSearch("Printed")
        HomePage.wait(10)

        SignIn.signIn(self.driver)

        
    def tearDown(self):
        self.driver.close()
        self.driver.quit()

if __name__ == '__main__':
    unittest.main()