package com.xuhaoming.hamidemo.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xuhaoming.hamidemo.mapper.HamiOrderMapper;
import com.xuhaoming.hamidemo.entity.HamiOrder;
import com.xuhaoming.hamidemo.service.HamiOrderService;
/**
 * @author xuhaoming
 * @date 2021/10/2
 */  
@Service
public class HamiOrderServiceImpl extends ServiceImpl<HamiOrderMapper, HamiOrder> implements HamiOrderService{

}
