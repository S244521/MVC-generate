package packageCode.service.impl;

import packageCode.entity.TabEntity;
import packageCode.mapper.TabMapper;
import packageCode.service.TabService;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;


@Service
public class TabServiceImpl extends ServiceImpl<TabMapper,TabEntity> implements TabService {

}