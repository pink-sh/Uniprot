Uniprot project for abcam test

- This is a maven project, just clone and run mvn clean install and deploy the war file generated in target/ folder on a Tomcat container
- The webapp is done using jersey/jaxb as backend and Angulajs as frontend
- Make sure that the BASE_URL variable in webapp/js/abcam.js points to the right backend endpoint (if you run in your local machine should be already fine)

Tested with:
- Java 8
- Tomcat 8
- Eclipse Juno
