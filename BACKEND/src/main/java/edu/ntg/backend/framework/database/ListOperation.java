package edu.ntg.backend.framework.database;

import edu.ntg.backend.dto.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ListOperation {


    @Autowired
    ConnectionManager con;

    public List<?> getList(Class cls) throws Exception {
        return getList(cls, null);
    }

    public List<?> getList(Class cls, String filter) throws Exception {
        NTGConnection DBConInfo = null;
        try {

            String TableName = getTableName(cls);
            List<FieldInfo> fields = getFieldInfo(cls);


            String Sql = featchSqlForUserList(TableName, fields, filter);
            DBConInfo = this.con.fetchStatement();
            ResultSet rs = DBConInfo.getStatement().executeQuery(Sql);
            ArrayList<Object> users = new ArrayList<>();
            while (rs.next()) {

                Object obj = cls.newInstance();

                for (FieldInfo field : fields) {
                    field.setter.invoke(obj, new Object[]{
                            field.Type == 2 ? rs.getLong(field.DBName) : rs.getString(field.DBName)}
                    );
                }

                users.add(obj);
            }

            return users;
        } catch (Exception e) {

            e.printStackTrace();
            throw e;

        } finally {
            this.con.freeResources(DBConInfo);
        }


    }

    private List<FieldInfo> getFieldInfo(Class cls) throws NoSuchMethodException {

        List<FieldInfo> re = new ArrayList<>();
        Field[] fList = cls.getDeclaredFields();

        for (Field f : fList) {

            NTGField a = f.getAnnotation(NTGField.class);
            if (a != null) {

                FieldInfo fi = new FieldInfo();

                fi.name = f.getName();
                fi.DBName = a.DBName().toLowerCase();
                if (fi.DBName.isEmpty()) {
                    fi.DBName = fi.name;
                }
                fi.Type = (f.getType().getName().toLowerCase().contains("long")) ? 2 : 1;

                String setterMethodName = "set" + getFieldNameStartByCapitalLatter(fi.name);
                Class[] par = new Class[]{
                        fi.Type == 1 ? String.class : long.class,};


                fi.setter = cls.getMethod(setterMethodName, par);

                re.add(fi);
            }

        }

        return re;
    }

    private String getFieldNameStartByCapitalLatter(String name) {
        return name.substring(0, 1).toUpperCase() + name.substring(1);
    }

    private String getTableName(Class cls) throws SQLException {

        NTGTable table = (NTGTable) cls.getAnnotation(NTGTable.class);
        if (table == null) {
            throw new SQLException("NTGTable annotation is not defined @" + cls.toGenericString());
        }
        return table.name();
    }

    private String featchSqlForUserList(String TableName, List<FieldInfo> Fields, String filter) throws Exception {
        StringBuffer sql = new StringBuffer("SELECT ");
        sql.append(Fields.get(0).DBName);

        for (int i = 1; i < Fields.size(); i++) {
            sql.append(",");
            sql.append(Fields.get(i).DBName);
        }

        sql.append("\tFROM  ");
        sql.append(TableName);
        if (filter != null && filter.length() > 0) {
            sql.append(" WHERE ");
            sql.append(filter);
        }
        return sql.toString();
    }

    public static void main(String[] args) throws Exception {
        ListOperation listOperation = new ListOperation();
        listOperation.getList(Users.class);
    }


}
