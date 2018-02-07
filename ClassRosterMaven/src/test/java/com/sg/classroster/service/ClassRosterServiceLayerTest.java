package com.sg.classroster.service;

import com.sg.classroster.dao.ClassRosterAuditDao;
import com.sg.classroster.dao.ClassRosterAuditDaoStubImpl;
import com.sg.classroster.dao.ClassRosterDao;
import com.sg.classroster.dao.ClassRosterDaoStubImpl;
import com.sg.classroster.dto.Student;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ClassRosterServiceLayerTest {

    private ClassRosterServiceLayer service;

    public ClassRosterServiceLayerTest() {
        ClassRosterDao dao = new ClassRosterDaoStubImpl();
        ClassRosterAuditDao auditDao = new ClassRosterAuditDaoStubImpl();
        service = new ClassRosterServiceLayerImpl(dao, auditDao);
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testCreateStudent() throws Exception {
        Student student = new Student("0002");
        student.setFirstName("Sally");
        student.setLastName("Smith");
        student.setCohort("Java January 2015");
        service.createStudent(student);
    }

    @Test
    public void testCreateStudentDuplicateID() throws Exception{
        Student student = new Student("0001");
        student.setFirstName("Sally");
        student.setLastName("Smith");
        student.setCohort("Java January 2015");
        try {
            service.createStudent(student);
            fail("Expected ClassRosterDuplicateIDException was not thrown");
        } catch (ClassRosterDuplicateIdException e) {
            return;
        }

    }

    @Test
    public void testCreateStudentInvalidData() throws Exception {
        Student student = new Student("0002");
        student.setFirstName("");
        student.setLastName("Smith");
        student.setCohort("Java January 2015");

        try {
            service.createStudent(student);
            fail("Expected ClassRosterDataValidationException was not thrown");
        } catch (ClassRosterDataValidationException e){
            return;
        }
    }

    @Test
    public void testGetAllStudents() throws Exception {
        assertEquals(1, service.getAllStudents().size());
    }

    @Test
    public void testGetStudent() throws Exception {
        Student student = service.getStudent("0001");
        assertNotNull(student);
        student = service.getStudent("9999");
        assertNull(student);
    }

    @Test
    public void removeStudent() throws Exception {
        Student student = service.removeStudent("0001");
        assertNotNull(student);
        student = service.removeStudent("9999");
        assertNull(student);
    }
}