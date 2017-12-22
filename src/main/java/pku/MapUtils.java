package pku;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

/*
 *  自定义一些项目中能用到的Map集合方法
 */
public class MapUtils { 
	public static Map<String, Integer> in = new HashMap<>();
	public static Map<String, Integer> out = new HashMap<>();
	public synchronized static boolean containsKey(String key) {
		return in.containsKey(key);
	}
	public synchronized static void setValue(String key, int value) {
		in.put(key, value);    
	}
	public synchronized static int getValue(String key) {
		return in.getOrDefault(key, 0);   //如果存在则返回key所对应的value值，不存在则返回0
	}
	public synchronized static int getOut(String key){
		return out.getOrDefault(key, 0); //如果存在则返回key所对应的value值，不存在则返回0
	}
	
	public synchronized static void setOut(String key, int value) {
		out.put(key, value);
	}
	//测试getOrDefault方法
	public static void main(String[] args) throws FileNotFoundException, IOException {
		in.put("0", 2);
		Integer orDefault = in.getOrDefault("0", 3);
		System.out.println(orDefault);
		
	}

}
