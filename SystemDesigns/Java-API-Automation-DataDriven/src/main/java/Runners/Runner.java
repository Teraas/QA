package Runners;

public class Runner {

	public static void main(String[] args) throws Exception {
		String xmlRunner = "";
		String jsonConfig = "";
		try {
			xmlRunner = args[0];
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Please Enter xmlRunner file directory!!");
			return;
		}
		try {
			jsonConfig = args[1];
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Please Enter jsonConfig file directory!!");
			return;
		}
		System.out.println("XML Runner file: " + xmlRunner);
		System.out.println("JSON config file: " + jsonConfig);
		System.out.println("==================================");
//		TestNG runner = new TestNG();
//		List<String> suitefiles = new ArrayList<String>();
//		suitefiles.add(xmlRunner);
//		runner.setTestSuites(suitefiles);
//		runner.run();
		System.out.println("Execution is DONE");

	}

}
