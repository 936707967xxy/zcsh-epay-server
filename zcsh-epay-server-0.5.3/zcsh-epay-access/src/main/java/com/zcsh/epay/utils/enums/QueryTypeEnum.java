package com.zcsh.epay.utils.enums;


/**
 * 作者：Administrator <br>
 * 创建时间：2018年6月14日 <br>
 * 描述：系统查询类型
 */
public enum QueryTypeEnum {

	QUERY_TYPE_WP("wp","待付款订单查询"),
	QUERY_TYPE_WG("wg","待收货订单查询"),
	QUERY_TYPE_AO("ao","全部订单查询")
	;
	
	private String mess ;
    private String code ;
     
    private QueryTypeEnum( String code,String mess  ){
        this.mess = mess ;
        this.code = code ;
    }
    
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMess() {
		return mess;
	}

	public void setMess(String mess) {
		this.mess = mess;
	}
	
	public static String getName(String index) {
        for (QueryTypeEnum c : QueryTypeEnum.values()) {
            if (c.getCode() == index) {
                return c.mess;  
            }
        }
        return null;
    } 
}
