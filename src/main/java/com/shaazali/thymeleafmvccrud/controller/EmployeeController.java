package com.shaazali.thymeleafmvccrud.controller;

import com.shaazali.thymeleafmvccrud.entity.Employee;
import com.shaazali.thymeleafmvccrud.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    // add mapping for "/list"

    @GetMapping("/list")
    public String listEmployees(Model theModel){

        // get the employees from db
        List<Employee> theEmployees = employeeService.findAll();

        // add to the spring model
        theModel.addAttribute("employees",theEmployees);

        return "list-employees"; // list-employees.html
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel){

        //create model attribute to bind form data
        Employee theEmployee = new Employee();

        theModel.addAttribute("employee",theEmployee);

        return "employee-form";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("employeeId") int theId, Model theModel){

        // get the employee from the service
        Employee theEmployee = employeeService.findById(theId);
        // set employee in the model to prepopulate the form
        theModel.addAttribute("employee",theEmployee);
        // send over to our form

        return "employee-form";
    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") Employee theEmployee){

        // save the employee
        employeeService.save(theEmployee);

        // use a redirect to prevent duplicate submissions
        return "redirect:/employees/list";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("employeeId") int theId){
        employeeService.deleteById(theId);

        return "redirect:/employees/list";
    }
}
