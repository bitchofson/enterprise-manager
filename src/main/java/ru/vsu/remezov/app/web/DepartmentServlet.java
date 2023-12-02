package ru.vsu.remezov.app.web;

import ru.vsu.remezov.domain.Department;
import ru.vsu.remezov.infrastructure.repository.impl.PostgresDepartmentRepository;
import ru.vsu.remezov.infrastructure.repository.impl.PostgresStorage;
import ru.vsu.remezov.presentation.controller.DepartmentController;
import ru.vsu.remezov.usecase.impl.DepartmentService;

import ru.vsu.remezov.domain.Employee;
import ru.vsu.remezov.infrastructure.repository.impl.PostgresEmployeeRepository;
import ru.vsu.remezov.presentation.controller.EmployeeController;
import ru.vsu.remezov.usecase.impl.EmployeeService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;



@WebServlet(name="DepartmentServlet", 
urlPatterns={
		"/department", 
		"/department/edit",
		"/department/update",
		"/department/delete",
		"/department/new",
		"/department/list",
		"/department/insert",
		})
public class DepartmentServlet extends HttpServlet {
	
    private static final long serialVersionUID = 1L;
    
    private DepartmentController departmentController;
    
    private EmployeeController employeeController;

    
    public DepartmentServlet() {
        try {
            PostgresStorage postgresStorage = new PostgresStorage();
            postgresStorage.connect();
        
            this.departmentController = new DepartmentController(new DepartmentService(new PostgresDepartmentRepository(postgresStorage)));
            this.employeeController = new EmployeeController(new EmployeeService(new PostgresEmployeeRepository(postgresStorage)));

            
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
            case "/department/new" -> showNewForm(request, response);
            case "/department/insert" -> insertDepartment(request, response);
            case "/department/delete" -> deleteDepartment(request, response);
            case "/department/edit" -> showEditForm(request, response);
            case "/department/update" -> updateDepartment(request, response);
            default -> listDepartments(request, response);
        }
        } catch(SQLException ex) {
        	throw new ServletException();
        }
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/department/department-form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Department department = departmentController.findById(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/department/department-form.jsp");
        request.setAttribute("department", department);
        dispatcher.forward(request, response);
    }

    private void insertDepartment(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String name = request.getParameter("name");
        departmentController.createDepartment(Department.builder().name(name).build());
        response.sendRedirect("list");
    }

    private void deleteDepartment(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
       int id = Integer.parseInt(request.getParameter("id"));
       departmentController.deleteDepartment(Department.builder().id(id).build());
       response.sendRedirect("list");
    }

    private void updateDepartment(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        departmentController.updateDepartment(Department.builder().id(id).name(name).build());
        response.sendRedirect("list");
    }

    private void listDepartments(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {        
        
    	List<Department> departmentList = departmentController.findAllDepartments();
        Map<String, Integer> departmentSumSalary = new HashMap<>(departmentController.getDepartmentSalarySums(employeeController.findAllEmployees()));        
        
        request.setAttribute("listDepartments", departmentList);
        request.setAttribute("mapDepartmentSumSalary", departmentSumSalary);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/department/department-list.jsp");
        dispatcher.forward(request, response);
    }

}
