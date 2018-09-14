import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by fengjinman Administrator on 2018/6/14.
 *
 * 这个爬虫的代码用的java自带的类，不需要导入任何包
 */
public class Analyze {

    public HouseInfo regexMain() {

        String url = Const.url + Const.uid;
        String result = ConnectionUtil.Connect(url);
        HouseInfo h = getHouseInfo(result);
        h.setUid(Const.uid);
        return h;
    }

    private static Pattern imgpattern=Pattern.compile("<img class=\"img-large\".*\" />");
    private static Pattern pattern1 = Pattern.compile("fcg\">\\w*.+\\s*<dd>\\w*.+</dd>");
    private static Pattern pattern2 = Pattern.compile("f24 fcr\">[0-9]+");
    private static Pattern pattern3 = Pattern.compile("房屋类型：</dt>\\s*<dd>[\\u4e00-\\u9fa5]+( *[\\u4e00-\\u9fa5]+)*");
    private static Pattern pattern4 = Pattern.compile("建筑类型：</dt>\\s*<dd>[\\u4e00-\\u9fa5]+( *[\\u4e00-\\u9fa5]+)*");
    private static Pattern pattern5 = Pattern.compile("建筑年代：</dt>\\s*<dd>[0-9]+");
    private static Pattern pattern6 = Pattern.compile("容积率：</dt>\\s*<dd>[0-9]+\\.[0-9]+");
    private static Pattern pattern7 = Pattern.compile("物业公司：</dt>\\s*<dd>[\\u4e00-\\u9fa5]+( *[\\u4e00-\\u9fa5]+)*");
    private static Pattern pattern8 = Pattern.compile("开发商：</dt>\\s*<dd>[\\u4e00-\\u9fa5]+( *[\\u4e00-\\u9fa5]+)*");
    private static Pattern pattern9 = Pattern.compile("物业费：</dt>\\s*<dd>[0-9]+\\.[0-9]+");


    private HouseInfo getHouseInfo(String targetStr) {
        //houseInfo这个对象就是用来存放我们需要的信息
        HouseInfo houseInfo=new HouseInfo();

        //提取图片url 分析字符串中的字符是否满足图片标签的正则匹配，匹配到了就截取图片
        //todo 现在如果有多个图片，会覆盖之前获得的图片
        Matcher imgmatcher=imgpattern.matcher(targetStr);
        while (imgmatcher.find()){
            String imgString=imgmatcher.group();
            int n=imgString.lastIndexOf("=\"");
            String imgUrl=imgString.substring(n+2,imgString.length()-3);
            houseInfo.setImgUrl(imgUrl);
        }
        //首先提取出包含房产信息的html片段，再分别处理
        Matcher matcher1 = pattern1.matcher(targetStr);
        String info = "";
        while (matcher1.find()) {
            info = matcher1.group();
            Matcher matcher2 = pattern2.matcher(info);
            while (matcher2.find()) {
                String price = matcher2.group().substring(9);
                houseInfo.setPrice(price);
            }
            Matcher matcher3 = pattern3.matcher(info);
            String houseType = "";            //可能有的没有
            while (matcher3.find()) {
                int n = matcher3.group().lastIndexOf(">");
                houseType = matcher3.group().substring(n + 1);
            }
            houseInfo.setHouseType(houseType);
            //建筑类型同理
            Matcher matcher4 = pattern4.matcher(info);
            String blockType = "";            //可能有的
            while (matcher4.find()) {
                int n = matcher4.group().lastIndexOf(">");
                blockType = matcher4.group().substring(n + 1);
            }
            houseInfo.setBlockType(blockType);
            //建筑年代
            Matcher matcher5 = pattern5.matcher(info);
            String blockAge = "";            //可能有的没有
            while (matcher5.find()) {
                int n = matcher5.group().lastIndexOf(">");
                blockAge = matcher5.group().substring(n + 1);
            }
            houseInfo.setBlockAge(blockAge);
            //容积率
            Matcher matcher6 = pattern6.matcher(info);
            String FAR = "";            //可能有的没有
            while (matcher6.find()) {
                int n = matcher6.group().lastIndexOf(">");
                FAR = matcher6.group().substring(n + 1);
            }
            houseInfo.setFAR(FAR);
            //物业公司
            Matcher matcher7 = pattern7.matcher(info);
            String pManage = "";            //可能有的没有
            while (matcher7.find()) {
                int n = matcher7.group().lastIndexOf(">");
                pManage = matcher7.group().substring(n + 1);
            }
            houseInfo.setPManage(pManage);
            //开发商
            Matcher matcher8 = pattern8.matcher(info);
            String company = "";            //可能有的没有
            while (matcher8.find()) {
                int n = matcher8.group().lastIndexOf(">");
                company = matcher8.group().substring(n + 1);
            }
            houseInfo.setCompany(company);
            //物业费
            Matcher matcher9 = pattern9.matcher(info);
            String fee = "";            //可能有的没有
            while (matcher9.find()) {
                int n = matcher9.group().lastIndexOf(">");
                fee = matcher9.group().substring(n + 1);
            }
            houseInfo.setFee(fee);
        }
        return houseInfo;
    }



}