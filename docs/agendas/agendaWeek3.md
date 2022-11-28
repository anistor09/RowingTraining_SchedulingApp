# Agenda Week 3

The Agenda for the third week of the project

* Date: 29/11/2022
* Main Focus: Establish a definite requirements list, delineate the application's structure, start delegating issues, establish some work related procedures, and sketch a rough design of the application.
* Chair: Alexandru Nistor
* Note Taker: Alexandru Marin

## Opening

* Check if everyone is present.
* Check if everyone is up do date with the lectures.

## Approval of the agenda

* Does someone have something to add to this agenda?

## Reflection

* What went wrong in this past week?
* What went right in this past week?
* What should we improve for next week?

## Points of Action

* What is our requirements list lacking and which steps do we need to take to make it definite and open the issues?
* Is everyone satisfied with the classes and microservices that we have decided to have?
* Has everyboody looked over the repository template?
* When can we get a final feedback on the arhitecture in order to upload it by Friday?
* Request feedback from the TA on the Code of Conduct.
* Request feedback from the TA on the Context Mapping.
* Request feedback from the TA on the Classes Design.
* We should create a development branch before we start the coding part.


## More questions for the TA regarding the requirements:
* Should a user that has enrolled in an activity be able to cancel after he was accepted?
* What special rules can exist for a competition? Are all team members in a team always from the same organization and gender for all competitions?
* Should the user be notified in case he gets declined to join an activity?
* Should users be able to add new certificates? (Other than C4, 4+, 8+)
* Should we write requirements that do not affect the user? (Like more boats can be added to the system, because this affects us, the developers, not the user)
* Should activities have a location specified by the activity owner?
* Can an activity owner participate in their own activity?
* Should we include user account (Id, pass, user, posted activities, enrolled activities) in the bounded context map? Because during the lecture it was not part of the example on the slides.
* For the activity class we are wondering whether we can better make an activity object (with attributes Id, Owner of the activity- User, Pending participants - List[User], Selected participants- List[User], Date, Time, Type of boat) with children competition (with attributes gender, organization, competitiveness) and training (with no attributes), but then training does not have any attributes. Or if we should make training the parent with competition as a child, but then we define a competition as being a type of training, which it is not really.


## Action plan for this week (Scrum board)

* Open the issues of the backlog on gitlab
* Create a rough skeleton on the application
* Upload the draft for Assignemnt 1 by Friday

## Any additions

* Next week, Rares is the Chair, the minute taker is ... 

