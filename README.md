# AAFCspringLearning
Trying to figure out Java, Spring, Groovy, Grails, Junit and Mockito

# Painfully detailed notes 

Spring Learning:   
[Spring Inversion of Control](#commit-s1-section-4-lecture-20-21)  
[Baseball Coach eg setup](#commit-s1-section-4-lecture-21)  
[Configuring IoC, What is a Spring Bean](#commit-s1-section-4-lecture-23---spring-inversion-of-control)  
[Practical Spring IoC](#commit-s1-section-4-lecture-25---practical-spring-inversion-of-control)  
[Dependency Injection](#commit-s1-section-5-lecture-29---spring-dependency-injection)

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

# Spring

###### Commit: S1 Section 4, Lecture 20, 21
- Here: https://github.com/whereismybaymax/AAFCjavaJunitLearning/commit/316b77c98d22dbeea751792cae8c579bcde97e8b  
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
- Here: https://github.com/whereismybaymax/AAFCjavaJunitLearning/commit/fbc9b85aaf6c1e17ec67892d4a6b232c5ded695e 
- Software Engineering best Practice: Code to an interface
    - instead of coding to the baseball coach implementation, want to use something that all coaches can support
    - every coach will have a getDailyWorkout
    - an interface doesn't have any implementation code; only the specification 
        - it only says what's available and how it's implemented
            - what: method called getDailyWorkout and dif coaches will implement this method depending on the type of coach that they are
- now, the output will still be the same but this handles the requirement of other coaches using it


###### Commit: S1 Section 4, Lecture 23 - Spring Inversion of Control 
![Spring Container](https://github.com/whereismybaymax/AAFCjavaJunitLearning/blob/master/Notes/Images/2018-02-09%2010_29_13-Spring%20%26%20Hibernate%20for%20Beginners%20_%20Udemy.png)
- From: https://www.tutorialspoint.com/spring/spring_ioc_containers.htm
    - Spring Container: Is the core of Spring Framework. 
    - The container will create the objects, wire them together, configure them and manage their complete life cycle from creation till destruction 
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
    
**Step 1: Configure your Spring Beans - XML Configuration file way** Eg: 
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
- More on applicationContext below under 'More info on applicationContext'

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



###### Commit: S1 Section 4, Lecture 25 - Practical Spring Inversion of Control
- Here: https://github.com/whereismybaymax/AAFCjavaJunitLearning/commit/91047c9300adc5e43c9cf06640325da9751f9e25 
- Download starter files from: http://www.luv2code.com/downloads/udemy-spring-hibernate/spring-hibernate-source-code-v18.zip 
- Unzip, copy file :spring-core->spring-demo-one->starter-files->applicationContext.xml , paste in src folder
    - In Intellij, a pop-up with ask for the app Context to be configured. Just go through it. 
- Used the file to configure the spring bean (step 1), used HelloSpringApp.java for doing step 2 and 3
    - Run HelloSpringApp.java to see results (Yay! First spring app)
![Done Spring Container](https://github.com/whereismybaymax/AAFCjavaJunitLearning/blob/master/Notes/Images/2018-04-06%2016_30_24-Spring%20%26%20Hibernate%20for%20Beginners%20_%20Udemy.png)
- Now, have app that is configurable! 
- In case of Java web applications using Spring MVC, the *DispatchServlet* will load the application context for you, so you only have to create a springapp-servlet.xml file in WEB-INF folder of the application. 

**_More info on applicationContext_**
- Source: https://stackoverflow.com/questions/19615972/application-context-what-is-this
- ApplicationContext is the context that loads the configuration (usually a XML file)
    - Then Spring will start managing the beans. 
- Manually loading the application context at the beginning of application (context= new C..) is usually for sample purposes or standalone applications
- Note that **an application context is associated to a single configuration** (XML based or not). Period.
- You can have more than one application context per application 
```java
public class Foo {
    public static void main(String[] args) {
        ApplicationContext context =
            new ClassPathXmlApplicationContext("path/to/applicationContext.xml");
        ApplicationContext context2 =
            new ClassPathXmlApplicationContext("path/to/applicationContext.xml");
        //use the context as you wish...
    }
}
```
- Note that we have two application contexts using the same XML configuration. Can you do this? 
    - Yes, as seen here. What's the difference, then? 
    - The **main** difference is that Spring beans singleton scopes are singleton **per application context**
        - This mean when retrieving a *Bar* bean that's configured in applicationContext.xml file from *context* will not be the same as retrieving it from *context2*, but several retrieves from *context* will return the same Bar bean instance.
    - But, not always good practice and most people would recommend having all your beans configured in a single place (via XML or another) and loaded by a single application context.

###### Commit: S1 Section 4, Lecture 26, 27, 28 - Practical Spring Inversion of Control Cont.
- Here: https://github.com/whereismybaymax/AAFCjavaJunitLearning/commit/f3671bd6459da0d48691de1c9864bdef5504ad95
- Now, in applicationContext.xml, you can just change the coach from TrackCoach to BaseballCoach
    - Run HelloSpringApp.java and the different class will be run without having to change anything else
- Question - Why do we specify the Coach interface in getBean()? {In HelloSpringApp.java}
```java
//Retrieve bean from spring container
        Coach theCoach = context.getBean("myCoach", Coach.class);
```
- Answer - When we pass the interface to the method, behind the Scenes, Spring wil cast the object for you
    - However, there are some slight difference than normal casting. 
    - From the Spring docs: 
        - Behaves the same as getBean(String) but provides a measure of type safety by throwing a BeanNotOfRequiredTypeException if the bean is not of the required type. This means that ClassCastException can't be thrown on casting the result correctly, as can happen with getBean(String).
        - Source:  http://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/beans/factory/BeanFactory.html#getBean-java.lang.String-java.lang.Class-
 - Practice 
    1) Define a new implementation for the Coach interface. You can use whatever sport you like.
    2) Reference your new implementation in the Spring config file
    3) Test your application to verify you are retrieving information from your new Coach implementation
    - Sample answer: http://www.luv2code.com/downloads/udemy-spring-hibernate/solution-practice-activities.zip

**_Dependency Injection - language agnostic explanation_**
- Source: https://www.youtube.com/watch?v=IKD2-MAkXyQ
![Dependency Model](https://github.com/whereismybaymax/AAFCjavaJunitLearning/blob/master/Notes/Images/2018-04-12%2016_10_42-Dependency%20Injection%20-%20YouTube.png)
- Dependency: Just another object that your class needs to function
- If you have a Model class that fetches data from a Database object, can say "Model has a dependency of the database object"  

**Meaning of Injecting Dependencies**
![Injecting Dependency](https://github.com/whereismybaymax/AAFCjavaJunitLearning/blob/master/Notes/Images/2018-04-12%2016_12_51-Dependency%20Injection%20-%20YouTube.png)
- Means the dependency is pushed into the class from the outside
    - In image above, outside is the database
    - meaning, you shouldn't instantiate dependencies using the 'new' operator from inside of the class
        -instead, take it as a constructor parameter or via a setter
            - you don't need a fancy container, class or an object to do it. 
                - may make your life easier but you don't need them  
                                
**Why Dependency Injection?**
- Eg - You program a robot to use lumber to built walls
    - Once you get to the Doorway, can program to build custom doors or program to use existing door
    - Would just use a door from a supplier - This is Dependency Injection
- DI decouples your class's construction from the construction of it's dependencies
    - Important cause of the *Dependency Inversion Principle*  
    
**Dependency Inversion Principle**
![Dependency Inversion](https://github.com/whereismybaymax/AAFCjavaJunitLearning/blob/master/Notes/Images/2018-04-20%2009_08_08-Dependency%20Injection%20-%20YouTube.png)
- Dependency inversion is the principle that code should depend upon abstractions. 
    - By depending on abstractions, we are decoupling our implementations from each other 
- If we use interfaces, we can substitute different dependencies as long as they all satisfy the required interface.
- By using DI, we decouple our code from the lower level implementation
    - making code cleaner, easier to modify and easier to re-use    
*Extra:*
![Problem-Solution](https://github.com/whereismybaymax/AAFCjavaJunitLearning/blob/master/Notes/Images/2018-04-20%2009_15_07-Dependency%20Injection%20-%20YouTube.png)
- Problem - Each of our classes require all the adopted dependencies
    - So, to construct each class, need to figure out what dependencies they need and how to instantiate those dependencies
- Solution - Using a container: The container is a map of dependecies that your class needs with the logic to create those dependencies if they haven't been created yet
![Container](https://github.com/whereismybaymax/AAFCjavaJunitLearning/blob/master/Notes/Images/2018-04-20%2009_19_33-Dependency%20Injection%20-%20YouTube.png)
- Everytime you ask for a dependency, the map will figure out which dependency to use and then the container will check to see if it created one of those dependencies already
    - If it has, it'll just use that one. 
    - Else, it'll create the dependency, store it and then return it. (Think Spring Container)
![Container DI](https://github.com/whereismybaymax/AAFCjavaJunitLearning/blob/master/Notes/Images/2018-04-20%2009_22_29-Dependency%20Injection%20-%20YouTube.png)
- So, instead of constructing all the classes oneself, you ask the container and it will then resolve the dependencies, construct your object and return it to you
     - the container can resolve complex dependencies transparently
     - If you want to swap out a generic dependency, you only need to update the container
 - For cleaner and modular code, use DI.

###### Commit: S1 Section 5, Lecture 29 - Spring Dependency Injection
- Here: https://github.com/whereismybaymax/AAFCjavaJunitLearning/commit/7c4d3d0506820fea0b2986047f0218d14c7d6d84

**What is Dependency Injection?**
- Definition: The dependency inversion principle. The client delegates calls to another object the responsibility of providing its dependencies. 
![Dependency Injection - Car Factory](https://github.com/whereismybaymax/AAFCjavaJunitLearning/blob/master/Notes/Images/2018-04-11%2014_55_47-Spring%20%26%20Hibernate%20for%20Beginners%20_%20Udemy.png)
- If you want a car that gets built at the factory on demand
    - Have to talk to the factory so they'll build the car for you 
    - The factory will do the assembling, etc, so they inject all the dependencies of the car
        - will inject tires, seats, etc
- You simply outsource the construction and injection of object to an external entity (Eg: Car factory)
- Spring has an Object Factory
![Dependency Injection - Spring Factory](https://github.com/whereismybaymax/AAFCjavaJunitLearning/blob/master/Notes/Images/2018-04-11%2015_00_29-Spring%20%26%20Hibernate%20for%20Beginners%20_%20Udemy.png)
    - When asking for a Coach object, this object may have additional dependencies (helper objects - other objects needed to perform an operation)
        - Instead of having to build the coach object and all it's dependencies, Spring Framework/Factory will do this work for you
        - Here, like a car object, you'll get a coach object
- Spring Container: 
    - *Primary Functions:*
        - Create and manage objects (Inversion of Control) {gone through so far}
        - Inject object's dependencies (Dependency Injection)
- Demo Example: 
  ![FortuneService](https://github.com/whereismybaymax/AAFCjavaJunitLearning/blob/master/Notes/Images/2018-04-11%2015_09_51-Spring%20%26%20Hibernate%20for%20Beginners%20_%20Udemy.png)
    - Our Coach already provides daily workouts ✔
    - Now will provide daily fortunes
        - New helper: **FortuneService**
        - This is a **dependency**
    - **_dependency = helper_** 
    - Coach will make use of FortuneService(dependency)
- Injection Types: 
    - There are many types of injection with Spring
    - Two most common: 
        - Constructor Injection
        - Setter Injection
    - Later - "auto-wiring" of beans in the Annotations section later
- Development Process - *Constructor Injection*
    1) Define the dependency interface and class
    2) Create a constructor in your class for injections
    3) Configure the dependency injection in Spring config file

**Step 1: Define the dependency interface and class**
```java
//File: FortuneService.java
public interface FortuneService{ 
    public String getFortune();  
}
```
- it's an interface that has a method called getFortune that returns a String
```java
//File: HappyFortuneService.java
public class HappyFortuneService implements FortuneService{
    public String getFortune(){
        return "Today is your lucky day!"; 
    }
}
```
- It's the one that implements the method getFortune
- Eg - Such a service can connect to database, etc

**Step 2: Create a constructor in your class for injections**
```java
//File: BaseballCoach.java
public class BaseballCoach implements Coach{
    //Define Field
    private FortuneService fortuneService; 
    
    //Define Constructor
    public BaseballCoach(FortuneService theFortuneService){
        fortuneService = theFortuneService; 
    }
    ...
}
``` 
- Making use of constructor injection where the dependencies are injected by calling a constructor
- So, creating a constructor Baseball Coach that will accept this dependency
    - the private field is assigned in the constructor

**Step 3: Configure the dependency injection in Spring config file**
```java
//File: applicationContext.xml
//Define dependency/helper
<bean id="myFortuneService" class="springdemo.HappyFortuneService">
</bean>

<bean id="myCoach" class="springdemo.BaseballCoach">
//Inject the dependency/helper using constructor injection
    <constructor-arg ref="myFortuneService" />
</bean>
``` 
- At the top, define the bean 
    - {Question - does order matter}
- Next, inject dependency into BaseballCoach
    - the 'myFortuneService' is the same id name in the bean of fortune service
    - behind the scenes 
        - Spring will look at Baseball coach, call it's constructor, and then pass in a reference of myFortuneService
        - So Spring will construct the object and pass in the appropriate data into the constructor
- Remember: Spring has a object factory so it's responsible for creating objects and injecting their dependencies    

###### Commit: S1 Section 5, Lecture 30 - Behind the scene - Spring Dependency Injection + More on Dependency Injection        
**How Spring Performs your Config file BTS**
- In config file, you had the following: 
```java
//File: applicationContext.xml
//Define dependency/helper
<bean id="myFortuneService" class="springdemo.HappyFortuneService">
</bean>

<bean id="myCoach" class="springdemo.BaseballCoach">
//Inject the dependency/helper using constructor injection
    <constructor-arg ref="myFortuneService" />
</bean>
```     
- In Spring Framework, it did: 
```java
HappyFortuneService myFortuneService = new HappyFortuneService(); 

BaseballCoach myCoach = new BaseballCoach(myFortuneService); 
```
- Spring's Object Factory does this: 
    - when the first bean was created, it constructed the object for you
    - For the second bean myCoach, Spring creates a new Baseball Coach
        - based on the config file, it will pass in a constructor argument of myFortuneService

###### S1 Section 5, Lecture 31, 32, 33 - Practical Spring Dependency Injection
- Here: https://github.com/whereismybaymax/AAFCjavaJunitLearning/commit/98c22e5822dc5d2f616737af8c036a1ab79c2bf6 
- Create the interface FortuneService.java and class HappyFortuneService.java
- add method from FortuneService.java to Coach.java interface
- fix the unimplemented methods in all the classes that inherit the Coach interface 
- In Baseball Coach, define private field and constructor for dependency injection 
    - So, spring will construct our object, pass in a dependency and we accept it and assign it
    - so, our class is ready to accept dependency injection from Spring framework
- Add the dependency to container
- Configure in HelloSpringApp.java

###### S1 Section 5, Lecture 34 - Purpose of no arg constructor
- Question - Why was a no arg constructor created? Aren't they implied by Java and only required when you also have an overloaded constructor? Or is this a Spring specific thing?
- Answer - When you don’t define any constructor in your class, compiler defines default one for you, however when you declare any constructor (in your example you have already defined a parameterized constructor), compiler doesn’t do it for you.
    - Since you have defined a constructor in class code, compiler didn’t create default one. While creating object you are invoking default one, which doesn’t exist in class code. Then the code gives an compilation error.

###### S1 Section 5, Lecture 35 - Setter Injection - Overview
- Spring Injection Types
    - Constructor Injection (done above)
    - Setter Injection
- Setter injection - Spring will inject dependencies by calling setter method(s) on your class
- Development Process - Setter Injection
    1) Create setter method(s) in your class for injections
    2) Configure the dependency injection in Spring config file  
**Step 1: Create setter method(s) in your class for injections**
```java
public class CricketCoach implements Coach{
    private FortuneService fortuneService; 
    
    public CricketCoach(){
    }
    //This is the method called by Spring when it inject the dependency using the setter injection
    public void setFortuneService(FortuneService fortuneService){
        this.fortuneService = fortuneService; 
    }
    ...
}
```

**Step 2: Configure the dependency injection in Spring config file**
```java
//File: applicationContext.xml
<bean id="myFortuneService"
    class = "com.springdemo.HappyFortuneService">
</bean>

<bean id="myCriketCoach"
    class="com.springdemo.CricketCoach">
    
    <property name="fortuneService" ref="myFortuneService" />
</bean>
```
- at bottom, the dependency is injecting using the property name
- the ref refers to the bean id above
- property name="fortuneService" ref="myFortuneService"
    - Spring will take the property name and call the setter method on your class for that giving property 
        ```java
        public void setFortuneService(...)
        ```
            - capitalize first letter of property name: setFortuneService
        - So, it will look for a public method called setFortuneService
            - Spring will call the setFortuneService and then pass in that value for that call
- Call setter method on Java class: 
    - In general, any property name you have, spring will attempt to make a call to the  setter method for a given property
 - Eg: 
 ```java
<property name="bestAthlete" ref="" /> 
//--------
    public void setBestAthlete(...) 
```  
- Spring takes the property name, makes the first letter of that property Capital and then attempt to make a call to the setter method for that given property
    - Spring will look for a public method called setBestAthlete and make the method call on the fly 

**How Spring Processes your Config file**
 ```java
<bean id="myFortuneService"
    class ="com.springdemo.HappyFortuneService">
</bean>

<bean id="myCricketCoach"
    class="com.springdemo.CricketCoach">
    
    <property name="fortuneService" ref="myFortuneService"
</bean>
```
    What Spring Framework does internally:
```java
HappyFortuneService myFortuneService = new HappyFortuneService();

CricketCoach myCricketCoach = new CricketCoach(); 
myCricketCoach.setFortuneService(myFortuneService); 
``` 
- Spring creates an instance of your fortune service using the new keyword for no-arg constructor
- For cricket coach, Spring will create a new CricketCoach using the no-arg constructor and then it'll inject the dependency by calling the setter method using the property name
    - In this case, the property name is fortuneService so spring will call setFortuneService and pass in a reference to that given bean
        - this is what happens BTS when Spring processes the configuration file

###### S1 Section 5, Lecture 36,37 - Practical Setter Injection - Write some code
- Here: https://github.com/whereismybaymax/AAFCjavaJunitLearning/commit/7d99ff96264053325c751e136d7c26bf5af09711

###### S1 Section 5, Lecture 38 - Injecting Literal Values - Overview

- How to inject literal values into Spring Objects
- ![Injecting Literal values]()
- Development Process steps
    - Create setter method(s) in your class for injections
    - Configure the injection in Spring config file
**Step 1: Create setter method(s) in your class for injections**
```java
//File: CricketCoach.java
public class CricketCoach implements Coach{
    //create private fields to hold those values
    private String emailAddress; 
    private String team; 
    
    //create setter methods
    public void setEmailAddress(String emailAddress) ...
    public void setTeam(String team) ...
    ...
}
```  
**Step 2: Configure the injection in Spring config file**
```java
<bean id="myCricketCoach"
    class="com.springdemo.CricketCoach">
    
    <property name="fortuneService" ref="myFortuneService" />
    
    //value is used for literal values while ref is used for refer to other objects
    <property name="emailAddress" value="thebestcoach@luv2code.com" />
    <property name="team" value="Sunrisers Hyderabad"/>
</bean>
```

placeholder
###### S1 Section 5, Lecture 39 - Injecting Literal Values - Write some code

###### S1 Section 5, Lecture 40 - FAQ - Why use CricketCoach class instead of Coach Interface?

###### S1 Section 5, Lecture 41 - Injecting Literal Values from a Properties File - Overview

###### S1 Section 5, Lecture 42 - Injecting Literal Values from a Properties File - Write some code

###### S1 Section 5, Lecture 43 - Practical Activity #2: Dependency Injection with XML Configuration

###### S1 Section 6, Lecture 44 - Bean Scopes - Overview

###### S1 Section 6, Lecture 45,46 - Bean Scopes - Write some Code  

###### S1 Section 6, Lecture 47 - Bean Lifecycle - Overview

###### S1 Section 6, Lecture 48 - Defining init and destroy methods - Method Signatures 

###### S1 Section 6, Lecture 49 - Bean Lifecycle - Write some code

###### S1 Section 6, Lecture 50 - Destroying Lifecycle and Prototype Scope

###### S1 Section 6, Lecture 51 - Practical Activity #3 - Bean Scopes with XML Configuration  

###### S1 Section 7, Lecture 52 - Spring configuration with Java Annotations Inversion of Control Overview - Component Scanning

###### S1 Section 7, Lecture 53 - Annotations Project Setup

###### S1 Section 7, Lecture 54,55 - Explicit Component Names - Write some code

###### S1 Section 7, Lecture 56 - Default Component Names - Overview

###### S1 Section 7, Lecture 57 - Default Component Names - Write some code

###### S1 Section 7, Lecture 58 - Practical Activity #4 - Inversion of Control with Annotations

###### S1 Section 8, Lecture 59 - Spring configuration with Java Annotations Dependency Injection - Constructor Injection - Overview

###### S1 Section 8, Lecture 60 - FAQ - What if there are Multiple Implementations

###### S1 Section 8, Lecture 61, 62 - Constructor Injection - Write some code

###### S1 Section 8, Lecture 63 - FAQ - Constructor Injection - Autowired Optional? 

###### S1 Section 8, Lecture 64 - Setter Injection - Overview

###### S1 Section 8, Lecture 65 - Setter Injection - Write some code

###### S1 Section 8, Lecture 66 - Method Injection

###### S1 Section 8, Lecture 67 - Field Injection - Overview

###### S1 Section 8, Lecture 68 - Field Injection - Write some code

###### S1 Section 8, Lecture 69 - Which Injection Type Should You Use? 

###### S1 Section 8, Lecture 70 - Qualifiers for Dependency Injection - Overview

###### S1 Section 8, Lecture 71,72 - Qualifiers for Dependency Injection - Write some code

###### S1 Section 8, Lecture 73 - Annotations - Default Bean Names (special case)

###### S1 Section 8, Lecture 74 - Using @Quantifiers with Constructors - Overview

###### S1 Section 8, Lecture 75 - How to inject properties file using Java annotations

###### S1 Section 8, Lecture 76 - Practical Activity #5 - Dependency Injection with Annotations

###### S1 Section 9, Lecture 77 - @Scope Annotation - Overview

###### S1 Section 9, Lecture 78 - @Scope Annotation - Write Some Code

###### S1 Section 9, Lecture 79 - Bean Lifecycle Method Annotations - Overview

###### S1 Section 9, Lecture 80 - Note about @PostConstruct and @PreDestroy Method Signatures

###### S1 Section 9, Lecture 81 - Note For Java 9 users - @PostConstruct and @PreDestroy Method Signatures

###### S1 Section 9, Lecture 82 - Bean Lifecycle Method Annotations - Write some code

###### S1 Section 9, Lecture 83 - Note about Destroy Lifecycle and Prototype Scope

###### S1 Section 9, Lecture 84 - Practical Activity #6 - Bean Scopes with Annotations

###### S1 Section 10, Lecture 85 - Spring Configuration with Java Code - Overview

###### S1 Section 10, Lecture 86 - Spring Configuration with Java Code - Write Some code

###### S1 Section 10, Lecture 87 - Defining Spring Beans with Java Code - Overview

###### S1 Section 10, Lecture 88,89 - Defining Spring Beans with Java Code - Write some code

###### S1 Section 10, Lecture 90 - Injecting Values from Properties File - Overview

###### S1 Section 10, Lecture 91, 92 - Injecting Values from Properties File - Write some code

###### S1 Section 10, Lecture 93 - FAQ - Problems with Injecting Values - Value not returning ${foo.e-mail}

###### S1 Section 10, Lecture 94 - Practice Activity #7 - IoC and DI with Java Configuration

###### S1 Section 11, Lecture 90 - 
  







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
