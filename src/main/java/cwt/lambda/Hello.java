package cwt.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

/**
 * Basic string input, string output.
 * <p>
 * http://docs.aws.amazon.com/lambda/latest/dg/java-programming-model-req-resp.html
 */
public class Hello implements RequestHandler<String, String> {
    @Override
    public String handleRequest(String input, Context context) {
        return String.format("Hello %s.", input);
    }
}