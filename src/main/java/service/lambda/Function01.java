package service.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class Function01 implements RequestStreamHandler{

    @Override
    public void handleRequest(InputStream inputStream, OutputStream outputStream, Context context) throws IOException
    {
        JsonObject responseJson = new JsonObject();
        JsonObject responseBody = new JsonObject();
        JsonObject headerJson = new JsonObject();

        responseBody.addProperty("message", "it's everything OK here!");

        headerJson.addProperty("x-custom-header", "my custom header value");

        responseJson.addProperty("statusCode", 200);
        responseJson.add("headers", headerJson);
        responseJson.addProperty("body", responseBody.toString());

        OutputStreamWriter writer = new OutputStreamWriter(outputStream, "UTF-8");
        writer.write(responseJson.toString());
        writer.close();
    }
}
