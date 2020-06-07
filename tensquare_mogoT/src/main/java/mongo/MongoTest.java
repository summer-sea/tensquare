package mongo;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.HashMap;

/**
 * @program: tensquare_parent
 * @author: 大禹
 * @create: 2020-05-26 21:19
 **/

public class MongoTest {
    public static void main(String[] args){
        //连接mogo服务
        MongoClient mongoClient =new MongoClient("192.168.254.129");
        //得到要操作的数据库
        MongoDatabase spitdb = mongoClient.getDatabase("spitdb");
        //得到要操作的集合也就是 表
        MongoCollection<Document> spit = spitdb.getCollection("spit");
        //封装查询条件
        BasicDBObject bason = new BasicDBObject("userid","1013");
        //得到集合中所有的文档
        FindIterable<Document> documents = spit.find(bason);
        //插入数据
        HashMap<Object, Object> map = new HashMap<>();
        map.put("content","时间过得好快");
        map.put("userid","1016");
        map.put("visits",100);
        Document document =new Document();
        spit.insertOne(document);
        //遍历数据
        /*for (Document document : documents) {
            System.out.println("内容"+document.getString("content"));
            System.out.println("用户id"+document.getString("userid"));
            System.out.println("访问量"+document.getInteger("visits"));
        }*/

    mongoClient.close();
    }
}
