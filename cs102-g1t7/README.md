### All submissions are to be uploaded in eLearn. Submissions via email are ignored.
* Your submission zip file should consist of the following
* final-presentation.pptx (can be in other format)
* src/main/java
This directory contains all your Java source files.
* target/classes
This directory is left empty during submission. After compile.bat runs, the class files will be stored here automatically.
* lib (Only if using any external libraries)
This directory contains any jar files that you use for your application.
* sql (for MySQL)
* documentation
This directory contains the documentation for deployment guide and any other documentation that the team wishes to include. Please ensure that you document the deployment guide clearly such that the instructions can be easily followed to deploy the application on a fresh machine. 
This directory contains the deploy.sql script, and README.txt.
* Only the use of external free or open source libraries (e.g. opencsv) is allowed. No other form of code-sharing is allowed. Co-development of code with members from other teams is a definite NO-NO.

# Instructions

## ------------------------------How to run the program ------------------------------
1. Run deploy.sql to create the database.
2. Run compile.bat .
3. Run run.bat .
## -------------------------------------------Login------------------------------------------
4. Access the interface with `localhost:3000/login` .
5. Use Test account to login: Username: test@test.com Password: abc .
6. In the homepage, you can sort by selecting the way you want to sort and press `confirm`.
7. You can view more details of the vessel, for example - distance to go,  by clicking onto it.
8. To add to Alerts, click the `Add Alert` button and it will be accessible via the `Alerts Configured` button.
9. There will also be a `Delete Alert` button where you can click on it if you no longer need to be informed about the changes in schedule of the vessel.
10. To check if any realtime alerts that have been triggered, click on the `Alerts Triggered` button.
11. In the `Vessel Schedule` page, click on the buttons with dates on them i.e. `2021-04-11` to view the Vessel schedule according to the date selected.
12. Click logout and it will lead you to login page.
## ----------------------------------Forget & Reset password--------------------------------
10. Click forget password at login page.
11. Use Test account to do so: Username: test@test.com DoB: 01/02/2000 .
12. Click `Reset`.
13. Try login with the new password and logout.
## ------------------------------------------Creating New Account----------------------------------------
14. Click register.
15. Fill in all the fields.
16. Click `Create Account`.
17. Login and test.
## ------------------------------Change API and API call interval---------------------------
18. Login with admin account: Username: admin@psa.com Password: abc
19. You will see the webservice page. 
	NOTE: You do not need to add all of the field, just what you need to change.
20. Click submit and check.
## ------------------------------Add or delete email suffix------------------------------------
21. In the admin account, click email settings.
22. Type the new suffix you want and click `add`. It will be reflected in the table.
23. Type the suffix you want to delete and click `delete`. It will be removed from the table.
24. Logout.
