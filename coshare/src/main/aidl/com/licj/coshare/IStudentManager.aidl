// IStudentManager.aidl
package com.licj.coshare;

// Declare any non-default types here with import statements
import com.licj.coshare.Student;

interface IStudentManager {
    List<Student> getStudentList();
    void addStudent(in Student student);
}
