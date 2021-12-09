
# Phase 2 Documentation

## Functionality

### Updated Specifications (Progress Report)

UseCases

-   ListScheduleUseCase
    

    -   Displays a list of public/the user’s schedules
    

-   ReminderPromptUseCase
    

    -   Gives users their schedule for the day upon login.
    

-   SetActiveUseCase
    
-   removeScheduleUseCase
    
-   BMIUseCase
    

    -   Allows users to get their current BMI and health range.
    

-   AddHeightWeightUseCase
    
-   HeightWeightOvertimeUseCase
    

    -   Gives users’ height and weight history from a date they give to the current day
    

-   AddPublicScheduleUseCase
    
-   editSchedule
    

    -   Users can modify the name of their schedules, and add and remove workouts/meals
    

Design patterns

-   Strategy pattern
    

    -   cm/ft, kg/lbs
    

-   Factory pattern
    

    -   Meal, Workouts
    

-   Facade
    
    
### Major Decisions

The User can now keep track of their weight and height on a daily basis. To keep track of these records along with the date of any changes, we created a new database (“User_HW”) collection that is linked with their unique username.


## Individual contributions since Phase 1

Jayson

-   [Kept working on Database methods](https://github.com/CSC207-UofT/course-project-spherical-miracle/pull/34/commits/785ee656d25b8a6067059139e0eec6865f8c931f)
    
-   Implemented Use Cases & Controller & Presenter According to it
    

    -  ListScheduleUseCase
    

    -   Refactored functions to be reused in different controller & UseCase, such as SetActiveUseCase & removeScheduleUseCase
    

    -   [ReminderUseCase](https://github.com/CSC207-UofT/course-project-spherical-miracle/pull/32)
    
    -   [SetActiveUseCase](https://github.com/CSC207-UofT/course-project-spherical-miracle/pull/32)
    
    -   [RemoveScheduleUseCase](https://github.com/CSC207-UofT/course-project-spherical-miracle/pull/33)
    
    -   [BMIUseCase](https://github.com/CSC207-UofT/course-project-spherical-miracle/pull/34)
    
    -   [AddHeightWeightUseCase](https://github.com/CSC207-UofT/course-project-spherical-miracle/pull/34)
    

        -   Edited User Entity to have an instance variable of Height & Weight
    
        -   Added a class, MeasurementRecords to return a class of user height & weight information
    
        -   Added Database collection called “User_HW”, storing their Weight & Height informations overtime
    
        -   New Database methods to store/retrieve users MeasurementRecords
    
        -   Used Strategy Design, allowing users to input their preferred measurement
    

    -   [HeightWeightOvertimeUseCase](https://github.com/CSC207-UofT/course-project-spherical-miracle/pull/38)
    
    -   AddPublicScheduleUseCase
    

-   Added new “menu” to UI
    

    -   weightHeightBMIMenu
    

-   [Added packaging](https://github.com/CSC207-UofT/course-project-spherical-miracle/pull/42)
    
-   Code clean-ups
    

  

Sissi

-   Implemented [deleting schedule use case](https://github.com/CSC207-UofT/course-project-spherical-miracle/pull/33),  [reminder prompt](https://github.com/CSC207-UofT/course-project-spherical-miracle/pull/25), and fitness measurements summary with teammates
    

-   Background research on [potential UI features](https://docs.google.com/document/d/1616cj5ZThe-MEnUiC2B7aPG5E_4EYj0UEi_dT_AVMvw/edit?usp=sharing) and [formulas used to calculate BMI](https://docs.google.com/document/d/1gr2S1TRmUMcGdp5NsxDVAfAen8QCoAPwUWTB6J8my8A/edit)
    
-   Lead discussion on accessibility for UI(created visual wireframe for app version of program)
    
-   Code clean up: Javadocs + removing warnings, dead code, comments, TODOs, and code smells  
      
    

Michael

-   Clean Architecture/SOLID refactoring
    

    -   [Introduced new entry point to App and refactored UI, Facade design pattern for controller](https://github.com/CSC207-UofT/course-project-spherical-miracle/pull/30)
    

    -   Refactored UI based on SRP, DIP
    

-   Features/specification implemented (PR linked)
    

    -   [Edit schedule](https://github.com/CSC207-UofT/course-project-spherical-miracle/pull/41)
    

        -   Changing the name of a schedule
    
        -   Creating/removing a workout/meal from a specific day
    

    -   [Conversion between String representations of entities to instances of entities](https://github.com/CSC207-UofT/course-project-spherical-miracle/pull/24)
    
    -   [Separated enums into their own classes](https://github.com/CSC207-UofT/course-project-spherical-miracle/commit/bde16524b24c32df17d787dd6ec4b4865587f6e7)
    
    -   [Implemented a mock database for testing purposes](https://github.com/CSC207-UofT/course-project-spherical-miracle/pull/43)
    
    -   [Decoupled Use Cases to remove dependencies](https://github.com/CSC207-UofT/course-project-spherical-miracle/pull/47)
    

-   Explained the roles of the output boundaries and the presenter and their implementation
    
-   Cleaned up code smells, ensured variable names follow Java naming conventions
    
-   Improved user interface using prompts with more clarity
    

  

Edgar

-   [Added test cases for use cases](https://github.com/CSC207-UofT/course-project-spherical-miracle/pull/48)
    
-   Ran the code multiple times to find any bugs
    
-   Helped write pseudo code for the reminder part of the code
    

  

Pirooz

-   Discussed with group members what design patterns may be useful in our code
    
-   Implemented [factory design](https://github.com/CSC207-UofT/course-project-spherical-miracle/pull/36) pattern for Workout/Meal entities
    
-   Implemented an abstract parent class for Workout/Meal entities (AbstractMealWorkout)
    
-   Written [test cases](https://github.com/CSC207-UofT/course-project-spherical-miracle/pull/35) for Day and Meal classes
    
-   [Fixed bugs](https://github.com/CSC207-UofT/course-project-spherical-miracle/pull/35/commits/5784a542616bdf23832c68b999e909044f849259) in Day class (RemoveMeal and RemoveWorkout)
    
-   Helped [clean up code smells](https://github.com/CSC207-UofT/course-project-spherical-miracle/pull/40) (dead code, comments), TODOs, fixing bugs
    
-   Helped (a bit) with making code follow clean architecture rules
    
-   Helped clean up and [refactor](https://github.com/CSC207-UofT/course-project-spherical-miracle/pull/38/commits/5376ac2ff4851e8c97bd960a7eab0cfb43a26ffc) a bit of the methods for BMI calculator
    

  

Tianji

-   Implemented:
    

    -   factory design pattern for Workout/Meal entities
    
    -   strategy design for weight and height conversions (in AddHeightWeightUseCase)
    
    -   [BMIUseCase](https://github.com/CSC207-UofT/course-project-spherical-miracle/pull/34) (returns user’s BMI)
    
    -   [HeightWeightOvertimeUseCase](https://github.com/CSC207-UofT/course-project-spherical-miracle/pull/38) (return user’s height and weight since given date)
    
    -   [ReminderPromptUseCase](https://github.com/CSC207-UofT/course-project-spherical-miracle/commit/805828e348c264629e2f1042d241440efe00511a) (return user’s schedule for the day upon login)
    
    -   Converting strings from database to schedule object ([FetchScheduleUseCase.stringToSchedul](https://github.com/CSC207-UofT/course-project-spherical-miracle/commit/0917b99c1f280fbc60ecabecb76850e1f5989015)e)
    

-   Cleaned up code for all entities (code smells fixed: dead, duplicate, and lazy code)
    

    -   [Duplicate code](https://github.com/CSC207-UofT/course-project-spherical-miracle/commit/ce6c81e64f16a5bc78a31ed94aebc67fb19a4669)
    
    -   [Dead Code 1](https://github.com/CSC207-UofT/course-project-spherical-miracle/commit/2242ee2b7b1765b1acc3607e36e2c2ce1c07e1f2)
    

-   Co-lead discussion on how and which design patterns to incorporate.
    

  

## SOLID DESIGN

#### Single Responsibility Principle

All of our use cases demonstrate the single responsibility principle. See [use cases](https://github.com/CSC207-UofT/course-project-spherical-miracle/tree/main/src/main/java/Schedule/UseCase).

Each of them have a clear, defined purpose.

  

#### Open/Closed Principle

Our interactors are open to extension but closed for modification.

Example: Saving data to a database

-   The [use case](https://github.com/CSC207-UofT/course-project-spherical-miracle/blob/6e20963b2e4f74f3c3e463d39d567d782e1376a2/src/main/java/Schedule/UseCase/ManageScheduleUseCase.java#L58) that handles the stringification of entities follows OCP
    
-   If we want to extend the behavior of this use case by saving it to a database, we don’t need to modify any of the high-level code, i.e. use case. Instead, as long as the low-level database class implements the required data access interfaces, the save behavior will work as intended.
    

-   Code examples: [MongoDB](https://github.com/CSC207-UofT/course-project-spherical-miracle/blob/6e20963b2e4f74f3c3e463d39d567d782e1376a2/src/main/java/DataAccess.java), [MockDatabase for testing](https://github.com/CSC207-UofT/course-project-spherical-miracle/blob/6e20963b2e4f74f3c3e463d39d567d782e1376a2/src/test/java/MockDatabase.java)
    

Second Example: ScheduleEntityFactory

-   The factory allows for extension for new similar entities such as a drink or snack entity
    

-   This does not result in necessary modifications of high-level code that uses the factory
    

.

#### Liskov Substitution Principle

Throughout our code, we followed LSP by declaring an abstract collection C as the type, and instantiating with objects of type C. For example: see [declared type and instantiation of List and ArrayList](https://github.com/CSC207-UofT/course-project-spherical-miracle/blob/6e20963b2e4f74f3c3e463d39d567d782e1376a2/src/main/java/Schedule/Entities/Day.java#L25) in our Day class.

  

#### Interface Segregation Principle

We divided the DataAccessInterface into two interfaces that handle different scenarios: ScheduleDataAccess and UserDataAccess

-   ScheduleDataAccess handles the data handling for schedule-related data
    
-   UserDataAccess handles user-related data.
    

This way, the use cases that create users do not depend on the interfaces that it does not use. In this case, the schedule-related methods.

  

We were looking to do the same for the Presenter class. However, due to time constraints, we were not able to implement that. For now, a change in schedule-related output methods will also cause non schedule-related modules to be recompiled and redeployed. This is not ideal because changes in an unrelated method/class may result in redeployment.

  

Luckily, not all our classes ignore the ISP. Our ManageSchedule use case class mainly follows ISP as it implements different input boundaries for each method that does something different. When ScheduleController is dependent on a particular use case, change to an unused use case doesn’t cause it to recompile.

  
  

#### Dependency Inversion Principle

All our use cases have instance variables that depend on an abstraction. In other words, the instance variables are all instances of an interface, not a class. This abstraction decouples the dependency from the implementation, allowing us to make changes to the implementation without affecting the code that depends on the interface. This is evident in our Controller classes when we inject the database gateway and output interfaces to the use case. [Example](https://github.com/CSC207-UofT/course-project-spherical-miracle/blob/6e20963b2e4f74f3c3e463d39d567d782e1376a2/src/main/java/Schedule/ScheduleController.java#L34).

![](https://lh6.googleusercontent.com/iOyLh9tUNDIOAq3hFyiODd9Hzfv4Ya5IZldtoqo7OJT2oixxRRPV3NIvVWoeuzwyXq2QrlyTVj1dpgRzDECB0CWxkHkFNyEme_ZJYwh8dUKDSybIHE8qVOIqmJAfkWCMDQ1qucgJ)## Clean Architecture

Our code is structured like the following diagram: The classes with the most details are in the outer layer, and our entities are in the innermost layer.

![](https://lh4.googleusercontent.com/05j0DH6GoKO-tPGSWwfAjCcsFQkkbs9OmQT3MJ86Z5xjHoLeZHwHFNFrHi9GWtO6QqSeg9fEndJU0XGwPJ6s7nfTIf9sjfehv13J0jLHlgBRqqBq6sYBjyG-rtsEqPoxFc41wSwn)

To see this, you can take a look at the import statements in each of our classes. Classes in the adapter layer will only have imports from the Use Case Layer, or the boundaries between the adapter layer and the outermost layer, needed for dependency injection. Example screenshots:

![](https://lh5.googleusercontent.com/AlxMsopCzwrfzxpFDY8IputtjGekOTaN16o1BdkgBaVkLBwXRaCcUx1FUQv2yZrqh2n2suy6KZMD3hoQpO04EWAWkp2JXFBdJChxK5eEb5WT3Vo8qMeQzMgfO_7IG7FloC6pwIPy)

![](https://lh5.googleusercontent.com/7NEIYGo0cS-FkteYHaE-NipC4GMnc-nkfJlXh8SMXV04Mg6cASLpz78PuYKUGnW14uBq0XqnUVQMnlg1UeKlEiGThJpUq_nxk4ib9Xr_aG_6BsLiVAqzIlKUXN2CW4RITKqTCQHJ)

![](https://lh4.googleusercontent.com/UJjUhmEFRCnfN7JXfwCwcrfZ2UAwxR9f85JnVwbvf8Rot7MMHqO39p0SJkOEq1LOuGBfzcni5axgCfzQPwdki3TU26udLedmUhViWls1qq1Ky__ixIFWxUjEYMtZF-ZoAauyyR5a)

  

### Scenario Walkthrough - User Signup

Once the program runs, the database and its ``dataAccess`` class is initialized in the outermost layer, along with the UI.

The user is prompted by ``ScheduleUI`` to login or sign up.

The UI takes in user input and sends it (as a simple data structure) to ``UserController``, The ``UserController`` parses the input  and passes the input through ``CreateUserInputBoundary`` to access the ``CreateUserUseCase``.

The ``DataAccessInterface`` is injected into the ``CreateUserUseCase``.

The use case packages user info into a simple data structure and passes it to the ``saveUser`` method implemented by the ``DataAccessInterface``.

Our ``DataAccess``class implements the ``DataAccessInterface``, and writes the given data into our MongoDB database.

This walkthrough demonstrates Clean Architecture because none of the classes in the inner circle know how the database is implemented. The use cases only interface with the input boundaries and the dataAccess interfaces.

  
  

### Create and view Schedules

User signs up or log in.

User then inputs the option to create a schedule, which is taken in by ``SchedulerUI``

The UI then prompts the user to input a schedule name, which is then sent to ``MainController``, delegating it to ``ScheduleController``.

The controller then sends the schedule name to ``ManageScheduleUseCase``, which then creates a new empty schedule and sent to ``Presenter`` to prompt the user to either edit the schedule or save the schedule and return to the main menu

The user then chooses to edit the schedule, making ``ManageScheduleUseCase`` send ``Presenter`` to prompt the user to pick which days they want to add meals/workouts.

This information is then given back to the use case to change the schedule accordingly.

Once the user is done making their changes, the use case gives the new schedule to ``ScheduleDataAccess``, which then gives the information to DataAccess to save the information into the MongoDB database.

The user is then sent back to the main menu of the UI, and from here, can choose to view their schedules.

After the user inputs that they want to view their schedules, this request is again sent to ``ScheduleController`` which then gets schedules of the current user by calling ``FetchScheduleUseCase``.

The controller will then call ``Presenter`` to print out the users’ current schedule in the UI and options to choose a schedule to do something with

The user then inputs the number of the schedule they want to see and types in detail to see what the schedule is like.

### See today’s reminders

User signs up or logs in.

User then inputs the option to see their reminders for today, which is taken in by ``SchedulerUI``

The UI then sends this input to ``MainController``, which then delegates it to ``ScheduleController``.

The controller then calls ``ReminderPromptUseCase``, which will get the information of what schedules the user has to be reminded of today from ``FetchSchedulesUseCase``.

The output will then be sent back to ``Presenter`` to output the information back to the user.

### Add information about height/weight and see BMI

User signs up or logs in.

User then inputs the option to add information about height/weight and BMI, which is taken in by ``SchedulerUI``

The UI then gives the user options to view their current BMI/height/weight, history of their BMI/height/weight, add a new record of their height/weight, or to return back to the main menu

The user then inputs the option to add a new record of their height and weight, which is then sent to ``MainController`` which delegates it to``UserController``.

The input is then sent to ``AddHeightWeightUseCase``, which is then given to the ``Presenter`` class to prompt the user to input their height and weight

Once the user inputs correctly formatted information of their height/weight, ``AddHeightWeightUseCase`` sends the information to `UserDataAccess``, which then gives the information to DataAccess to save the information into the MongoDB database.

The user is then sent back to the original menu in the UI, where they can then choose to view their current BMI/height/weight

This is again sent to ``UserController`` which is then given to ``BMIUseCase``.

The use case will then get the user’s current height/weight from ``FetchUserUseCase`` and use that information to get the user’s current BMI

It will then give this information to ``Presenter`` to output the user’s BMI information to the UI.

### Clear violations in our current code

Previously, our main UI instantiated entities directly, which violated clean architecture. A new change we made for Phase was to create a main controller that the UI calls on to fulfill actions.

Furthermore, the code for our main UI was fairly bloated and didn’t follow the Single Responsibility Principle. Keeping this in mind, we tried to avoid bloated code and other code smells as we implemented new features into our program. For example, to keep the main controller organized, we delegated and grouped its responsibilities into three sub-controllers: ``userController, scheduleController, sessionController.``

We’ve also been tracking any notable violations throughout Phase 2 such that we’ve gone back and resolved those issues, whether that be by having a productive discussion with teammates to discuss an alternate solution or by refactoring code.

### The Dependency Rule consistently followed when interacting with details in the outer layer?

In our scenario walkthrough for user signup, we used dependency injection with the ``CreateUserUseCase``. We did not directly call the saveUser method in the ``DataAccess``. Instead, we injected the ``UserDataAccess`` interface and have the use case call the method in the interface.

##  Design Patterns

-   Facade
    
-   Factory Design
    

![](https://lh3.googleusercontent.com/tQrZ1XhRBFhKc-HPYqKWJ6IVfu3lmwKRXG_PcYy1irxoBSga-q-H_2B7lVobAK7iK0ObMs8HIZmf4WRDpSs0udtCRMuOW-mFPtsclt5Rwdg6YJildVaz09mPV4ZnXUXdZ5Z4mq7N)

-   Strategy Design
    

![](https://lh4.googleusercontent.com/_V39yiVAvsRnPl1TfFBNp6GVy94x-KFfM0XOXAAihc6ScwOz61Eib4D9b21ELE2X2I0HvzcIDb4rNUO6aNfc5oNiviIoRJUNvBk_65XAyUDjGMvLubSNUQHCf8HObVaBsGkzENYW)

##  Github Features

This phase, we were more active in letting our teammates know as soon as possible about pull requests. As a result, we stayed more organized and on top of any big changes to the program.

We also continued keeping each other updated on issues, questions, or updates via our group chat and during [team meetings](https://docs.google.com/document/d/1VYp_cdAkw4R7k7D2m_IcSGUWHfWKHaIGIPGL9AWWNc8/edit#heading=h.t2bamzv2yim5).![](https://lh6.googleusercontent.com/Qcts8s5WMOzF3caSp_3OaR112T_1Z6_OEGbltCcO0dxpZ9oG-UeRcsQz4IhMd0WzS-hi2W4LF__UvQG8IY33BlmbLz5-hblPL6cHNWiOnbTgV8TTnKyOUP6mJ7oUp41gD5VJySiq)![](https://lh5.googleusercontent.com/SI0mnes70nMTvCtnXvr96pOfnw_tBWO_NjxBkUfyEQeGrqEJdKluVqHG-cKspNb0jYCSUj_jaj_C08kyfkEb8OrqtkCmcWKFKe8NCRRLuhyI2wFF3wP3p3q3l-mt92ic_IZaxT6X)

##  Code Style and Documentation

We also tried to keep code style in mind during this phase, but sometimes being focused on implementing a new feature led to forgetting about proper documentation. In those cases, we made sure to periodically make note of files that we needed to clean up later.

## ## Testing

Testing was difficult because many classes take user input and it’s hard to simulate and test that, so the only way we could really see if it was working correctly was by running the code itself. Everything we could test though, which is mainly entities and use cases, we mostly tried to test as thoroughly as we can, thinking of edge cases for which the program should accept or reject. From this, we were also able to find bugs in a few of the classes, mainly the Day class.

##  Code Organization & Refactoring

Throughout Phase 2, we looked out for possible ways to refactor and reorganize our code. Addressing the feedback from phase 1, we looked for coupling in our use cases and [fixed it](https://github.com/CSC207-UofT/course-project-spherical-miracle/pull/47). We went through use cases where one use case is calling another, and we delegated that call to the controller. In addition to decoupling, we made sure to [separate the LoginResult enum from the Use Case](https://github.com/CSC207-UofT/course-project-spherical-miracle/commit/bde16524b24c32df17d787dd6ec4b4865587f6e7) and import it when needed.

### Packaging Strategies

We updated our packaging to be more systematic and specific in terms of where files are located. In addition to packaging by feature, there are now subfolders within the feature folders. These subfolders separate files by which layer they’re in for Clean Architecture, which makes our project much easier to navigate.

  

Also, we have updated our packaging strategies to use [Layer Structure](https://github.com/CSC207-UofT/course-project-spherical-miracle/pull/42). As our program now follows the clean architecture rule, we thought this packaging can show the segregation of UI, Interface Adapters, and the Business rules.

  

####  Accessibility Report

Note: all wireframes for the app user interface are at the end of this document.

1.  For each Principle of Universal Design, write 2-5 sentences or point form notes explaining which features your program adhere to that principle.
    

Principle 1: Equitable Use

-   Program has features to accommodate people with disabilities:
    

-   Intellij offers dark mode/high contrast mode, options to adjust font size, and screen reader for the text interface
    
-   Features that would be integrated into the app UI: dark mode/high contrast mode, option for larger scale font and buttons, screen reader, alternate keystrokes or bindings for those who have physical disabilities and may not be able to easily move their hands
    

-   These are all customisable from the user’s end to make their user experience equal to other users’ experience
    

Principle 2: Flexibility in Use

-   Like stated above, an individual user’s preferences can be applied with the customisable features for both the text interface and app design
    
-   Users can choose to listen to read the UI, different colour themes, and potentially use different keystrokes to initiate actions (not implemented yet)
    

Principle 3: Simple and Intuitive Use

-   All text in our program is made to be as readable and comprehensible as possible, even across different age groups and reading levels
    
-   Information that is presented to the user is either as simple as possible or organized into a nice format
    

-   ex. Viewing a neat list of possible actions in the main menu)
    

-   We also have some feedback prompts for telling the user if any action was successfully completed
    

-   Ex. feedback of successfully logging the user out as opposed to immediately shutting the program down)
    

Principle 4: Perceptible Information

-   Necessary information can be perceived via text and dark mode or screen reader
    
-   In the future, we’d also want to implement alternate keystrokes or allow for console use to input information and navigate our program
    

Principle 5: Tolerance for Error

-   Currently, text interface gives prompt telling user if they inputted invalid commands or information
    

-   They are allowed to try again as many times as they want until they input the correct information
    

-   App interface would do the same
    

Principle 6: Low Physical Effort

-   Text interface requires user to hit physical keys on their keyboard
    

-   but actions don’t time out (action is not intensive or limited to be carried out in a short amount of time)
    

-   The app interface would require swipes for vertical scrolling and taps to press out buttons
    

-   Users can also change the keystrokes/keybinds to make it easier for themselves (Ex. WASD where hand can kept in one position and user doesn’t have to jump around the keyboard)
    

Principle 7: Size and Space for Approach and Use

-   App interface is laid out in a way to facilitate the flow of actions so that there is minimal clicking and swiping to complete actions
    

-   Big home button and clear navigation bar for important pages on app UI
    
-   Would allow for assistive device usage
    

-   Not as applicable for text interface because it specifically requires keyboard usage to input commands and information
    

-   Future plans would be to also allow for assistive devices and alternate console usage  
      
    

2.  Write a paragraph about who you would market your program towards, if you were to sell or license your program to customers.
    

We would market our program to people who are interested in staying organized or committed to a fit lifestyle. This could be anyone from teenagers to mature adults. They can also be starting to implement fitness into their lives, since our program is beginner friendly: flexible and to use. Users can control how intense they want their fitness regime to be.

Specifically, our program offers BMI calculation and tracking of height and weight. These features may be particularly helpful for people trying to lose, gain, or maintain a certain weight.

3.  Write a paragraph about whether or not your program is less likely to be used by certain demographics.
    

Both the text interface and app interface are customisable and would offer flexibility to make our program more accessible and appeal to people who are colour-blind, far-sighted, visually impaired, dyslexic, or require using assistance devices or alternate consoles. Again, all of these features are optional for users so our program has high accessibility.

## Future ideas:

In general, we currently have a robust set of basic operations and actions that revolve around creating, editing, adding, and deleting schedules (which contain workouts and meals) for a user.

  

In the future, we would like to extend our current operations and allow for more specificity in user’s schedules. For instance, introducing macros (food groups) for meals and choosing a time slot for workouts. Additionally, we chose to focus on functionality of our code for Phase 2, so we would take the time to fully implement a working app interface in the future.

Also, there are some specifications we excluded as we thought it was unnesscary, or due to time constrain. Instead, we implemented other feature such as HeightWeightOvertimeUseCase, to display users weight and height change overtime.

3 main pages that are accessible from navigation bar: Day page, Schedule page, Personal Page![](https://lh4.googleusercontent.com/ITjCpOSgyL0PhnpMy_N9xJHM6AWhsJejC4U2PNhk5GcjYsS9lo9GbZo3ExoAXnbqdm5-35O6JMe_jRMLHWFun-KWC1OS5NCFt4NeZWqMtrS-canZOaTczaIIjQsCS6jExUBqbksx)

Flow of actions from Day page![](https://lh4.googleusercontent.com/wSZ86DJ4GM9dgSPImQ-nOXaOl49PVOXOafolCNi-xwdzC5_uPEyPlzZC1Sg7_oDGB-42WQHEflOoMJFUIsTMtYr0L0oGN56qz6KpVqAUwjUOIrPevbTjS4MSvV4LqOpbh7kA6P6t)Flow of actions from Schedule page![](https://lh6.googleusercontent.com/Fu2-DCa00Bmjys7tm3znOWyfCNlJb2BrqKZFluUkfLCagRve00JsDdLWb1MmjaHnHFC7QXRYeRtJog7ZqTmeiMSa5-q6yTMi_Iz6OaFKcgIm749sDtxlLWjlaoCppDde9qjURUGh)
