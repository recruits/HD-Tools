package com.chilicool.hdtools.support;

import java.text.DecimalFormat;

/**
 * @author chilicool zhangzechen@cttic.cn
 * @Description: <p></p>
 * @Date 2018/3/9 19:35
 * @Version V1.0
 */
public class DigitalUtil {
    private static final DecimalFormat dFormat = new DecimalFormat("0.00");

    /**
     * @Description: <p>格式化double为小数点后两位</p>
     * @author chilicool zhangzechen@cttic.cn
     */
    public static Double f2bit(Double val) {
        return null != val ? Double.valueOf(format(val.toString())) : val;
    }

    private static String format(String val) {
        if (!"".equals(val)) {
            return dFormat.format(val);
        }
        return val;
    }
}
