steps: 

1. clone this repository and open it in intelliJ
   
2. Start a MySQL database on port 3306 and remember the password

3. in intelliJ go to the 3 dots in the right upper corner in intelliJ and click edit configuration.
   set active profiles to dev and add an environment variable and type in: DATABASE_PASSWORD=root (if you chose root as database password).

4. Run the application

5. Go to the resources/SQL/data.sql file

6. Right click on that file and select run data.sql (this will populate the database with 2 users and 2 tasks each belonging to them.

7. Go to https://github.com/dansaa8/todo-frontend

8. Clone that repository

9. open a terminal where you opened that repository and enter npm run dev

10. now you can navigate to http://localhost:5173 in a browser and login in as either bertil or ingrid, both has the password "user".
