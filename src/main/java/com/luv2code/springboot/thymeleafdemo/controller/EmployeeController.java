package com.luv2code.springboot.thymeleafdemo.controller;

import com.luv2code.springboot.thymeleafdemo.entity.Employee;
import com.luv2code.springboot.thymeleafdemo.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

	private EmployeeService employeeService;

	public EmployeeController(EmployeeService theEmployeeService){
		employeeService = theEmployeeService;
	}

	@GetMapping("/list")
	public String listEmployees(Model theModel) {

		List<Employee> theEmployees = employeeService.findAll();
		// add to the spring model
		theModel.addAttribute("employees", theEmployees);

		return "employees/list-employees";
	}

	//Thêm
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model thModel){

		Employee theEmployee = new Employee();

		thModel.addAttribute("employee",theEmployee);

		return "employees/a";
	}

	@PostMapping("/save")
	public String saveEmployee(@ModelAttribute("employee") Employee theEmployee){

		employeeService.save(theEmployee);

		return "redirect:/employees/list";
	}

	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("employeeId") int theId,Model theModel){

		Employee thEmployee  = employeeService.findById(theId);

		theModel.addAttribute("employee",thEmployee);

		return "employees/form-employees";
	}

	@GetMapping("/delete")
	public String deleteEmployees(@RequestParam("employeeId") int theId){

		employeeService.deleteById(theId);

		return "redirect:/employees/list";
	}

	@PostMapping("/update")
	public String updateEmployee(@ModelAttribute("employee") Employee theEmployee){

		employeeService.save(theEmployee);

		return "redirect:/employees/list";
	}

}









