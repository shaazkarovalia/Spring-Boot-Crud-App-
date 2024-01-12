package com.shaazali.thymeleafmvccrud.dao;

import com.shaazali.thymeleafmvccrud.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//                                                       Entity type  primary key
//                                                            |          |
//                                                            V          V
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    // that's it ... no need to write any code LOL!
    // that's the beauty of Spring Data JPA

    // add a method to sort by last name

    public List<Employee> findAllByOrderByLastNameAsc();

    // INTERESTING NOTE: spring data jpa will parse the method name, looks for a specific format and pattern
    //creates appropriate query... behind the scenes.

}
