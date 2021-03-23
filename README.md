# How to run these tests locally
### Set up
1. Make sure you have docker installed
1. Make sure that you have proper browser image downloaded

### Main
1. docker run --net testmprh --name testmprh  -d -p 4444:4444 -v /dev/shm:/dev/shm selenium/standalone-chrome:latest - Runs docker container of webdriver with defined name and network
1. docker run -it --net testmprh -v ~/Idea\ Projects/mprh-autotest/:/application circleci/openjdk:stretch bash - Runs source code as a container
1. cd application
1. Credentials to >  http://testmprh:4444/wd/hub
    1. (optional) to run locally: http://localhost:4444/wd/hub
1. **mvn test** to run all tests
      * append to run certain xml file (-Dsurefire.suiteXmlFiles=src/test/resources/TestSuites/LoginTestSuites/PositiveLoginTestSuites.xml)
      * append to run certain groups of tests (-Dgroups=login)


