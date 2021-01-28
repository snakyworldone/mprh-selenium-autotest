# How to run these tests locally
### Set up
1. Make sure you have docker installed
2. Make sure that you have proper browser image downloaded

### Main
1. docker run --net testmprh --name testmprh  -d -p 4444:4444 -v /dev/shm:/dev/shm selenium/standalone-chrome:latest
2. docker run -it --net testmprh -v ~/Idea\ Projects/mprh-autotest/:/application circleci/openjdk:stretch bash
3. cd application
4. Credentials to >  http://testmprh:4444/wd/hub
4.1 (optional) to run locally: http://localhost:4444/wd/hub
5. mvn test (or -Dsurefire.suiteXmlFiles=src/test/resources/TestSuites/LoginTestSuites/PositiveLoginTestSuites.xml)

