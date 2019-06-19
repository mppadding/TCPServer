# Matthijs Padding's TCP Server
This project contains my code for the TCP Server assignment at Saxion University of Applied Sciences.
It is a multithreaded TCP server that will send the client the news at a specified geolocation. It uses the Google News
RSS feed to do so.

# Quickstart
Clone this repository
> git clone https://github.com/pukkertje/TCPServer

Build using your favourite tools and run the program.
By default it will use port 9000 to host the server. Inputting q into the console will terminate all current connections
and shutdown the server, exiting the program. Clients can connect to the TCP server's IP and port 9000.

# Commands
## Help
Help returns the usage and description of all implemented commands. Example output:
> Help for News server:  
> help            Runs this command  
> news {location} Gives news about {location}  
> ping            Pong!  
> quit            Aborts the connection  

## Ping
Returns pong! Can be used to test if the server is still online and responding

## News
The news command can be used to fetch the news about a specific geolocation. The geolocation can have spaces in the name,
such as 'new york'. Example command:
> news enschede

## Quit
Terminates the connection from the client.

# License
See _LICENSE_
