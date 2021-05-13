import com.lxz.crodw.entity.Admin;
import com.lxz.crodw.entity.Role;
import com.lxz.crowd.mapper.AdminMapper;
import com.lxz.crowd.mapper.RoleMapper;
import com.lxz.crowd.service.api.AdminService;
import com.lxz.crowd.service.api.RoleService;
import com.lxz.crowd.util.CrowdUtile;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

//spring整合junit
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-persist-mybatis.xml","classpath:spring-persist-tx.xml"})
public class crownTest {
    @Autowired
    private DataSource source;
    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private AdminService adminService;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private RoleService roleService;
    @Test
    public void testCrowd() throws SQLException {
        Connection connection = source.getConnection();
            Admin admin = new Admin(null, "lxz1", "lxz1", "吕先正", "@qq.com", null);
//            adminService.saveAdmin(admin);
            adminMapper.insert(admin);
        }

    @Test
    public void testLog(){
        // 获取日志记录对象
        Logger logger = LoggerFactory.getLogger(crownTest.class);
// 按照Debug 级别打印日志
        logger.debug("admin.toString()");
        logger.debug("admin.toString()");
        logger.debug("admin.toString()");
        logger.info("info.toString()");
        logger.info("info.toString()");
        logger.info("info.toString()");
        logger.warn("info.toString()");
        logger.warn("info.toString()");
        logger.warn("info.toString()");
        logger.error("info.toString()");
        logger.error("info.toString()");
        logger.error("info.toString()");
    }
    @Test
    public void testRole() throws SQLException {
        Connection connection = source.getConnection();
        for (int i = 0; i <200 ; i++) {
            roleMapper.insert(new Role(null,"lxz"+i));

        }

    }
}
