package fun.steven.bookstore.mr.hadoop;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class KeywordCountMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

    private String[] keywords = new String[0];
    private final IntWritable ONE = new IntWritable(1);

    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        String ks = context.getConfiguration().get("mr.keywords", "");
        if (!ks.isEmpty()) {
            keywords = ks.split("\\|", -1);
        }
    }

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        String low = line.toLowerCase();
        for (String k : keywords) {
            if (k == null || k.isEmpty()) continue;
            String lowk = k.toLowerCase();
            int count = 0;
            int idx = low.indexOf(lowk);
            while (idx >= 0) {
                count++;
                idx = low.indexOf(lowk, idx + lowk.length());
            }
            if (count > 0) {
                context.write(new Text(k), new IntWritable(count));
            }
        }
    }
}
