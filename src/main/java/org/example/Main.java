package org.example;

import org.example.entity.Employee;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        LinkedList<Employee> employees = new LinkedList<>();
        employees.add(new Employee(1, "Onur", "Demirtaş"));
        employees.add(new Employee(1, "Onur", "Demirtaş"));
        employees.add(new Employee(2, "Şevval", "Taşkın"));
        employees.add(new Employee(2, "Şevval", "Taşkın"));
        employees.add(new Employee(3, "Furkan", "Salman"));
        employees.add(new Employee(3, "Furkan", "Salman"));
        employees.add(new Employee(4, "Emre", "Duman"));

        List<Employee> duplicates = findDuplicates(employees);
        Map<Integer, Employee> uniques = findUniques(employees);
        List<Employee> noDuplicates = removeDuplicates(employees);

        System.out.println("Duplicate count: " + duplicates.size());
        System.out.println("Unique map size: " + uniques.size());
        System.out.println("Only single records: " + noDuplicates.size());
    }

    public static List<Employee> findDuplicates(List<Employee> input) {
        HashMap<Integer, Integer> counts = new HashMap<>();
        HashMap<Integer, Employee> firstById = new HashMap<>();
        LinkedList<Employee> duplicates = new LinkedList<>();

        for (Employee employee : input) {
            if (employee == null) {
                continue;
            }
            Integer id = employee.getId();
            counts.put(id, counts.getOrDefault(id, 0) + 1);
            firstById.putIfAbsent(id, employee);
            if (counts.get(id) == 2) {
                duplicates.add(firstById.get(id));
            }
        }

        return duplicates;
    }

    public static Map<Integer, Employee> findUniques(List<Employee> input) {
        HashMap<Integer, Employee> result = new HashMap<>();

        for (Employee employee : input) {
            if (employee == null) {
                continue;
            }
            result.putIfAbsent(employee.getId(), employee);
        }

        return result;
    }

    public static List<Employee> removeDuplicates(List<Employee> input) {
        HashMap<Integer, Integer> counts = new HashMap<>();
        HashMap<Integer, Employee> firstById = new HashMap<>();
        LinkedList<Employee> result = new LinkedList<>();

        for (Employee employee : input) {
            if (employee == null) {
                continue;
            }
            Integer id = employee.getId();
            counts.put(id, counts.getOrDefault(id, 0) + 1);
            firstById.putIfAbsent(id, employee);
        }

        for (Map.Entry<Integer, Integer> entry : counts.entrySet()) {
            if (entry.getValue() == 1) {
                result.add(firstById.get(entry.getKey()));
            }
        }

        return result;
    }
}
