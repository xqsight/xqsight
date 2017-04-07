import com.xqsight.cms.model.CmsAd;
import com.xqsight.cms.service.CmsAdService;
import com.xqsight.common.base.controller.BaseController;
import org.junit.Test;

/**
 * @author wangganggang
 * @date 2017/04/07
 */

public class BaseControllerTest {

    @Test
    public void test(){
        BaseController<CmsAdService,CmsAd,Long> baseController = new BaseController();
    }
}
