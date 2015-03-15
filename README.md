<!---
Copyright (C) 2015  Karl Bennett

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.
-->
spring-jaxws-cxf
===========

This project shows a simple example of how to integrate CXF the Apache JAX-WS implementation with Spring using pure
Java configuration.

### Build

    mvn clean verify

### Configuration

Both of the configuration files within this project can be used in complete isolation to register the contained JAX-WS
endpoint.

[`JavaConfiguration`](src/test/java/example/spring/jaxws/cxf/JavaConfiguration.java)

This configuration class shows the minimum amount of code to register a single JAX-WS endpoint through pure Java
configuration.

[`XMLConfiguration`](src/test/java/example/spring/jaxws/cxf/XMLConfiguration.java)

This configuration file shows how existing CXF XML configuration can be used in Java configuration.