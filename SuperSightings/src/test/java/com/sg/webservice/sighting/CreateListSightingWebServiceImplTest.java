package com.sg.webservice.sighting;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/test-applicationContext.xml"})
@Transactional
@Rollback
public class CreateListSightingWebServiceImplTest {

    @Test
    public void getCreateListSightingViewModel() {
    }

    @Test
    public void saveCreateSightingCmdModel() {
    }
}