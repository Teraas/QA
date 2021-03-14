from Utilities.Utility import Utility
import Data.UserData

# UI element selectors
submitLoginId = "SubmitLogin"
password = "password"
username = "username"
class SignIn(Utility):
    def __init__(self, driver):
        self.driver = driver
        Utility.__init__(self,driver)
    def signIn(self):
        super().SendKeys(self.driver.find_element_by_id(username),Data.UserData.Data.username)
        super().wait(1) #avoid static waits as much as possible
        super().SendKeys(self.driver.find_element_by_id(password),Data.UserData.Data.password)
        super().wait(1) #avoid static waits as much as possible

        super().click(self.driver.find_element_by_id(submitLoginId))
        super().click(self.SubmitLogin())
        super().wait(30)


