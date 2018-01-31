
#Painfully detailed notes
####Commit 2: S1 Section 4, Lecture 20, 21  
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



####Source: 
S1 - Spring and Hibernate for Beginners tutorials  
S2 - JUnit and Mockito Crash Course

####Shortcuts: 
- In Idea, 'psvm' + tab gives: public static void main function

####Technical Stuff: 
JDK: jdk1.8.0_112
Spring: 
Junit: 5