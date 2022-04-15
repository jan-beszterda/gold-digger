# gold-digger
GoldDigger project for Backend course

Group 6 members: Jan Beszterda, Damir Kahvic

Instructions:

1. Clone this GitHub repository
2. Install PostgreSQL from https://www.postgresql.org/download/
3. Create an empty database with a name of your choice using pgAdmin
4. Place database name, PostgreSQL username and password in `application.properties` file
5. Run the application
6. Open `data.sql` file and uncomment the whole code
7. Rerun the application
8. Navigate to `/golddigger-site` folder using terminal
9. Run `npm install` command 
10. Start the application by typing `npm start`

Known Bugs:
1. If you're using IntelliJ you might get a problem and the website won't work correctly. This is because IntelliJ does  not recognise `useNavigate` function from `react-router-dom` even though `react-router-dom` is installed. Press `Shift` twice and type `Registry` in the search box. Open `Registry` and search for `typescript.external.type.definitions.package`. Edit values column and remove `react-router-dom` from the list. Restart IntelliJ. This should solve the problem.