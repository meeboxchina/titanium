package cn.titanium;

import java.io.IOException;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

public class Counter {

	public Counter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 * @throws IOException 
	 * @throws Exception 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws IOException, ClassNotFoundException, Exception {
		// TODO Auto-generated method stub
		 Configuration conf = new Configuration();
		 conf.addResource(new Path("/home/sunyu/core-site.xml"));
		 conf.addResource(new Path("/home/sunyu/hdfs-site.xml"));
		 conf.addResource(new Path("/home/sunyu/yarn-site.xml"));
		 
		 String[] otherArgs = new GenericOptionsParser(conf, args).getRemainingArgs();
		 if (otherArgs.length != 2) {
		 	//System.err.println("Usage: wordcount <in> <out>");
		 	//System.exit(2);
		 }
		 
		 
		 Job job = new Job(conf, "word count");
		 job.setJarByClass(Counter.class);
		 job.setMapperClass(CountMapper.class);
		 job.setCombinerClass(CountReducer.class);
		 job.setReducerClass(CountReducer.class);
		 job.setOutputKeyClass(Text.class);
		 job.setOutputValueClass(IntWritable.class);
		 
		 
		 FileInputFormat.addInputPath(job, new Path("/devops/log"));
		 FileOutputFormat.setOutputPath(job, new Path("/devops/count10"));
		 
		 //FileInputFormat.addInputPath(job, new Path(otherArgs[0]));
		 //FileOutputFormat.setOutputPath(job, new Path(otherArgs[1]));
		 System.exit(job.waitForCompletion(true) ? 0 : 1);
		
	}

}
