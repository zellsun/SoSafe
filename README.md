About the Project

This project is for our class Summer 2014 COEN 359 Design Pattern.
Thanks for our teacher Dr. Rani Mikkilineni's outstanding instructing.
Team members: Zhe Sun (zsun@scu.edu), Hongsen He (hhe@scu.edu).


Copy Right Declaration

The pictures and sound clips embedded in this application are from the Internet.
These files are just for the project demo purpose, no commercial usage.
Courtesy sources of these files.


Directory and File Description

SoSafe


    ├─build     // Building Project Output Files
    ├─dist      // Executable Jar File after Building Project
    │  └─lib    // Dependency Runtime Libraries
    ├─lib       // Dependency Libraries
    ├─nbproject // NetBeans Project Files
    ├─sosafe_db // Sample Database Files
    └─src       // Source Code
        ├─META-INF          // Meta Data
        └─sosafe            // Main Module
           ├─control       // Controller Module for Controlling Views
           │  └─exceptions // Exceptions Used in Controller Module
           ├─model         // Model Module for Data Representation
           ├─network       // Network Module for Networking
           │  ├─client     // Client Sub Module for Sending Data
           │  └─server     // Server Sub Module for Receiving Data
           ├─report        // Report Module for Generating Bills
           │  └─htmlhelper // HTML Helper Sub Module for Helping Generating HTML Data
           ├─res           // Resource Files such as Pictures and Audio Clips
           └─view          // View Module for GUI


How to Use it?
Take Windows platform as an example:

0) You must have the JRE installed in your system. (Please refer to http://www.java.com)

1) Execute Run_SoSafe.bat (Such as by double-click it). For Linux, OS X, Solaris, please use command line: java -jar "./dist/SoSafe.jar" 

2) The directory sosafe_db is the sample data. You can use it to log in.
Username is: zsun@scu.edu
Password is: Test1234

3) If you want to start this application with clean database and sign up you own account, you can just delete or rename directory sosafe_db. Then it will start with creating a clean database.
Note that it will slow down the starting time when it starts with creating a clean database.

4) After you logged in, you can do everything you like. Especially you can send alarm to this application by Sensor Simulator. It's really cool, try it:)

Thank you! Have a nice day.
