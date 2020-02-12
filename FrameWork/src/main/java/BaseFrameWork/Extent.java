package BaseFrameWork;

import java.io.File;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class Extent extends Mail{

	static ExtentTest test;
	static ExtentReports report;

	static String filename = "SVEP4158_MAHINDRA_FOTA_WEB_ATMTN_REPORT_" + timestamp + ".html";

	@BeforeTest
	public static void start() {
		
		report = new ExtentReports("D:\\EMP 878 AJ\\Reports\\FOTA\\" + filename);
		report.addSystemInfo("User Name", "Arputha Jerrin");
		report.assignProject("Mahindra FOTA");
		report.addSystemInfo("Project ID", "SVEP4158");
		report.addSystemInfo("Project Name", "Mahindra FOTA");
		report.addSystemInfo("Platform", "Web");
		report.addSystemInfo("APK Build", "");
		report.loadConfig(new File(System.getProperty("user.dir") + "/extent-config.xml"));

	}

	@AfterTest
	public static void end() throws Exception {

		report.flush();		
		/*sendmailAttachment(filename,"arputhajerrin.e@contus.in");*/		

		/*arputhajerrin.e@contus.in
		 * dinesh.k@contus.in
		 * Deprojectsqa@contus.in*/
		
	}

}

