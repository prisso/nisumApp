Nisum Songs' Searcher app
-------------------------

The idea in this challenge is to use the maximum of the state-of-art in Android. I don't have enough time but I'll try to do it.

- In this first commit I've prepared projects' structure without writing a class. All the code that is visible was made by Android Studio except those related to Navigation and views (XML files)

- In this second commit I've introduced models and Search API. No test file is necessary at this time.

- New commit. In this time, 4 test were added and must fail. Does test are related to the services.

- Ok, here I made some change in the model since a field added is not used in when media=music abd renamed a class.
Also, request permission to internet and to check the status of network.
I've defined some interfaces for Storage and Search services. Finally, implemented the required classes.
Two of three test are passed. I must continue to resolve the issues in the RemoteSearchServiceTest.

- The issue was fixed and the tests ends successfully now. A new test was introduced for empty term.

- Here I introduce a test for the SearchRepository class. I made multiple approachs using different powerful frameworks without success. Finally, I made a change in the the LocalSearchService class introducing the StorableSearchService which is a extension of SearchService interface. Other changes were made in the code since I forgot a field in the Song model

- Implemented SearchViewModel. No test was carried out this time. Paging and manage exception is made here.
Clarification: paging is carried out by simulation since Apple API doesn't let you make paging. You have option to take a maximum number of results but no from where you receive the list.

- Since I gona have same problem to test AlbumViewModel a moved SearchRepository class to a new one called SearchRepositoryImpl. SearchRepository is a interface now.

- In this new commit and the next ones, the idea is to develop step-by-step the TDD process. Running the test wouldn't fail because
the AlbumViewModel class has been created before. I don't have made it before since TDD process is time-consuming and I don't have enought time right now. In real projects, a balance must be done between end-time constraint and requirements.

- Second step in TDD process. Fulfilled the two test related to create an album from collection id. Test fails in compilation time since methods hasn't been created yet. So, we create a empty method in the AlbumViewModel class including the LiveData property for the song's list. Now test fails because asserts don't verifys since no code it's inside method block.

- The test previously fails because an exception when Observer was mocking. Another approach is proposed in a classic way. The method is completed and now tests end successfuly

- Complete code to test when viewModel loadInfo for complete fragment's view. The loadInfo block is empty. Test fails.

- LoadInfo method was fulfilled so test ends successfully.

- Close this Step-by-step with a simple inclusion of code to control building album state. Including a test for it.


- Implemented SearchFrament. No test will made from now. I haven't more time neither mind.




