package ExcepetionWork;

import ExcepetionWork.IndexIsNagetiveException;
import ExcepetionWork.IndexIsOutofRangeException;
import java.lang.NullPointerException;

public class MyStringBuffer implements IStringBuffer {
	
	int capacity = 16;
    int length = 0;
    char[] value;
    
    public MyStringBuffer() {
        value = new char[capacity];
    }
 
    public MyStringBuffer(String str) {
        this();
        if (null == str)
            return;
 
        if (capacity < str.length()) {
            capacity = value.length * 2;
            value = new char[capacity];
        }
 
        if (capacity >= str.length())
            System.arraycopy(str.toCharArray(), 0, value, 0, str.length());
 
        length = str.length();
 
    }
	@Override
	public void append(String str) throws IndexIsOutofRangeException, IndexIsNagetiveException {
		insert(length, str);
	}

	@Override
	public void append(char c) throws IndexIsOutofRangeException, IndexIsNagetiveException {
		insert(length, c);
	}

	@Override
	public void insert(int pos, char b) throws IndexIsOutofRangeException, IndexIsNagetiveException {
		insert(pos,String.valueOf(b));
	}

	@Override
	public void insert(int pos, String b) throws IndexIsOutofRangeException, IndexIsNagetiveException {
		 //边界条件判断
        if(pos<0)
        	throw new IndexIsNagetiveException();
          
        if(pos>length)
        	throw new IndexIsOutofRangeException();
          
        if(null==b)
        	throw new NullPointerException();
          
        //扩容
        while(length+b.length()>capacity){
            capacity = (int) ((length+b.length())*1.5f);
            char[] newValue = new char[capacity];
            System.arraycopy(value, 0, newValue, 0, length);
            value = newValue;
        }
          
        char[] cs = b.toCharArray();
          
        //先把已经存在的数据往后移
          
        System.arraycopy(value, pos, value,pos+ cs.length, length-pos);
        //把要插入的数据插入到指定位置
        System.arraycopy(cs, 0, value, pos, cs.length);
          
        length = length+cs.length;

	}

	@Override
	public void delete(int start) throws IndexIsOutofRangeException, IndexIsNagetiveException {
		delete(start,length);
	}

	@Override
	public void delete(int start, int end) throws IndexIsOutofRangeException, IndexIsNagetiveException {
		if(start<0)
            throw new IndexIsNagetiveException();
         
        if(start>length)
        	throw new IndexIsOutofRangeException();
         
        if(end<0)
        	throw new IndexIsNagetiveException();
         
        if(end>length)
        	throw new IndexIsOutofRangeException();
         
        if(start>=end)
        	throw new IndexIsOutofRangeException();
         
        System.arraycopy(value, end, value, start, length- end);
        length-=end-start;
	}

	@Override
	public void reverse() {
		for (int i = 0; i < length/2; i++) {
            char temp = value[i];
            value[i] = value[length-i-1];
            value[length-i-1] = temp;
        }
	}

	@Override
	public int length() {
		// TODO Auto-generated method stub
		return length;
	}
	
	public String toString(){
        
        char[] realValue = new char[length];
 
        System.arraycopy(value, 0, realValue, 0, length);
         
        return new String(realValue);
         
    }

}
//MyStringBuffer基本代码参考于https://how2j.cn/k/number-string/number-string-mystringbuilder/331.html