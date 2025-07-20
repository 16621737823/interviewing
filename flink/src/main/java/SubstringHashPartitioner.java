import org.apache.flink.api.common.functions.Partitioner;

public class SubstringHashPartitioner implements Partitioner<String> {
    @Override
    public int partition(String key, int numPartitions) {
        // 只取前两个字符进行哈希计算
        String prefix = key.length() >= 2 ? key.substring(0, 2) : key;
        return Math.abs(prefix.hashCode()) % numPartitions;
    }
}