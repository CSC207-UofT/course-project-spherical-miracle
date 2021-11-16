## Functionality

## Updated Specifications

### Phase 1

Implemented Entity: Meal (store meal information)

Implemented Entity: Day (store workouts + meals for the day)

Let users set up if the schedule to be public or private, which allows the other users to browse for schedules and use theirs as a template

Register(save) users in the database, and allow logging in by comparing user data from db

Packaged classes by feature

- Inputs: name, username, email, password, workout plan (Schedule that has a Day which includes Meals and Workouts of the day)
- Outputs: Schedule for the week
1. User enters their name, username, email and password
2. If their input is valid, give the user access to create a schedule or quit the program
3. Let the user give the schedule a name
4. Create a workout (input name and estimated calories burnt) or meal (input name and estimated calories) and store it in a Day class for each day of the week in the schedule
5. Repeat Step 4 until the user is finished planning
6. The user then receives a printed output of their meals and workouts for each day of the week in the schedule they created

Reminder prompt on Login
Upon login, users are prompted with their plan(s) for the day (meals and workouts)

Inputs: Day of the week, LoginSUCCESS/FAILURE
Output: String representation of workout and meal object(s)
Admin-level interface
Interface to remove user/workouts/edit schedule templates

Plans for Phase 2
User.User can track height, weight, BMI, over time
Input: Weight, Height, Date
Output: A graph/table that shows the history
Weight loss/gain helper
User.User will receive a suggested calorie intake based on their workout schedule and their height + height

Input: User.User health info, target weight
Output: Suggested calorie intake, target amount of daily calories burned
Implement UI - web app/android app

### Major Decisions

Created Meal and Day class

We came to this decision as we realized using strings to represent the days and the meals is not efficient or organized enough to store the information we want. This is especially true since each workout and meal has small details like calories burnt or consumed, and storing them all as separate strings can quickly get messy. Thus, we created a Day class that will store the meals and workouts for the day, and a Meal class that will store the name and calories of a meal (and maybe macros contents of the meal in the future). Additionally, we decided to cap the maximum amount of Workouts that can be stored in a Day object to be five, and the number of Meals to be unlimited. We discussed how the average user realistically is unlikely to do more than five different types of workouts a day, but may consume more or less than three meals a day (ex. brunch, snacks, afternoon tea, etc.). 

Using the MongoDB database for the project

We needed some kind of database for this project as there is a lot of information that should be stored permanently, namely users and their schedules. As a team, we researched different data storage methods and discussed what kind best suited our needs. We concluded that MongoDB was our best option, as it lets us store individual information of each user in a HashMap-like object with any kind of information we want. As a plus, a few of the members of our team have already used this database before and are somewhat familiar with it.

## SOLID DESIGN

#### Single Responsibility Principle

All of our use cases demonstrate the single responsibility principle. Each of them have a clear, defined purpose. However, for our submission in phase 1, our User Interface does more than displaying information and receiving user input; the UI also handles the logic behind which message to show, making it responsible for more than its defined task.

#### Interface Segregation Principle
We divided the DataAccessInterface into two interfaces that handle different scenarios: ScheduleDataAccess and UserDataAccess
ScheduleDataAccess handles the data handling for schedule-related data. Whereas UserDataAccess handles user-related data. This way, the use cases that create users do not depend on the interfaces that it does not use. In this case, the schedule-related methods.

#### Dependency Inversion Principle

All our use cases have instance variables that depend on an abstraction. In other words, the instance variables are all instances of an interface, not a class. This abstraction decouples the dependency from the implementation, allowing us to make changes to the implementation without affecting the code that depends on the interface. This is evident in our DataAccess class and the use cases that depend on the AccessInterfaces. 

## Clean Architecture

### Scenario Walkthrough - User Signup

Once the program runs, the database and its ``dataAccess`` class is initialized in the outermost layer, along with the UI. 

The user is prompted by ``ScheduleUI`` to login or sign up. 

The UI takes in user input and sends it (as a simple data structure) to ``UserController``, The ``UserController`` parses the input and passes the input through ``CreateUserInputBoundary`` to access the ``CreateUserUseCase``.

The ``DataAccessInterface`` is injected into the ``CreateUserUseCase``. 

The use case packages user info into a simple data structure and passes it to the ``saveUser`` method implemented by the ``DataAccessInterface``.

Our ``DataAccess``class implements the ``DataAccessInterface``, and writes the given data into our MongoDB database.

This walkthrough demonstrates Clean Architecture because none of the classes in the inner circle know how the database is implemented. The use cases only interface with the input boundaries and the dataAccess interfaces.

### Clear violations in our current code

We currently are in the process of refactoring ``ScheduleUI`` to be consistent with clean architecture. For now, parts of the UI still instantiates entities directly, violating clean architecture.

### The Dependency Rule consistently followed when interacting with details in the outer layer?

In our scenario walkthrough above, we used dependency injection with the ``CreateUserUseCase``. We did not directly call the saveUser method in the ``DataAccess``. Instead, we injected the ``UserDataAccess`` interface and have the use case call the method in the interface. 

## Design Patterns

One possible design we are planning to use is the command design, where an action to be executed is packed together with the relevant information for it. Some parts of the UI interact with entities directly without delegating that task to the controller, so the command design can potentially resolve that issue since it would encapsulate the details of the task. 

We currently do have the set up for the command design in terms of logging in and having a session controller and login use case to handle that task; we would like to continue with that approach for other requests that the UI takes in.

Another possible design we are considering using is the decorator design for implementing different types of workouts. For now, we are allowing users to differentiate between different workout types by giving them names, but we could potentially make workout subtypes for a user to choose from. Since the decorator design prevents the core object from being tampered with while allowing for supplementary features, this would suit our needs well.

## Github Features

Since Phase 0, we have been using pull requests more frequently and made more use of the comments and review threads on GitHub. However, since our primary mode of communication between each other is through a group chat, not all of our discussions are shown on GitHub. For instance, we often discuss a pull request via message or on a call, but then briefly summarize the conclusion on GitHub or simply write a comment that the pull request was reviewed and is good to go.

We also have been consistent with working in branches and pulling recent changes that our teammates have pushed; we regularly communicate in the group chat when we individually update our branches so teammates are on the same page. If anything, we’d like to be more mindful of the order of merging changes with so many branches going on.

## Code Style and Documentation

Overall, we tried our best to keep warnings to a minimum and check for them before pushing, but it can be difficult with so many branches going on and everyone focusing on different tasks to do. For future reference, we will continue to focus on keeping code neat, including keeping an eye out for warnings.

Javadoc is used in all of our code, with the exception of code that is still being worked out or pseudo code. For more lengthy or complex methods, we’ve also added in comments while coding to explain chunks of code for outside programmers and each other as teammates.

As for our Github repository, our code overall is nicely packaged, organized, and comprehensible overall. Any past or ongoing updates to our code can be tracked in our pull requests, which also contain some conversation in the comments about any issues or concerns that may have occurred.

## Testing

We know time is tight in the project, so it is fine if you don't test everything if your group decides to focus on other aspects of the program, but we want to see evidence of testing.

A significant portion of your code should be tested to earn full marks for this (run your tests with coverage to check).

It’s tricky to perform a complete test on the database used in our system, as we have to go between the database application and check if data was correctly uploaded to the MongoDb server. However, we have made tests for the basic components of our program, such as logging in a user into their account and making sure that their username and password matches the one in our database. As for our command line, we’ve also been testing it by running it directly in the console, making meal and workout plans, and checking what is being printed to the user. There are still other aspects that we want to test, but so far we’ve covered the most core aspects.

## Code Organization & Refactoring

Pull request #6 and #7 are both examples of refactoring code. #6 involved facilitating the flow of information better by creating helper methods in class, and #7 was a move to make one of our use cases adhere to the clean architecture principles better by making sure that components in different layers contained single responsibilities.

One obvious code smell we have falls under the bloater category: our main method for the command line is quite large because there’s potentially a lot of text input to be read, processed, stored, and outputted. We’ve refactored it by creating several helper methods, but it’s still a lengthy method since it initializes a lot of objects and we’ve recently implemented new features into it. Our next step is to polish up the implementation and then refactor the code down further or delegate responsibilities to the controller class more.

### Packaging Strategies
We thought about two different packaging strategies: by feature and by layers. In the end, we decided on packaging the project by feature as this makes it easier for us to understand which class works with which entity. Packaging by layers doesn’t necessarily keep us as organized, as we have multiple components in some layers that don’t share a similar function or have much to do with each other at all.




