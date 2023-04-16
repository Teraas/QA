#AlphaGrep Assignment - Exchange Mock
- Requirements:
  On a front office trading platform, one of the services would send order orders to exchange for
  execution. Exchange may send a variety of responses to an order depending on various factors.
  Automation is an essential part of SDLC and we would like to automate this flow such that exchange
  behavior can be controlled and made predictable.
  One possible way to control the exchange behavior is to implement a rule engine which can be used
  to generate a specific kind of a response to a request matching a certain criterion.
  The rules can be –
  If the qty of an order is multiple of x then generate NEW_ORDER_CONFIRM otherwise reject  
  If the symbol is xyz then generate new_order_confirm and trade_confirm 
  If price is greater than x for symbol xyz then reject 
  If price is 123 then generate reject 
  And so on..
  Implement an application which –
1. Receives the incoming requests
2. Parses the requests
3. Evaluates the configured rules
4. Send the response back as per the rule outcome. The rule can be configured to send more
   than one response to a request.
   The request/response may be sent/received over TCP or from file or shared memory. You are free to
   choose the transport mechanism of your choice.

# Requirement 2:
Also, write a script which compares the received responses with the responses stored in a golden
copy source. Any difference should be highlighted in a file/email or over console.

# Design considerations:
Request fields - 15, separation - ‘|’
Response fields - 12, separation - ‘|’
Field level consideration(datatypes etc) highlighted in classes.
Request/Response fields and their names are fixed.

Request consume mechanism for app - Files - txt
Respoinse publish mechanism for app - Files - csv

Rule-Engine and rule configs - Pass rules names from CLI or client. Use a rule interface.

Response Comparator - Native java, report as CSV as well as Response Object. A response object will give assertion flexibility in client classes, rather than again loading response from file always.
Will assume the golden copy is in csv files for usecases. Even if in a DB, they could be added in csv.
Will assume for comparison, the response(s) for a request can be uniquely identified by Exchange_Order_Id even in case a single request generates multiple responses.
Consumes file from proj_home
Application - ExchangeMock

App features:
Add rules
Update rules
Activate rules for usecase/test
Request parser
Run app and execute rules
Generate response(s)
Comparare responses
File utility to read/write csvs
Scalability: Allows any no of rules without much effort
Testability:
Write test cases - use app and use Comaparator
Add tests in existing framework, use app as dependency. Also consume req file, and publish response files.
Maintenance: Rule execution follows strategy pattern, which allows to add modify rules without touching core classes
Reusability: Every feature is modularised.

# Test cases:
Added junit tests in test/java

# System requirement:
- Java 1.8+
- Linux/Mac
# Limits:
Currently every rule is returning 1 response. But can be updated to return List of responses.
App can not integrated with only JVM based clients/frameworks
App is not completely env-agnostic, few feature like file reading are not handling OS specific paths