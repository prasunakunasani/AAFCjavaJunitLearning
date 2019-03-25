# AAFCspringLearning
Trying to figure out Spring (beginning), Junit and Mockito

# Painfully detailed notes 

Mockito Learning:  
[Mockito Intro - Stubbing](#commit-s3-step-1-2-3)  
[Mocking](#commit-s3-step-4)  
[Mocking List interface](#commit-s3-step-5)  
[BDD Behaviour](#commit-s3-step-6)  
[Verify calls on Mocks](#commit-s3-step-7---verify-calls-on-mocks)   
[Capturing arguments passed to Mocks](#commit-s3-step-8---capturing-arguments-passed-to-a-mock)  
[Mockito Annotations @Mock @InjectMocks @RunWith, @Captor](#commit-s3-step-10---mockito-annotations-mock-injectmocks-runwith-captor)  
[Mockito Spy](#commit---s3-step-13---mockito-spy)  

Junit Learning:    
[Junit Intro](#commit-s2-section-3-lecture-6---junit-intro)  

Web Logic  Learning: 
[Understanding WebLogic Domains]()
[]()


# Junit

###### Commit: S2 Section 3, Lecture 6 - Junit intro
From: http://www.vogella.com/tutorials/JUnit/article.html  
Where should the test be located?  
Typical, unit tests are created in a separate project or separate source folder to keep the test code separate from the real code. The standard convention from the Maven and Gradle build tools is to use:

src/main/java - for Java classes
src/test/java - for test classes
- See Shortcuts on how to run a test

# Mockito

###### Commit: S3 Step 1, 2, 3
- Here: https://github.com/whereismybaymax/AAFCjavaJunitLearning/commit/d62b14117679c0fe427f097b3b0f1f0cdc21ff9e 
- TodoBusinessImpl is SUT - System Under test
- TodoService is a dependency for TodoBusinessImpl
- A stub: A class that returns dummy data
- Stubs are not the right way to go because: 
    - assume you want to test different output based on dif user, you'll have to add more logic to the stubs
        - code becomes complex when you want to do dynamic conditions 
            - wanting to get different values for dif scenarios
    - assume the TodoService interfaces to more functions, then you'll have to add more functions in the stub
        - service definition - more maintenance 
- https://github.com/in28minutes/MockitoTutorialForBeginners/blob/master/Step01.md  
- https://github.com/in28minutes/MockitoTutorialForBeginners/blob/master/Step02.md  
- https://github.com/in28minutes/MockitoTutorialForBeginners/blob/master/Step03.md  

###### Commit: S3 Step 4
- Here: https://github.com/whereismybaymax/AAFCjavaJunitLearning/commit/42117a4e337dc4dcc4022b74d51eee81368c78e5
- Static imports - Eg- assertEquals is a static method that's in org.junit.Assert class
- Mocks
    - mocking is creating objects that simulate the behaviour of real objects
    - unlike stubs, mocks can be dynamically created from code - at runtime 
    - frameworks like mockito allow one to create a dummy class 
- https://github.com/in28minutes/MockitoTutorialForBeginners/blob/master/Step04.md  

###### Commit: S3 Step 5
- Here: https://github.com/whereismybaymax/AAFCjavaJunitLearning/commit/1753387520e39c16d6e338ac91b7018ff713964f 
- Building a mock for List in java - List in an interface 
- ListTest.java code contains: 
    - a minimum setup stuff needed to use @Mock annotation vs the one shown in the course using mock(blah.class)
    - How to mock methods with or without parameters
    - How to mock multiple responses for the same method
    - How to use Argument Matchers
    - How to mock ThrowException stuff - PS - the test for it doesn't work but may work for real life
- https://github.com/in28minutes/MockitoTutorialForBeginners/blob/master/Step05.md  

###### Commit: S3 Step 6 
- Here: https://github.com/whereismybaymax/AAFCjavaJunitLearning/commit/5bb1dbb1796399feeee0f78beb1b4003383d5ebb 
- BDD - Behaviour driven development (http://static.javadoc.io/org.mockito/mockito-core/2.13.0/org/mockito/BDDMockito.html)
- Eg - Agile projects use user stories that are split to scenarios
    - A user story is a very high-level definition of a requirement, containing just enough information so that the developers can produce a reasonable estimate of the effort to implement it and test it. 
> Story: Returns go to Stock   
In order to keep track of stock   
As a store owner
I want to add items back to stock when they're returned

> Scenario 1: Refunded items should be returned to stock    
Given that a customer previously bought a black sweater from me  
And I have three black sweaters in stock.   
When he returns the black sweater for a refund  
Then I should have four black sweater in stock  

> Scenario 2: Replaced items should be returned to stock  
Given that a customer previously bought a blue garment from me  
And I have two blue garments in stock  
And three black garments in stock  
When he returns the blue garments for a replacement in black  
Then I should have three blue garments in stock   
And two black garments in stock  

- Tests should be written in a *Given, When, Then*  
- At the beginning of the test, 
    - Given is the setup
    - When is the actual method call
    - Then all the asserts show that something has happened
- Mockito BDD has specific methods that help one to write tests in that particular manner
- Egs in code: 
    - given(mockList.get(anyInt())).willReturn("Blah"); 
    - given(todoServiceMock.retrieveTodos("DummyName")).willReturn(todos); 
    - assertThat(expected,is(actual)); 
    - assertThat(filteredTodos.size(),is(2));
- https://github.com/in28minutes/MockitoTutorialForBeginners/blob/master/Step06.md  

###### Commit: S1 Section 4, Lecture 22
- Here: https://github.com/whereismybaymax/AAFCjavaJunitLearning/commit/5e3fbe5c052025847c6b6ec1c7d36477927488e8 
- one requirement - The whole point of using interfaces is so one can easily change the coach for another sport: 
        - Hockey, Cricket, Tennis, Gymnastics, etc...
- the other requirement - App should be configurable
    - not configurable yet since the new TrackCoach() or the new BaseballCoach() is still hardcoded in MyApp.java

###### Commit: S3 Step 7 - Verify Calls on Mocks
- Here: https://github.com/whereismybaymax/AAFCjavaJunitLearning/commit/185d012e13ed194804573ff234466ed60345ece4
- May want to check if a method is called or not! and also how many times a method is called
- until now, we used something like given(mockList.get(anyInt())).willReturn("Blah")
    - but no idea if mockList.get(anyInt()) was called at all
- Basically, we need to know how to test void functions
    - verify methods in Mockito let you check if some method is called
        - It internally uses: verify(mock, times(1)).someMethod("was called once"); 
- Egs in Code: 
    - verify(todoServiceMock).deleteTodo("Learn Mockito");
    - verify(todoServiceMock, never()).deleteTodo("Learn Spring");
    - verify(todoServiceMock, times(2)).deleteTodo("Learn Junit");
    - verify(todoServiceMock, atLeastOnce()).deleteTodo("Learn Junit");
    - verify(todoServiceMock, atLeast(5)).deleteTodo("Learn Mockito");
- Just reference in general (https://stackoverflow.com/questions/27787487/java-verify-void-method-calls-n-times-with-mockito): 
    - verify(mock, times(5)).someMethod("was called five times");
    - verify(mock, never()).someMethod("was never called");
    - verify(mock, atLeastOnce()).someMethod("was called at least once");
    - verify(mock, atLeast(2)).someMethod("was called at least twice");
    - verify(mock, atMost(3)).someMethod("was called at most 3 times");
    - verify(mock, atLeast(0)).someMethod("was called any number of times"); // useful with captors
    - verify(mock, only()).someMethod("no other method has been called on the mock");
- https://github.com/in28minutes/MockitoTutorialForBeginners/blob/master/Step07.md

###### Commit: S3 Step 8 - Capturing Arguments passed to a Mock
- Here: https://github.com/whereismybaymax/AAFCjavaJunitLearning/commit/0b8d9ec532f0fe08fb5f23fde769b0ae07d39a29 
- How to capture an argument that's passed to a mock?
- Side note: Before, used verify but for BDD, can use then(mockedObjectInstance).should(never()).method("argument");
- Argument Capture can be used to capture more complex objects 
- Egs in code: 
    - given(todoServiceMock.retrieveTodos("DummyName")).willReturn(todos);
    - then(todoServiceMock).should().deleteTodo("Learn Mockito");
    - then(todoServiceMock).should(never()).deleteTodo("Learn Spring");
    - ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);
    - then(todoServiceMock).should(times(3)).deleteTodo(stringArgumentCaptor.capture());
    - assertThat(stringArgumentCaptor.getAllValues().size(),is(3)); 
- https://github.com/in28minutes/MockitoTutorialForBeginners/blob/master/Step08.md

###### S3 Step 9 - Hamcrest Matchers (skipped)

###### Commit: S3 Step 10 - Mockito Annotations @Mock @InjectMocks @RunWith, @Captor
- Here: https://github.com/whereismybaymax/AAFCjavaJunitLearning/commit/88cf0c64d31b9365bf0476d58553c9a0c132be97
- @Mock will make an mock
    - ~~can add @RunWith(MockitoJunitRunner.class) on top of the test class~~ - Works with out. Don't know the details of what this is for. 
    - Needs to be placed just below the class
- @InjectMock
    - eg, in TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceMock), 
        - we are creating a new instance of TodoBusinessImpl using the todoServiceMock as a constructor parameter. 
            - So, we are trying to inject the todoServiceMock into the TodoBusinessImpl 
                - instead of doing this manually, mockito can do it
        - Mockito can look at look at all things declared as InjectMock 
        - If you do: 
            - @InjectMocks
            - TodoBusinessImpl todoBusinessImpl
                - Mockito will look at all things present in actual class todoBusinessImpl
                    - in there, the things that are declared are todoService
        - So, it will look at all the dependencies of todoBusinessImpl and see if there is a mock that matches it
            - So, it will automatically inject the todoService so the original line can be removed
    - Therefore, if there are 5 dependencies, can just mock them and using InjectMock then mockito will inject the dependencies.
    - This makes the test more readable
 - @Captor
    - Before, to declare an argument captor, you did this: 
        -  ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);  
    - Now, can do: 
        - @Captor
        - ArgumentCaptor<String> stringArgumentCaptor
            - this automatically creates a captor of a particular type

###### S3 Step 11 - Mockito Junit Rules - Skipped cause' not in Junit 5

###### S3 Step 12 - Real World Mockito example with Spring - No actual implementation. Just random notes: 
- Eg - For a more complicated project, you might have components such as 'Data' and 'Business'
    - Each of these might have 'api' and 'impl'
        - API is the interface stuff while impl is the actual implementation
    - Therefore, if the business has to talk to the database side, it will use the API part of it. 
    
###### Commit - S3 Step 13 - Mockito Spy
- With mocks, we don't care about the impl of class but sometimes might want to override specific functionality of class - then we need a spy
- A Spy gets all logic from the class. 
    - So, you can override specific methods (using stubbing (when().thenReturn()...))
        - Only the overridden method changes. The rest of the methods are still working normally. 
- With a spy, you let the real action happen while listening onto it
    - Like a spy watching what's happening
- Some takeaways 
    - If you call verify on a mock, you can check what methods are being called related to the mocked class
    - If you call verify on a spy, you can check what methods are being called related to the actually class which you are testing
        - Probably more useful during Integration testing (Nvm...I don't know...)
    - spies are apparently used usually when you don't have access to the code or dependencies in a legacy system
        - in general, apparently stick to mocks since with spys, you're using a bit of actual logic and mocked methods where it can get confusing
    - https://stackoverflow.com/questions/15052984/what-is-the-difference-between-mocking-and-spying-when-using-mockito 
        - When Mockito creates a mock – it does so from the Class of a Type, not from an actual instance. The mock simply creates a bare-bones shell instance of the Class, entirely instrumented to track interactions with it. On the other hand, the spy will wrap an existing instance. It will still behave in the same way as the normal instance – the only difference is that it will also be instrumented to track all the interactions with it.
        - If there is an object with 8 methods and you have a test where you want to call 7 real methods and stub one method you have two options:
        - Using a mock you would have to set it up by invoking 7 callRealMethod and stub one method
        - Using a spy you have to set it up by stubbing one method
        - The official documentation on doCallRealMethod recommends using a spy for partial mocks.
        
###### S3 Step 13 - Why does Mockito not allow stubbing final and private methods. Just random notes: 
- With unit testing, we are suppose to test the public interfaces of the class
    - Mockito wants to promote that and doesn't allow mocking of private methods
    
- Static methods is apparently bad for OOD so also not allowed (PowerMock allows this - but the code is still bad) 


###### Understanding Weblogic concepts

![WeblogicDomains](https://github.com/prasunakunasani/AAFCjavaJunitLearning/blob/master/Notes/Images/2018-09-25%2011_47_16-Beginning%20Oracle%20WebLogic%20for%20Administrators%20_%20Udemy.png)
- Web Domain 
    - a logically related group of web logic servers and resources
    - a domain always includes an administration server
- Admin server 
    - a central point in which you can create, manage and monitor resources and servers within a domain
    - can also contain 0 or more managed servers 
- Managed Servers
    - where you deploy resources like web apps, web services, ejs, data sources, messaging resources, etc
    - where the bulk of app lives

![WebLogicDeployment](https://github.com/prasunakunasani/AAFCjavaJunitLearning/blob/master/Notes/Images/2018-09-25%2011_50_39-Beginning%20Oracle%20WebLogic%20for%20Administrators%20_%20Udemy.png)
- Since the weblogic domain is logical in nature, it can be deployed in a distributed fashion across many servers or against a single server
- In diagram, got admin server deployed in single server and then Managed servers 1 and 2 deployed on another server
- Then server 3 is deployed on another stand alone machine
- So, weblogic domains allow a scalable architecture to deploy resources across multiple servers (think cloud scale)

- Domains can be defined based on a lot of factors
    - Eg: by department (HR, etc), geographic region, function (security, operations, etc)
 
**What's in a Domain**
- Domain consists of a admin server and web logic server and very logical in nature
- Domain has one admin server and 0 or many managed servers
    - 0 is allowed with just an admin server but this might only be used in dev environments (when you just create a domain)
    - In prod, you'd create managed servers and off load processes onto the managed servers

**Contents of a Domain**
- XML configuration files that define all the Servers, Resources, Services contained within that domain
- Security Stores
    - eg: embedded ldap info, key store info for configuring SSL and trust
- Log files
    - for app services and services themselves
- May contain application files
    - eg: enterprise archive, web archive or ejb
- Persistent file stores
    - eg: persisting jsm messages
- Transaction Logs
    - If transactions are enabled in the app

**What is a WebLogic Server**
![WeblogicService](https://github.com/prasunakunasani/AAFCjavaJunitLearning/blob/master/Notes/Images/2018-09-27%2013_06_25-Beginning%20Oracle%20WebLogic%20for%20Administrators%20_%20Udemy.png)
- It's a combination of the domain configuration above associated with a set of configuration files that define it's parameters(run time parameters and configuration)
- WLS 12c Runtime is a set of java libraries
    - that runs on top of Java. 
    - Weblogic is a JVM process listening in on network ports that you defined and configuration
    
**WebLogic Resources**
- Domains also contain resources and services
- Some are used by managed servers and some by the application itself
- Administrators can define these resources and deploy them to WL servers with the domain
- For managed servers, you can define such resources as machine definitions which identify an association between a managed server and a physical or virtual host
- Most of the resources you'll define are application specific
     - such as: 
        - Security Providers
        - Machines
        - Virtual Hosts
        - JDBC
        - JMS messaging services (like JMS destination or a foreign JMS provider)
        - Persistent Stores
        - Logging
        - Diagnostics & Monitoring
        - Clusters

**Installing Java**
- In order to run Weblogic, need; 
    - at least JDK 7
    - Oracle Generic Installed (Oracle Weblogic 12.1.3)
        - need Java to installed generic installer
- Java installation link: https://www.oracle.com/technetwork/java/javase/downloads/index.html
- Look for an installation guide for the version that you are installing on google

**Installing Oracle**

- Random info: 
    - - Working directory - can organize it however you want. 
          - Eg: /u01/udemy
              - Then can point WEBLOGIC_HOME and ORACLE_HOME back to /u01/udemy

- Oracle WebLogic 12.1.3 (Generic 880 MB Installer)
    - Need JDK separately since it no longer comes with Java JDK installation
- Oracle installation link: https://www.oracle.com/technetwork/middleware/weblogic/downloads/wls-main-097127.html
- Eg: Assume fmw_12.1.3.0.0_wls_jar (file name for generic installer) is saved in /u01/udemy directory
- Step 1: Run generic WebLogic installer
    - $java -jar fmw_12.1.3.0.0_wls.jar
        - this will execute the java program
- Step 2: Specify Oracle Inventory location
    - Inventory directory is used to maintain an inventory of all the oracle products installed in system
    - Eg: /u01/udemy/oraInventory
    - 'Operating system group' is the system group that will have access to the inventory
        - Eg: staff, whatever. 
- Step 3: Specify Oracle Home Location 
    - Basically where you want the wls12130 to go (eg: for SMS it was C:\Oracle\wls12130)
    - eg: /u01/udemy/Oracle/Middleware/Oracle_home
    - where teh core of weblogic will be installed
- Step 4: Specify installation type
    - Example code can be used to deploy sample projects to weblogic.
        - It also includes sample scripts to do administration type activities
    - The guy picked 'Complete with Examples', Next 
    - For SMS you picked WebLogic Server on Nicolas's computer. Don't know what David picked on yours. 
- Just go through the rest of the stuff
    - Deselect 'Automatically launch the Quickstart Configuration Wizard'
- In the terminal window, if you do a ls, you'll see the new files and stuff 

**Creating a WebLogic Domain**
- Eg: Go back to /u01/udemy
- In order to run a weblogic domain, need the configuration wizard
    - this is a shell script that will launch a wizard driven UI that will walk you through a creating of a weblogic domain
- cd to where the 'wlserver\common\bin' folder is 
    - It has shell scripts and we care about config.sh
    - Eg: $cd \u01\udemy\Oracle\Middleware\Oracle_Home\wlserver\common\bin
    - SMS eg: C:\Oracle\wls12130\wlserver\common\bin
- $config.exe 
    - ./config.sh on mac
- First find location of domain you want to create. 
    - Select 'Create a new domain'
    - Eg: For purpose of tutorial, it can be created in u01\udemy\domains\base_domain directory
        - base_domain is the name of the domain and can be whatever
    - SMS Eg: C:\Oracle\wls12130\user_projects\domains\SMS_domain
- Next page: Templates page
    - Within weblogic you can create a domain based off a number of templates
        - 'Basic WebLogic Domain' defines a basic configuration of a web logic domain
        - templates are used to speed up the configuration and creating of a domain
    - Select Basic WebLogic Domain
- Next: Define a username and password for administrator account of weblogic
    - this account will be used to startup weblogic sever and log into the admin console
    - SMS Eg: This is probably the credentials you enter into the console when you run SMS
- Next: Configuration wizard
    - Can create web logic domain in two modes
        - Development
            - strictly used for dev and qa environments
            - For Continuous integration model, can drop stuff into a directory and web logic will automatically deploy it
                - this is also possible in dev mode and not prod
            Eg: - just pick this
        - Production
            - a more secure configuration of web logic
            - requires you to enter username and password to startup the server 
            - does not put to application for deployment
    - jdk can just be the default one
- Next: Advanced configuration page: 
    - Can modify these settings later so can leave these unchecked
- Next: Review screen
    - if you click on the Admin Server, can see the default settings for admin server
        - eg: 
            - Listen Addresses: All Local Addresses
            - Listen Port: 7001
                - this is the default weblogic port
            - Server Name: AdminServer 
    - these settings can be changed later in admin console if you want. 
- Next, you'll get the Domain Location and Admin Server URL and click finish
    - eg: Domain location: /u01/udemy/domains/base_domain
    - eg: Admin Server URL: http://blah.local:7001/console
- Now, if you ls in /u01/udemy, you should see a domains directory and the base_domain directory

**Domain directory layout (within blah_domain directory) and how to read WLS output**
- Below are a list of directories you'll see the domain directory
- autodeploy
- bin
    - has a series of shell scripts for starting and stopping weblogic and nodemanager
- config
    - this directory maintains the configuration for every server and resource within domain
    - imp - don't modify anything in here. If any was to change, it would be changed from the admin console
    - has config.xml file
        - main configuration file for weblogic
        - here, all the servers and domain configurations are specified
        - all the stuff in there are the configuration stuff that you see in the admin console
            - eg: Adminserver is defined as a <server> element
- console-ext
- fileRealm.properties
- init-info
- lib
- nodemanager
- security
- servers
    - will have a list of all web logic servers you create
    - when you create a weblogic domain, only server you'll see is AdminServer
    - as you create additional managed servers, they will appear in this directory
    - has AdminServer directory
        - security directory in here
        - when we start AdminServer for the first time, will see additional directories including log files
        - will find log files for admin server and domain later on 
- startWebLogic.sh

**Starting weblogic using script**
- Within the domain directory, look for bin directory
- bin
    - to Start weblogic, startWebLogic.exe
        - execute in script and you'll see output
            - You'll know weblogic started successfully and you'll know it's running when you see 'Server state changed to RUNNING'
            - if you look up a couple lines, you'll see channel messages
                - Channel "Default" is now listening on <ip address:port number that web logic is listening on> then the protocols it will respond to like iiop, t3 (weblogic propriotory protocol), ldap, snmp and http
                    - can look for this message to see what port web logic server is running on 
        - SMS eg: I think this happens in eclipse...
- To see log files: 
- Go to $cd u01/udemy/domains/servers/AdminServer/logs
    - AdminServer.log
        - server log for AdminServer
    - access.log
        - http access log
        - if we didn't access the admin console, there might not be anything in here (requests)
    - base_domain.log
        - individual web logic servers have their own log files and the domain itself has a overarching log file
        - if you 'cat' the log, you'll see all the logging from when we started the AdminServer
        - the running messages are here

**Weblogic admin console**
- Once you start running the server, can go to console using the http://localhost:7001/console
- use the username and password specific when you ran the installation wizard
- From within console, can access the weblogic 'Environment' ![ServersLink](https://github.com/prasunakunasani/AAFCjavaJunitLearning/blob/master/Notes/Images/2018-10-02%2015_42_21-Beginning%20Oracle%20WebLogic%20for%20Administrators%20_%20Udemy.png)
    - You'll see a list of servers that are part of your weblogic domain
    - Might only see one server: AdminServer with state, heath, listen port 
    - ![ServersList](https://github.com/prasunakunasani/AAFCjavaJunitLearning/blob/master/Notes/Images/2018-10-02%2015_47_49-Beginning%20Oracle%20WebLogic%20for%20Administrators%20_%20Udemy.png) 
    - table is customizable 
        

# Subversion
**What's a Repository**
- Stores all the files in the version control system
- Think of it like a file server..that can time travel
- Stores files in a folder structure
- Stores file revision
    - can say who changed a file, and when, what changed in file
    
**Central Repository**
- Files and revisions are stored on a server
    - a central repo can be a piece of software running on your local machine as well so it doesn't actually have to be a dedicated server
- Clients connect across a network
    - like a browser connects to a server
    - when changes are ready for the repo, can use client to send changes to server
    - with central repo setup, it takes care of figuring out what files were changed and how they were changed
    - cause' now, you can figure out what changed and the local one will just ask and get the info
- Clients send changed files to the server
- Server determines changes (who, what, and when)
- Subversion uses centralized setup

**Distributed Repositories**
- Files are stored on the client(s) and server (if any)
    - all the files are stored on the client machines who work on the code
    - each client is essentially it's own full repository
    - you can have a server in this setup but then the server acts like any other client
- Clients connect across a network
    - clients can connect to each other across a network in order to share changes
- Clients create and share changesets (patches)
    - but unlike a cereal repo where you send files to server, distributed setup only sends change sets
    - so if you change 3 lines of a 3mb file, and saved as a distributed repo, only the two change lines of code are saved
        - no need to make a new copy of the file
        - so this setup is much smaller and faster cause' dealing with less data
        - but clients have to be smarter since they have to figure out what changed between two revisions of a file
- Git and Mercurial use distributed repo setup.  

**How are They the Same**
- People work on local copies of a file
    - When you make changes in a day, you'll not making changes directly in the repository
- Changes to local copies are merged together
    - you work on local copies of the code until you're ready to save the changes, then you merge the changes
    - even if you made changes all day and desire to start all over, no need to save changes to the repo at all
    - can get fresh copies of the original files and start over
- Editing software does not have to be version control aware (the client can be separate)

**Which is Better**
- Neither...with a good client, it's hard to tell the difference
    - cause' they abstract away the way to actually save the data
- It's possible to convert subversion to git

**Projects and Repositories**
- A repository is like a directory tree, and a ****project**** is like a subdirectory
- A repository can have multiple projects
- A project is a collection of related files that share the same root directory  
    - a repository can store all the dif changes to the project
    - as you commit changes to repository, it's like making a brand new project folder underneath the current one
- The simple project structure is linear

**Linear Project Structure**
![LinearTrunk](https://github.com/prasunakunasani/AAFCjavaJunitLearning/blob/master/Notes/Images/Screen%20Shot%202018-09-30%20at%205.45.11%20PM.png)
- Just make changes to the project directly and commit them
    - we don't have copies of project other than the main copy
        - {think smsp in subversion}
        - {think committing to the master directly each time}
    - In this case, every time we send changes, we make a new revision of the project under the main project directory
-  So to see the latest changes in a project, have to find the latest revision of the project
-  If the whole team is sending changes to the same project root, changes will be merged together immediately
    - easy to follow revision cycle
    - but bad way to set project up

**Tree Structure**
![treeStructure](https://github.com/prasunakunasani/AAFCjavaJunitLearning/blob/master/Notes/Images/Screen%20Shot%202018-09-30%20at%205.51.15%20PM.png)
- It is a common setup with Subversion
- The trunk, branch, tag structure is what many svn clients set for you
- Main project directory is called a ****trunk****
    - has the latest 'good' version of project where code compiles and code works
- Changes can be made in a ****branch**** that will be merged back into the trunk.
    - branch holds copies of project that people use while working on changes
    - These can be multiple branches of same project if people are working on dif sets of changes
    - can even have branches of branches if you want
    - Once a branch is ready, the changes in that branch are merged back into the trunk
       - using a trunk/branch structure, can be sure that code in branch remains stable
        - all the changes have tested or deemed ready to test
        - risky development can happen in branches so code in trunk doesn't get broken
- Important snapshots are saved as ****tags****
    - tags directory is a special branch that holds snapshots of the trunk at a particular point of time

**One Tree for Each Project**
![org1](https://github.com/prasunakunasani/AAFCjavaJunitLearning/blob/master/Notes/Images/Screen%20Shot%202018-09-30%20at%206.32.26%20PM.png)
- this trunk/branch/tag structure there are two ways to set project at root of directory
- One way: setup a single top level trunk tag and branch folder and put all projects below those folders
    - this keeps the trunks/tags/branches together
    - SMS is setup up this way. 

![org2](https://github.com/prasunakunasani/AAFCjavaJunitLearning/blob/master/Notes/Images/Screen%20Shot%202018-09-30%20at%207.02.37%20PM.png)
- Other way: Each project is a top level folder and each project gets it's own trunk/branch/tag sub folder
- either is fine and depends on how you like it
    - can start with one folder structure, can change to the other. 

**When to Branch?**
- Unstable trunk: There is no branching. All work happens in the trunk
    - simple and everything happens in trunk
    - will always know where latest code change are
    - gives unstable code in trunk
        - eg - writing complicated code but not done so unfinished changes will go to trunk 
        - now, the trunk might not compile (bad for team)
- Semi-stable trunk: Most work happens in the trunk; a branch is used for large changes
    - might work for agile teams if project is broken in small changes everyday
    - here, so sort of testing is needed so trunk is tested often and problems are caught early
- Stable trunk: Most/all work happens in the branches; nothing gets merged with the trunk until you know it works
    - testing will happen in branches before merging to trunk
    - stable cause' no code gets moved to trunk till changes work
    - trunk is safe version of project and ready for release
    - downside - if many members of teams are working on dif branches, merging back can require a lot of testing, merging and lot of conflict resolution

**Release Branches and Tags**
- When a new software version is ready, it's common to create a release branch (e.g., ****/branch/release/1.0).
    - this is a copy of trunk frozen in time so testers can do QA do release branch while developers can keep doing new stuff 
- The release can be tested while development continues in the trunk 
    - if problems are found in release branch, bug fixes can be merged with release branch and trunk
    - this way, can only copy bug fixes to the release instead of teh entire trunk back over
    - this is imp cause' if trunk has no changes that don't belong in release yet
- Release can be copied to a ****tag**** - which is really just a branch - once testing is finished. 
    - once release is fully tested and ready to ship, it's usually copied to a special branch called 'tag'
    - a tag acts like a snapshot of code so it's easy to go back and find a specific copy if need to
         eg- after 1.0 release, might do 1.0-tag with a copy of project when it's released
            - when 1.1 is release, create a 1.1 tag
                - this way, can go back adn see what the code looked like for a specific release

**Plenty of Variations**
- If you're just starting, you can take a middle-of-the-road approach (semi-stable trunk) and adjust from there

**Working Copies**
- You never work with files directly in the repository
    - think of repo as final server and always use a local copy (working copy)
- You use a local copy called a ****working copy****
- Files are stored on your computer, not the server
- You can edit the files in any program. SVN integration is not required since only editiing local files.
    - but first might have to pull files 

**Checking Out**
- To create a working copy, you ****check out**** the project files from trunk or a branch
    - can checkout from branch or trunk or switch by making a working copy
- This only make a local copy of all the files; no changes are made on the server
- Multiple people can check out the same set of files
    - VCS doesn't care about how many copies. Just when changes come in

**Updating your working copy**
- If multiple people are working on the same project, you can ****update**** your copy to get the latest changes
    - it's imp for testing to see if your code works. 
    - but since it's part of a larger code, need to know if code compiles by integrating your changes
    - update also shows conflict between your and other's changes
- Any ****conflicts**** between your changes and the changes of others will be shown
    - eg. changed same line of code or delete something one is working on
    - can override one over other, do manually or ignore for now
- You decide how to resolve conflicts

**Committing changes**
- Making a ****commit**** will save your local changes back to the repository
    - when committed, they are merged back into the project. 
    - can merge into branch or trunk. Usually it's the branch or trunk you are working in
- SVN server will merge the changes
- You need to update and resolve conflicts before you commit!
    - update and then commit!

**Commit Messages**
- You can add a ****log message**** when you commit, explaining what changes
    - eg. even if you fixed a type, say you fixed a typo
- You must always write good commit messages
- Eg - what feature you added, what problem you fixed, what problems you ran into when writing the code, bug tracking ticket numbers, etc
- useful for searching later

**History and Revisions**
- Every time you commit your changes, the SVN server adds a new ****revision number**** to entire repository
    - the revision number applies to every single file in the project
    - later on, revision 100 will give every file in the exact state when the change was made
- Revision numbers are incremental and are shared among all projects
    - repo doesn't keep a dif set of numbers for each project
    - if commit made to project a and get 100 and someone else makes a revision to project b in same repo, they'll get 101
        -  So, when looking at a project, will see only revision numbers specific to your project
- ****History**** can show what changes in prior revisions
    - when you want to see what changed between dif revision
- History shows commit messages too

**Commit Strategies**
- Frequency often depends on how many people are working on the same branch as you are
- Alone in the branch? Commit often!
    - might even commit multiple times a day if making a lot of changes
        - can find changes easily in small commits than large
- Sharing the branch? At lease make sure your code compiles before committing - and update often
    - want to make sure that code compiles!
- Some people recommend at least ****daily**** commits
    - if scared to commit to branch cause' you don't wanna annoy them, make your own branch, commit often and then merge back. 

**What about File Locking?**
- If changes can be merged, no need for locking but can
- Locking files for long periods of time can slow down teams
- Why lock when you can merge?
- If you do not want to lock, SVN allows you to lock individual files (not entire projects) of specific users

**What about Binary Files?**
- can't merge changes to a binary file since it messes it up
- Text files can be merged, but how do you merge a binary file? You can't
- SVN can usually detect binary files automatically, and not try to merge them. 
- It might be good to store binaries in a separate project. 
    - Eg. those that work with image can get their own branch

**Do I need a server?**
- Yes, although the server can be on your local machine if working alone or so
- Because SVN is a centralized VCS, the server does a lot of the work
    - and the server is the master copy and has all versions of files
- If you are using a distributed system like Git, a server generally isn't required

**Do I Always Need Access to the Server**
- No, your working copy is local
- Server access is needed when you want to ****update**** and ****commit**** (or get info from the server)
- Otherwise, you can do all your work offline until you're ready to talk to the server
    - only need to connect when you need the server

**Multiple Repositories on One Server?**
- Yes, you can have multiple repositories on the same server
    - eg. dif repository for dif software projects
- You can also move projects from one repository to a another one. 
    - but this is an admin thing
        - {think of that person who gave the un/p for sms svn account}

**Same Project on Multiple Servers?**
- eg. customer has own svn server and you want to manage the project on their server at same time
    - In general, no cause' not simple
- There are mirroring solutions, but ultimately you should'nt try to have the same project on more than one server
- Interesting alternative is git-svn
    - this allows one to manage projects locally and then send to subversion server {??}

- Skipped sections on how to have own VisualSVN server on Windows and Mac

**Command Line client - Possible garbage**
- svn client is installed, this should work: 
    - $where svn
        - else, need to play with environment variables
- $svn --version
- $svn info <https://nameofURLfromSVNserver/blah/>
    - enter username and password and SVN will remove who the last user you authenticated as
    - and won't prompt for the username again
    - gives info on repository
    - HTTPS access is 443 so make sure that's not used
        - can go to 'resource monitor' in Windows and then can click on 'Listening Ports'
        - then see what's listening on 443
- On client, there will be sample configuration files in %APPDATE%\Subversion on machine
    - these are created when the client is installed
    - $cd %APPDATA%\subversion
- skipped on how to use svn on mac because' you will never do such things!

**TortoiseSVN**
- Download Tortoise SVN and can also install 'Command line client tools' in the Custom Setup if you want
    -{I think you need them for David's script to work cause' it uses the command line tool - same with toad I think}
- TortoiseSVN is a shell extension so can't just double click icon or something
    - might need to right click folder and then use tortoise svn 

**SVN Plugins for Eclipse**
- subclipse  plugin
- subversive plugin {todo - which is used in SMS documentation?}
- these are the two common ones and you can just pick one
- In Eclipse, install new software, work with: eclipse release
    - this will contact eclipse and gets software specific for the version you're using
    - eg. subversive revision graph and team provider
    - this will give a 'Subversion Overview' thing in Overview page
        - If you can't see it, go to Window-Preference->team->Subversion Connector Discovery popup or...
        - OR Window->Preference->SVN->Get Connectors
        - this asks which connector to use
        
**SVN Connector Types**
- SVNKit is native Java (a total rewrite) on all platforms
- JavaHL is a binary JNI library, wrapper around SVN, and platform specific (different package for Win64, Linux, and Mac). 
- You probably won't notice a difference.

**Using SVNKit**
- Usually "just works"
- If you want to use JavaHL, use the same version as your command-line tools
- JavaHL install tips: http://subclipse.tigris.org/wiki/JavaHL

- Skipped 'Version of SVN, forwards/backwards compatibility and links'

**Deferred/Skipped Connecting a new Java Project in Eclipse**
    - it talks about setting up a folder to SVN server
    - you don't need this since whoever is managing the svn server at aafc is probably creating a folder...maybe...
    
**Connecting to an existing Java Project using Eclipse**
- For SMS, this isn't necessary since David's script does it for you. But for any old svn project...or understanding what the script is doing..
- In eclipse, create worksapce
- File->New project->SVN->project on SVN
- URL of the base repository the project is in and not the project url itself
     - the one that has multiple projects
     - username and password
 - Select Resource : is which project to check out
    - Can just paste the url of project branch or trunk
    - By default, pick the "head revision"
        - 'Head Revision' = latest revision of the chosen trunk or branch  
- Check out as: has to do with how project appears in eclipse and nothing to with svn
    - Depth - check out whole project = 'Recursively'
    - Just select the same revision as before

**Dealing with proejcts that move to a new location**
- Where a project is moved to a new repository or a different place in the same repository
- Server dies
- You need to clean up to restructure the repository
- You went crazy and thought this is something that's necessary
- Can create a new Eclipse workspace and point it to the new respository
- You skipped the rest about how to move the project yourself since you won't do that...


**Skipped Create/connect using command line**
**Skipped/Deferred Create/connect using TortoiseSVN**

****

Maven 
###### S1 Section 46, Lecture 385, 386 - Maven Overview
**What is Maven?**
- Maven is a Project Management tool
- Most popular use of Maven is for build management and dependencies

**What Problems Does Maven Solve?**
- When building your Java project, you may need additional JAR files
    - Eg: Spring, Hibernate, Commons Logging, JSON, etc
- One approach is to download the JAR files from each project web site
    - Then, manually add the JAR files in your build path/classpath

**Project WITHOUT maven**
![]()
- Developer will have to find the jar files and manually add them to build path

**Maven Solution**
- Tell Maven the projects you're working with (dependencies)
    - Spring, Hibernate, etc
- Maven will go out and download the JAR files for those projects for you
- And Maven will make those JAR files available during compile/run time
- Like giving Maven the list of what you want and it'll find it itself

**Project WITH Maven**
![]()
- Maven will download them and pull to your computer and make them available for you to us

**Maven - How it Works**
![]()

- Maven will read the config file
- Then it'll check the local repo on your computer
- If it's not in your repo, it'll pull from maven central repo into local repo so you can build yoru local cache
- Then Maven will use it to build and run your application


**Handling JAR Dependencies**
- When Maven retrieves a project dependency
    - It will also download supporting dependencies
    - Eg: Spring depends on commons-logging
        - so it will grab that one as well
- Maven will handle this automagically

**Building and Running**
- When you build and run your app, Maven will handle class/build path for you
- Based on config file, Maven will add JAR files accordingly


**Standard Directory Structure**
- Normally when you join a new project, 
    - Each development team dreams up their own directory structure
    - Not ideal for new comers and not standardized
- Maven solves this problem by providing a standard directory structure

![]()
- At root of directory, will have a pom.xml file
    - this is the maven configuration file 
- src/main/java
    - where you place the main java source code
- src/main/resources
    - where you'll place your properties files or config files used by your application
- src/main/webapp
    - where you'll place jsp files, web config files, images, etc
- src/test/java
    - where unit testing source code goes
- src/test/resources
    - any any properties or config files used by the unit test code
- target
    - actual destination directory for compiled code. 
    - any artifacts that maven will generate
    
- eg standard directory structure
![]()

![]()

**Standard Directory Structure Benefits**
- For new developers joining a project,
    - they can easily find code, properties files, unit tests, web files, etc
- Most major IDEs have built-in support for Maven
    - Eclipse, Intellij, NetBeans, etc
    - IDEs can easily read/import Maven projects
- Maven projects are portable
    - Developers can easily share projects between IDEs
    - So can create a maven project in eclipse and open it in intellij
        - maybe not sms...

**Advantages of Maven**
- Dependency Management
    - Maven will find JAR files for you
    - No more missing JARs
- Building and Running your project
    - No more build path/classpath issues
- Standard directory structure
- You can build and run a project with minimal local configuration

###### S1 Section 46, Lecture 387, 388 - POM file and Coordinates
**POM File - pom.xml**
- POM.xml: Project Object Model file
- Configuration file for your project
    - Eg: "Shopping list" for Maven
    - where you tell maven that the proejct depends on X number of dependencies and ask it to go find them
- Located in the root of your Maven project

**POM File structure**
![]()
- project meta data
    - list of dependenceis and list of plugins
    - info about project like project name, version, output type
- dependencies
    - tell that this is the list of projects we depend on so gonna list those dependcies there
- plugins
    - additional custom tasks to run
    
**Simple POM File**
![]()

**Project Coordinates**
- Project Coordinates uniquely identify a project
    - Similar to GPS coordinates for your house: latitude/longitude
- Precise information for finding your house (city, street, house#)

![]() 

**Project Coordinates - Elements**
![]()

**Eg of Project Coordinates**
![]()
- The Spring one is defined by Spring people and similar for hibernate
- Their development teams cam up with the GroupID, artificateID and version for their given project

**Adding Dependencies**
- You want to add support of Spring and Hibernate in your project, 
    - you simply add those as dependencies in the pom.xml
- Maven will go on internate, pull those jar files so we can use in our application
![]()

**Dependency Coordinates**
- To add given dependency project, we need: 
    - ****Group ID****, ****Artifact ID****
    - ****Version**** is optional
        - Best practice is to include the version (for repeatable builds)
            - using devops
                - So can say our project works with x, y, z of a given project so you know it's been tested and verified
- May see this refereed to as: GAV
    - ****G****roup ID, ****A****rtifact ID and ****V****ersion
    - "what's teh GAV of project"

**How to Find Dependency Coordinates**
- Option 1: Visit the project page (spring.io, hibernate.org, etc)
    - If there's a maven section, they'll give the dependency that you can copy and paste to the maven pom.xml file
- Option 2: Visit http://search.maven.org 
    - easiest approach
    - it will also have older versions and when each version was updated
    - project information has teh GAV as well as dependency information that can be copy and pasted. 
- Option 3: http://mvnrepository.com
    - 3rd party site (see indexed repositories to see where it's searching)
    - will search central repository and other repositories 
    - also gives versions and last updated

###### S1 Section 46, Lecture 389 - Maven Archetypes


#### Source: 
S1 - Spring and Hibernate for Beginners tutorials  
S2 - JUnit and Mockito Crash Course  
S3 - Mockito Tutorial with Junit Examples (https://github.com/in28minutes/MockitoTutorialForBeginners)
S4 - Beginning Oracle WebLogic for Administrators
S5 - SVN for Java Developers


#### Shortcuts:
- In Idea, 
    - 'psvm' + tab gives: public static void main function
    - If implementing an interface in a class, do 'Ctrl + I' to automatically implement the methods
    - If for a function, you want to see what parameters are accepted, do 'Ctrl + P'
    - To commit, do 'Ctrl + K'
- To create a test, go to the class for which you want to create the test. 
          - On a Mac/PC, use 'Alt + Enter'
          - Or, right click class, Go to -> Test, click on 'Create New Test', yes for same root source folder, select Junit5 (Fix if necessary), change the folder to where tests go
          - If there are red text on the Junit lines, do 'Alt+Enter' again and click on 'Add Junit5 to classpath' or something similar 
- In Eclipse, to add static imports, do 'Ctrl + 1' or 'Command + 1'
- In Eclipse, To automatically add the static imports in Mockito to come up automatically: 
    - Windows:  Windows -> Preferences, type 'Favorites', select Favorites in Java, add whatever classes (new type), apply, okay
    - Mac:  Eclipse -> Preferences...rest as above
        - Classes: starting typing in mockito and then: 
            - org.mockito.Mockito.*
            - org.mockito.BDDMockito.*
            - org.hamcrest.Matchers.*
            - org.hamcrest.CoreMatchers.*
    - Now, as soon as you type in the methods, eclipse will automatically suggest the imports
 - In Intellij to add a Maven jar file,
    - Go to Maven Repository on the internet, find Mockito, download Jar file, move to lib folder
    - go to File->Project Structure->Dependencies->+->add Jar files, select the jar file
 - Import a Maven project in Intellij
    - File-> New -> Module from Existing Source -> navigate to where the folder with pom.xml file is-> OK -> Just use the default stuff -> should eventually see the packages as another project

#### Technical Stuff:
JDK: jdk1.8.0_112  
Spring: 5
Junit: 5  
