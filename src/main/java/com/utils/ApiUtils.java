package com.utils;

import io.restassured.response.Response;
import lombok.SneakyThrows;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public final class ApiUtils
{
    private ApiUtils() { };

    @SneakyThrows(IOException.class)  // this eliminates need of declaring exception to method signature
    public static String getStringOutOfJSON(String filePath)
    {
        return new String(Files.readAllBytes(Paths.get(filePath)));
    }

    @SneakyThrows(IOException.class)
    public static void writeJsonToFile(String filePath, Response response)
    {
        Files.write(Paths.get(filePath),response.asByteArray());
    }
}
