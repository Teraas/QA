from selenium import webdriver
from selenium.webdriver.support.select import Select
from selenium.webdriver.common.by import By 
from selenium.webdriver.support.ui import WebDriverWait 
from selenium.webdriver.support import expected_conditions as EC

class Utility:
    def __init__(self,driver):
        self.driver=driver
    def MaximizeBrowser(self):
        self.driver.maximize_window()
    def Navigate(self,URL):
        self.driver.get(URL)
    def selectfromlistByText(self,element,text):
        selectElement=Select(element)
        selectElement.select_by_visible_text(text)
    def click(self,element):
        element.click()
    def SendKeys(self,element,text):
        element.send_keys(text)
    def wait(self,time):
        self.driver.implicitly_wait(time)
    def waitUntilElementById(self,ele):
        WebDriverWait(self.driver, 10).until(
        EC.presence_of_element_located((By.ID, ele)))
    def ClickUsingJavaScript(self,element):
        self.driver.execute_script("arguments[0].click();",element)