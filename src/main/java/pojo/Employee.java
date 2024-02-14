package pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(setterPrefix = "set", buildMethodName = "getEmployee")
public class Employee
{
    private int ID;
    private String first_name;
    private String last_name;
}
