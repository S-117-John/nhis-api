package com.zebone.modules.mobile.bd.unit.service;

import com.alicp.jetcache.anno.CachePenetrationProtect;
import com.alicp.jetcache.anno.CacheRefresh;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.Cached;
import com.zebone.common.entity.bd.unit.BdUnit;

import java.util.List;
import java.util.concurrent.TimeUnit;

public interface BdUnitService {

    @Cached(expire = 3600, cacheType = CacheType.REMOTE)
    @CacheRefresh(refresh = 1800, stopRefreshAfterLastAccess = 3600, timeUnit = TimeUnit.SECONDS)
    @CachePenetrationProtect
    List<BdUnit> listBdUnit();
}
