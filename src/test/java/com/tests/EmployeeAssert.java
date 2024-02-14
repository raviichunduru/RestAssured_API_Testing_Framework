package com.tests;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;
import pojo.Employee;

public class EmployeeAssert extends AbstractAssert<EmployeeAssert, Employee>
{

    private EmployeeAssert(Employee employee, Class<?> selfType)
    {
        super(employee, selfType);
    }

    public static EmployeeAssert assertThat(Employee employee)
    {
        return new EmployeeAssert(employee, EmployeeAssert.class);
    }

    public EmployeeAssert hasID (int expectedID)
    {
        Assertions.assertThat(actual.getID())
                .isEqualTo(expectedID)
                .withFailMessage("ID not matching");

        return this;
    }
}

