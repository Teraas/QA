from selenium import webdriver
from sys import platform

ChromeWebDriverPathLinux = './chromedriver'
FirefoxWebDriverPathLinux = './firefoxdriver'
ChromeWebDriverPathWin = 'chromedriver.exe'
FirefoxWebDriverPathWin  = 'firefoxdriver.exe'

class Driver:
    self.ChromeWebDriverPath = ''
    def getDriver(name):

        if (name == "Chrome"):
            if "linux" in platform:
                self.driver = webdriver.Chrome(ChromeWebDriverPathLinux)
            elif "win" in platform:
                self.driver = webdriver.Chrome(ChromeWebDriverPathWin) 

        else:
            if "linux" in platform:
                self.driver = webdriver.Firefox(FirefoxWebDriverPathLinux)
            elif "win" in platform:
                self.driver = webdriver.Firefox(FirefoxWebDriverPathWin)
        return self.driver