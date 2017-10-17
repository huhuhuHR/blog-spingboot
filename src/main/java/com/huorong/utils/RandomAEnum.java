package com.huorong.utils;

import com.huorong.domain.RandomTookitNameAndIconfont;

/**
 * Created by huorong on 17/10/17.
 */
public class RandomAEnum {
    public static RandomTookitNameAndIconfont randomAEnum() {
        int max = 2;
        int min = 0;
        int Random = (int) Math.round(Math.random() * (max - min + 1)) + min;
        switch (Random) {
        case 0:
            return RandomTookitNameAndIconfont.RABBIT;
        case 1:
            return RandomTookitNameAndIconfont.CAT;
        case 3:
            return RandomTookitNameAndIconfont.INSECT;
        }
        return RandomTookitNameAndIconfont.CAT;
    }
}
