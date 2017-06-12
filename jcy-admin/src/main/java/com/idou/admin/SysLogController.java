package com.idou.admin;

import com.idou.entity.SysLogEntity;
import com.idou.service.SysLogService;
import com.idou.util.PageUtils;
import com.idou.util.Query;
import com.idou.util.R;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 系统日志
 *
 * @author zhangSi
 * @email: andy_si@163.com
 * @create 2017-04-10 上午 10:23
 **/
@RestController
@RequestMapping("/sys/log")
public class SysLogController {
    @Autowired
    private SysLogService sysLogService;

    /**
     * @return
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:log:list")
    public R list(@RequestParam Map<String, Object> params) {
        Query query = new Query(params);
        List<SysLogEntity> sysLogList = sysLogService.queryList(query);
        int total = sysLogService.queryTotal(query);
        PageUtils pageUtil = new PageUtils(sysLogList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }
}
