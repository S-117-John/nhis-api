package com.zebone.modules.mobile.bd.ou.service;

import com.alicp.jetcache.anno.CachePenetrationProtect;
import com.alicp.jetcache.anno.CacheRefresh;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.Cached;
import com.zebone.common.entity.bd.ou.BdOuUser;

import java.util.concurrent.TimeUnit;

public interface BdOuUserService {


    @Cached(expire = 3600, cacheType = CacheType.REMOTE)
    BdOuUser getUser(String code);
}
