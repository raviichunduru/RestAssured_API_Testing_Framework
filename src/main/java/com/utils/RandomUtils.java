package com.utils;

import com.github.javafaker.Faker;
import static com.utils.FakerUtils.getNumber;

public final class RandomUtils
{
    private RandomUtils() {};

    public static int getID()
    {
       return  FakerUtils.getNumber(1000,2000);
    }

    public static String getFirstName()
    {
        return FakerUtils.getFirstName().toLowerCase();
    }

    public static String getLastName()
    {
        return FakerUtils.getLastName().toLowerCase();
    }


}
