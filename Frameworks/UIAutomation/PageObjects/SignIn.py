from Utilities import Utility
import Data.UserData

# UI element selectors
submitLoginId = "SubmitLogin"
passwordId = "password"
usernameId = "username"
searchQueryId = "search_query_top"
class SignIn(Utility):
    def __init__(self, driver):
        self.driver = driver
        Utility.__init__(self,driver)
    def signIn(self):
        super().waitUntilElementById(usernameId)
        super().SendKeys(self.driver.find_element_by_id(usernameId),Data.UserData.Data.username)
        super().waitUntilElementById(passwordId) #avoid static waits as much as possible
        super().SendKeys(self.driver.find_element_by_id(passwordId),Data.UserData.Data.password)

        super().waitUntilElementById(submitLoginId)
        super().click(self.driver.find_element_by_id(submitLoginId))
        super().waitUntilElementById(self.searchQueryId)


