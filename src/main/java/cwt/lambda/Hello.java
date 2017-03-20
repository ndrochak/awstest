package cwt.lambda;

import com.amazonaws.services.lambda.runtime.Context;

/**
 * Basic string input, string output.
 * <p>
 * http://docs.aws.amazon.com/lambda/latest/dg/java-programming-model-req-resp.html
 */
public class Hello {
    public String myHandler(String name, Context context) {
        return String.format("Hello %s.", name);
    }
}