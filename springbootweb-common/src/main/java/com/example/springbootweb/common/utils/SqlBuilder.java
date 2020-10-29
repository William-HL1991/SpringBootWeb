package com.example.springbootweb.common.utils;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

/**
 *  * 简易sql拼接
 *  * 注意不同重载方法的不同注释！！！
 * @author william
 * @date 2020/10/28
 */
public class SqlBuilder {
    /**
     * 不要包含where
     * @param sql
     */
    public SqlBuilder(String sql) {
        this.sb  = new StringBuilder(sql);
    }

    /**
     * 不要包含where
     * @param sql
     * @param append
     */
    public SqlBuilder(String sql, String append) {
        this.sb  = new StringBuilder(sql);
        this.sb.append(append);
    }

    /**
     * 不要包含where
     * @param sql
     * @param append1
     * @param append2
     */
    public SqlBuilder(String sql, String append1, String append2) {
        this.sb  = new StringBuilder(sql);
        this.sb.append(append1).append(append2);
    }

    private StringBuilder sb;
    boolean haveWhere = false;
    boolean haveSet = false;
    int OrderByInd = -1;
    int isOr;

    public SqlBuilder setHaveSet(boolean val) {
        this.haveSet = val;
        return this;
    }

    public SqlBuilder setHaveWhere(boolean val) {
        this.haveWhere = val;
        return this;
    }

    public SqlBuilder append(Object str) {
        this.sb.append(str);
        return this;
    }

    public SqlBuilder append(Object str, Object str2) {
        this.sb.append(str).append(str2);
        return this;
    }

    public SqlBuilder append(Object str, Object str2, Object str3) {
        this.sb.append(str).append(str2).append(str3);
        return this;
    }

    public SqlBuilder delete(int start) {
        return delete(start, sb.length());
    }

    public SqlBuilder delete(int start ,int end) {
        this.sb.delete(start, end);
        return this;
    }

    public SqlBuilder insert(int offset, Object str) {
        this.sb.insert(offset, str);
        return this;
    }

    public int indexOf(String str) {
        return sb.indexOf(str);
    }

    public SqlBuilder startOr() {
        isOr = 1;
        return this;
    }

    public SqlBuilder endOr() {
        sb.append(")");
        isOr = 0;
        return this;
    }

    /**
     * 原数据不能有加了where关键字；
     * val为null或val=="" 都为=""
     * @param column
     * @param val
     * @return
     */
    public SqlBuilder appendWhere(String column, String val) {
        return appendWhere(column, val, false);
    }

    /**
     * 原数据不能有加了where关键字；
     *
     * @param column
     * @param val
     * @param continueBlank 为true时，val为null或val==""都不加入; 否则 val为null或val=="" 都为=""
     * @return
     */
    public SqlBuilder appendWhere(String column, String val, boolean continueBlank) {
        if (isEmpty(val)) {
            if (continueBlank) {
                return this;
            }
            val="";
        }
        sb.append(getWhere()).append(column).append("='").append(val.replace("'","")).append("'");
        return this;
    }

    /**
     * 原数据不能有加了where关键字；
     * 直接加入表达式
     * @param relation
     * @return
     */
    public SqlBuilder appendWhere(String relation) {
        if (!isEmpty(relation)) {
            sb.append(getWhere()).append(relation);
        }
        return this;
    }

    /**
     * 原数据不能有加了where关键字；
     * val为null或val=="" 都不加入条件(特别注意)
     * @param column
     * @param val
     * @return
     */
    public SqlBuilder appendWhereLike(String column, String val) {
        if (isEmpty(val)) {
            return this;
        }
        sb.append(getWhere()).append(column).append(" like '%").append(val.replace("'","")
                .replace("%","")).append("%'");
        return this;
    }

    /**
     * 原数据不能有加了where关键字；
     * val为null或val=="" 都不加入条件(特别注意)
     * @param column
     * @param val
     * @return
     */
    public SqlBuilder appendWhereLikeRight(String column, String val) {
        if (isEmpty(val)) {
            return this;
        }
        sb.append(getWhere()).append(column).append(" like '").append(val.replace("'","")
                .replace("%","")).append("%'");
        return this;
    }

    /**
     * 原数据不能有加了where关键字；
     * val为null或val=="" 都不加入条件(特别注意)
     * @param column
     * @param val
     * @return
     */
    public SqlBuilder appendWhereIn(String column, String val) {
        if (isEmpty(val)) {
            return this;
        }
        sb.append(getWhere()).append(column).append(" in (").append(val).append(")");
        return this;
    }

    /**
     * 原数据不能有加了where关键字；
     * val为null 则不加入条件(特别注意)
     * @param column
     * @param val
     * @param type
     * @return
     */
    public SqlBuilder appendWhereIn(String column, List<String> val, String[] type) {
        return appendWhereIn(column, val.toArray(type));
    }

    /**
     * 原数据不能有加了where关键字；
     * val为null 则不加入条件(特别注意)
     * @param column
     * @param val
     * @return
     */
    public SqlBuilder appendWhereIn(String column, String[] val) {
        if (val == null) {
            return this;
        }
        sb.append(getWhere()).append(column).append(" in (");
        for (int i = 0; i < val.length; i++) {
            if (i > 0) {
                sb.append(",");
            }
            sb.append("'").append(val[i]).append("'");
        }
        sb.append(")");
        return this;
    }

    /**
     * 原数据不能有加了where关键字；
     * val为null 则不加入条件(特别注意)
     * @param column
     * @param val
     * @param type
     * @return
     */
    public SqlBuilder appendWhereIn(String column, List<Integer> val, Integer[] type) {
        return appendWhereIn(column, val.toArray(type));
    }

    /**
     * 原数据不能有加了where关键字；
     * val为null 则不加入条件(特别注意)
     * @param column
     * @param val
     * @return
     */
    public SqlBuilder appendWhereIn(String column, Integer[] val) {
        if (val == null) {
            return this;
        }
        sb.append(getWhere()).append(column).append(" in (");
        for (int i = 0; i < val.length; i++) {
            if (i > 0) {
                sb.append(",");
            }
            sb.append(val[i]);
        }
        sb.append(")");
        return this;
    }

    /**
     * 原数据不能有加了where关键字；
     * val为null或val=="" 都不加入条件(特别注意)
     * @param column
     * @param val
     * @return
     */
    public SqlBuilder appendWhereNotIn(String column, String val) {
        if (isEmpty(val)) {
            return this;
        }
        sb.append(getWhere()).append(column).append(" not in (").append(val).append(")");
        return this;
    }

    /**
     * 原数据不能有加了where关键字；
     * @param column
     * @param val
     * @return
     */
    public SqlBuilder appendWhere(String column, int val) {
        return appendWhere(column, val, null);
    }

    /**
     * 原数据不能有加了where关键字；
     * @param column
     * @param val
     * @param continueVal 为true时，val==continueVal则不加入
     * @return
     */
    public SqlBuilder appendWhere(String column, int val, Integer continueVal) {
        if (continueVal != null && continueVal == val) {
            return this;
        }
        sb.append(getWhere()).append(column).append("=").append(val);
        return this;
    }

    /**
     * 原数据不能有加了where关键字；
     * @param column
     * @param condition
     * @param val
     * @return
     */
    public SqlBuilder appendWhere(String column, String condition, int val) {
        sb.append(getWhere()).append(column).append(condition).append(val);
        return this;
    }

    /**
     * 原数据不能有加了where关键字；小心使用该方法
     * @param column
     * @param val
     * @param continueNull 为true时，val==null则不加入
     * @return
     */
    public SqlBuilder appendWhere(String column, Integer val, boolean continueNull) {
        if (val == null && continueNull) {
            return this;
        }
        sb.append(getWhere()).append(column).append("=").append(val);
        return this;
    }

    /**
     * 原数据不能有加了where关键字；
     * @param column
     * @param val
     * @return
     */
    public SqlBuilder appendWhere(String column, boolean val) {
        sb.append(getWhere()).append(column).append("=").append(val);
        return this;
    }

    /**
     * 原数据不能有加了set关键字；
     * val为null或"" 都设置成null
     * @param column
     * @param val
     * @return
     */
    public SqlBuilder appendSet(String column, String val) {
        return appendSet(column, null, val, false);
    }

    /**
     * 原数据不能有加了set关键字；
     * @param column
     * @param val
     * @param isWriteBlack 为true时，val为null或"" 都设置成""
     * @return
     */
    public SqlBuilder appendSet(String column, String val, boolean isWriteBlack) {
        return appendSet(column, null, val, isWriteBlack);
    }

    public SqlBuilder appendSet(String column, Object appendColumn, String val, boolean isWriteBlack) {
        sb.append(getSet()).append(column);
        if (appendColumn != null) {
            sb.append(appendColumn);
        }
        sb.append("=");
        if (isEmpty(val)) {
            if (isWriteBlack) {
                sb.append("''");
            } else {
                sb.append("null");
                return this;
            }
        } else {
            sb.append("'").append(val.replace("'","")).append("'");
        }
        return this;
    }

    /**
     * 原数据不能有加了set关键字；
     * val为null或"" 都设置成null
     * @param text
     * @return
     */
    public SqlBuilder appendSet(String text) {
        sb.append(getSet()).append(text);
        return this;
    }

    /**
     * 原数据不能有加了set关键字；
     * @param column
     * @param val
     * @return
     */
    public SqlBuilder appendSet(String column, Integer val) {
        sb.append(getSet()).append(column).append("=").append(val);
        return this;
    }

    /**
     * 原数据不能有加了set关键字；
     * @param column
     * @param val
     * @return
     */
    public SqlBuilder appendSet(String column, boolean val) {
        sb.append(getSet()).append(column).append("=").append(val);
        return this;
    }

    /**
     * 原数据不能有加了set关键字；
     * @param column
     * @param val
     * @return
     */
    public SqlBuilder appendSet(String column, Float val) {
        sb.append(getSet()).append(column).append("=").append(val);
        return this;
    }

    /**
     * 原数据不能有加了set关键字；
     * @param column
     * @param val
     * @param saveCount 保留小数位
     * @return
     */
    public SqlBuilder appendSet(String column, Float val, int saveCount) {
        sb.append(getSet()).append(column).append("=").append(val == null ? val : formatFloat(val, saveCount));
        return this;
    }

    /**
     * 原数据不能有加了set关键字；
     * @param column
     * @param val
     * @return
     */
    public SqlBuilder appendSet(String column, Double val) {
        sb.append(getSet()).append(column).append("=").append(val);
        return this;
    }

    /**
     * 原数据不能有加了set关键字；适用于energy_m12
     * @param column
     * @param appendColumn
     * @param val
     * @return
     */
    public SqlBuilder appendSet(String column, Object appendColumn, String val) {
        return appendSet(column, appendColumn, val, false);
    }

    /**
     * 原数据不能有加了set关键字；
     * @param column
     * @param val
     * @param saveCount
     * @return
     */
    public SqlBuilder appendSet(String column, Double val, int saveCount) {
        sb.append(getSet()).append(column).append("=").append(val == null ? val : formatFloat(val, saveCount));
        return this;
    }

    /**
     * 原数据不能有加了limit关键字；
     * @param pager
     * @return
     */
    public SqlBuilder appendLimit(Pager<?> pager) {
        sb.append(" limit ").append(pager.getStartCount()).append(",").append(pager.getPageSize());
        return this;
    }
    /** 原数据不能有加了limit关键字；
     */
    public SqlBuilder appendLimit(Pager<?> pager,int addSize) {
        sb.append(" limit ").append(pager.getStartCount()).append(",").append(pager.getPageSize()+addSize);
        return this;
    }


    /**
     * 看对字符串值是否需要加上单引号
     * @param val
     * @param after
     * @return
     */
    public SqlBuilder appendVal(String val, String after) {
        return appendVal(val, after, false);
    }

    /**
     * 看对字符串值是否需要加上单引号
     * @param val
     * @param isCanBlank
     * @return
     */
    public SqlBuilder appendVal(String val, boolean isCanBlank) {
        return appendVal(val, null, isCanBlank);
    }

    /**
     * 看对字符串值是否需要加上单引号
     * @param val
     * @return
     */
    public SqlBuilder appendVal(String val) {
        return appendVal(val, null, false);
    }

    /**
     * 看对字符串值是否需要加上单引号
     * @param val
     * @param after
     * @param isCanBlank
     * @return
     */
    public SqlBuilder appendVal(String val, String after, boolean isCanBlank) {
        if (isEmpty(val)) {
            if (isCanBlank) {
                sb.append("''");
            } else {
                sb.append("null");
            }
        } else {
            sb.append("'").append(val.replace("'","‘")).append("'");
        }
        if (after != null) {
            sb.append(after);
        }
        return this;
    }

    public SqlBuilder appendVal(Integer val) {
        return this.append(val);
    }

    public SqlBuilder appendVal(Integer val, String after) {
        sb.append(val);
        if (after != null) {
            sb.append(after);
        }
        return this;
    }

    public SqlBuilder appendVal(Float val) {
        return this.append(val);
    }

    public SqlBuilder appendVal(Float val, String after) {
        this.append(val);
        if (after != null) {
            sb.append(after);
        }
        return this;
    }

    public SqlBuilder appendVal(Double val) {
        return this.append(val);
    }

    public SqlBuilder appendVal(Double val, String after) {
        this.append(val);
        if (after != null) {
            sb.append(after);
        }
        return this;
    }

    public SqlBuilder appendVal(boolean val) {
        return this.append(val);
    }

    public SqlBuilder appendVal(boolean val, String after) {
        this.append(val);
        if (after != null) {
            sb.append(after);
        }
        return this;
    }
    public SqlBuilder appendOrder(String columns) {
        OrderByInd = sb.length();
        sb.append(" order by ").append(columns);
        return this;
    }

    public SqlBuilder appendParams(String[] params) {
        for (int i = 0; i < params.length; i++) {
            if (i != 0) {
                sb.append(",");
            }
            sb.append(params[i]);
        }
        return this;
    }

    public SqlBuilder appendParamFn(String fn, String[] params) {
        for (int i= 0; i < params.length; i++) {
            if (i != 0) {
                sb.append(",");
            }
            appendParamFn(fn,params[i]);
        }
        return this;
    }
    public SqlBuilder appendParamFn(String fn, String p) {
        if (p.indexOf(" ") == -1) {
            sb.append(fn).append("(").append(p).append(")").append(p);
        } else {
            String[] ps = p.split(" ");
            sb.append(fn).append("(").append(ps[0]).append(")").append(ps[1]);
        }
        return this;
    }


    private String getWhere() {
        if (haveWhere) {
            if (isOr == 0) {
                return " and ";
            } else if (isOr == 1) {
                isOr = 2;
                return " and (";
            } else {
                return " or ";
            }
        } else {
            haveWhere = true;
            return " where ";
        }
    }

    private String getSet() {
        if (haveSet) {
            return ",";
        } else {
            haveSet = true;
            return " set ";
        }
    }

    public int length() {
        return sb.length();
    }

    public String toString() {
        return sb.toString();
    }

    public String toString(int len) {
        String result = sb.substring(0, len);
        return result;
    }

    public String toString(int len, boolean print) {
        String result = toString(len);
        if (print) {
            System.out.println(result);
        }
        return result;
    }

    public String toString(boolean print) {
        String result = sb.toString();
        if (print) {
            System.out.println(result);
        }
        return result;
    }

    /**
     * 注意规则，有order时。
     * @return
     */
    public String toStringCount() {
        return toStringCount(null, null, null, false);
    }

    /**
     * 注意规则，有order时。
     * @param print
     * @return
     */
    public String toStringCount(boolean print) {
        return toStringCount(null, null, null, print);
    }

    /**
     * 注意规则，有order时。
     * @param param
     * @param print
     * @return
     */
    public String toStringCount(String param, boolean print) {
        return toStringCount(param, null, null, print);
    }

    /**
     * 注意规则，有order时。
     * @param param
     * @param endStr
     * @return
     */
    public String toStringCount(String param, String endStr) {
        return toStringCount(param, endStr, null, false);
    }

    /**
     * 注意规则，有order时。
     * @param param
     * @param endStr
     * @param print
     * @return
     */
    public String toStringCount(String param, String endStr, boolean print) {
        return toStringCount(param, endStr, null, print);
    }

    /**
     * 注意规则，有order时。
     * @param param
     * @param endStr
     * @param lasSub
     * @return
     */
    public String toStringCount(String param, String endStr, String lasSub) {
        return toStringCount(param, endStr, lasSub, false);
    }

    /**
     * 注意规则，有order时。
     * @param param
     * @param endStr
     * @param lasSub
     * @param print
     * @return
     */
    public String toStringCount(String param, String endStr, String lasSub, boolean print) {
        int start = sb.indexOf(" from ");
        int end = sb.length();
        int limit = lasSub == null ? sb.lastIndexOf(" limit ") : "".equals(lasSub) ? sb.length() : sb.lastIndexOf(lasSub);
        if (lasSub != null) {
            end = limit;
        } else if (OrderByInd != -1) {
            end = OrderByInd;
        } else if (limit != -1) {
            end = limit;
        }
        StringBuilder temp = new StringBuilder("select ").append(isEmpty(param) ? "count(*) count" : param);
        temp.append(sb.substring(start, end));
        if (!isEmpty(endStr)) {
            temp.append(endStr);
        }
        if (print) {
            System.out.println(temp.toString());
        }
        return temp.toString();
    }

    /**
     * 字符串是否为null或空字符串("")
     * @param text
     * @return
     */
    public static boolean isEmpty(String text) {
        return text == null || text.length() == 0 || "null".equals(text);
    }

    /**
     * 格式化浮点数。四舍五入，保留自定义位数
     * @param data
     * @param digits
     * @return
     */
    public static String formatFloat(double data, int digits) {
        NumberFormat nf = NumberFormat.getInstance(Locale.CHINA);
        nf.setGroupingUsed(false);
        nf.setMaximumFractionDigits(digits);
        return nf.format(data);
    }
}
