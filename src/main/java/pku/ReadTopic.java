package pku;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;

public class ReadTopic {
	private  String topic;
	public ReadTopic(String topic){
		this.topic=topic;	
	}
	
	public DataInputStream getDataInputStream() throws FileNotFoundException {
		while (MapUtils.getValue(topic) == 1){   //取得其Value值 如果为1表示循环等待
			
		}
		MapUtils.setValue(topic, 1);
		//RandomAccessFile raf = new RandomAccessFile("./data/" + topic + ".dat", "rw");
		File file = new File("./data/" + topic + ".dat");
		DataInputStream dis = new DataInputStream(new BufferedInputStream(new FileInputStream(file)));
		return dis;
	}

	public synchronized  void unLock() {
		MapUtils.setValue(topic, 0);  //解除锁定
	}
}
