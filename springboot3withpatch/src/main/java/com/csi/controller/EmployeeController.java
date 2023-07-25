package com.csi.controller;

import com.csi.exception.RecordNotFoundException;
import com.csi.model.Employee;
import com.csi.service.EmployeeServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
    EmployeeServiceImpl employeeServiceImpl;

    @PostMapping("/savedata")
    public ResponseEntity<Employee>saveData(@Valid @RequestBody Employee employee){
        return new ResponseEntity<>(employeeServiceImpl.saveData(employee), HttpStatus.CREATED);

    }

    @GetMapping("/getalldata")
    public ResponseEntity<List<Employee>>getAllData(){
        return ResponseEntity.ok(employeeServiceImpl.getAllData());
    }


    @GetMapping("/getdatabyid")
    public ResponseEntity<Optional<Employee>> getDataById(@PathVariable int empId){
        return ResponseEntity.ok(employeeServiceImpl.getDataById(empId));
    }

    @PatchMapping("/updateaddress/{empId}/{empAddress}")
    public ResponseEntity<Employee>updateDataOnlyAddress(@PathVariable int empId,@PathVariable String empAddress){
        Employee employee=employeeServiceImpl.getDataById(empId).orElseThrow(()->new RecordNotFoundException("Id does not exist"));
        employee.setEmpEmailId(empAddress);
        return ResponseEntity.ok(employeeServiceImpl.updateDataOnlyAddress(employee));
    }



    @PutMapping("/updatedata/{empId}")
   public ResponseEntity<Employee>updateData(@PathVariable int empId,@Valid@RequestBody Employee employee){
        Employee employee1=employeeServiceImpl.getDataById(empId).orElseThrow(()->new RecordNotFoundException("Employee Id does not exist"));
        employee1.setEmpContactNumber(employee.getEmpContactNumber());
        employee1.setEmpAddress(employee.getEmpAddress());
        employee1.setEmpSalary(employee.getEmpSalary());
        employee1.setEmpName(employee.getEmpName());
        employee1.setEmpDOB(employee.getEmpDOB());
        employee1.setEmpEmailId(employee.getEmpEmailId());

        return new ResponseEntity<>(employeeServiceImpl.updateData(employee1),HttpStatus.CREATED);
    }

    @DeleteMapping("/deletedatabyid/{empId}")
    public ResponseEntity<String>deleteDataById(@PathVariable int empId){
        employeeServiceImpl.deleteDataById(empId);
        return ResponseEntity.ok("DATA DELETED SUCCESSFULLY");
    }
}
