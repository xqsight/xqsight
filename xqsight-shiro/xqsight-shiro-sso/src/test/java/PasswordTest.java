import com.xqsight.common.model.shiro.BaseUserModel;
import com.xqsight.sso.utils.PasswordHelper;
import org.junit.Test;

/**
 * @author wangganggang
 * @date 2017/04/24
 */
public class PasswordTest {

    @Test
    public void testpawd(){
        BaseUserModel baseUserModel = new BaseUserModel();
        baseUserModel.setPassword("admin");
        baseUserModel.setUserName("admin");
        PasswordHelper.encryptPassword(baseUserModel);
        System.out.println(baseUserModel.getSalt());
        System.out.println(baseUserModel.getPassword());
    }
}
