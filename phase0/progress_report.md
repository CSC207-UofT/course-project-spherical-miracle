## Specification
### Phase 0
Implemented Use Case: UserRegister (Walk through)
### Phase 1
#### Users can create workout plans
User can add workouts to their plan for each day of the wee

#### Reminder prompt on Login
Upon login, users are prompted with their plan(s) of the day

#### Create workout routine/schedule templates to get user started 

#### Admin-level interface
Interface to remove user/workouts/edit schedule templates


### Phase 2
#### User can track height, weight, BMI, over time

#### Weight loss/gain helper
User will receive a suggested calorie intake based on their workout schedule and their height + height

#### Implement UI - web app/android app?




## CRC model
We have implemented the following CRC models.
#### Entities
User
UserDatabase (will be changed)
Workout
Meal (To be implemented in the code in Phase 2)
Schedule
ScheduleDatabase (will be changed)
#### Use Cases
ManageUser
UserLogin
ManageData (to be done later)
#### Interface Adapters
InOut (Façade)
WorkoutSchedulerMain
#### Interface
CreateUserInputBoundary
LoginInputBoundary


## Scenario Walk-through
Start program
Have a choice to signup or login (just do signup for now)
Asked to input username, password, email, and their name
Prompted to choose what they want to do: they are given options to add a workout (later going to add food)
They need to add something to the workout like day 1: chest, day 3: legs, day 5: back, day 7: cardio. Then the remaining days will just be filled in as rest days.
For now, just put the broad name of the day (eg. the area of the body that is going to be worked on), but later on, will add the ability to specify the exercises done / and give reminders to workout.

## Skeleton Program
InOut: controller that takes in data from the interface and hands it off to the respective classes to handle
ManageSchedule: Adds and removes new schedules to the database
ManageUser: Adds and removes new users to the database
Schedule: Entity for the weekly schedules 
ScheduleDatabase: Database to keep all the schedules created
User: Entity that stores information about a specific user’s account
UserDatabase: Entity that keeps track of multiple users
UserLogin: Use Case that allows a user to log in with a password corresponding to their username
Workout: Entity that stores the type/name of the workout and details about it (ex. number of calories burned during it)
WorkoutSchedulerMain: the interface, reads the commands and inputs from the program user
LoginInputBoundary, CreateUserInputBoundary, CreateUserUseCase: in the process of being implemented for Phase 1

## Our plans
We have been working in groups of 2. We randomized the group for upcoming ideas for CRC cards & Coding. Everyone has worked on designing CRC cards, and the following group each did some coding:

Michael & Jayson
Worked on Entities
Tianji & Pirooz
Worked on Use cases
Edgar & Sissi
Worked on Interface + Controller

We are planning to randomize the groups again and working on making the app more customizable and personal for the user. Potential plans include adding more data to track about the user’s meal and workout plans. We’d also like to consider validating the email of users who sign up and validating the string input that we receive from the user.


## Designs so far that work well with our code
Using an array to keep track of a 7 day schedule (fixed size + faster to traverse)
Having separate entities for different aspects of the trackers (meal vs workout)

## Our questions
Best way to store our data and not just in a local way
If we wanted to have the interface as a website versus an Android app, are there different implementation benefits of either one?
Different ways to connect user and schedule and benefits of each approach?
