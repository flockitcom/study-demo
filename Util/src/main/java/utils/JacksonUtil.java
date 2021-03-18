package utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * Jackson序列化
 * @author zqian
 * @date 2021/1/29
 */
public class JacksonUtil {
    private static final ObjectMapper mapper = new ObjectMapper();
    private Person person = new Person("David", 17);

    /**
     * Java对象转JsonNode对象
     * @throws JsonProcessingException
     */
    @Test
    public void javaBeanToJsonNode() throws JsonProcessingException {
        String json = mapper.writeValueAsString(person);
        JsonNode jsonNode = mapper.readTree(json);
        System.out.println("*************************************");
        jsonNode = mapper.valueToTree(person);
        System.out.println("*************************************");
        jsonNode = mapper.convertValue(person, JsonNode.class);
        System.out.println("*************************************");
    }

    /**
     * Java对象转字符串互转
     * @throws JsonProcessingException
     */
    @Test
    public void javaBeanToStr() throws JsonProcessingException {
        String json = mapper.writeValueAsString(person);
        System.out.println(json);
        Person person = mapper.readValue(json, Person.class);
        System.out.println(person);
    }

    /**
     * Java对象转文本
     * @throws JsonProcessingException
     */
    @Test
    public void javaBeanToTxt() throws IOException {
        File file = new File("src/main/resources/person.json");
//        mapper.writeValue(file, person);
        Person person = mapper.readValue(file, Person.class);
        System.out.println(person);
    }

}

@Data
@NoArgsConstructor
@AllArgsConstructor
class Person {
    private String name;
    private Integer age;
}
