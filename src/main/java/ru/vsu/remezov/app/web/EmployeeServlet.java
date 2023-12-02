package ru.vsu.remezov.app.web;


import ru.vsu.remezov.domain.Employee;
import ru.vsu.remezov.domain.Department;
import ru.vsu.remezov.infrastructure.repository.impl.PostgresEmployeeRepository;
import ru.vsu.remezov.infrastructure.repository.impl.PostgresDepartmentRepository;
import ru.vsu.remezov.infrastructure.repository.impl.PostgresStorage;
import ru.vsu.remezov.presentation.controller.DepartmentController;
import ru.vsu.remezov.presentation.controller.EmployeeController;
import ru.vsu.remezov.usecase.impl.DepartmentService;
import ru.vsu.remezov.usecase.impl.EmployeeService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;



@WebServlet(name="EmployeeServlet", urlPatterns={
		"/employee", 
		"/employee/edit",
		"/employee/update",
		"/employee/delete",
		"/employee/new",
		"/employee/list",
		"/employee/insert",
		})
public class EmployeeServlet extends HttpServlet {
	
    private static final long serialVersionUID = 1L;
    
    private EmployeeController employeeController;
    
    private DepartmentController departmentController;
    
    public EmployeeServlet() {
        try {
            PostgresStorage postgresStorage = new PostgresStorage();
            postgresStorage.connect();
            this.employeeController = new EmployeeController(new EmployeeService(new PostgresEmployeeRepository(postgresStorage)));
            this.departmentController = new DepartmentController(new DepartmentService(new PostgresDepartmentRepository(postgresStorage)));
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
        String action = request.getServletPath();
        
        try {
        switch (action) {
            case "/employee/new" -> showNewForm(request, response);
            case "/employee/insert" -> insertEmployee(request, response);
            case "/employee/delete" -> deleteEmployee(request, response);
            case "/employee/edit" -> showEditForm(request, response);
            case "/employee/update" -> updateEmployee(request, response);
            default -> listEmployees(request, response);
        }
        } catch(SQLException ex) {
        	throw new ServletException();
        }
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
    	
    	List<Department> departments = departmentController.findAllDepartments();
    	request.setAttribute("departmentList", departments);
    	
        RequestDispatcher dispatcher = request.getRequestDispatcher("/employee/employee-form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Employee employee = employeeController.findById(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/employee/employee-form.jsp");
        
        request.setAttribute("employee", employee);
        
      	List<Department> departments = departmentController.findAllDepartments();
    	request.setAttribute("departmentList", departments);
    	
        dispatcher.forward(request, response);
    }

    private void insertEmployee(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        	
    	int age = Integer.parseInt(request.getParameter("age"));
        int salary = Integer.parseInt(request.getParameter("salary"));
        String fullName = request.getParameter("fullName");
        int idDepartment = Integer.parseInt(request.getParameter("idDepartment"));
        
        Department department = departmentController.findById(idDepartment);
        
        employeeController.createEmployee(Employee
        		.builder()
        		.fullName(fullName)
        		.age(age).salary(salary)
        		.department(department)
        		.build());
        
        response.sendRedirect("list");
    }

    private void deleteEmployee(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
       int id = Integer.parseInt(request.getParameter("id"));
       employeeController.deleteEmployee(Employee.builder().id(id).build());
       response.sendRedirect("list");
    }

    private void updateEmployee(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        
    	int id = Integer.parseInt(request.getParameter("id"));
        int age = Integer.parseInt(request.getParameter("age"));
        int salary = Integer.parseInt(request.getParameter("salary"));
        String fullName = request.getParameter("fullName");
        int idDepartment = Integer.parseInt(request.getParameter("idDepartment"));
        
        Department department = departmentController.findById(idDepartment);

        employeeController.updateEmployee(Employee
        		.builder().id(id)
        		.fullName(fullName)
        		.age(age).salary(salary)
        		.department(department)
        		.build()
        		);
        response.sendRedirect("list");
    }

    private void listEmployees(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        List<Employee> employeeList = employeeController.findAllEmployees();
        request.setAttribute("listEmployees", employeeList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/employee/employee-list.jsp");
        dispatcher.forward(request, response);
    }

}