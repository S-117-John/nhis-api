package com.zebone.modules.mobile.bd.pd.service;

import com.alicp.jetcache.anno.CachePenetrationProtect;
import com.alicp.jetcache.anno.CacheRefresh;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.Cached;
import com.zebone.common.entity.bd.pd.BdPd;
import com.zebone.common.entity.bd.pd.BdPdAs;
import com.zebone.modules.mobile.bd.pd.vo.BdPdVO;

import java.util.List;
import java.util.concurrent.TimeUnit;

public interface BdPdService {

    /**
     * 检索药品信息
     * @param pkPd
     * @return
     */
    BdPdVO getBdPd(String pkPd);

    @Cached(expire = 3600, cacheType = CacheType.REMOTE)
    @CachePenetrationProtect
    List<BdPd> listBdPs(List<String> ids);

    /**
     * 检索药品
     * @param spCode
     * @return
     */
    List<BdPdAs> search(String spCode);


}
