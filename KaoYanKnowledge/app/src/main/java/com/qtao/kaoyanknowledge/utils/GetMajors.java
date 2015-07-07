package com.qtao.kaoyanknowledge.utils;

import android.content.Context;

import com.qtao.kaoyanknowledge.models.MajorItem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

/**
 * Created by AotY on 2015/7/7.
 * 用于从文件中获取专业
 */
public class GetMajors {

    private Context context;

    /**
     * 用于记录那些已经添加了，借鉴编译原理中词法分析的思想
     */
    private Hashtable<String, MajorItem> symbols;

    public GetMajors(Context context) {
        this.context = context;
        symbols = new Hashtable<>();
    }

    public List<MajorItem> getMajorsFromFile() {
        final List<MajorItem> result = new ArrayList<>();
        try {
            InputStream is = context.getAssets().open("majors.txt");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
//                line = bufferedReader.readLine();
                L.e("line ==" + line);
                String[] lineItems = line.split(",");
                MajorItem lineRoot = null;
                for (int i = 0; i < lineItems.length; i++) {
                    // eg: 01 哲学,0101 哲学
                    MajorItem item1 = null;
                    final String item = lineItems[i];
                    final String itemFields[] = item.split(" ");
                    L.e("itemFields.size == "+ itemFields.length);
                    if (i == 0) {//一行最开始的major
                        lineRoot = symbols.get(itemFields[0]);
                        if (lineRoot == null) {
                            lineRoot = new MajorItem(itemFields[0], itemFields[1]);
                            symbols.put(itemFields[0], lineRoot);
                            result.add(lineRoot);
//                            result.add() 是否包含
                        }
                    } else {
                        item1 = symbols.get(itemFields[0]);
                        if (item1 == null) {
                            item1 = new MajorItem(itemFields[0], itemFields[1]);
                            //加入符号表
                            symbols.put(itemFields[0], item1);
                        }
                        //加入一行
                        lineRoot.addChild(item1);
                    }
                }//end for

            }//end while
        } catch (IOException e) {
            e.printStackTrace();
        }
        //返回
        L.e("result.size() ==" +result.size());
        return result;
    }


}
