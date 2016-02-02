package com.winry;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.zip.CRC32;

import org.junit.Test;

import com.winry.util.DateTimeUtil;

//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = ImgmurApplication.class)
public class ImgmurApplicationTests {

	@Test
	public void contextLoads() {
	}
	
	@Test
	public void md5Test() throws NoSuchAlgorithmException, UnsupportedEncodingException {
		String time = DateTimeUtil.getNowTime();
		System.out.println(time); 
		String str = Base64.getEncoder().encodeToString(time.getBytes("utf-8"));
		System.out.println(str); 
	}
	
	@Test
	public void crcTest(){
		CRC32 crc = new CRC32();
		crc.update("221259".getBytes());
		System.out.println(crc.toString());
	}
	

}
