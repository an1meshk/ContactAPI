package com.animesh.contactapi.testsuite;

import com.animesh.contactapi.repo.ContactRepoTest;
import com.animesh.contactapi.service.ContactServiceTest;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.runners.Suite;

/**
 * Created by Animesh Kumar on 10-08-2018.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({ContactServiceTest.class,
        ContactRepoTest.class})
public class ContactAPITestSuite {
}
