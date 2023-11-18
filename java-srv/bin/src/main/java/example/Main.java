package example;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.MasterNotRunningException;
import org.apache.hadoop.hbase.client.HBaseAdmin;

import java.io.IOException;

//install hbase locally & hbase master start
public class Main {

    public static void main(String[] args) throws IOException {
        /*
        try{
            new Main().connect();
        } catch(Exception e){
            System.out.println("Connection error." + e.getMessage());
        }
        */
       new Main().connect();
    }

    public void connect() throws IOException {
        Configuration config = HBaseConfiguration.create();

        String path = this.getClass().getClassLoader().getResource("hbase-site.xml").getPath();

        //config.set("hbase.zookeeper.quorum","<ZK-QUORUM>");

        config.addResource(new Path(path));

        try {
            HBaseAdmin.available(config);
            System.out.println("HBase is running");
        } catch (MasterNotRunningException e) {
            System.out.println("HBase is not running. " + e.getMessage());
            return;
        }

        HBaseClientOperations client = new HBaseClientOperations();
        client.run(config);
        /*
        try {
            HBaseClientOperations.run(config);
        } catch(Exception ex){
            System.out.println("Client failure. " + ex.getMessage());
        }
        */
    }

}