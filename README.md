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




