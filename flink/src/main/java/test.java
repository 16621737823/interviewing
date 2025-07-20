import org.apache.flink.configuration.Configuration;
import org.apache.flink.configuration.RestOptions;
import org.apache.flink.configuration.TaskManagerOptions;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

public class test {
    public static void main(String[] args) throws Exception {
        // 使用本地模式并开启 WebUI
        Configuration conf = new Configuration();
        conf.setString(RestOptions.BIND_PORT, "8081-8089");
        conf.setInteger(TaskManagerOptions.NUM_TASK_SLOTS, 2);
        StreamExecutionEnvironment env = StreamExecutionEnvironment.createLocalEnvironmentWithWebUI(conf);

        DataStream<String> dataStream = env.fromElements("apple", "banana", "cherry");

        dataStream.partitionCustom(new SubstringHashPartitioner(), value -> value)
                .print();

        env.execute("Fixed Slot Demo");
    }
}
