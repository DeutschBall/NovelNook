# Novel Nook D1



## Sub groups

PM: 李成溪

| Team ID | Role          | Team Leader |
| ------- | ------------- | ----------- |
| Team 1  | Super -user   | 倪海瑞      |
| Team 2  | Patron        | 陈逸飞      |
| Team 3  | Administrator | 孙剑豪      |
| Team 4  | Library Staff | 任旭坤      |



## User Roles

* Super -user
* Patron
* Admin
* Library Staff
* Guest



## Super -user - Team 1

### User’s Story mapping

#### Admin management

Add&delete

	* Find the admin
 * Add the admin
	* delete the admin

Data backup

 * back up the latest data
 * delete useless data

Data Restore

* Restore Data

### Product Backlog

| User Story ID | User Story Description                                       | Priority | Estimate(Story Points) | Status |
| ------------- | ------------------------------------------------------------ | -------- | ---------------------- | ------ |
| SU001         | As a super user, I want to create and delete administrators account. | 5        | 3                      | To do  |
| SU002         | As a super user, I want to back up important data in case of data loss. | 4        | 3                      | To do  |
| SU003         | As a super user, I want to restore data after corrupt        | 3        | 2                      | To do  |

### Sprint Backlog

| User Story | Task                                  | Estimate(Hours) | Status | Sprint |
| :--------- | ------------------------------------- | --------------- | ------ | ------ |
| SU001      | Develop admin account add function    | 8               | To do  | SP01   |
| SU001      | Develop admin account delete function | 6               | To do  | SP01   |
| SU002      | Develop data backup function          | 10              | To do  | SP02   |
| SU003      | Develop restore function              | 10              | To do  | SP03   |

### Sprint Plan

| Development phase | task                                  | Date    |
| ----------------- | ------------------------------------- | ------- |
| SP01              | Develop admin add and delete function | Week 3  |
| SP02              | Design front page of the super -user  | Week 4  |
| SP03              | Develop data backup function          | Week 8  |
| SP04              | Develop restore function              | Week 12 |



## Patron - Team 2

### User Story Mapping

SEARCH & VIEW

- Search for a material by keywords, author, subject.
- View borrowing status.

- View borrowing history and due date.
- view materials' detailed information.

BORROW & RESERVE & RENEW

- Borrow a material after the system confirms it is available.
- Reserve a material and receive notification when it is available.

- Delay the material due date for a specified period of time by the patron.

RETURN & CHARGE

- Return a material corresponding to a borrowing record.
- Charge for the borrowing fee.
- Charge for extra fee by time if overdue.

### Product Backlog

| User Story ID | User Story Description                                       | Priority | Estimate(Story Points) | Status |
| ------------- | ------------------------------------------------------------ | -------- | ---------------------- | ------ |
| PT01          | As a patron, I wish I could search for a material.           | 5        | 5                      | To do  |
| PT02          | As a patron, I wish I could view the detailed material information. | 4        | 4                      | To do  |
| PT03          | As a patron, I wish I could view the borrowing status of a material. | 4        | 4                      | To do  |
| PT04          | As a patron, I wish I could view my borrowing history and due date. | 4        | 2                      | To do  |
| PT05          | As a patron, I wish I could borrow a material which is available. | 3        | 4                      | To do  |
| PT06          | As a patron, I wish I could return  borrowed materials.      | 3        | 3                      | To do  |
| PT07          | As a patron, I wish I could reserve a material that can't be borrowed temporarily. | 2        | 2                      | To do  |
| PT08          | As a patron, I wish I could receive a notification when the materials I reserve are available. | 2        | 2                      | To do  |
| PT09          | As a patron, I wish I could renew borrowed materials.        | 2        | 2                      | To do  |
| PT10          | As a patron, I wish I could receive a notification when due dates of some materials I borrowed are approaching. | 1        | 1                      | To do  |

### Sprint Backlog

| User Story | Task                                                         | Estimate(Hours) | Status | Sprint |
| :--------- | ------------------------------------------------------------ | --------------- | ------ | ------ |
| PT01       | Develop search engine functions                              | 8               | To do  | SP01   |
| PT01       | Design and develop web UI of search                          | 6               | To do  | SP01   |
| PT02       | Develop material information view                            | 4               | To do  | SP01   |
| PT02       | Design and develop web UI of material information            | 3               | To do  | SP01   |
| PT03       | Develop the function of viewing the borrowing status         | 4               | To do  | SP01   |
| PT03       | Design and develop web UI of viewing the borrowing status    | 3               | To do  | SP01   |
| PT04       | Develop the function of viewing borrowing history and due date. | 4               | To do  | SP01   |
| PT04       | Design and develop web UI of borrowing history and due date  | 4               | To do  | SP01   |
| PT05       | Develop function of material borrowing                       | 10              | To do  | SP02   |
| PT05       | Design and develop web UI of material borrowing              | 8               | To do  | SP02   |
| PT06       | Develop function of borrowing fee calculation                | 2               | To do  | SP02   |
| PT06       | Develop function of material returning                       | 10              | To do  | SP02   |
| PT06       | Design and develop web UI of material returning              | 8               | To do  | SP02   |
| PT07       | Develop function of material reserving                       | 6               | To do  | SP03   |
| PT07       | Design and develop web UI of reservation                     | 5               | To do  | SP03   |
| PT08       | Develop function of reserve notification                     | 6               | To do  | SP03   |
| PT09       | Develop function of material renewal                         | 6               | To do  | SP04   |
| PT09       | Design and develop web UI of material renewal                | 4               | To do  | SP04   |
| PT10       | Develop function of notification of overdue material         | 2               | To do  | SP05   |

### Sprint Plan

| Development phase | task                                                         | Date      |
| ----------------- | ------------------------------------------------------------ | --------- |
| SP01              | Develop functions of search engine, viewing material information and viewing borrowing history and due date | Week 3    |
| SP02              | Develop function of borrowing, charge system and material returning system | Week 4    |
| SP03              | Develop material reserving system                            | Week 5-8  |
| SP04              | Develop material renewing system                             | Week 9-10 |
| SP05              | Develop function of notification of overdue material         | Week 11   |



## Admin - Team 3

### User Story Mapping

LIST 

​	List user and user group

​	delete and add new user

​	set user role

DETAILED 

​	view 

​	modify

BATCH OPERATION

​	add user by importing excel table

​	checkbox on the user list page

### Product Backlog

| User story ID | User Story Description                                       | Priority | Estimate (Story Points) | Stauts |      |
| ------------- | ------------------------------------------------------------ | -------- | ----------------------- | ------ | ---- |
| AD01          | As an administrator, I wish i could list all the users and add new user | 5        | 5                       | To do  |      |
| AD02          | As an administrator,I wish i could view users' detailed information via the button on the user list | 4        | 4                       | To do  |      |
| AD03          | As an administrator,i wish i could modify a user's information on the detailed information page | 4        | 4                       | To do  |      |
| AD04          | As an administrator,i wish i could set one's role            | 3        | 3                       | To do  |      |
| AD05          | As an administrator,i wish i could add users by inporting excel table | 3        | 3                       | To do  |      |
| AD06          | As an administrator,i wish i could batchly operate on the user list | 3        | 3                       | To do  |      |

### Sprint Backlog

| User story ID | Task                                                         | Estimate(Hours) | Sprint |      |
| ------------- | ------------------------------------------------------------ | --------------- | ------ | ---- |
| AD01          | As an administrator, I wish i could list all the users and add new user | 8               | 1      |      |
| AD02          | As an administrator,I wish i could view users' detailed information via the button on the user list | 16              | 2      |      |
| AD03          | As an administrator,i wish i could modify a user's information on the detailed information page | 8               | 3      |      |
| AD04          | As an administrator,i wish i could set one's role            | 16              | 4      |      |
| AD05          | As an administrator,i wish i could add users by inporting excel table | 16              | 5      |      |
| AD06          | As an administrator,i wish i could batchly operate on the user list | 16              | 6      |      |

### Sprint Plan

| Development phase | task                                                     | Date      |
| ----------------- | -------------------------------------------------------- | --------- |
| SP01              | Develop the view ,add ,delete function of userlist       | Week 3-4  |
| SP02              | Develop the view function of detailed user information   | Week5     |
| SP03              | Develop the modify function of detailed user information | Week 6    |
| SP04              | Develop user group management                            | Week 7-8  |
| SP05              | Develop excel importing function                         | Week 9-11 |
| SP06              | Design batch operation                                   | Week 12   |



## Team4 library staff

### User story

1. As a library staff, I want to be able to add newmaterials to the library's catalog, so that users can easily find and access the latest materials.
2. As a library staff, I want to be able to deletematerials from the catalog, so that users can be assured that all materials listed are still available.
3. As a  library staff, I hope to update the status of the materials timely, such as normal, checked out, lost, damaged, label off to complete materials management.
4. As a library staff, I want to be able to track materials loans and returns, so that I can ensure that library materials are properly accounted for.
5. As a library staff, I want to be able to send overdue notices to users, so that they can return materials in a timely manner.
6. As a library staff , I want to be able to communicate with users via email or chat, so that I can respond to inquiries and provide assistance in a timely manner.
7. As a library staff, I hope to publish an announcement on the homepage to inform users of the latest borrowing policy.

### User Story Mapping

Material Management

- Add new materials.
- Delete materials from the depository.
- Update the status of the materials.

Notification

- Track materials returns.
- Send overdue notices by email.

Communication

- Communicate with users via chat.
- Publish announcements.

### Product Backlog

| User Story ID | User Story Description                                       | Priority | Estimate(Story Points) | Status |
| ------------- | ------------------------------------------------------------ | -------- | ---------------------- | ------ |
| LS001         | As a library staff, I want to be able to add new materials to the library's catalog, so that users can easily find and access the latest materials. | 5        | 5                      | To do  |
| LS002         | As a library staff, I want to be able to delete materials from the catalog, so that users can be assured that all materials listed are still available. | 5        | 5                      | To do  |
| LS003         | As a  library staff, I hope to update the status of the material timely, such as normal, checked out, lost, damaged, label off to complete  management. | 5        | 5                      | To do  |
| LS004         | As a library staff, I want to be able to track materials loans and returns, so that I can ensure that library materials are properly accounted for. | 4        | 4                      | To do  |
| LS005         | As a library staff, I want to be able to send overdue notices to users by email, so that they can return materials in a timely manner. | 4        | 4                      | To do  |
| LS006         | As a library staff, I want to be able to communicate with users via email or chat, so that I can respond to inquiries and provide assistance in a timely manner. | 3        | 3                      | To do  |
| LS007         | As a library staff, I hope to publish an announcement on the homepage to inform users of the latest borrowing policy. | 2        | 2                      | To do  |

### Sprint Backlog

| User Story | Task                                                        | Estimate(Hours) | Status | Sprint |
| :--------- | ----------------------------------------------------------- | --------------- | ------ | ------ |
| LS001      | Develop the function of adding materials.                   | 8               | To do  | SP01   |
| LS002      | Develop the function of removing materials.                 | 8               | To do  | SP01   |
| LS003      | Develop the function of updating materials.                 | 8               | To do  | SP02   |
| LS003      | Design and develop the UI of material management.           | 10              | To do  | SP02   |
| LS004      | Develop the function of  material loan and return tracking. | 6               | To do  | SP03   |
| LS005      | Develop Overdue notice functionality.                       | 10              | To do  | SP04   |
| LS005      | Develop communcation functionality with users via chat.     | 12              | To do  | SP05   |
| LS006      | Design and develop the UI of chat interface                 | 10              | To do  | SP05   |
| LS007      | Design and develop the UI of announcement.                  | 4               | To do  | SP06   |

### Sprint Plan

| Development phase | task                                                         | Date      |
| ----------------- | ------------------------------------------------------------ | --------- |
| SP01              | Develop the function of adding materials.                    | Week 3    |
| SP02              | Develop the function of removing materials.                  | Week 4    |
| SP03              | Develop the function of  material loan and return tracking.  | Week 5-6  |
| SP04              | Develop Overdue notice functionality.                        | Week 7-8  |
| SP05              | Develop communcation functionality with users via email or chat. | Week 9-11 |
| SP06              | Design and develop the UI of announcement.                   | Week 12   |



## Release Plan

| Release   | Start Date | End Date   | Scope(User Story)                      | Status      |
| --------- | ---------- | ---------- | -------------------------------------- | ----------- |
| Release 1 | 2023-03-06 | 2023-04-03 | SU001;  PT001-003;  AD01-02; LS001-004 | In progress |
| Release 2 | 2023-04-04 | 2023-04-24 | SU002; PT004;  AD03; LS005             | Planned     |
| Release 3 | 2023-04-25 | 2023-05-22 | SU003; PT005;  AD04-06; LS005-007      | Planned     |


