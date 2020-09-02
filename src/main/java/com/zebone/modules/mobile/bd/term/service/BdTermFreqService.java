package com.zebone.modules.mobile.bd.term.service;

import com.alicp.jetcache.anno.CachePenetrationProtect;
import com.alicp.jetcache.anno.CacheRefresh;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.Cached;
import com.zebone.common.entity.bd.term.BdTermFreq;

import java.util.List;
import java.util.concurrent.TimeUnit;

public interface BdTermFreqService {

    @Cached(expire = 3600, cacheType = CacheType.REMOTE)
    @CacheRefresh(refresh = 1800, stopRefreshAfterLastAccess = 3600, timeUnit = TimeUnit.SECONDS)
    @CachePenetrationProtect
    List<BdTermFreq> list();

    BdTermFreq findByCode(String code);
}
