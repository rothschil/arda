package xyz.wongs.drunkard.alipay;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.FileItem;
import com.alipay.api.domain.AlipayOpenOperationOpenbizmockBizQueryModel;
import com.alipay.api.domain.AlipayTradePayModel;
import com.alipay.api.request.AlipayOfflineMaterialImageUploadRequest;
import com.alipay.api.request.AlipayOpenOperationOpenbizmockBizQueryRequest;
import com.alipay.api.request.AlipayTradePayRequest;
import com.alipay.api.response.AlipayOfflineMaterialImageUploadResponse;
import com.alipay.api.response.AlipayTradePayResponse;

public class Main {

    static String APP_PRIVATE_KEY="MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCFuMidecvpZ2sj4i+siBh7LdLWh/rBYlZ4n0Pve3tCeOvnF2Q0r03uOxsJ2DlgD1QaD3WtM7hRRGAOYIfxerC+hdfCa/0W8NfUVL30t7j6GSVIRbgLV4LVtsCBQbLGQedhNn+o51+uDkTuVvDFobRxAbkuO7MwlbE6vXXiRCSy/l01FsH30tuRrrd0jYeqVzfIo30RDaPMn6wlwgcjopMjbYpN3zB8Iy+XDs/2hGWOEz0RUPoSAXcI6Jru3C7Xv4aPlxrKIbs9abZQcxWxxJq5BQHffNIXwzK3cvba6wcW73G2+Ao02iZcRQFqbEifAk1np8V2E8lj1n06EFpTjNotAgMBAAECggEAS7BO5Uu2TpN6AmKZh3e1G6vRRhrLpUArH7VON7RWDUbyh+gmkOeVHb4YzNa1ixjCCsorWnEG6ADiueCLsfQ3LHAHswV6yMxj7cxVhLmlGvK2tpS32rua06rQhZkRPsS42b6GPa1Q6+0puRo+yC6COuC112R1RY/FNDFfE0Zs217rGCGgXeT4VWpNOWQdc9aw1folrUnzCmbjdbkVzLgjo46Jlnd9j33HmMGi+22bEDXcueng5Li8lNy1k3Pm75URYmso9Ey6R5Fvi5ms3nfJHzOFwsfp6lvOXY89KKsQiHbMCwDqf4ed2csZjgsV0/IJS52a15pcWadeAbU8dRSRJQKBgQC8bvZl43VdHJ3ZtyGEab5ChgCd2zu3xoei+y5hJf+gbES6j5IhBjqwVwzXB0gWlyjN0HjPIIWBcaGUPcvpWjYZ3saVXyKFa/Uwbi44ehMlFuZoqoNUeX+PHB0IUp8ehwNUtrTsM+TsalT5pYENzNkNumUBhStOHxXV3bJ+FCtdOwKBgQC1q6CypskfnkvRlssnWlL9MStdwShmDGNl2iO22Efe2VjEVe0+c3LyxbzPdTYfIG1PMuLRUDLmsapmf/jW1Uyh05mHoxCAKasTgM1FHvt90zlZB9REiVk94zGVyJt1ChQDNKnL88olfLGH2r/1y4Cdw2TFdvu1ZbNpplbDx9hPtwKBgQCTcbwp08bVGfxGDK8UXm26vgIjgxWCYr1lgWg0KjiQ/cb1uDvezXIE48ivRQpaauNJ0LOHV2o/9Hs7dPNm3+jva0s2PF/VyLjtsXlHRfZPElEuWyYyZnOytovdJFFP4NNxaRm+rYiWmUluotuNwdmhjVVV0C8njNlYJIZzczLPWwKBgDhlnFB5mGw5Xv4kmtKPpr21GupTAXCUnfLLglTL1ZvnyzfAGRNPEbsuCYw5reCB141Dlcg25mZZdzW0upIuy0hdgQbrZz7yMIqwbIjtslMAVIg/9vSD0vF0azs9eI0f4NCR87g96V4z1IUBMqBiWX4gX1Mxubg5ZzYADmVez9cHAoGAZST7g1GN/l9hxfWcrGdW6Axe+uJk1IGjMy17upCzNMIgVO45RStDUaQ2s698S9BNJysMIvVBdh7jzAvbXQKrzckjkJejYcMSs1pgqIPRV8XeRCp27vaE6gYn480X7PoaCYaDwQGYGfaXrmKSRKEMa7U0M8gWZyyl00Ndb/9dgoM=";
    static String ALIPAY_PUBLIC_KEY="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAiQf8O29ex7AD5MLPSQvkMUeug7bccqftE13IhVpuhH24EZEorKoVrdB3SrVpbxb3GSqad2YYxmEovyPmQtXpscyYUNJHv3+P/lxAF9sh4gqFMGIj6CleN+pswd53t9aFwoU409AGqWC2DocGtqyb9xr3h52S2+B3f7DJotHTu+O+S/koiqLjZa+OzhKkSSzuXXGX1Erg2lZVY+sh0DkSHGm6VRb7GkK4dTlJ7Pe+2WNtpJE7hePQjnJvbWu2O7SgVJrl9sFeeOvxgnUHhHE4cEqynkqUQOL6oZl2WpatdMZoqH2puDCczcynzqqmLIb3dHpHcYKhmmzcPpSv+dCLDQIDAQAB";
    static String APP_ID = "2021000118618985";
    static String SERVER_URL="https://openapi.alipaydev.com/gateway.do";
    static String CHARSET ="utf-8";
    static AlipayClient ALIPAY_CLIENT = new DefaultAlipayClient(SERVER_URL, APP_ID, APP_PRIVATE_KEY, "json", CHARSET, ALIPAY_PUBLIC_KEY, "RSA2");


    public static void main(String[] args) {
//        try {
//
//            // 1. 创建AlipayClient实例
//            AlipayClient alipayClient = new DefaultAlipayClient(SERVER_URL, APP_ID, APP_PRIVATE_KEY, "json", CHARSET, ALIPAY_PUBLIC_KEY, "RSA2");
//            // 2. 创建使用的Open API对应的Request请求对象
//            AlipayOpenOperationOpenbizmockBizQueryRequest request = getRequest();
//            // 3. 发起请求并处理响应
//            AlipayOpenOperationOpenbizmockBizQueryResponse response = alipayClient.execute(request);
//            if (response.isSuccess()) {
//                System.out.println("调用成功。");
//            } else {
//                System.out.println("调用失败，原因：" + response.getMsg() + "，" + response.getSubMsg());
//            }
//        } catch (Exception e) {
//            System.out.println("调用遭遇异常，原因：" + e.getMessage());
//            throw new RuntimeException(e.getMessage(), e);
//        }

//        uploadImage();
        buy();
    }


    private static AlipayOpenOperationOpenbizmockBizQueryRequest getRequest() {
        // 初始化Request，并填充Model属性。实际调用时请替换为您想要使用的API对应的Request对象。
        AlipayOpenOperationOpenbizmockBizQueryRequest request = new AlipayOpenOperationOpenbizmockBizQueryRequest();
        AlipayOpenOperationOpenbizmockBizQueryModel model = new AlipayOpenOperationOpenbizmockBizQueryModel();
        request.setBizContent("  {" +
                "    \"primary_industry_name\":\"IT科技/IT软件与服务\"," +
                "    \"primary_industry_code\":\"10001/20102\"," +
                "    \"secondary_industry_code\":\"10001/20102\"," +
                "    \"secondary_industry_name\":\"IT科技/IT软件与服务\"" +
                " }");
        return request;
    }

    public static void uploadImage(){

        // 实例化具体API对应的request类,类名称和接口名称对应，当前调用接口名称：alipay.offline.material.image.upload
        AlipayOfflineMaterialImageUploadRequest request = new AlipayOfflineMaterialImageUploadRequest();
        request.setImageName("test");
        //Windows请填写绝对路径，不支持相对路径；Linux支持相对路径
        FileItem item = new FileItem("D:/Alipay/Images/2.jpg");
        request.setImageType("JPG");
        request.setImageContent(item);
        //执行API请求
        try {
            AlipayOfflineMaterialImageUploadResponse response = ALIPAY_CLIENT.execute(request);
            //调用成功，则处理业务逻辑
            if(response.isSuccess()){
                //获取图片访问地址
                String imageUrl = response.getImageUrl();
                System.out.println("调用成功。");
                            } else {
                System.out.println("调用失败，原因：" + response.getMsg() + "，" + response.getSubMsg());
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
    }

    public static void buy(){
        AlipayTradePayRequest request = new AlipayTradePayRequest();
        AlipayTradePayModel model = new AlipayTradePayModel();
        request.setBizModel(model);

        model.setOutTradeNo(System.currentTimeMillis()+"");
        model.setSubject("Iphone13 Pro 128G");
        model.setTotalAmount("7899.00");
        //沙箱钱包中的付款码
        model.setAuthCode("285023030960502645");
        model.setScene("bar_code");

        AlipayTradePayResponse response = null;
        try {
            response = ALIPAY_CLIENT.execute(request);
            System.out.println(response.getBody());
            System.out.println(response.getTradeNo());
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
    }
}
