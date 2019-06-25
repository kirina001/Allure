package com.music.allureTest;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.music.common.ExcelReader;
import com.music.common.ExcelWriter;
import com.music.inter.DataDrivenOfInter;

public class DataDrivenInvoke {

	public DataDrivenOfInter http;
	public ExcelReader caseExcel;
	public ExcelWriter resultExcel;
	public static String createdate;
	public static String resultXlsxPath;

	@Test(dataProvider = "keywords")
	public void f(String rowNo, String group, String type, String casename, String keywords, String param1,
			String param2, String param3, String k1, String k2, String k3, String k4, String k5, String k6) {
		int No = 0;
		No = Integer.parseInt(rowNo);
		http.line = No;
		System.out.println(rowNo + casename);
		runHttpWithInvoke(keywords, param1, param2, param3);
//		List<?> list=runHttpWithInvoke(keywords, param1, param2, param3);
//		for(int i=0;i<list.size();i++) {
//			System.out.println(list.get(i));
//		}
		// 调用校验方法
//		assertResults();

	}

	
	public void assertResults() {
		System.out.println("this is new test");
		// 读取excle结果文件
		Assert.assertEquals("读取到的数据", "PASS");// 循环判定
//		  Assert.assertTrue(false);

	}

	@DataProvider
	public Object[][] keywords() {
		return caseExcel.readAsMatrix();
	}

	@BeforeSuite
	public void beforeSuite() {

		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd+HH-mm-ss");
		createdate = sdf.format(date);
		String rootpath = System.getProperty("user.dir");
		caseExcel = new ExcelReader(rootpath + "\\cases\\music_auto_testng.xlsx");
		resultExcel = new ExcelWriter(rootpath + "\\cases\\music_auto_testng.xlsx",
				rootpath + "\\cases\\result-" + createdate + "music_auto_testng.xlsx");
		resultXlsxPath = rootpath + "\\cases\\result-" + createdate + "music_auto_testng.xlsx";
		System.out.println("所需路径" + resultXlsxPath);
		http = new DataDrivenOfInter(resultExcel);

	}

	@AfterSuite
	public void afterSuite() {
		caseExcel.close();
		resultExcel.save();
		// 发送邮件
//		report testReport = new report();
//		testReport.sendreport(resultXlsxPath,createdate);

	}

	//将校验方法传入？
	@SuppressWarnings("unused")
	private void runHttpWithInvoke(String keywords, String param1, String param2, String param3) {
		try {
			Method httpMethod0 = http.getClass().getDeclaredMethod(keywords);//执行关键字类里面的某一个方法
			// invoke语法，需要输入类名以及相应的方法用到的参数
			httpMethod0.invoke(http);
			
//			List<?> list = (List<?>)httpMethod0.invoke(http);
//			return list;
			return ;
		} catch (Exception e) {
		}
		try {
			Method httpMethod1 = http.getClass().getDeclaredMethod(keywords, String.class);
			// invoke语法，需要输入类名以及相应的方法用到的参数
			httpMethod1.invoke(http, param1);
//			List<?> list1=(List<?>)httpMethod1.invoke(http, param1);
//			return list1;
			return ;
		} catch (Exception e) {
		}
		try {
			Method httpMethod2 = http.getClass().getDeclaredMethod(keywords, String.class, String.class);
			// invoke语法，需要输入类名以及相应的方法用到的参数
			httpMethod2.invoke(http, param1, param2);
//			List<?> list2=(List<?>)httpMethod2.invoke(http, param1, param2);
//			return list2;
			return ;
		} catch (Exception e) {
		}
		try {
			Method httpMethod3 = http.getClass().getDeclaredMethod(keywords, String.class, String.class, String.class);
			// invoke语法，需要输入类名以及相应的方法用到的参数
			httpMethod3.invoke(http, param1, param2, param3);
//			List<?> list3=(List<?>)httpMethod3.invoke(http, param1, param2, param3);
//			return list3;
			return ;
		} catch (Exception e) {
		}
		
	}

	// 获取运行后的结果进行校验

}
