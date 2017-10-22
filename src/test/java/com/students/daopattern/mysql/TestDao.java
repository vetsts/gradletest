package com.students.daopattern.mysql;

import com.students.daopattern.dao.StudentDao;
import com.students.daopattern.dto.Mark;
import com.students.daopattern.dto.TestMark;
import java.util.List;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestDao {

    private static StudentDao stud = null;

    @BeforeClass
    public static void connectDao() throws Exception {
        stud = new MySqlStudentDao("test");
    }

    @AfterClass
    public static void closeDao() throws Exception {
        stud.close();
    }

    @Test
    public void testDaoConection() {
        Assert.assertNotNull(stud);
    }

    @Test
    public void testCreate() throws Exception {
        List list = stud.getStudSubjMarkAll();
        int oldListSize = list.size();
        Assert.assertTrue("Размер должен быть > 0", oldListSize > 0);
        stud.createMark(new Mark(5, 5, 5));
        list = stud.getStudSubjMarkAll();
        int newListSize = list.size();
        Assert.assertNotNull("Новый список не должен быть пустым", list);
        Assert.assertEquals("Размер списка должен быть равен 1", 1, newListSize - oldListSize);
    }

    @Test
    public void testUpdateSubject() throws Exception {
        stud.updateSubject(5, "Информатика");
        TestMark tm = stud.getTestSubject(5);
        String subject = tm.getSubject();
        Assert.assertEquals("Название предмета должно быть Информатика", "Информатика", subject);
    }

    @Test
    public void testSelectStudents() throws Exception {
        List list = stud.getStudents();
        Assert.assertNotNull("Список студентов не должен быть пустым", list);
    }

    @Test
    public void testDeleteMark() throws Exception {
        List list = stud.getStudSubjMarkAll();
        int oldListSize = list.size();
        Assert.assertTrue("Размер должен быть > 0", oldListSize > 0);
        stud.delete(new Mark(22, 5, 5, 5));
        list = stud.getStudSubjMarkAll();
        int newListSize = list.size();
        Assert.assertNotNull("Новый список не должен быть пустым", list);
        Assert.assertEquals("Размер списка должен быть равен 1", 1, oldListSize - newListSize);
    }

}
