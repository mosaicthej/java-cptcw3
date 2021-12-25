/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cpt105.cw3.partA;;
import java.util.Scanner;
import java.util.ArrayList;
/**
 *
 * @author
 */
public class Q3 {
    public static void addStudent(ArrayList Students, int stuId, String name, String gender){
        Students.add(stuId);
        Students.add(name);
        Students.add(gender);
    }
    public static void addCourse(ArrayList Courses, String courseName, int courseId){
        Courses.add(courseName);
        Courses.add(courseId);    
    }
    public static void addTeacher(ArrayList Teachers, String loginName, int teacherId, String password){
        Teachers.add(loginName);
        Teachers.add(teacherId);    
        Teachers.add(password);
    }
    public static void addGrade(ArrayList Grade, int stuId, int courseId, int grade){
        Grade.add(stuId);
        Grade.add(courseId);    
        Grade.add(grade);
    }
    public static void listStudents(ArrayList Students){
        if(Students.size()==0){
            System.out.println("There is no student");
            System.out.println("press any keyto continue");
        }else{
            for(int i =0; i < Students.size();i++){
            System.out.println(Students.get(i));
        }
        }
    }
    public static void listTeachers(ArrayList Teachers){
        if(Teachers.size()==0){
            System.out.println("There is no teacher");
            System.out.println("press any keyto continue");
        }else{
            for(int i =0; i < Teachers.size();i++){
            System.out.println(Teachers.get(i));
        }
        }
    }
    public static void listCourses(ArrayList Courses){
        if(Courses.size()==0){
            System.out.println("There is no course");
            System.out.println("press any keyto continue");
        }else{
            for(int i =0; i < Courses.size();i++){
            System.out.println(Courses.get(i));
        }
        }
    }
    public static void listGrade(ArrayList Grade){
        if(Grade.size()==0){
            System.out.println("There is no grade");
            System.out.println("press any keyto continue");
        }else{
            for(int i =0; i < Grade.size();i++){
            System.out.println(Grade.get(i));
        }
        }
    }
    
    public static void main(String[] args){
        ArrayList students = new ArrayList();
        ArrayList teachers = new ArrayList<String>();
        ArrayList grade = new ArrayList<String>();
        ArrayList courses = new ArrayList<String>();
        System.out.println("***** Operation Menu ******");
        System.out.println("1: add a student");
        System.out.println("2: add a course");
        System.out.println("3: add a teacher");
        System.out.println("4: add a grade");
        System.out.println("5: list all student");
        System.out.println("6: list all courses");
        System.out.println("7: list all teachers");
        System.out.println("8: list all grades");
        System.out.println("9: search a student");
        System.out.println("10: search a course");
        System.out.println("11: search a teacher");
        System.out.println("12: search a grade");
        System.out.println("0: exit the program");
        System.out.println("Please input a number to run the program");
        Scanner sc = new Scanner(System.in);
        Scanner c1 = new Scanner(System.in);
        Scanner Id = new Scanner(System.in);
        Scanner Name = new Scanner(System.in);
        Scanner Gender = new Scanner(System.in);
        int choice = sc.nextInt();
        switch(choice){
        case 1:
            System.out.println("Please input student information (input '-1' to the end):");
            if(c1.nextInt()==-1){
                break;
            }else{
                int stuId = Id.nextInt();
                String name = Name.nextLine();
                String gender = Gender.nextLine();
                Q3.addStudent(students, stuId, name, gender);                              
            }
        case 2:
            
        break;
        case 3:
            
        break;
        case 4:
            
        break;
        case 5:
            
        break;
        case 6:
            
        break;
        case 7:
            
        break;
        case 8:
            
        break;
        case 9:
            
        break;
        case 10:
            
        break;
        case 11:
            
        break;
        case 12:
            
        break;
        case 0:
            
        break;
        default:
    }
    }
}
