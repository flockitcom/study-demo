package util.serialize;

import com.google.protobuf.InvalidProtocolBufferException;

/**
 * protocol buffers
 *
 * @author zhangqian
 */
public class ProtoBufUtil {
    public static void main(String[] args) throws InvalidProtocolBufferException {
        ResponseOuterClass.Response.Builder builder = ResponseOuterClass.Response.newBuilder();
        // 设置字段值
        builder.setData("hello www.tizi365.com");
        builder.setStatus(200);

        ResponseOuterClass.Response response = builder.build();
        // 将数据根据protobuf格式，转化为字节数组
        byte[] byteArray = response.toByteArray();

        // 反序列化,二进制数据
        ResponseOuterClass.Response newResponse = ResponseOuterClass.Response.parseFrom(byteArray);
        System.out.println(newResponse.getData());
        System.out.println(newResponse.getStatus());
    }
}
