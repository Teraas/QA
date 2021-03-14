import time
from Utilities.Utility import Utility

class HomePage(Utility):
    def __init__(self, driver):
        self.driver = driver
        Utility.__init__(self,driver)

    def GlobalSearch(self):
        self.GSearch= self.driver.find_element_by_id("search_query_top")
        return self.GSearch
    def SearchButton(self):
        self.SearchButton= self.driver.find_element_by_name("submit_search")
        return self.SearchButton
    def DoAGlobalSearch(self,text):
        super().wait(30)
        super().SendKeys(self.GlobalSearch(),text);
        super().wait(20);
        super().click(self.SearchButton());
        time.sleep(20)



