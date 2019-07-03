# Matthijs Padding's TCP Server
This project contains my code for the TCP Server assignment at Saxion University of Applied Sciences.
It is a multithreaded TCP server that will send the client the news at a specified geolocation. It uses the Google News
RSS feed to do so.

# Quickstart
Clone this repository

```git clone https://github.com/pukkertje/TCPServer```

Build using your favourite tools and run the program.
By default it will use port 9000 to host the server. Inputting q into the console will terminate all current connections
and shutdown the server, exiting the program. Clients can connect to the TCP server's IP and port 9000.

# Commands
## Help
Help returns the usage and description of all implemented commands. Example output:
```
Help for News server:  
help            Runs this command  
news {location} Gives news about {location}  
ping            Pong!  
quit            Aborts the connection  
```

## Ping
Returns pong! Can be used to test if the server is still online and responding

## News
The news command can be used to fetch the news about a specific geolocation. The geolocation can have spaces in the name,
such as 'new york'. Example command:
> news enschede

## Joke
Returns a random joke from a list of jokes.

## Quit
Terminates the connection from the client.

# Protocol
All text is send in a human readable format, example of output:
```
Welcome to Matthijs Padding's News server
Type `help` to get help.
> help

Help for Matthijs' Server:
quit            Aborts the connection
help            Runs this command
news {location} Gives news about {location}.
ping            Returns pong!
joke            Returns a random joke.
> news enschede

News for enschede
News (Thu, 13 Jun 2019 11:12:42 GMT): International Photogrammetry and Remote Sensing Community Gathers in Enschede - GIM International
Read more at: https://www.gim-international.com/content/news/international-photogrammetry-and-remote-sensing-community-gathers-in-enschede

> ping

Pong!
> joke

Why are programmers so selfish?
        Because they always choose i for the iterator!
> quit

Goodbye :(
```

# Extending
The server can easily be extending by adding more commands. An abstract Command class is provided with which
more commands can be implemented. Command Skeleton:
```java
public class Skeleton extends Command {

    public Skeleton() {

    }

    @Override
    public String execute(String[] arguments) {
        /* Return printed data */
    }

    @Override
    public String getHelp() {
        return "skeleton\t\tExample help.\n";
    }

    @Override
    public String getCommand() {
        return "skeleton";
    }

    @Override
    public int getArgumentNumber() {
        return 0;
    }

    @Override
    public boolean hasEnoughArguments(int arguments) {
        return arguments == 0;
    }
}
```

To handle HTTPS requests one can use the helper class ```HTTPSClient```. This class will return an ```InputStream```
fetched from the provided URL.

# License
See _LICENSE_