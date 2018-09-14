import lombok.Data;

/**
 * Created by fengjinman Administrator on 2018/6/14.
 */
@Data
public class HouseInfo {

    private String uid;
    private String price;
    private String houseType;
    private String blockType;
    private String blockAge;
    private String FAR;
    private String fee;
    private String pManage;
    private String company;
    private String imgUrl;


    @Override
    public String toString() {
        return "HouseInfo{" +
                "uid='" + uid + '\'' +
                ",\n price='" + price + '\'' +
                ",\n houseType='" + houseType + '\'' +
                ",\n blockType='" + blockType + '\'' +
                ",\n blockAge='" + blockAge + '\'' +
                ",\n FAR='" + FAR + '\'' +
                ",\n fee='" + fee + '\'' +
                ",\n pManage='" + pManage + '\'' +
                ",\n company='" + company + '\'' +
                ",\n imgUrl='" + imgUrl + '\'' +
                '}';
    }
}