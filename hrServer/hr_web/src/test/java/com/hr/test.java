package com.hr;




import com.hr.utils.JWTConfigProperties;
import com.hr.utils.JWTUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
import java.util.UUID;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringBootTest(classes = Application.class)
public class test {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JWTConfigProperties properties;

    @Test
    public void testGetStudent() {

        // studentMapper.addStudent(student);
        //System.out.println(studentMapper.getAllStudentByScore(100D,200D));

        //System.out.println(studentMapper.getAllStudentByClass("软件一班"));
        //System.out.println(studentMapper.getAllStudentByName("lihua"));
        //System.out.println(studentMapper.updateByPrimaryKey(student));
        //System.out.println(studentMapper.addStudent(student));
        //System.out.println(studentMapper.deleteByName("lihua"));
        //System.out.println(studentMapper.getAllStudent());
    }

    @Test
    public void test() throws ParseException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //System.out.println(passwordEncoder.encode("@Xwf123"));
        //System.out.println(UUID.randomUUID().toString().substring(0,10));
        //System.out.println(new Timestamp((new Date().getTime())));
        //System.out.println(studentMapper.queryStudent(1,8).getContent());
    }
}
