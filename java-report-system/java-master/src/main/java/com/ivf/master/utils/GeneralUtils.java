package com.ivf.master.utils;

import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *  工具类
 */
public class GeneralUtils {

    private static final Logger LOGGER = Logger.getLogger(GeneralUtils.class.getName());

    /**
     * 将对象中的所有 String 类型的 null 字段转换为空字符串 ("")。
     *
     * 注意：该方法只转换 String 类型的字段，其他类型字段不做处理。
     *
     * @param <T> 待转换对象的类型
     * @param obj 需要转换的对象
     * @return 转换后的对象，如果传入的对象为 null，则返回 null
     */
    public static <T> T nullToString(T obj) {
        // 如果传入的对象为 null，直接返回 null
        if (obj == null) {
            return null;
        }

        // 获取对象的所有声明字段（包括私有字段）
        Field[] fields = obj.getClass().getDeclaredFields();

        // 遍历所有字段
        for (Field field : fields) {
            // 只处理 String 类型的字段
            if (String.class.equals(field.getType())) {
                try {
                    // 设置字段的可访问性（即使是私有字段也可以访问）
                    field.setAccessible(true);
                    // 如果字段的值为 null，将其设置为空字符串 ""
                    if (field.get(obj) == null) {
                        field.set(obj, "");
                    }
                } catch (IllegalAccessException e) {
                    // 捕获 IllegalAccessException 并记录警告日志
                    LOGGER.log(Level.WARNING, "Failed to set field: {0} - {1}", new Object[]{field.getName(), e.getMessage()});
                }
            }
        }
        // 返回转换后的对象
        return obj;
    }

    /**
     * 转化 Map null 值为 空字符串
     */
    public static Map<String, Object> processMap(Map<String, Object> inputMap) {
        HashMap<String, Object> resultMap = new HashMap<>();

        if (inputMap == null) {
            return resultMap;
        }

        for (Map.Entry<String, Object> entry: inputMap.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();

            if (value == null) value = "";

            resultMap.put(key, value);
        }

        return resultMap;
    }

    /**
     * 判断列表是否为空
     *
     * @author CAI
     */
    public static boolean isListEmpty(List<?> list) {
        return list == null || list.isEmpty();
    }

    /**
     * 如果目录为空则创建
     *
     * @author CAI
     */
    public static void createDirectoryIfNotExists(String directoryPath) throws IOException {
        Path path = Paths.get(directoryPath);
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }
    }

    /**
     * 把source转为target
     * @param source source
     * @param target target
     * @param <T> 返回值类型
     * @return 返回值
     * @throws Exception newInstance可能会抛出的异常
     */
    public static <T> T mapToObj(Map source, Class<T> target) throws Exception {
        Field[] fields = target.getDeclaredFields();
        T o = target.newInstance();
        for(Field field:fields){
            Object val;
            if((val=source.get(field.getName()))!=null){
                field.setAccessible(true);
                field.set(o,val);
            }
        }
        return o;
    }

    /**
     * LocalDate转Date
     */
    public static Date LocalDToDate(LocalDate localDate) {

        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = localDate.atStartOfDay(zoneId);

        return Date.from(zdt.toInstant());
    }
}
