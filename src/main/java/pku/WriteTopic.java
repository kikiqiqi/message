package pku;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class WriteTopic {
   private  String topic;
  // MappedByteBuffer out=null;
   //这里我需要一个超级大的cache
  // private int cacheSize=1024*1024*1024;
	
	public WriteTopic(String topic){
		this.topic=topic;	
	}
	
	public synchronized DataOutputStream getDataOutputStream() throws IOException {
		while (MapUtils.getOut(topic) == 1){   //取得其Value值 如果为1表示循环等待
		}
		MapUtils.setOut(topic, 1);
		File file = new File("./data/" + topic + ".dat");
		//ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("./data/" + topic + ".dat"));
	 	//RandomAccessFile dos = new RandomAccessFile("./data/" + topic + ".dat","rw");  
	 	//out=memoryMappedFile.getChannel().map(FileChannel.MapMode.READ_WRITE,0,cacheSize);
	 	DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(file,true)));
		return dos;
	}

}
