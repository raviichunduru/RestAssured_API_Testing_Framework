package com.tests;

import io.restassured.response.Response;
import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

public class ResponseAssert extends AbstractAssert<ResponseAssert, Response>
{
    private ResponseAssert(Response response, Class<?> selfType)
    {
        super(response, selfType);
    }

    public static ResponseAssert assertThat(Response response)
    {
        return new ResponseAssert(response, ResponseAssert.class);
    }

    public ResponseAssert isPostRequestSuccessful()
    {
        Assertions.assertThat(actual.getStatusCode() == 201)
                .withFailMessage(() -> "Status code is not 201");

        return this;
    }

    public ResponseAssert hasHeaderApplicationJson ()
    {
        Assertions.assertThat(actual.header("Content-Type")
                .contains("application/json"))
                .withFailMessage("Header with Content-type as application/json is not present");

        return this;
    }

    public ResponseAssert isResponseTimeWithInLimit ()
    {
        Assertions.assertThat(actual.timeIn(TimeUnit.MILLISECONDS)).isLessThan(500)
                .withFailMessage("Response time is not with in limit");

        return this;
    }




}
