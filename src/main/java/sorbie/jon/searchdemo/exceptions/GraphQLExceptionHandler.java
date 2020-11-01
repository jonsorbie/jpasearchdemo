package sorbie.jon.searchdemo.exceptions;

import graphql.GraphQLException;
import graphql.kickstart.spring.error.ThrowableGraphQLError;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Component
public class GraphQLExceptionHandler {

    // If a Resolver throws a GraphQLException, this handler propagates the error message to the calling client
    @ExceptionHandler(GraphQLException.class)
    public ThrowableGraphQLError handle(GraphQLException e) {
        // The real error gets logged by the framework
        return new ThrowableGraphQLError(e);
    }

    // Propagates a generic error message to the calling client
    @ExceptionHandler(RuntimeException.class)
    public ThrowableGraphQLError handle(RuntimeException e) {
        // The real error gets logged by the framework
        return new ThrowableGraphQLError(e, "Internal server error");
    }
}
