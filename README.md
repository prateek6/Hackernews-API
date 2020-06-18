# Hackernews-API
**API requirments**
- /top-stories — returns the top 10 stories ranked by score in the last 10 minutes (see instructions). Each story will have the title, url, score, time of submission, and the user who submitted it.

- /comments — returns the top 10 parent comments on a given story, sorted by the total number of comments (including child comments) per thread. Each comment will have the comment's text, the user’s HN handle, and their HN age. The HN age of a user is basically how old their Hacker News profile is in years.

- /past-stories — returns all the past top stories that were served previously.

![hacker news](https://user-images.githubusercontent.com/12758437/85059555-74868980-b1c1-11ea-9450-90b1e424e767.png)

Project configuration:
1. Change log path file in logback.xml  
  `<property name="LOG_PATH" value="Replace file path here"/>`

      example : `<property name="LOG_PATH" value="/home/prateek/server_log"/`

2. create database in MySQL using below comand

    ` create database hackernews; `

3. Change below details in application.properties
-   spring.datasource.url = jdbc:mysql://localhost:3306/hackernews?autoReconnect=true
-   spring.datasource.username=root
-   spring.datasource.password =password

4. To run the project use this command
  - `./gradlew bootRun`
