package generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import generator.domain.Stamp;
import generator.service.StampService;
import generator.mapper.StampMapper;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【stamp(邮票)】的数据库操作Service实现
* @createDate 2022-11-03 22:30:18
*/
@Service
public class StampServiceImpl extends ServiceImpl<StampMapper, Stamp>
    implements StampService{

}



