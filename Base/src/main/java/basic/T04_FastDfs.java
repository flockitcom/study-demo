package basic;

import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;

import java.io.IOException;

public class T04_FastDfs {
    public static void main(String[] args) throws IOException, MyException {
        ClientGlobal.initByProperties("fpr-app/src/main/resources/config/fastdfs-client.properties");
        TrackerClient tracker = new TrackerClient();
        TrackerServer trackerServer = tracker.getConnection();
        StorageServer storageServer = null;
        StorageClient client = new StorageClient(trackerServer, storageServer);
        NameValuePair[] metaList = new NameValuePair[1];
        metaList[0] = new NameValuePair("fileName", "1.PNG");
        //执行上传
        String[] fileId = client.upload_file("C:\\Users\\Administrator\\Desktop\\1.jpg", "jpg", metaList);
        System.out.println("上传完成" + fileId[0] + "/" + fileId[1]);
        trackerServer.close();
    }
}
