package com.zebone.modules.mobile.doctor.controller;


import com.zebone.common.entity.bd.ou.BdOuUser;
import com.zebone.core.launch.constant.AppConstant;
import com.zebone.core.tool.api.R;
import com.zebone.modules.mobile.bd.ou.service.BdOuUserService;
import com.zebone.modules.mobile.doctor.vo.DoctorVO;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "医生信息", tags = "医生信息")
@RestController
@RequestMapping(AppConstant.APPLICATION_MOBILE_DOCTOR)
public class DoctorController {

    @Autowired
    private BdOuUserService bdOuUserService;

    @GetMapping
    public R<DoctorVO> getDoctor(String code){
        BdOuUser user = bdOuUserService.getUser(code);
        DoctorVO doctorVO = new DoctorVO();
        if(user!=null){
            doctorVO.setName(user.getNameUser());
        }
        return R.data(doctorVO);
    }
}
