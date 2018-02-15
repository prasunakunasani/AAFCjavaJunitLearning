
# Painfully detailed notes 
###### Commit: S1 Section 4, Lecture 20, 21
- Inversion of Control: The approach of outsourcing the construction and management of objects  
    - outsource to object factory
    - Basically, the app will outsource creation and management of objects and this outsourcing will be handled by the object factory. 
   
 Coding Scenario:
 
![alt text](https://github.com/whereismybaymax/AAFCjavaJunitLearning/blob/master/Notes/Images/2018-01-31%2016_24_05-Spring%20%26%20Hibernate%20for%20Beginners%20_%20Udemy.png)
- MyApp is the main class that calls the Baseball Coach
    - but also want to be able to configure any type of coach (hockey, criket, etc)
    - MyApp.java: main method
    - BaseballCoach.java
    - Coach.java: interface after refactoring
    - TrackCoach.java: using it to switch to a dif coach to see if the app still works

###### Commit: S1 Section 4, Lecture 21
- Software Engineering best Practice: Code to an interface
    - instead of coding to the baseball coach implementation, want to use something that all coaches can support
    - every coach will have a getDailyWorkout
    - any interface don't have any implementation code, only the specification 
        - only say what's available and how it's implemented
            - what: method called getDailyWorkout and dif coaches will implement this method depending on the type of coach that they are
- now, the output will still be the same but this handles the requirement of other coaches using it

###### Commit: S2 Section 3, Lecture 6 - Junit intro
From: http://www.vogella.com/tutorials/JUnit/article.html  
Where should the test be located?  
Typical, unit tests are created in a separate project or separate source folder to keep the test code separate from the real code. The standard convention from the Maven and Gradle build tools is to use:

src/main/java - for Java classes
src/test/java - for test classes
- See Shortcuts on how to run a test

###### Commit: S3 Step 1, 2, 3
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
- Static imports - Eg- assertEquals is a static method that's in org.junit.Assert class
- Mocks
    - mocking is creating objects that simulate the behaviour of real objects
    - unlike stubs, mocks can be dynamically created from code - at runtime 
    - frameworks like mockito allow one to create a dummy class 
- https://github.com/in28minutes/MockitoTutorialForBeginners/blob/master/Step04.md  

###### Commit: S3 Step 5
- Building a mock for List in java - List in an interface 
- ListTest.java code contains: 
    - a minimum setup stuff needed to use @Mock annotation vs the one shown in the course using mock(blah.class)
    - How to mock methods with or without parameters
    - How to mock multiple responses for the same method
    - How to use Argument Matchers
    - How to mock ThrowException stuff - PS - the test for it doesn't work but may work for real life
- https://github.com/in28minutes/MockitoTutorialForBeginners/blob/master/Step05.md  

###### Commit: S3 Step 6 
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
- one requirement - The whole point of using interfaces is so one can easily change the coach for another sport: 
        - Hockey, Cricket, Tennis, Gymnastics, etc...
- the other requirement - App should be configurable
    - the new TrackCoach() or the new BaseballCoach() is still hardcoded in MyApp.java

###### Commit: S1 Section 4, Lecture 23 - Spring Inversion of Control 
![Spring Container](https://github.com/whereismybaymax/AAFCjavaJunitLearning/blob/master/Notes/Images/2018-02-09%2010_29_13-Spring%20%26%20Hibernate%20for%20Beginners%20_%20Udemy.png)
- From: https://www.tutorialspoint.com/spring/spring_ioc_containers.htm
    - Spring Container: Is the core of Spring Framework. 
    - The container will create the objects, wire them together, configure them and manage their complete life cylce from creation till destruction 
    - The container gets it's instructions on what objects to instantiate, configure and assemble by reading the configuration metadata.  
- When asked, Spring provides an object based on an configuration file or annotation and give the appropriate implementation (make app configurable). 
- Primary Functions of Spring Object Factory: 
    - Create and manage objects (Inversion of Control (IoC))
    - Inject object's dependencies (Dependency Injection)
- 3 ways of Configuring Spring container: 
    1) XML configuration file (legacy, but most legacy apps still use this)
    2) Java Annotations (modern)
    3) Java Source Code (modern)
- Spring Development Process
    - Configure your Spring Beans
    - Create a Spring Container
    - Retrieve Beans from Spring Container
**Step 1: Configure your Spring Beans** Eg: 
```java
//File: applicationContext.xml
<beans ...>
    <bean id="myCoach"
        class = "springdemo.BaseballCoach.java">
    </bean>
</beans>
```
- The id is like an alias that java app will use to retrieve a bean from the spring container
- class is the actual implementation for the app
**Step 2: Creating a Spring Container**
- Spring container is generally known as *ApplicationContext*
- These have specialized implementations: 
    - ClassPathXmlApplicationContext
    - AnnotationConfigApplicationContext
    - GenericWebApplicationContext
    - others...
```java
//create a spring container
ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml"); 
```
- want to read the XML file in my class path so use the ClassPathXmlApplicationContext
    - passing in the name of the configuration file (from step 1)
        - can use any name as long as we're consistent  in step 1 and 2
**Step 3: Retrieve Beans from Container**: 
- So far, have the container created. Next step is retrieving beans from the container
    - app will talk to Spring Container asking for the Coach object. And based on what's in the config file, you'll get an implementation of the given interface
```java
//retrieve bean from spring container
Coach theCoach = context.getBean("myCoach",Coach.class); 
```
- 'myCoach' is the bean id defined in the configuration file
- coach.class is the name of the interface that BaseballCoach implements  

**What is a Spring Bean?**  
- A "Spring Bean" is simply a Java object.
- When Java objects are created by the Spring Container, then Spring refers to them as "Spring Beans".
- Spring Beans are created from normal Java classes .... just like Java objects.
- In the early days, there was a term called "Java Beans". Spring Beans have a similar concept but Spring Beans do not follow all of the rigorous requirements of Java Beans.
- In summary, whenever you see "Spring Bean", just think Java object. ::):

###### Commit: S3 Step 7 - Verify Calls on Mocks
- May want to check if a method is called or not! and also how many times a method is called
- until now, we used something like given(mockList.get(anyInt())).willReturn("Blah")
    - but no idea if mockList.get(anyInt()) was called at all
- Basically, we need to know how to test void functions
    - verify methods in Mockito let you check if some method is called
- Egs in Code: 
    - verify(todoServiceMock).deleteTodo("Learn Mockito");
    - verify(todoServiceMock, never()).deleteTodo("Learn Spring");
    - verify(todoServiceMock, times(2)).deleteTodo("Learn Junit");
    - verify(todoServiceMock, atLeastOnce()).deleteTodo("Learn Junit");
    - verify(todoServiceMock, atLeast(5)).deleteTodo("Learn Mockito");
- https://github.com/in28minutes/MockitoTutorialForBeginners/blob/master/Step07.md

###### Commit: S3 Step 8 - Capturing Arguments passed to a Mock
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
- @Mock will make an mock
    - can add @RunWith(MockitoJunitRunner.class) on top of the test class
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
                - Mokito will look at all things present in actual class todoBusinessImpl
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

#### Source: 
S1 - Spring and Hibernate for Beginners tutorials  
S2 - JUnit and Mockito Crash Course  
S3 - Mockito Tutorial with Junit Examples (https://github.com/in28minutes/MockitoTutorialForBeginners)

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
- In Eclipse, To automatically add the static imports in Mockito to come up automatically : 
    - Windows:  Windows -> Preferences, type 'Favorites', select Favorites in Java, add whatever classes (new type), apply, okay
    - Mac:  Eclipse -> Preferences...rest as above
        - Classes: starting typing in mockito and then: 
            - org.mockito.Mockito.*
            - org.mockito.BDDMockito.*
            - org.hamcrest.Matchers.*
            - org.hamcrest.CoreMatchers.*
    - Now, as soon as you type in the methods, eclipse will automatically suggest the imports
 - In Intellij to add a Maven jar file,
    - Go to Maven Repository, find Mockito, download Jar file, move to lib folder
    - go to File->Project Structure->Dependencies->+->add Jar files, select the jar file
   

#### Technical Stuff:
JDK: jdk1.8.0_112  
Spring:   
Junit: 5  
