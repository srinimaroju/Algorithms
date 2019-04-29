import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class OrgChartTester {
	public static void main(String[] args) {
		ConsoleProcessor processor = new ConsoleProcessor();
		processor.processAllLines();
	}
}

class ConsoleProcessor {

	public OrgChart orgChart = new OrgChart();

	public void processAllLines() {

		// The name of the file to open.
		String fileName = "~/Downloads/test_cases/input010.txt";

		// This will reference one line at a time
		String line = null;

		try {
			// FileReader reads text files in the default encoding.
			FileReader fileReader = 
					new FileReader(fileName);

			// Always wrap FileReader in BufferedReader.
			BufferedReader bufferedReader = 
					new BufferedReader(fileReader);
			line = bufferedReader.readLine();
			Integer numLines = 0;
			numLines = Integer.valueOf(line.trim());
			while((line = bufferedReader.readLine()) != null) {
				System.out.println(line);
				this.processLine(line);
			}   

			// Always close files.
			bufferedReader.close();         
		}
		catch(FileNotFoundException ex) {
			System.out.println(
					"Unable to open file '" + 
							fileName + "'");                
		}
		catch(IOException ex) {
			System.out.println(
					"Error reading file '" 
							+ fileName + "'");                  
			// Or we could just do this: 
			// ex.printStackTrace();
		}
	}

	protected void processLine(String line) {
		String[] parsedCommand = line.split(",");

		// ignore empty lines
		if (parsedCommand.length == 0) {
			return;
		}

		switch (parsedCommand[0]) {
		case "add":
			orgChart.add(parsedCommand[1], parsedCommand[2], parsedCommand[3]);
			break;
		case "print":
			orgChart.print();
			break;
		case "remove":
			orgChart.remove(parsedCommand[1]);
			break;
		case "move":
			orgChart.move(parsedCommand[1], parsedCommand[2]);
			break;
		case "count":
			System.out.println(orgChart.count(parsedCommand[1]));
			break;
		}
	}
}

class OrgChart {
	ArrayList<Integer> employees;
	HashMap<Integer, Employee> emps;
	Employee root = null;
	public OrgChart() {
		employees = new ArrayList<Integer>();
		emps = new HashMap<Integer, Employee>();
	}

	public void add(String id, String name, String managerId)
	{
		int emid = Integer.parseInt(id);
		if(employees.contains(emid)) {
			//ignore
			return;
		}
		Employee emp = new Employee(Integer.parseInt(id), name);
		int intmanagerId = Integer.parseInt(managerId);
		if (!employees.contains(intmanagerId)) {
			intmanagerId = -1;
		} else {
			//Employee manager = emps.
			Employee manager = emps.get(intmanagerId);
			manager.reports.add(emp);
			emp.manager = manager;
			emp.level = manager.level + 1;
		}

		if(root==null) {
			root = emp;
		}
		emps.put(emid, emp);
		employees.add(emid);
	}

	public void print()
	{
		//System.out.println(Arrays.deepToString(employees.toArray()));
		int size = employees.size();
		//System.out.println("size" + size);
		for(int i=0;i<size;i++) {
			int empid = employees.get(i);
			Employee emp = emps.get(empid);
			if(emp==null)  {}
			else if(emp.level==0) {
				System.out.print(emp);
			}
		}
	}

	public void remove(String employeeId)
	{
		int intEmployeeId = Integer.parseInt(employeeId);
		if(!employees.contains(intEmployeeId)) {
			return;
		}
		Employee employee = emps.get(intEmployeeId);
		employees.remove(intEmployeeId);
		//emps.remove(new Integer(intEmployeeId));
		if(employee.reports==null) {
			return;
		} else {
			for(int i=0;i<employee.reports.size();i++) {
				Employee reportee = employee.reports.get(i);
				if(employee.manager!=null) {
					employee.manager.reports.add(reportee);
					reportee.manager = employee.manager;
				} else {
					reportee.manager = null;
				}
			}
		}
		
	}

	public void move(String employeeId, String newManagerId)
	{
		int intEmployeeId = Integer.parseInt(employeeId);
		int intNewManagerId = Integer.parseInt(newManagerId);
		if(!employees.contains(intEmployeeId) || !employees.contains(intNewManagerId)) {
			return;
		}
		Employee employee = emps.get(intEmployeeId);
		//System.out.println("Moving..");
		//System.out.println(employee);
		//System.out.println("to..");
		
		Employee newManager = emps.get(intNewManagerId);
		//System.out.println(newManager);

		try {

			if(employee.manager!=null) {
				Employee oldManager = employee.manager;
				oldManager.reports.remove(employee);
				if(employee.manager.value == newManager.value) {
					//Old and new manager are same
					return;
				}
			} 
		}
		catch(NullPointerException e) {
			//System.out.println("null");
		}

		newManager.reports.add(employee);
		employee.level = newManager.level+1;
	}

	public int count(String employeeId)
	{
		this.print();
		int intEmployeeId = Integer.parseInt(employeeId);
		if(!employees.contains(intEmployeeId)) {
			return 0;
		}
		Employee employee = emps.get(intEmployeeId);
		if(employee.reports ==null) {
			return 0;
		}
		return employee.getReportCount() + 1;
	}
}

class Employee {
	int value;
	String name;
	List<Employee> reports;
	int level;
	Employee manager;
	public int getReportCount() {
		int reportCount = 0;
		//System.out.println("getting count for " + this.value);
		if(reports==null || reports.size()==0) {
			return 1;
		}
		for(int i=0;i<reports.size();i++) {
			reportCount += reports.get(i).getReportCount();
		}
		return reportCount;
	}
	public String toString() {
		StringBuffer output = new StringBuffer(this.padding(this.level) + this.name + " [" + this.value + "]");
		if(reports.isEmpty()) {
			output.append("\n");
		} else {
			output.append("\n");
			for(int i=0;i<reports.size();i++) {
				output.append(reports.get(i).toString());
			}
		}
		return output.toString();
	}
	public String padding(int level) {
		StringBuffer output = new StringBuffer("");
		for(int i=0;i<level;i++) {
			output.append("  ");
		}
		return output.toString();
	}
	public Employee(int value, String name) {
		this.value = value;
		this.name = name;
		this.level = 0;
		reports = new ArrayList<Employee>();
		this.manager = null;
	}
	@Override
	public boolean equals(Object obj) 
	{ 
		if(this == obj) 
			return true; 

		if(obj == null || obj.getClass()!= this.getClass()) 
			return false; 

		// type casting of the argument.  
		Employee geek = (Employee) obj; 

		// comparing the state of argument with  
		// the state of 'this' Object. 
		return (geek.value == this.value); 
	} 
}
