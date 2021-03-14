from selenium import webdriver
from selenium.webdriver.support.select import Select

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
    def ClickUsingJavaScript(self,element):
        self.driver.execute_script("arguments[0].click();",element)