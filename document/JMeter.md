## JMeter

[JMeter Homepage.](https://jmeter.apache.org)

JMeter provide test function for Java application measure performance and load.

#### There are variable test function in JMeter.

- Web - HTTP, HTTPS
- SOAP / REST Web service
- FTP
- Database (using JDBC)
- Mail (SMTP, POP3, IMAP)
- ...

#### Support CLI
- Convenient interlock with CI or CD tools.

#### Major concept
- Thread Group : One user per thread
- Sampler : A Action that user doing
- Listener : Work when receive response
- Configuration : Setting value for use by Sampler or Listener
- Assertion : The way how to check if response is successful 
