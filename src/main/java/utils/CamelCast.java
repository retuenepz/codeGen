package utils;

public class CamelCast {
    /**
     * 驼峰命名转化
     * @param s 源字符串
     * @param firstCapital 转化结果的首字母是否要大写
     * @return
     */
    public static String toCamel(String s ,boolean firstCapital){
        if(s != null){
            StringBuilder sb = new StringBuilder();
            char[] chars = s.toCharArray();
            if(firstCapital){
                chars[0] = Character.toUpperCase(chars[0]);
            }
            boolean toUpperCase = false;
            for (char c : chars) {
                if(c == '_'){
                    toUpperCase = true;
                }else{
                    if(toUpperCase){
                        sb.append(Character.toUpperCase(c));
                        toUpperCase = false;
                    }else{
                        sb.append(c);
                    }
                }
            }
            return sb.toString();
        }
        return null;
    }
}
