package pku;

import java.util.HashMap;
import java.util.Map;

public class MySynchronized {
     
	public static Map<String, ReadTopic> ins = new HashMap<>();
	public static Map<String, WriteTopic> ous = new HashMap<>();



	public synchronized static ReadTopic getInput(String topic) {
		ReadTopic in = ins.getOrDefault(topic, null);  //判断该topic流是否存在，存在则返回DataInput对象，否则返回null
		if (in == null) {
			ins.put(topic, new ReadTopic(topic));  //如果为null 则重新定义DataInput对象
		}
		return ins.get(topic);
	}

	public synchronized static WriteTopic getOutput(String topic) { //通过topic对象获得其DataOutput对象
		WriteTopic in = ous.getOrDefault(topic, null);
		if (in == null) {
			ous.put(topic, new WriteTopic(topic));  //如果不存在就新建一个DataOutput对象
		}
		return ous.get(topic);
	}

}
