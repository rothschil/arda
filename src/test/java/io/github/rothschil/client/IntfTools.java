package io.github.rothschil.client;


import io.github.rothschil.common.intf.IntfConfEntity;

public class IntfTools {

    /**
     * @author <a href="mailto:WCNGS@QQ.COM">Sam</a>
     * @param url   地址自己传
     * @return IntfConfEntity
     **/
    public static IntfConfEntity building(String url) {
        IntfConfEntity intfConf = new IntfConfEntity();
        intfConf.setInterfaceName("IntfTools");
        intfConf.setAddress(url);
        intfConf.setMethodName("POST");
        intfConf.setTimeout(15000);
        intfConf.setHeaderInfo("{'X-APP-ID':'96a13df75d448a7d5d9cf89dcb73fa76','X-APP-KEY':'d5bf0696c8a866369b5e379263f51087','x-auth-token':'eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJkMjB.ivr'}");
        return intfConf;
    }

}
