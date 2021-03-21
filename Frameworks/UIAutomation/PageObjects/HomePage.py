import time
from Utilities import Utility


searchQueryId = "search_query_top"
searchSubmitButton = "submit_search"
class HomePage(Utility):
    def __init__(self, driver):
        self.driver = driver
        Utility.__init__(self,driver)

    def Search(self,text):
        super().waitUntilElementById(searchQueryId)
        super().SendKeys(self.driver.find_element_by_id(searchQueryId),text);
        super().waitUntilElementById(searchSubmitButton)
        super().click(self.driver.find_element_by_name(searchSubmitButton));
        time.sleep(20)



