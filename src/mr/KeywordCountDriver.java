package fun.steven.bookstore.mr.hadoop;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class KeywordCountDriver {

    public static void main(String[] args) throws Exception {
        if (args.length > 3) {
            System.err.println("Usage: KeywordCountDriver [inputDir] [outputDir] [keywordsFile]");
            System.exit(2);
        }

        String input = args.length >= 1 ? args[0] : "mr/input";
        String output = args.length >= 2 ? args[1] : "mr/output/keyword-count";
        String keywordsFile = args.length >= 3 ? args[2] : "mr/keywords.txt";

        List<String> kws = new ArrayList<>();
        File kf = new File(keywordsFile);
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(kf), StandardCharsets.UTF_8))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (!line.isEmpty()) kws.add(line);
            }
        }

        String joined = String.join("|", kws);

        Configuration conf = new Configuration();
        conf.set("mr.keywords", joined);

        Job job = Job.getInstance(conf, "keyword-count");
        job.setJarByClass(KeywordCountDriver.class);

        job.setMapperClass(KeywordCountMapper.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        job.setReducerClass(KeywordCountReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        job.setInputFormatClass(TextInputFormat.class);

        FileInputFormat.addInputPath(job, new Path(input));
        Path outPath = new Path(output);

        FileSystem fs = FileSystem.get(conf);
        if (fs.exists(outPath)) fs.delete(outPath, true);
        FileOutputFormat.setOutputPath(job, outPath);

        boolean res = job.waitForCompletion(true);
        System.exit(res ? 0 : 1);
    }
}
