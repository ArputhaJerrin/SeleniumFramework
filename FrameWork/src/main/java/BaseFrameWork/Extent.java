package BaseFrameWork;

import java.io.File;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class Extent extends Mail{

	static ExtentTest test;
	static ExtentReports report;

	static String filename = "ATMTN_REPORT_" + timestamp + ".html";

	@BeforeTest
	public static void start() {
		
		report = new ExtentReports("File Path" + filename);
		report.addSystemInfo("User Name", "Arputha Jerrin");
		report.assignProject("Project Name");
		report.addSystemInfo("Project ID", "Project ID");
		report.addSystemInfo("Project Name", "Project Name");
		report.addSystemInfo("Platform", "Web");
		report.addSystemInfo("APK Build", "");
		report.loadConfig(new File(System.getProperty("user.dir") + "/extent-config.xml"));

	}

	@AfterTest
	public static void end() throws Exception {

		report.flush();		
		sendmailAttachment(filename,"Mail id");		
		
	}

}

