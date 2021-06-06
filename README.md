Nisum Songs' Searcher app
-------------------------

The idea in this challenge is to use the maximum of the state-of-art in Android. I don't have enough time but I'll try to do it.

In this first commit I've prepared projects' structure without writing a class. All the code that is visible was made by Android Studio except those related to Navigation and views (XML files)

In this second commit I've introduced models and Search API. No test file is necessary at this time.

New commit. In this time, 4 test were added and must fail. Does test are related to the services.

Ok, here I made some change in the model since a field added is not used in when media=music abd renamed a class.
Also, request permission to internet and to check the status of network.
I've defined some interfaces for Storage and Search services. Finally, implemented the required classes.
Two of three test are passed. I must continue to resolve the issues in the RemoteSearchServiceTest.

The issue was fixed and the tests ends successfully now. A new test was introduced for empty term.

