package com.zebone.modules.mobile.cn.support;

import com.zebone.modules.mobile.cn.vo.CnOrderVO;

import java.util.List;
import java.util.Map;

/**
 * 排序设置标志工具类
 * 
 */
public  class SortByOrdUtil {
	
		public void ordGroup(List<CnOrderVO> list){
			if(list == null || list.size() == 0){
				return;
			}
			int size = list.size();
			if(size == 2 && this.isSameGroup(list.get(0), list.get(1))){
				setSign(list.get(0), getSign(1));
				setSign(list.get(1), getSign(3));
				return;
			}
			for(int i = 1 ; i < size-1 ; i++){
				CnOrderVO vo_before = list.get(i-1);
				CnOrderVO vo = list.get(i);
				CnOrderVO vo_next = list.get(i+1);
				//设置当前元素的标志
				if(isSameGroup(vo_before, vo)){
					//设置第一个元素的标志
					if(i == 1){
						setSign(vo_before, getSign(1));
					}
					if(isSameGroup(vo_next, vo)){
						setSign(vo, getSign(2));
						//设置最后一个元素的标志
						if(i == size - 2){
							setSign(vo_next, getSign(3));
						}
					}else{
						setSign(vo, getSign(3));
					}
				}else if(isSameGroup(vo_next, vo)){
					setSign(vo, getSign(1));
					//设置最后一个元素的标志
					if(i == size - 2){
						setSign(vo_next, getSign(3));
					}
				}
			}
		}
		
		protected String getSign(int i){
			if(i == 1)
				return "┌";
			else if(i == 2)
				return "|";
			else
				return "└";
		}
	protected boolean isSameGroup(CnOrderVO vo_before, CnOrderVO vo) {
		return (vo.getOrdsnParent()).equals(vo_before.getOrdsnParent());
	}

	protected void setSign(CnOrderVO o, String sign) {
		o.setSign(sign);
	}
}
