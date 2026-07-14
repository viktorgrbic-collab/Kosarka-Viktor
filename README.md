Basketball League Manager (Kosarka)

A Java desktop client-server application for managing a basketball league — teams, players, countries, and league statisticians — built with a classic 3-tier architecture and raw TCP socket communication.

Overview

This project simulates a real-world league management system used by statisticians to register and maintain data about countries, leagues, teams, and players. It was built as a multi-module NetBeans project demonstrating a full client-server design: a Swing GUI client, a multithreaded socket server, and a shared module containing the domain model and network transfer objects.

Architecture

The solution is split into three NetBeans modules:


KosarkaKlijent (Client) — a Java Swing desktop application providing forms for login and CRUD operations (search, add, edit, view details) on countries, leagues, teams, and players.
KosarkaServer (Server) — a multithreaded TCP server that accepts client connections, dispatches incoming requests to dedicated Service Object (SO*) classes, and persists data through a JDBC-based database broker.
KosarkaZajednicki (Common/Shared) — the shared library containing the domain model (Drzava/Country, Liga/League, Tim/Team, Igrac/Player, Statisticar/Statistician, etc.) and the request/response transfer objects exchanged between client and server.


Communication flow: the client serializes a Request (operation code + payload) and sends it over a socket; a dedicated ThreadClient on the server reads the request, routes it based on an Operation code to the matching Service Object, executes the database operation, and returns a serialized Response with a status code.

Key Features


User authentication (statistician login)
Full CRUD management for:

Countries
Leagues
Teams
Players



Search/filter forms for each entity
Detail views showing related data (e.g., a team's league, country, and roster)
Concurrent client handling via a dedicated thread per connection


Tech Stack


Language: Java (Swing for the GUI)
IDE / Build: NetBeans project structure, Ant build scripts
Networking: raw TCP sockets with Java object serialization (ObjectInputStream / ObjectOutputStream)
Persistence: JDBC with a singleton DBBroker executing hand-written SQL against a relational database
Design patterns: Singleton (controllers, DB broker), a lightweight Service Object pattern on the server, and a generic AbstractDomainObject base class standardizing CRUD SQL generation across entities


Project Structure

KosarkaKlijent/     # Swing client: login & CRUD forms per entity
KosarkaServer/       # Socket server, service objects, JDBC broker
KosarkaZajednicki/   # Shared domain model + request/response DTOs

Notes

This was developed as an academic/learning project to practice layered client-server design, socket-based communication, and JDBC persistence in Java — rather than as a production-ready application.

How to run Kosarka-App

1.Open in NetBeans — open the Kosarka Viktor folder; it has 3 modules: KosarkaZajednicki, KosarkaServer, KosarkaKlijent.
2.Set up a database — create the tables (Country, League, Team, Player, Statistician) and add a dbconfig.properties file with your DB url, username, password. Add the matching JDBC driver jar to KosarkaServer.
3.Build all 3 modules (shared module first, it's a dependency of the other two).
4.Run KosarkaServer first — it starts listening for client connections.
5.Run KosarkaKlijent — log in with a statistician account, then use the app (manage countries, leagues, teams, players).
