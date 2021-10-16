## Specification

### Phase 0
Implemented Use Case: UserRegister (Walk through)
User enters their name, username, email and password
If their input are valid, give the user access to create a schedule or quit
Let the user input the schedule name
Create a workout (input name and estimated calories burnt) and store it in a day of the week in the schedule
Repeat 4 until everything is planned.
After planning everything, outputs the schedule for the week


### Phase 1
#### Users can create workout plans
User can add workouts to their plan for each day of the week
Inputs: WorkoutName, CaloriesBurnt, Day of Week
Output: Workout object is created and added to a plan

#### Reminder prompt on Login
Upon login, users are prompted with their plan(s) of the day 
Inputs: Day of the week, LoginSUCCESS/FAILURE
Output: String output of workout object(s)


#### Create workout routine/schedule templates to get user started 
Input: 
Output: Schedules for user to choose

#### Admin-level interface
Interface to remove user/workouts/edit schedule templates


### Phase 2
#### User can track height, weight, BMI, over time
Inputs: Weight, Height, Date
Output: 
#### Weight loss/gain helper
User will receive a suggested calorie intake based on their workout schedule and their height + height
Input: User health info, target weight
Output: Suggested calorie intake, target amount of daily calories burned 

#### Implement UI - web app/android app
