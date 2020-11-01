# jpa-search-demo
Proof of concept for using JPA Specifications in combination with query
parameters obtained from a URL string.  Also contains a GraphQL endpoint as
a demo.

Note that the `hibernate-jpamodelgen` artifact has been included in `pom.xml`,
which will generate JPA metamodel classes and place them in
`target/generated-sources`. You may need to tell your IDE that
`target/generated-sources` is a source directory.

### Endpoints
Once running, you can get to the search endpoint here:

http://localhost:8080/pet-search

You can give it values for name, species, minAge and maxAge. Example:

http://localhost:8080/pet-search?species=DOG&maxAge=10

The H2 console should be available here:

http://localhost:8080/h2-console

The GraphQL endpoint is available here:

http://localhost:8080/graphql

The GraphQL playground is available here:

http://localhost:8080/playground

