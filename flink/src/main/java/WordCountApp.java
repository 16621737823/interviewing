import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.configuration.RestOptions;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;

public class WordCountApp {
    public static void main(String[] args) {
        // 使用本地模式并开启 WebUI
        Configuration conf = new Configuration();
        conf.setString(RestOptions.BIND_PORT, "8081-8089");
        StreamExecutionEnvironment env = StreamExecutionEnvironment.createLocalEnvironmentWithWebUI(conf);

        // 定义数据源：source
//        DataStreamSource<String> source = env.socketTextStream("10.137.9.197", 9999);

        // 业务处理：transformation
//        source.flatMap(new FlatMapFunction<String, String>() {
//            @Override
//            public void flatMap(String value, Collector<String> collector) throws Exception {
//                String[] words = value.split(",");
//                for (String word : words) {
//                    collector.collect(word.trim());
//                }
//            }
//        }).map(new MapFunction<String, Tuple2<String, Integer>>() {
//            @Override
//            public Tuple2<String, Integer> map(String value) throws Exception {
//                return new Tuple2<>(value, 1);
//            }
//        }).keyBy(new KeySelector<Tuple2<String, Integer>, String>() {
//            @Override
//            public String getKey(Tuple2<String, Integer> value) throws Exception {
//                return value.f0;
//            }
//        }).sum(1).print();

        DataStream<String> dataStream = env.fromElements("apple", "banana", "cherry");

        dataStream.partitionCustom(new SubstringHashPartitioner(), value -> value)
                .print();

        // 执行
        try {
            env.execute("Word Count App");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
