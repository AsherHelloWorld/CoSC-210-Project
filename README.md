# W26-COSC-210

## Team Name:
* Team member 1: **Asher Nathan**
* Team member 2: **Quinn Handy**

## Project proposal:
### <ins> Weekly Planner </ins>
This application will allow users to keep track of what we need to do that week, by showing the user all 7 days, and the events on those given days. It will consist of "Event Objects" or something equivalent to this, and be able to have an arbitrary number of these objects inside. We will use the planner ourselves, and offer it to other people if they are interested.


### USER STORIES FOR *ASHER*
* As a user, I want to be able to add an event to my weekly plan so that I can keep track of my week. 
* As a user, I want to be able to see all my current tasks, so that I can get a sense of how my week looks. 
* As a user, I want to be able to set a time/date for that event to help avoid overlaps. 
* As a user, I want to be able to delete an event, so that I can change my plans. 
* As a user, I want to be able to clear my entire week, so that when a new week starts, I can start fresh. 
* As a user, I want to be able to label a task "permanent," so that it will perservere through a week clear (such as a class that I have every week).

### USER STORIES FOR *QUINN*
* As a user, I want to be able to change the time/date of events so I can adjust if things change. 
* As a user, I want to add notes to events so I can prepare for what's happening. 
* As a user, I want to add a location to events so I can know where it is. 
* As a user, I want to be able to view all the events I have in a given day so that I can be ready for the day. 

## Phase 4: Task 2

Representative sample of events logged during a typical session (printed to console on application quit):
```
=== Event Log ===
Mon Mar 23 17:45:01 PDT 2026
task added to planner: COSC 210 Lecture [Monday at 10h]

Mon Mar 23 17:45:14 PDT 2026
permanent task added to planner: Weekly Team Meeting [Wednesday at 14h]

Mon Mar 23 17:45:28 PDT 2026
task added to planner: Gym Session [Tuesday at 8h]

Mon Mar 23 17:45:35 PDT 2026
task added to planner: Study Session [Thursday at 3h]

Mon Mar 23 17:45:42 PDT 2026
planner tasks sorted by day and time

Mon Mar 23 17:45:50 PDT 2026
task removed from planner: Gym Session [Tuesday at 8h]

Mon Mar 23 17:45:58 PDT 2026
planner cleared: 2 non-permanent task(s) removed
```
## Phase 4: Task 3

Looking at the UML diagram, one refactoring worth considering is extracting a PersistenceManager interface (or abstract class) that both JsonReader and JsonWriter would implement or extend. Currently, PlannerGUI and UI each directly instantiate JsonReader and JsonWriter as concrete classes, which tightly couples the UI layer to a specific persistence format. By introducing a PersistenceManager abstraction, the UI would depend on an interface rather than concrete implementations — making it straightforward to swap in a different storage backend (say, XML or a database) without touching any UI code. This is a classic application of the Dependency Inversion Principle, and while the current JSON-only scope makes it unnecessary, the coupling becomes a real liability the moment a second format is needed.

A second refactoring worth noting is splitting PlannerGUI into smaller, more focused classes. Right now PlannerGUI handles layout initialization, user input validation, task CRUD operations, persistence, and event log printing all in one class — which is a violation of the Single Responsibility Principle. For example, the input validation logic (parsing the time field, catching invalid day/duration exceptions, showing error dialogs) could live in a dedicated TaskFormController, while the list rendering could move to a TaskListPanel. This would make each piece easier to test in isolation and easier for a second developer to understand quickly. The tradeoff is added indirection and more files to navigate for what is ultimately a small application, so it is a judgment call rather than a strict improvement.
