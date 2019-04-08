 package com.uway.common.utils;

 import com.alibaba.druid.util.StringUtils;
 import com.alibaba.fastjson.JSON;
 import com.alibaba.fastjson.JSONArray;
 import com.alibaba.fastjson.JSONObject;
 import com.alibaba.fastjson.serializer.JSONSerializer;
 import com.alibaba.fastjson.serializer.SerializeWriter;
 import com.alibaba.fastjson.serializer.SerializerFeature;
 import com.alibaba.fastjson.serializer.ValueFilter;

 import java.util.ArrayList;
 import java.util.List;
 import java.util.Map;
 import java.util.Map.Entry;

 public class JSONUtils
 {
   private static ValueFilter valueFilter = new FastJsonValueFilter();
 
   private static final SerializerFeature[] features = { SerializerFeature.WriteDateUseDateFormat, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteNullStringAsEmpty };
 
   public static String toJson(Object target)
   {
/*  38 */     return JSON.toJSONString(target, valueFilter, features);
   }
 
   public static List<String> toCombo(Map<String, String> map)
   {
     List list = new ArrayList();
     if (map != null) {
       for (Entry entry : map.entrySet()) {
        String str = "{\"text\":\"" + (String)entry.getValue() + "\",\"value\":\"" + (String)entry.getKey() + "\"}";
        list.add(str);
       }
     }
     return list;
   }
 
   public static List<String> toCombo(Map<String, String> map, String value) {
    List list = new ArrayList();
     if (map != null) {
       for (Entry entry : map.entrySet()) {
       String temp = (String)entry.getValue();
       String str = "";
       if (temp.equals(value))
           str = "{\"text\":\"" + (String)entry.getValue() + "\",\"value\":\"" + (String)entry.getKey() + "\",\"selected\":true}";
         else {
           str = "{\"text\":\"" + (String)entry.getValue() + "\",\"value\":\"" + (String)entry.getKey() + "\"}";
         }
         list.add(str);
       }
     }
/*  70 */     return list;
   }
 
   public static List<String> toComboByObject(JSONArray array, String idColumn, String nameColumn, String value)
   {
/*  75 */     List list = new ArrayList();
/*  76 */     if (array != null) {
/*  77 */       for (int i = 0; i < array.size(); i++) {
/*  78 */         JSONObject object = array.getJSONObject(i);
/*  79 */         String temp = object.get(idColumn).toString();
/*  80 */         String str = "";
/*  81 */         if (!temp.equals(value))
/*  82 */           str = "{\"text\":\"" + object.get(nameColumn) + "\",\"value\":\"" + object.get(idColumn) + "\"}";
         else {
/*  84 */           str = "{\"text\":\"" + object.get(nameColumn) + "\",\"value\":\"" + object.get(idColumn) + "\",\"selected\":true}";
         }
/*  86 */         list.add(str);
       }
     }
/*  89 */     return list;
   }
 
   public static <T> T fromJson(String json, Class<T> clazz)
   {
/*  99 */     if (StringUtils.isEmpty(json)) {
/* 100 */       return null;
     }
/* 102 */     return JSON.parseObject(json, clazz);
   }
 
   public static JSONArray formJsonToArr(String json)
   {
/* 111 */     return JSON.parseArray(json);
   }
 
   public static JSONObject formJson(String json)
   {
/* 120 */     return JSON.parseObject(json);
   }

   public static final <T> List<T> parseArray(String text, Class<T> clazz)
   {
/* 130 */     return JSON.parseArray(text, clazz);
   }

public static String toJson(Object target, String dateFormat) {


    SerializeWriter out = new SerializeWriter();

    try {
        JSONSerializer serializer = new JSONSerializer(out);
        for (SerializerFeature feature : features) {
            serializer.config(feature, true);
        }

        serializer.config(SerializerFeature.WriteDateUseDateFormat, true);

        if (dateFormat != null) {
            serializer.setDateFormat(dateFormat);
        }

        serializer.getValueFilters().add(valueFilter);

        serializer.write(target);

        return out.toString();
    } finally {
        out.close();
    }

    //mapping.put(Date.class, new SimpleDateFormatSerializer(dateFormat));
    //return JSON.toJSONString(target, mapping, features);
    //return JSON.toJSONStringWithDateFormat(target, dateFormat, features);
}
 }

/* Location:           E:\apache-tomcat-8.5.16\apache-tomcat-8.5.16\webapps2\s3-system\WEB-INF\lib\s3-common-1.0.1.jar
 * Qualified Name:     com.uway.common.utils.JSONUtils
 * JD-Core Version:    0.6.0
 */