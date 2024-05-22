<p align="center"><img src="https://i0.wp.com/blog.knoldus.com/wp-content/uploads/2021/03/Rest-assured.jpg?w=400&amp;ssl=1" alt="project-image"></p>

This project uses dummy json server, as purpose of this project is to build a robust framework to test / validate Rest API interactions.

Below are practises followed in this frame work.

1. Clean code practises. Ex : repetitive code turned into reusuable utility methods.

2. Integration with Extent reports, each test report inclues request, response body, exception details if test failed. 

3. Implemented Framework annotations to flag each method with author and category and inject same into extent reports for better user experience.

4. Data driven tests demonstration using TestNG's DataProvider annotation

5. Schema validation for Response JSON, using JsonSchemaValidator class

6. Used ThreadLocal for extent objects for multi thread test runs.

7. Usage of TestNG listeners to manage extent report initialization, flush, logging test status into report.

8. Implementation of Seperation of Concerns in Framework 
        ex: seperating random data generation using faker with business and service layer.

9. 





