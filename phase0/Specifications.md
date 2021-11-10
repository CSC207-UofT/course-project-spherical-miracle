## Specification

### Phase 0
Implemented Use Case: UserRegister (Walk through)
- Inputs: name, username, email, password, workout plan
- Outputs: Schedule.Schedule for the week 
1. User.User enters their name, username, email and password
2. If their input are valid, give the user access to create a schedule or quit
3. Let the user input the schedule name
4. Create a workout (input name and estimated calories burnt) and store it in a day of the week in the schedule
5. Repeat 4 until everything is planned.
6. After planning everything, outputs the schedule for the week


### Phase 1
#### Users can create workout plans
User.User can add workouts to their plan for each day of the week 
- Inputs: WorkoutName, CaloriesBurnt, Day of Week
- Output: Schedule.Schedule object is created and added to a plan

#### Reminder prompt on Login
Upon login, users are prompted with their plan(s) of the day 
- Inputs: Day of the week, LoginSUCCESS/FAILURE
- Output: String output of workout object(s)


#### Create workout routine/schedule templates to get user started 
- Output: Schedules for user to choose

#### Admin-level interface
Interface to remove user/workouts/edit schedule templates


### Phase 2
#### User.User can track height, weight, BMI, over time
- Input: Weight, Height, Date
- Output: A graph/table that shows the history
#### Weight loss/gain helper
User.User will receive a suggested calorie intake based on their workout schedule and their height + height
- Input: User.User health info, target weight
- Output: Suggested calorie intake, target amount of daily calories burned 

#### Implement UI - web app/android app
