# Wolftracker API
This project has been created for an assignment from Wolfpack IT. It is a public project so the company can see what I have done.

# Run
This API requires a MySql database to be running, it needs CREATE access to be able to create new tables and should have full control overt those tables. The database should be manually created, see the file `data-access/src/main/resources/hibernate.cfg.xml` for the connection settings (they can also be changed in the file).

The project comes with a `docker-compose` file to start a MySql container with the correct configuration to be used by the api. Just run `docker-compose up -d` in the root directory and start the application.

# Asignment
Welcome to the Backend Wolfpack assessment!
The leader of the pack wants us to create a mobile application to track all the wolves in
the pack. Assume you are creating this app in a team of developers, you are the lead
backend developer of the team. Therefore your task is to design and implement a
RESTful API for this app. The API should be built in such a way that it is easy to
understand for your colleagues and that it can be easily implemented by the app
developers in your team.
This app should have the following functionalities, in the future more functionalities
may be added.

## Wolves

It should be possible to list all the wolves including some basic personal information
such as their name, gender and birthdate. Furthermore it should be possible to add,
remove and update wolves in this list. All wolves have a location and it should be
possible to update the location of a wolf such that we can include a map with all wolves
in the app.

## Packs
Wolves like hanging out in packs, in our app we’d like an option to create packs to which
we can add wolves. Packs consist of a name and one or more wolves. Furthermore
sometimes we want to remove wolves from a pack again. The app should be able to
display a list of packs and the wolves which are in them.


## Notes:
- Implement the API as if you were doing this project for one of our customers.
- Quality goes over quantity, you don’t need to deliver a fully functional API, we
rather see a few endpoints implemented very well than all of them implemented
in a hurry.
- Implement the API in one of the following languages you prefer implementing it
in.
- PHP (Yii2)
- Java (Spring)
- C# (.NET Core)
- Use whatever libraries which you think are suitable for the job.
- Adding authentication is not required.
- App developers make mistakes, make sure your API can handle invalid requests
properly.
