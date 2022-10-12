package ru.ibs.framework.basetests;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import ru.ibs.framework.managers.DriverManager;
import ru.ibs.framework.managers.InitManager;
import ru.ibs.framework.managers.PageManager;
import ru.ibs.framework.managers.TestPropManager;

import static ru.ibs.framework.utils.PropConstants.BASE_URL;

public class BaseTests {

    protected PageManager app = PageManager.getPageManager();

    private final DriverManager driverManager = DriverManager.getDriverManager();

    @BeforeClass
    public static void beforeAll() {
        InitManager.initFramework();
    }

    @Before
    public void beforeEach() {
        driverManager.getDriver().get(TestPropManager.getTestPropManager().getProperty(BASE_URL));
    }

    @AfterClass
    public static void afterAll() {
        InitManager.quitFramework();
    }
}
