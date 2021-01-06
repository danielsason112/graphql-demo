package il.ac.afeka.cloud;

import java.io.IOException;
import java.net.URL;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;

import graphql.GraphQL;
import graphql.scalars.ExtendedScalars;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;

import static graphql.schema.idl.TypeRuntimeWiring.newTypeWiring;

@Component
public class GraphQLProvider {
	
	@Autowired
    GraphQLDataFetchers graphQLDataFetchers;

	private GraphQL graphQL;

	/*
	 * Create a new graphql.GraphQL instance. We will use this instance later to create our /graphql end-point,
	 * by exposing it as a Spring Bean via the GraphQL() method.
	 */
    @PostConstruct
    public void init() throws IOException {
        URL url = Resources.getResource("schema.graphqls");
        String sdl = Resources.toString(url, Charsets.UTF_8);
        GraphQLSchema graphQLSchema = buildSchema(sdl);
        this.graphQL = GraphQL.newGraphQL(graphQLSchema).build();
    }


    /*
     * Create the GraphQLSchema instance using:
     * 1. TypeDefinitionRegistry - the parsed version of the schema file.
     * 2. RuntimeWiring - register the DataFetchers (resolvers) that actually fetches the data.
     */
    private GraphQLSchema buildSchema(String sdl) {
    	TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(sdl);
        RuntimeWiring runtimeWiring = buildWiring();
        SchemaGenerator schemaGenerator = new SchemaGenerator();
        return schemaGenerator.makeExecutableSchema(typeRegistry, runtimeWiring); 
    }
    
    private RuntimeWiring buildWiring() {
        return RuntimeWiring.newRuntimeWiring()
        		.scalar(ExtendedScalars.Json)
                .type(newTypeWiring("Query")
                        .dataFetcher("recentPosts", graphQLDataFetchers.getRecentPostsDataFetcher()))
                .type(newTypeWiring("Post")
                		.dataFetcher("user", graphQLDataFetchers.getUserByEmailDataFetcher()))
                .type(newTypeWiring("Mutation")
                        .dataFetcher("createUser", graphQLDataFetchers.getCreateUserDataFetcher())
                        .dataFetcher("createPost", graphQLDataFetchers.getCreatePostDataFetcher()))
                .build();
    }
    
    @Bean
    public GraphQL graphQL() {
        return graphQL;
    }
}
