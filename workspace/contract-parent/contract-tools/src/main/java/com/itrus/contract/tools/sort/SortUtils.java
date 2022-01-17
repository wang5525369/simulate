package com.itrus.contract.tools.sort;

import com.baomidou.mybatisplus.extension.api.R;
import org.apache.poi.ss.formula.functions.T;

import java.text.Collator;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SortUtils {

    List<T> sortList(List<T> listT){
        ChinaComparator chinaComparator = new ChinaComparator();
        //CONVERT(chineseColumnName USING gbk);
        return listT;
    }
}
