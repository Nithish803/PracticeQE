To start the UI test

Using Commend Prompt

1. Navigate to project folder open commend prompt 

2. (multi browser)  Type (chrome 90) 

        mvn test -Dbrowser=firefox
                   OR
        mvn test -Dbrowser=chrome   
        
3. Hit enter

======================================================================================================

Using Eclipse 

1. Right click on pom.xml > Run As > Select 2 Maven build
(multi browser)
2. In Goals textbox  type

		 test -Dbrowser=firefox 
		        OR
		 test -Dbrowser=chrome  
		
3. Hit enter

======================================================================================================

To get Html report 

1. goto the path /test-output/reports (here you will get reports with test name and with time stamp)
2. open it with chrome/ firefox for better viewing experience 

======================================================================================================

CI-CD 

For this we need to share this project to git/svn repositories.
Then under source code section we can pass the repository location and branch name
Then under build section we can select Invoke top-level Maven targets and pass the Goal as 

		test -Dbrowser=firefox 
		        OR
		test -Dbrowser=chrome 
                                                                                           
we can directly run the test from local jenkins by passing the pom.xml path in advance section                                                                                         
                                                                                          


