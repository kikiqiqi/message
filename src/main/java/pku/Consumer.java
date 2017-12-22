package pku;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by yangxiao on 2017/11/14.
 */

public class Consumer {
	List<String> topics = new LinkedList<>();
	int pos = 0;
	static int a = 0;
	int existPos = 0;
	DataInputStream dis = null;
	File file = null;
	List<String> existTopics = new ArrayList<>();
	public void attachQueue(String queueName, Collection<String> t) throws Exception {
		topics.addAll(t);
		for (String topic : topics) {
			File f = new File("./data/" + topic + ".dat");
			if (f.exists()) {
				existTopics.add(topic);
			}
		}
	}

	public ByteMessage poll() throws Exception {
		if (pos >= topics.size()) {
			return null;
		}
		if (existPos == 0) {
			existPos = 1;
			ReadTopic d = MySynchronized.getInput(existTopics.get(pos));
			dis = d.getDataInputStream();
		}
		int len;
		byte[] headersByte = null;
		byte[] bytes = null;
		try {
			len = dis.readInt();
			headersByte = new byte[len];
			dis.read(headersByte);
			len = dis.readInt();
			bytes = new byte[len];
			dis.read(bytes);
			DefaultMessage msg = new DefaultMessage(bytes);
			String[] allHeaders = new String(headersByte).split(",");
			KeyValue headers = new DefaultKeyValue();
			for (String header : allHeaders) {
				String[] headerSplit = header.split(":");
				headers.put(headerSplit[0], headerSplit[1] + "");
			}
			msg.setHeaders(headers);
			return msg;
		} catch (EOFException e) {
			MapUtils.setValue(existTopics.get(pos), 0);
			pos++;
			if (pos >= existTopics.size()) {
				return null;
			}
			ReadTopic d = MySynchronized.getInput(existTopics.get(pos));
			dis = d.getDataInputStream();
			len = dis.readInt();
			headersByte = new byte[len];
			dis.read(headersByte);
			len = dis.readInt();
			bytes = new byte[len];
			dis.read(bytes);
			DefaultMessage msg = new DefaultMessage(bytes);
			String[] allHeaders = new String(headersByte).split(",");
			KeyValue headers = new DefaultKeyValue();
			for (String header : allHeaders) {
				String[] headerSplit = header.split(":");
				headers.put(headerSplit[0], headerSplit[1] + "");
			}
			msg.setHeaders(headers);
			return msg;
		}  
	}
}
