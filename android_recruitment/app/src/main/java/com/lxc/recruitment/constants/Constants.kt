package com.lxc.recruitment.constants

import com.lxc.base.http.entity.UserEntity
import com.lxc.recruitment.entity.AdministratorEntity
import com.lxc.recruitment.entity.CompanyEntity
import com.lxc.recruitment.entity.DictEntity
import com.lxc.recruitment.entity.PersonalEntity

object Constants {

    /**
     * 字典
     */
    var DICT: List<DictEntity> = mutableListOf()

    /**
     * 登录用户
     */
    var USER: UserEntity = UserEntity()

    /**
     * 个人信息
     */
    var PERSONAL: PersonalEntity = PersonalEntity()

    /**
     * 公司信息
     */
    var COMPANY: CompanyEntity = CompanyEntity()

    /**
     * 公司信息
     */
    var ADMIN: AdministratorEntity = AdministratorEntity()

    /**
     * 分组
     */
    var LIST  =  mutableMapOf<Long, List<DictEntity>>()
}

