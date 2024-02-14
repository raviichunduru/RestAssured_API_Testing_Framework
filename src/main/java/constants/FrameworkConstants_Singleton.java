package constants;

import jdk.jfr.Description;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import static java.util.Objects.isNull;

@Getter
@Description("Singleton pattern used here. single instance enough to read any property of class.. this is creation design pattern")
public class FrameworkConstants_Singleton
{

    private FrameworkConstants_Singleton() {};

    private static FrameworkConstants_Singleton INSTANCE = null;

    public static synchronized  FrameworkConstants_Singleton getInstance()
    {
       if(isNull(INSTANCE))
       {
           INSTANCE = new FrameworkConstants_Singleton();
       }
        return INSTANCE;
    }

    private final String REQUEST_JSON_FOLDER_PATH = System.getProperty("user.dir") + "/src/test/resources/request_jsons/";
    private final String RESPONSE_JSON_FOLDER_PATH = System.getProperty("user.dir") + "/response_jsons/";
}
