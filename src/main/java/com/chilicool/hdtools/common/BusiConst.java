package com.chilicool.hdtools.common;

/**
 * Created by chilicool on 2017/9/29.
 */
public class BusiConst {
    public interface SelType {
        String RADIO = "radio";
        String CHECKBOX = "checkbox";
    }

    public interface DobuleVal {
        Double zeroVal = new Double(0);
        Double oneVal = new Double(1);
        Double handurdVal = new Double(100);
    }

    public interface Action{
        String ADD = "add";
        String MOD = "mod";
    }

    // 项目阶段
    public interface ProjPhase {
        String IDEA = "0";
        String SCHEMA = "1";
        String DESIGN = "2";
        String IMPLEMENT = "3";
    }
}
