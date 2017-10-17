
package com.students.daopattern.mysql;


import com.students.daopattern.dao.PersistException;
import com.students.daopattern.dao.StudentDao;
import com.students.daopattern.dto.Mark;
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
    public void TestDaoConection() {
        Assert.assertNotNull(stud);
    }
    
    @Test
    public void TestCreate() throws PersistException {
        
        List list = stud.getStudSubjMarkAll();
        int oldListSize = list.size();
        Assert.assertTrue(oldListSize > 0);
        stud.createMark(new Mark(5, 5, 5));
        list = stud.getStudSubjMarkAll();
        int newListSize = list.size();
        Assert.assertNotNull(list);
        Assert.assertEquals(1, newListSize - oldListSize);
    }

}
