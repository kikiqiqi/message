package pku;

import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.File;

/**
 * Created by yangxiao on 2017/11/14.
 */
public class Producer {
	DataOutputStream in = null;
	File file = null;
	static int pos = 0;
	static int count = 0;
	static {
		File f = new File("./data");
		if (!f.exists()) {
			f.mkdirs();
		}
	}
	/*public Producer() { // 构造方法初始化
	}*/

	public ByteMessage createBytesMessageToTopic(String topic, byte[] body) throws Exception {
		ByteMessage msg = new DefaultMessage(body);  //初始化消息 消息体部分
		msg.putHeaders(MessageHeader.TOPIC, topic);    //设置消息头
		file = new File("./data/" + topic + ".dat");
		if (!file.exists()) {
			file.createNewFile();
		}
		return msg;
	}

	public void send(ByteMessage defaultMessage) throws Exception {
		WriteTopic output = MySynchronized.getOutput(defaultMessage.headers().getString(MessageHeader.TOPIC));
		in = output.getDataOutputStream();   //获得流
		KeyValue headers = defaultMessage.headers();
		StringBuilder sb = new StringBuilder();
		for (String key : headers.keySet()) {  //处理消息头信息
			Object object = headers.getObj(key);  
			if (object instanceof Integer) {  //  格式    key : 值
				sb.append(key).append(":").append(Integer.toString(((Integer) object))).append(",");
			}
			if (object instanceof Double) {
				sb.append(key).append(":").append(Double.toString(((Double) object))).append(",");
			}
			if (object instanceof Long) {
				sb.append(key).append(":").append(Long.toString(((Long) object))).append(",");
			}
			if (object instanceof String) {
				sb.append(key).append(":").append(((String) object) + "").append(",");
			}
		}
		in.writeInt(sb.substring(0, sb.length() - 1).toString().getBytes().length);// 消息头大小
		in.write(sb.substring(0, sb.length() - 1).toString().getBytes());  //消息头内容
		in.writeInt(defaultMessage.getBody().length);   //消息体大小
		in.write(defaultMessage.getBody());  //消息体内容
		in.flush();  //刷出

		MapUtils.setOut(defaultMessage.headers().getString(MessageHeader.TOPIC), 0); 
	}

	public void flush() throws Exception {

	}
}
